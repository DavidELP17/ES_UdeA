package com.davidelp17.arnolflorez.esudea.Home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.davidelp17.arnolflorez.esudea.R;
import com.davidelp17.arnolflorez.esudea.University.UniversityActivity;

public class HomeActivity extends AppCompatActivity
{
    public static final String TAGHomeActivity = "HomeActivity";
    public static final String IMAGE_CACHE_DIR = "thumbs";

    private NavigationView navView;
    private DrawerLayout mDrawerLayout;

    public static final String PREF_CONTRASEÑA = "CONTRASEÑA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);

        if (navView != null) {
            setupDrawerContent(navView);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Texto de Prueba", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        navView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                Snackbar.make(navView, "Ya Estás en la Pantalla Principal", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_university:
                                Intent MainActivity1 = new Intent(getApplicationContext(), UniversityActivity.class);
                                startActivity(MainActivity1);
                                break;
                            case R.id.nav_perfil:
                                break;
                            case R.id.nav_calendar:
                                break;
                            case R.id.nav_horario:
                                break;
                            case R.id.nav_grupos:
                                break;
                            case R.id.nav_mapas:
                                break;
                            case R.id.nav_galeria:
                                break;
                            case R.id.nav_sitioweb:
                                break;
                            case R.id.nav_info:
                                break;
                            case R.id.nav_exit:
                                finish();
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                        }

                        menuItem.setChecked(true);
                        getSupportActionBar().setTitle(R.string.app_name);

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
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void setPreferencias(String nContraseña){
        SharedPreferences pref = getSharedPreferences("Libertadores", MODE_PRIVATE);
        SharedPreferences.Editor editor= pref.edit();
        editor.putString(PREF_CONTRASEÑA, nContraseña);
        editor.commit();
    }
}

