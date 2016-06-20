package com.davidelp17.arnolflorez.esudea.Home;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.davidelp17.arnolflorez.esudea.Events.EventsActivity;
import com.davidelp17.arnolflorez.esudea.Home.Logger.ActivityBase;
import com.davidelp17.arnolflorez.esudea.Home.Logger.Log;
import com.davidelp17.arnolflorez.esudea.Home.Logger.LogWrapper;
import com.davidelp17.arnolflorez.esudea.Home.Logger.MessageOnlyLogFilter;
import com.davidelp17.arnolflorez.esudea.Information.InformationActivity;
import com.davidelp17.arnolflorez.esudea.R;

import java.util.Random;

public class HomeActivity extends ActivityBase implements AppCompatCallback
{
    public static final String TAG = "HomeActivity";

    // Whether the Log Fragment is currently shown
    private boolean mLogShown;
    private boolean MostrarNotificacion=false;
    private NavigationView navView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_home);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

        if (navView != null) {
            setupDrawerContent(navView);
        }

        if (savedInstanceState == null)
        {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            HomeFragment fragment = new HomeFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                CrearNotificacion(4);
                                Snackbar.make(navView, "Ya Estás en la Pantalla Principal", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_university:
                                CrearNotificacion(6);
                                Intent UniversityActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.University.UniversityActivity.class);
                                startActivity(UniversityActivity);
                                finish();
                                break;
                            case R.id.nav_perfil:
                                CrearNotificacion(2);
                                Intent ProfileActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Profile.ProfileActivity.class);
                                startActivity(ProfileActivity);
                                finish();
                                break;
                            case R.id.nav_calendar:
                                CrearNotificacion(9);
                                Intent CalendarActivity = new Intent(getApplicationContext(), EventsActivity.class);
                                startActivity(CalendarActivity);
                                finish();
                                break;
                            case R.id.nav_horario:
                                CrearNotificacion(12);
                                Snackbar.make(navView, "Recurso en Construcción", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_grupos:
                                CrearNotificacion(16);
                                Intent GroupsActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Groups.GroupsActivity.class);
                                startActivity(GroupsActivity);
                                finish();
                                break;
                            case R.id.nav_mapas:
                                CrearNotificacion(1);
                                Intent MapsActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Maps.MapsActivity.class);
                                startActivity(MapsActivity);
                                finish();
                                break;
                            case R.id.nav_sedes:
                                CrearNotificacion(0);
                                Intent PGroupsActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Groups.GroupsActivity.class);
                                startActivity(PGroupsActivity);
                                finish();
                                break;
                            case R.id.nav_galeria:
                                CrearNotificacion(19);
                                Intent PreGalleryActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Gallery.PreGallery.PreGalleryActivity.class);
                                startActivity(PreGalleryActivity);
                                finish();
                                break;
                            case R.id.nav_sitioweb:
                                CrearNotificacion(7);
                                Snackbar.make(navView, "Recurso en Construcción", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_login:
                                CrearNotificacion(3);
                                Intent LoginActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Login.LoginActivity.class);
                                startActivity(LoginActivity);
                                finish();
                                break;
                            case R.id.nav_settings:
                                CrearNotificacion(18);
                                Snackbar.make(navView, "Recurso en Construcción", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_info:
                                CrearNotificacion(13);
                                Intent InfoActivity = new Intent(getApplicationContext(), InformationActivity.class);
                                startActivity(InfoActivity);
                                finish();
                                break;
                            case R.id.nav_exit:
                                CrearNotificacion(17);
                                finish();
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                        }

                        menuItem.setChecked(true);
                        //getSupportActionBar().setTitle(R.string.app_name);

                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });

        navView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    /** Create a chain of targets that will receive log data */
    @Override
    public void initializeLogging()
    {
        // Wraps Android's native log framework.
        LogWrapper logWrapper = new LogWrapper();
        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
        Log.setLogNode(logWrapper);

        // Filter strips out everything except the message text.
        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
        logWrapper.setNext(msgFilter);

        Log.i(TAG, "Ready");
    }

    public void CrearNotificacion(int Bandera){

        Random random = new Random();

        int Rbandera = random.nextInt(20);

        android.util.Log.i(TAG, "CrearNotificacion: "+ Rbandera + "Numero " + Bandera);

        // Bandera=Rbandera;

        if (Bandera==Rbandera && !MostrarNotificacion) {

            Notification.Builder builder = new Notification.Builder(getApplicationContext());
            NotificationManager notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int notif_ref = 1;
            notifManager.notify(notif_ref, getBigTextStyle(builder));
            MostrarNotificacion=true;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private Notification getBigTextStyle(Notification.Builder builder) {

        long Hora_Disturbios= System.currentTimeMillis()-1000;

        Intent notIntent =
                new Intent(getApplicationContext(), HomeActivity.class);

        PendingIntent contIntent = PendingIntent.getActivity(
               getApplicationContext(), 0, notIntent, 0);


        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        builder
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon((((BitmapDrawable) getResources()
                        .getDrawable(R.mipmap.ic_launcher)).getBitmap()))
                .setTicker("Alerta EsUdeA!")
                .setContentTitle("Informe Universidad")
                .setContentText("Disturbios")
                .setContentInfo("Evacuacion 8:00 am ")
                .setVibrate(new long[]{0, 500, 100, 500})
                .setContentIntent(contIntent)
                .setSound(defaultSound)
                .setLights(Color.WHITE,2,1)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH);

        return new Notification.BigTextStyle(builder)
                .bigText("Se presentan disturbios desde las 7:20 am en la Ciudad Universitaria, Motivos desconocidos hasta el momento")
                .setBigContentTitle("Orden de Evacuacion")
                .setSummaryText("Más Informacion")
                .build();
    }
}
