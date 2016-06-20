package com.davidelp17.arnolflorez.esudea.University;

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
import com.davidelp17.arnolflorez.esudea.Groups.GroupsActivity;
import com.davidelp17.arnolflorez.esudea.Home.HomeActivity;
import com.davidelp17.arnolflorez.esudea.Information.InformationActivity;
import com.davidelp17.arnolflorez.esudea.R;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Objects;

public class UniversityDetailActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "Uni_name";

    private NavigationView navView;
    private DrawerLayout mDrawerLayout;

    String unirecibido;

    TextView informaciontitulo;
    TextView informacion;
    TextView programastitulo;
    TextView programas;
    TextView oficinastitulo;
    TextView oficinas;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.university_activity_detail);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        informaciontitulo = (TextView) findViewById(R.id.informaciontitulo);
        informacion = (TextView) findViewById(R.id.informacion);
        programastitulo = (TextView) findViewById(R.id.programastitulo);
        programas = (TextView) findViewById(R.id.programas);
        oficinastitulo = (TextView) findViewById(R.id.oficinastitulo);
        oficinas = (TextView) findViewById(R.id.oficinas);

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

                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                Intent HomeActivity1 = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(HomeActivity1);
                                finish();
                                break;
                            case R.id.nav_university:
                                Snackbar.make(navView, R.string.Snackbar_Ya_Estas, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_perfil:
                                Intent ProfileActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Profile.ProfileActivity.class);
                                startActivity(ProfileActivity);
                                finish();
                                break;
                            case R.id.nav_calendar:
                                Intent CalendarActivity = new Intent(getApplicationContext(), GroupsActivity.class);
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
                        getSupportActionBar().setTitle("University");

                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });

        navView.getMenu().getItem(1).setChecked(true);

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

        if (Objects.equals(unirecibido, "Facultad de Artes")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(0)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloartes);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionartes);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloartes);
            }
            if (programas != null) {
                programas.setText(R.string.programasartes);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloartes);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasartes);
            }
        }
        if (Objects.equals(unirecibido, "Facultad de Ciencias Agrarias")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(1)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloagrarias);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionagrarias);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloagrarias);
            }
            if (programas != null) {
                programas.setText(R.string.programasagrarias);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloagrarias);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasagrarias);
            }
        }
        if (Objects.equals(unirecibido, "Facultad de Ciencias Económicas")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(2)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloeconomicas);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacioneconomicas);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloeconomicas);
            }
            if (programas != null) {
                programas.setText(R.string.programaseconomicas);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloeconomicas);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinaseconomicas);
            }
        }
        if (Objects.equals(unirecibido, "Facultad de Ciencias Exactas y Naturales")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(3)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloexactas);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionexactas);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloexactas);
            }
            if (programas != null) {
                programas.setText(R.string.programasexactas);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloexactas);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasexactas);
            }
        }
        if (Objects.equals(unirecibido, "Facultad de Ciencias Farmacéuticas y Alimentarias")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(4)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulofarmaceutica);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionfarmaceutica);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulofarmaceutica);
            }
            if (programas != null) {
                programas.setText(R.string.programasfarmaceutica);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulofarmaceutica);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasfarmaceutica);
            }
        }
        if (Objects.equals(unirecibido, "Facultad de Ciencias Sociales y Humanas")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(5)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulosociales);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionsociales);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulosociales);
            }
            if (programas != null) {
                programas.setText(R.string.programassociales);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulosociales);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinassociales);
            }
        }
        if (Objects.equals(unirecibido, "Facultad de Comunicaciones")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(6)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulocomunicaciones);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacioncomunicaciones);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulocomunicaciones);
            }
            if (programas != null) {
                programas.setText(R.string.programascomunicaciones);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulocomunicaciones);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinascomunicaciones);
            }
        }
        if (Objects.equals(unirecibido, "Facultad de Derecho y Ciencias Políticas")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(7)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloderecho);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionderecho);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloderecho);
            }
            if (programas != null) {
                programas.setText(R.string.programasderecho);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloderecho);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasderecho);
            }
        }
        if (Objects.equals(unirecibido, "Facultad de Educación")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(8)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloeducacion);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacioneducacion);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloeducacion);
            }
            if (programas != null) {
                programas.setText(R.string.programaseducacion);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloeducacion);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinaseducacion);
            }
        }
        if (Objects.equals(unirecibido, "Facultad de Enfermería")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(9)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloenfermeria);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionenfermeria);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloenfermeria);
            }
            if (programas != null) {
                programas.setText(R.string.programasenfermeria);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloenfermeria);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasenfermeria);
            }
        }
        if (Objects.equals(unirecibido, "Facultad de Ingeniería")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(10)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloingenieria);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacioningenieria);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloingenieria);
            }
            if (programas != null) {
                programas.setText(R.string.programasingenieria);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloingenieria);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasingenieria);
            }
        }
        if (Objects.equals(unirecibido, "Facultad de Medicina")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(11)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulomedicina);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionmedicina);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulomedicina);
            }
            if (programas != null) {
                programas.setText(R.string.programasmedicina);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulomedicina);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasmedicina);
            }
        }
        if (Objects.equals(unirecibido, "Facultad de Odontología")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(12)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloodontologia);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionodontologia);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloodontologia);
            }
            if (programas != null) {
                programas.setText(R.string.programasodontologia);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloodontologia);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasodontologia);
            }
        }
        if (Objects.equals(unirecibido, "Facultad de Salud Pública")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(13)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulosaludpublica);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionsaludpublica);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulosaludpublica);
            }
            if (programas != null) {
                programas.setText(R.string.programassaludpublica);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulosaludpublica);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinassaludpublica);
            }
        }
        if (Objects.equals(unirecibido, "Escuela de Idiomas")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(14)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloidiomas);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionidiomas);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloidiomas);
            }
            if (programas != null) {
                programas.setText(R.string.programasidiomas);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloidiomas);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasidiomas);
            }
        }
        if (Objects.equals(unirecibido, "Escuela de Interamericana de Bibliotecología")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(15)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulobibliotecologia);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionbibliotecologia);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulobibliotecologia);
            }
            if (programas != null) {
                programas.setText(R.string.programasbibliotecologia);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulobibliotecologia);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasbibliotecologia);
            }
        }
        if (Objects.equals(unirecibido, "Escuela de Microbiología")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(16)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulomicrobiologia);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionmicrobiologia);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulomicrobiologia);
            }
            if (programas != null) {
                programas.setText(R.string.programasmicrobiologia);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulomicrobiologia);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasmicrobiologia);
            }
        }
        if (Objects.equals(unirecibido, "Escuela de Nutrición y Dietética")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(17)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulonutricion);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionnutricion);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulonutricion);
            }
            if (programas != null) {
                programas.setText(R.string.programasnutricion);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulonutricion);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasnutricion);
            }
        }
        if (Objects.equals(unirecibido, "Instituto de Filosofía")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(18)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulofilosofia);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionfilosofia);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulofilosofia);
            }
            if (programas != null) {
                programas.setText(R.string.programasfilosofia);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulofilosofia);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasfilosofia);
            }
        }
        if (Objects.equals(unirecibido, "Instituto de Educación Física y Deportes")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(19)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloeducacionfisica);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacioneducacionfisica);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloeducacionfisica);
            }
            if (programas != null) {
                programas.setText(R.string.programaseducacionfisica);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloeducacionfisica);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinaseducacionfisica);
            }
        }
        if (Objects.equals(unirecibido, "Instituto de Estudios Políticos")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(20)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloestudiospoliticos);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionestudiospoliticos);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloestudiospoliticos);
            }
            if (programas != null) {
                programas.setText(R.string.programasestudiospoliticos);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloestudiospoliticos);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasestudiospoliticos);
            }
        }
        if (Objects.equals(unirecibido, "Instituto de Estudios Regionales")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(21)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloestudiosregionales);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionestudiosregionales);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloestudiosregionales);
            }
            if (programas != null) {
                programas.setText(R.string.programasestudiosregionales);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloestudiosregionales);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasestudiosregionales);
            }
        }
        if (Objects.equals(unirecibido, "Corporacion Ambiental")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(22)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulocorpambiental);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacioncorpambiental);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulocorpambiental);
            }
            if (programas != null) {
                programas.setText(R.string.programascorpambiental);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulocorpambiental);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinascorpambiental);
            }
        }
        if (Objects.equals(unirecibido, "Corporacion de Ciencias Básicas Biomédicas")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(23)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulocorpocienciasbasicas);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacioncorpocienciasbasicas);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulocorpocienciasbasicas);
            }
            if (programas != null) {
                programas.setText(R.string.programascorpocienciasbasicas);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulocorpocienciasbasicas);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinascorpocienciasbasicas);
            }
        }
        if (Objects.equals(unirecibido, "Corporacion de Patologías Tropicales")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomUniDrawable(24)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulocorppatologiastropicales);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacioncorppatologiastropicales);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulocorppatologiastropicales);
            }
            if (programas != null) {
                programas.setText(R.string.programascorppatologiastropicales);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulocorppatologiastropicales);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinascorppatologiastropicales);
            }
        }

        //**********************************Extras de Internacional**********************************
        if (Objects.equals(unirecibido, "Alemania")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(0)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloalemania);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionalemania);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloalemania);
            }
            if (programas != null) {
                programas.setText(R.string.programasalemania);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloalemania);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasalemania);
            }
        }
        if (Objects.equals(unirecibido, "Argentina")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(1)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloargentina);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionargentina);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloargentina);
            }
            if (programas != null) {
                programas.setText(R.string.programasargentina);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloargentina);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasargentina);
            }
        }
        if (Objects.equals(unirecibido, "Belgica y Union Europea")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(2)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulobelgica);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionbelgica);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulobelgica2);
            }
            if (programas != null) {
                programas.setText(R.string.programasbelgica2);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulobelgica);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasbelgica);
            }
        }
        if (Objects.equals(unirecibido, "Brasil")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(3)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulobrasil);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionbrasil);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulobrasil);
            }
            if (programas != null) {
                programas.setText(R.string.programasbrasil);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulobrasil);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasbrasil);
            }
        }
        if (Objects.equals(unirecibido, "Canadá")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(4)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulocanada);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacioncanada);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulocanada);
            }
            if (programas != null) {
                programas.setText(R.string.programascanada);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulocanada);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinascanada);
            }
        }
        if (Objects.equals(unirecibido, "Chile")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(5)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulochile);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionchile);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulochile);
            }
            if (programas != null) {
                programas.setText(R.string.programaschile);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulochile);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinaschile);
            }
        }
        if (Objects.equals(unirecibido, "Cuba")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(6)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulocuba);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacioncuba);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulocuba);
            }
            if (programas != null) {
                programas.setText(R.string.programascuba);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulocuba);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinascuba);
            }
        }
        if (Objects.equals(unirecibido, "Ecuador")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(7)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloecuador);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionecuador);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloecuador);
            }
            if (programas != null) {
                programas.setText(R.string.programasecuador);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloecuador);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasecuador);
            }
        }
        if (Objects.equals(unirecibido, "España")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(8)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloespaña);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionespaña);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloespaña);
            }
            if (programas != null) {
                programas.setText(R.string.programasespaña);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloespaña);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasespaña);
            }
        }
        if (Objects.equals(unirecibido, "Estados Unidos")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(9)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloestadosunidos);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionestadosunidos);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloestadosunidos);
            }
            if (programas != null) {
                programas.setText(R.string.programasestadosunidos);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloestadosunidos);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasestadosunidos);
            }
        }
        if (Objects.equals(unirecibido, "Italia")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(11)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulofrancia);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionfrancia);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulofrancia);
            }
            if (programas != null) {
                programas.setText(R.string.programasfrancia);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulofrancia);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasfrancia);
            }
        }
        if (Objects.equals(unirecibido, "Francia")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(10)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloitalia);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionitalia);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloitalia);
            }
            if (programas != null) {
                programas.setText(R.string.programasitalia);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloitalia);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasitalia);
            }
        }
        if (Objects.equals(unirecibido, "China, Corea del Sur y Japón")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(12)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloasia);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionasia);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloasia);
            }
            if (programas != null) {
                programas.setText(R.string.programasasia);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloasia);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasasia);
            }
        }
        if (Objects.equals(unirecibido, "México")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(13)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulomexico);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionmexico);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulomexico);
            }
            if (programas != null) {
                programas.setText(R.string.programasmexico);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulomexico);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasmexico);
            }
        }
        if (Objects.equals(unirecibido, "Paises Bajos")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(14)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulopaisesbajos);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionpaisesbajos);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulopaisesbajos);
            }
            if (programas != null) {
                programas.setText(R.string.programaspaisesbajos);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulopaisesbajos);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinaspaisesbajos);
            }
        }
        if (Objects.equals(unirecibido, "Perú")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(15)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloperu);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionperu);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloperu);
            }
            if (programas != null) {
                programas.setText(R.string.programasperu);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloperu);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasperu);
            }
        }
        if (Objects.equals(unirecibido, "Reino Unido")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(16)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloreinounido);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionreinounido);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloreinounido);
            }
            if (programas != null) {
                programas.setText(R.string.programasreinounido);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloreinounido);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasreinounido);
            }
        }
        if (Objects.equals(unirecibido, "Venezuela")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomIntDrawable(17)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulovenezuela);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionvenezuela);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulovenezuela);
            }
            if (programas != null) {
                programas.setText(R.string.programasvenezuela);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulovenezuela);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasvenezuela);
            }
        }

        //**********************************Extras de Bienestar**********************************
        if (Objects.equals(unirecibido, "Apoyo Social")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomBieDrawable(0)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloapoyosocial);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionapoyosocial);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloapoyosocial);
            }
            if (programas != null) {
                programas.setText(R.string.programasapoyosocial);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloapoyosocial);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasapoyosocial);
            }
        }
        if (Objects.equals(unirecibido, "Bienestar con Sentido")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomBieDrawable(1)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulobienestarconsentido);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionbienestarconsentido);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulobienestarconsentido);
            }
            if (programas != null) {
                programas.setText(R.string.programasbienestarconsentido);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulobienestarconsentido);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasbienestarconsentido);
            }
        }
        if (Objects.equals(unirecibido, "Bienestar Deportivo")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomBieDrawable(2)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulobienestardeportivo);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionbienestardeportivo);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulobienestardeportivo);
            }
            if (programas != null) {
                programas.setText(R.string.programasbienestardeportivo);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulobienestardeportivo);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasbienestardeportivo);
            }
        }
        if (Objects.equals(unirecibido, "Bienestar Saludable")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomBieDrawable(3)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulobienestarsaludable);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionbienestarsaludable);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulobienestarsaludable);
            }
            if (programas != null) {
                programas.setText(R.string.programasbienestarsaludable);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulobienestarsaludable);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasbienestarsaludable);
            }
        }
        if (Objects.equals(unirecibido, "Bienestar Cultural")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomBieDrawable(4)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontitulobienestarcultural);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionbienestarcultural);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastitulobienestarcultural);
            }
            if (programas != null) {
                programas.setText(R.string.programasbienestarcultural);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastitulobienestarcultural);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasbienestarcultural);
            }
        }
        if (Objects.equals(unirecibido, "Extensión Cultural")) {
            if (imageView != null) {
                Glide.with(this).load(UniversityData.getRandomBieDrawable(5)).centerCrop().into(imageView);
            }
            if (informaciontitulo != null) {
                informaciontitulo.setText(R.string.informaciontituloextensioncultural);
            }
            if (informacion != null) {
                informacion.setText(R.string.informacionextensioncultural);
            }
            if (programastitulo != null) {
                programastitulo.setText(R.string.programastituloextensioncultural);
            }
            if (programas != null) {
                programas.setText(R.string.programasextensioncultural);
            }
            if (oficinastitulo != null) {
                oficinastitulo.setText(R.string.oficinastituloextensioncultural);
            }
            if (oficinas != null) {
                oficinas.setText(R.string.oficinasextensioncultural);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}