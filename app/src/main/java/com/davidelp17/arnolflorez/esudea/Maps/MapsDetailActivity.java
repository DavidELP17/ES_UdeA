package com.davidelp17.arnolflorez.esudea.Maps;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.davidelp17.arnolflorez.esudea.Events.EventsActivity;
import com.davidelp17.arnolflorez.esudea.Information.InformationActivity;
import com.davidelp17.arnolflorez.esudea.R;
import com.davidelp17.arnolflorez.esudea.University.UniversityData;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Objects;

public class MapsDetailActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "Uni_name";

    private NavigationView navView;
    private DrawerLayout mDrawerLayout;

    String unirecibido;

    TextView informaciontitulo;
    TextView informacion;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_activity_detail);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        informaciontitulo = (TextView) findViewById(R.id.informaciontitulo);
        informacion = (TextView) findViewById(R.id.informacion);

        Intent intent = getIntent();
        final String UniName = intent.getStringExtra(EXTRA_NAME);
        unirecibido = UniName;

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        if (collapsingToolbar != null) {
            collapsingToolbar.setTitle(UniName);
        }

        loadBackdrop();

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

        navView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)
                    {
                        switch (menuItem.getItemId())
                        {
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
                                Snackbar.make(navView, "Ya Estás en Mapas", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_galeria:
                                Intent PreGalleryActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Gallery.PreGallery.PreGalleryActivity.class);
                                startActivity(PreGalleryActivity);
                                finish();
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
                        getSupportActionBar().setTitle(R.string.mapa);

                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });

        navView.getMenu().getItem(7).setChecked(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabbotonuni);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SharingIntent = new Intent(Intent.ACTION_SEND);
                SharingIntent.setType("text/plain");
                String shareBody = UniName + " - " + informaciontitulo.getText().toString() + ": " + informacion.getText().toString();
                SharingIntent.putExtra(Intent.EXTRA_SUBJECT,"EsUdeA Información " + UniName);
                SharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(SharingIntent,"Compartir contenido vía"));
            }
        });
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);

        if (Objects.equals(unirecibido, "  Robledo")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomMapaDrawable(0)).centerCrop().into(imageView);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionrobledo);
            }
        }
        if (Objects.equals(unirecibido, "  Posgrados")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomMapaDrawable(1)).centerCrop().into(imageView);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionposgrados);
            }
        }
        if (Objects.equals(unirecibido, "  Ciudadela")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomMapaDrawable(2)).centerCrop().into(imageView);
            }
            if (informacion != null) {
                informacion.setText(R.string.lorem_ipsum);
            }
        }
        if (Objects.equals(unirecibido, "  Escuela Idiomas")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomMapaDrawable(3)).centerCrop().into(imageView);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionidiomasplace);
            }
        }
        if (Objects.equals(unirecibido, "  Medicina")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomMapaDrawable(4)).centerCrop().into(imageView);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionmedicina);
            }
        }
        if (Objects.equals(unirecibido, "  Medellin")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomMapaDrawable(5)).centerCrop().into(imageView);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionmedellin);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}