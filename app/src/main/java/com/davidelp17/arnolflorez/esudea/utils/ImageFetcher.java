package com.davidelp17.arnolflorez.esudea.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.davidelp17.arnolflorez.esudea.BuildConfig;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by searover on 3/17/15.
 * A simple subclass of {@link com.davidelp17.arnolflorez.esudea.utils.ImageResizer} that fetches and
 * resizes images fetched from a URL
 */
public class ImageFetcher extends ImageResizer {

    private static final String TAG = "ImageFetcher";
    private static final int HTTP_CACHE_SIZE = 10 * 1024 * 1024; // 10MB
    private static final String HTTP_CACHE_DIR = "http";
    private static final int IO_BUFFER_SIZE = 8 * 1024;

    private DiskLruCache mHttpDiskCache;
    private File mHttpCacheDir;
    private boolean mHttpDiskCacheStarting = true;
    private static Object mHttpDiskCacheLock = new Object();
    private static final int DISK_CACHE_INDEX = 0;

    /**
     * Initialize providing a target image width and height for the processing images.
     * @param context
     * @param imageWidth
     * @param imageHeight
     */
    protected ImageFetcher(Context context, int imageWidth, int imageHeight) {
        super(context, imageWidth, imageHeight);
        init(context);
    }

    /**
     * Initialize providing a target image width and height for the processing images.
     * @param context
     * @param imageSize
     */
    public ImageFetcher(Context context, int imageSize) {
        super(context, imageSize);
        init(context);
    }

    private void init(Context context){
        checkConnection(context);
        mHttpCacheDir = ImageCache.getDiskCacheDir(context,HTTP_CACHE_DIR);
    }

    @Override
    protected void initDiskCacheInternal(){
        super.initDiskCacheInternal();
        initHttpDiskCache();
    }

    private void initHttpDiskCache(){
        if(!mHttpCacheDir.exists()){
            mHttpCacheDir.mkdirs();
        }
        synchronized (mHttpDiskCacheLock){
            long usable = ImageCache.getUsableSpace(mHttpCacheDir);
            if(ImageCache.getUsableSpace(mHttpCacheDir) > HTTP_CACHE_SIZE){
                try {
                    mHttpDiskCache = DiskLruCache.open(mHttpCacheDir,1,1,HTTP_CACHE_SIZE);
                    if(BuildConfig.DEBUG){
                        Log.d(TAG,"Http cache initialized");
                    }
                } catch (IOException e) {
                    mHttpDiskCache = null;
                }
            }
            mHttpDiskCacheStarting = false;
            mHttpDiskCacheLock.notifyAll();
        }
    }

    @Override
    protected void clearCacheInternal(){
        super.clearCacheInternal();
        synchronized (mHttpDiskCacheLock){
            if(mHttpDiskCache != null && !mHttpDiskCache.isClosed()){
                try {
                    mHttpDiskCache.delete();
                    if(BuildConfig.DEBUG){
                        Log.d(TAG," Http cache cleared");
                    }
                } catch (IOException e) {
                    Log.e(TAG,"clearCacheInternal - " + e);
                }
                mHttpDiskCache = null;
                mHttpDiskCacheStarting = true;
                initHttpDiskCache();
            }
        }
    }

    @Override
    protected void flushCacheInternal(){
        super.flushCacheInternal();
        synchronized (mHttpDiskCacheLock){
            if(mHttpDiskCache != null){
                try {
                    mHttpDiskCache.flush();
                    if(BuildConfig.DEBUG){
                        Log.d(TAG,"Http cache flushed");
                    }
                } catch (IOException e) {
                    Log.e(TAG, "flush - " + e);
                }
            }
        }
    }

    @Override
    protected void closeCacheInternal(){
        super.closeCacheInternal();
        synchronized (mHttpDiskCacheLock){
            if(mHttpDiskCache != null){
                try {
                    if(!mHttpDiskCache.isClosed()){
                        mHttpDiskCache.close();
                        mHttpDiskCache = null;
                        if(BuildConfig.DEBUG){
                            Log.e(TAG,"Http cache closed");
                        }
                    }
                } catch (IOException e) {
                    Log.e(TAG,"closeCacheInternal - " + e);
                }
            }
        }
    }

    /**
     * Simple network connection check
     * @param context
     */
    private void checkConnection(Context context){
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo == null || !networkInfo.isConnectedOrConnecting()){
            Toast.makeText(context, "No Network connection found", Toast.LENGTH_LONG).show();;
            Log.e(TAG,"checkConnection - no connection found");
        }
    }

    /**
     * The main process method, which will be called by the ImageWorker in the AsyncTask background thread.
     * @param data
     * @return
     */
    private Bitmap processBitmap(String data){
        if(BuildConfig.DEBUG){
            Log.d(TAG,"processBitmap - " + data);
        }
        final String key = ImageCache.hasKeyForDisk(data);
        FileDescriptor fileDescriptor = null;
        FileInputStream fileInputStream = null;
        DiskLruCache.Snapshot snapshot;
        synchronized (mHttpDiskCacheLock){
            // Wait for disk cache to initialize
            while (mHttpDiskCacheStarting){
                try {
                    mHttpDiskCacheLock.wait();
                }catch (InterruptedException e) {

                }
            }

            if(mHttpDiskCache != null){
                try {
                    snapshot = mHttpDiskCache.get(key);
                    if(snapshot == null){
                        if(BuildConfig.DEBUG){
                            Log.e(TAG,"processBitmap, not found in http cache, downloading...");
                        }
                        DiskLruCache.Editor editor = mHttpDiskCache.edit(key);
                        if(editor != null){
                            if(downloadUrlToStream(data,editor.newOutputStream(DISK_CACHE_INDEX))){
                                editor.commit();;
                            }else {
                                editor.abort();
                            }
                        }
                        snapshot = mHttpDiskCache.get(key);
                    }
                    if(snapshot != null){
                        fileInputStream = (FileInputStream) snapshot.getInputStream(DISK_CACHE_INDEX);
                        fileDescriptor = fileInputStream.getFD();
                    }
                } catch (IOException e) {
                    Log.e(TAG,"processBitmap - " + e);
                }finally {
                    if(fileDescriptor == null && fileInputStream != null){
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {

                        }
                    }
                }
            }
        }

        Bitmap bitmap = null;
        if(fileDescriptor != null){
            bitmap = decodeSampleBitmapFromDescriptor(fileDescriptor,mImageWidth,mImageHeight,getImageCache());
        }
        if(fileInputStream != null){
            try {
                fileInputStream.close();
            } catch (IOException e) {
            }
        }
        return bitmap;
    }

    @Override
    protected Bitmap processBitmap(Object data){
        return processBitmap(String.valueOf(data));
    }

    public boolean downloadUrlToStream(String urlString, OutputStream outputStream){
        disableConnectionReuseIfNecessary();
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(),IO_BUFFER_SIZE);
            out = new BufferedOutputStream(outputStream,IO_BUFFER_SIZE);

            int b;
            while ((b = in.read()) != -1){
                out.write(b);
            }
            return true;
        } catch (MalformedURLException e) {
            Log.e(TAG, "Error in downloadBitmap - " + e);
        } catch (IOException e) {
            Log.e(TAG, "Error in downloadBitmap - " + e);
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            try {
                if(out != null){
                    out.close();;
                }
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {

            }
        }
        return false;
    }

    /**
     * Workaround for bug pre-Froyo, see here for more info:
     * http://android-developers.blogspot.com/2011/09/androids-http-clients.html
     */
    public static void disableConnectionReuseIfNecessary(){
        // HTTP connection reuse which was buggy pre-froyo
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO){
            System.setProperty("http.keepAlive", "false");
        }
    }
}
