package com.davidelp17.arnolflorez.esudea.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.davidelp17.arnolflorez.esudea.BuildConfig;
import com.davidelp17.arnolflorez.esudea.utils.Utils;

/**
 * Simple FragmentActivity to hold the main {@link com.davidelp17.arnolflorez.esudea.ui.PhotoGalleryFragment}
 * and not much else.
 */
public class MainActivity extends FragmentActivity {

    private static final String TAG = "MainActivity";
    private static final String IMAGE_CACHE_DIR = "thumbs";

    private int mImageThumbSize;
    private int mImageThumbSpacing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(BuildConfig.DEBUG){
            Utils.enableStrictMode();
        }
        super.onCreate(savedInstanceState);

        if(getSupportFragmentManager().findFragmentByTag(TAG) == null){
            final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(android.R.id.content, new PhotoGalleryFragment(), TAG);
            transaction.commit();
        }
    }
}
