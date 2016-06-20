package com.davidelp17.arnolflorez.esudea.Gallery;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.davidelp17.arnolflorez.esudea.Events.EventsActivity;
import com.davidelp17.arnolflorez.esudea.Gallery.PreGallery.Logger.Log;
import com.davidelp17.arnolflorez.esudea.Groups.GroupsActivityRaw;
import com.davidelp17.arnolflorez.esudea.Information.InformationActivity;
import com.davidelp17.arnolflorez.esudea.R;
import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;
import com.veinhorn.scrollgalleryview.loader.DefaultImageLoader;
import com.veinhorn.scrollgalleryview.loader.MediaLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GalleryActivity extends AppCompatActivity
{
    private static final String TAG = "GalleryActivity";

    private static GalleryActivity instance;

    private static final ArrayList<String> images = new ArrayList<>(Arrays.asList(
            "http://farm3.static.flickr.com/2598/3822016312_a87f7589e1.jpg",
            "https://noticiasorienteantioqueno.files.wordpress.com/2013/04/universidad-de-antioquia-oriente-antioquec3b1o-ampliacion1.jpg",
            "http://mokana.udea.edu.co/portal/page/portal/BibliotecaPortal/ElementosDiseno/img/Edificios/facultad_medicina.jpg",
            "https://c1.staticflickr.com/9/8288/7510249414_081a8fbc6d.jpg",
            "http://www.udea.edu.co/wps/wcm/connect/udea/9ad53009-231a-4eda-889a-28d995935a3a/posgrados-becas-convenios.jpg?MOD=AJPERES&CACHEID=9ad53009-231a-4eda-889a-28d995935a3a"
    ));

    //private static final String movieUrl = "https://www.youtube.com/watch?v=_iCpSD1JZ10";

    private NavigationView navView;
    private DrawerLayout mDrawerLayout;

    String datorecibido;

    int int_datorecibido;

    private ScrollGalleryView scrollGalleryView;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity_gallery);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        navView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Animacion para el boton de MENU
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        ////////////////////

        instance = this;

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras != null)
        {
            datorecibido = extras.getString("FOTO");
            Log.d(TAG, "Elemento Recibido: " + datorecibido);
            ab.setTitle(datorecibido);

            try
            {
                int_datorecibido = Integer.parseInt(datorecibido);
            }
            catch(NumberFormatException nfe)
            {
                System.out.println("Could not parse " + nfe);
            }
        }
        if(extras == null)
        {
            datorecibido = extras.getString("FOTO");
            Log.d(TAG, "Elemento Recibido: " + datorecibido);
            ab.setTitle(R.string.galeria);

        }

        List<MediaInfo> infos = new ArrayList<>(images.size());
        for (String url : images) infos.add(MediaInfo.mediaLoader(new PicassoImageLoader(url)));

        scrollGalleryView = (ScrollGalleryView) findViewById(R.id.scroll_gallery_view);

        if(Objects.equals(datorecibido, "Esculturas"))
        {
            scrollGalleryView
                    .setThumbnailSize(100)
                    .setZoom(true)
                    .setFragmentManager(getSupportFragmentManager())
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos1)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos2)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos4)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos6)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos7)));
        }
        if(Objects.equals(datorecibido, "Bloques"))
        {
            scrollGalleryView
                    .setThumbnailSize(100)
                    .setZoom(true)
                    .setFragmentManager(getSupportFragmentManager())
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.drawable.universidad_1)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(toBitmap(R.drawable.universidad_2))))
                    .addMedia(MediaInfo.mediaLoader(new MediaLoader() {
                        @Override
                        public boolean isImage() {
                            return true;
                        }

                        @Override
                        public void loadMedia(Context context, ImageView imageView, MediaLoader.SuccessCallback callback) {
                            imageView.setImageBitmap(toBitmap(R.drawable.universidad_3));
                            callback.onSuccess();
                        }

                        @Override
                        public void loadThumbnail(Context context, ImageView thumbnailView, MediaLoader.SuccessCallback callback) {
                            thumbnailView.setImageBitmap(toBitmap(R.drawable.universidad_3));
                            callback.onSuccess();
                        }
                    }))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos12)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos17)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos18)))
            ;
        }
        if(Objects.equals(datorecibido, "Sitios Celebres"))
        {
            scrollGalleryView
                    .setThumbnailSize(100)
                    .setZoom(true)
                    .setFragmentManager(getSupportFragmentManager())
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos26)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos27)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos28)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos30)));
        }
        if(Objects.equals(datorecibido, "Porterias"))
        {
            scrollGalleryView
                    .setThumbnailSize(100)
                    .setZoom(true)
                    .setFragmentManager(getSupportFragmentManager())
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos21)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos22)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos23)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos24)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos25)));
        }
        if(Objects.equals(datorecibido, "Espacios Deportivos"))
        {
            scrollGalleryView
                    .setThumbnailSize(100)
                    .setZoom(true)
                    .setFragmentManager(getSupportFragmentManager())
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos34)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos36)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos38)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos39)))
                    .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.raw.fotos41)));
        }


        if (navView != null) {
            setupDrawerContent(navView);
        }

        navView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)
                    {

                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                Intent HomeActivity1 = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Home.HomeActivity.class);
                                startActivity(HomeActivity1);
                                finish();
                                break;
                            case R.id.nav_university:
                                Intent UniversityActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.University.UniversityActivity.class);
                                startActivity(UniversityActivity);
                                finish();
                                break;
                            case R.id.nav_perfil:
                                Intent ProfileActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Profile.ProfileActivity.class);
                                startActivity(ProfileActivity);
                                finish();
                                break;
                            case R.id.nav_calendar:
                                Intent CalendarActivity = new Intent(getApplicationContext(), EventsActivity.class);
                                startActivity(CalendarActivity);
                                finish();
                                break;
                            case R.id.nav_horario:
                                Snackbar.make(navView, "Recurso en Construcción", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_grupos:
                                Intent GroupsActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Groups.GroupsActivity.class);
                                startActivity(GroupsActivity);
                                finish();
                                break;
                            case R.id.nav_mapas:
                                Intent MapsActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Maps.MapsActivity.class);
                                startActivity(MapsActivity);
                                finish();
                                break;
                            case R.id.nav_galeria:
                                Snackbar.make(navView, "Ya Estas en la Galería", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_sitioweb:
                                Snackbar.make(navView, "Recurso en Construcción", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_login:
                                Intent LoginActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Login.LoginActivity.class);
                                startActivity(LoginActivity);
                                finish();
                                break;
                            case R.id.nav_settings:
                                Snackbar.make(navView, "Recurso en Construcción", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_info:
                                Intent InfoActivity = new Intent(getApplicationContext(), InformationActivity.class);
                                startActivity(InfoActivity);
                                finish();
                                break;
                            case R.id.nav_exit:
                                finish();
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                        }

                        menuItem.setChecked(true);

                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });

        navView.getMenu().getItem(8).setChecked(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private Bitmap toBitmap(int image)
    {
        return ((BitmapDrawable) getResources().getDrawable(image)).getBitmap();
    }

    @Override
    public void onBackPressed()
    {
        Intent PreGalleryActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Gallery.PreGallery.PreGalleryActivity.class);
        startActivity(PreGalleryActivity);
        finish();

        GalleryActivity.getInstance().clearApplicationData();

        try {
            trimCache(getApplicationContext());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        super.onBackPressed();
    }

    //Fires after the OnStop() state
    @Override
    protected void onDestroy() {
        GalleryActivity.getInstance().clearApplicationData();

        super.onDestroy();
        try {
            trimCache(getApplicationContext());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }

    public static GalleryActivity getInstance() {
        return instance;
    }

    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));
                    Log.i("TAG", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");
                }
            }
        }
    }
}

/*      En OnCreate

    scrollGalleryView
                .setThumbnailSize(100)
                .setZoom(true)
                .setFragmentManager(getSupportFragmentManager())
                .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.drawable.universidad_1)))
                .addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(toBitmap(R.drawable.universidad_2))))
                .addMedia(MediaInfo.mediaLoader(new MediaLoader() {
                    @Override
                    public boolean isImage() {
                        return true;
                    }

                    @Override
                    public void loadMedia(Context context, ImageView imageView, MediaLoader.SuccessCallback callback) {
                        imageView.setImageBitmap(toBitmap(R.drawable.universidad_3));
                        callback.onSuccess();
                    }

                    @Override
                    public void loadThumbnail(Context context, ImageView thumbnailView, MediaLoader.SuccessCallback callback) {
                        thumbnailView.setImageBitmap(toBitmap(R.drawable.universidad_3));
                        callback.onSuccess();
                    }
                }))
                //.addMedia(MediaInfo.mediaLoader(new DefaultVideoLoader(movieUrl, R.mipmap.default_video)))
                .addMedia(infos);

 */