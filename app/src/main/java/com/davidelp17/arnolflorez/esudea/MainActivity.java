package com.davidelp17.arnolflorez.esudea;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.davidelp17.arnolflorez.esudea.ui.PhotoGalleryFragment;
import com.davidelp17.arnolflorez.esudea.utils.Utils;

public class MainActivity extends AppCompatActivity
{
    public static final String TAGMainActivity = "MainActivity";
    public static final String IMAGE_CACHE_DIR = "thumbs";

    private int mImageThumbSize;
    private int mImageThumbSpacing;

    private NavigationView navView;
    private DrawerLayout mDrawerLayout;

    String fragmentName1;

    public static final String PREF_CONTRASEÑA = "CONTRASEÑA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(BuildConfig.DEBUG){
            Utils.enableStrictMode();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);

        setFragment(HomeFragment.TAGHome, R.id.content_frame, false);

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

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;

                        int opc = 0;

                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                fragment = new HomeFragment();
                                fragmentName1 = HomeFragment.TAGHome;
                                fragmentTransaction = true;
                                opc = 1;
                                break;
                            case R.id.nav_university:
                                Intent MainActivity1 = new Intent(getApplicationContext(), MainActivityTabs.class);
                                startActivity(MainActivity1);
                                opc = 2;
                                break;
                            case R.id.nav_perfil:
                                fragment = new ProfileFragment();
                                fragmentName1 = ProfileFragment.TAGProfile;
                                fragmentTransaction = true;
                                opc = 3;
                                break;
                            case R.id.nav_calendar:
                                fragment = new CalendarFragment();
                                fragmentName1 = CalendarFragment.TAGCalendar;
                                fragmentTransaction = true;
                                opc = 4;
                                break;
                            case R.id.nav_horario:
                                fragment = new HorarioFragment();
                                fragmentName1 = HorarioFragment.TAGHorario;
                                fragmentTransaction = true;
                                opc = 5;
                                break;
                            case R.id.nav_grupos:
                                fragment = new GruposFragment();
                                fragmentName1 = GruposFragment.TAGGrupos;
                                fragmentTransaction = true;
                                opc = 6;
                                break;
                            case R.id.nav_mapas:
                                fragment = new MapsFragment();
                                fragmentName1 = MapsFragment.TAGMaps;
                                fragmentTransaction = true;
                                opc = 7;
                                break;
                            case R.id.nav_galeria:
                                fragment = new PhotoGalleryFragment();
                                fragmentName1 = PhotoGalleryFragment.TAGPhotoGallery;
                                fragmentTransaction = true;
                                opc = 8;
                                break;
                            case R.id.nav_sitioweb:
                                fragment = new WebsiteFragment();
                                fragmentName1 = WebsiteFragment.TAGWebsite;
                                fragmentTransaction = true;
                                opc = 9;
                                break;
                            case R.id.nav_info:
                                fragment = new InfoFragment();
                                fragmentName1 = InfoFragment.TAGInfo;
                                fragmentTransaction = true;
                                opc = 10;
                                break;
                            case R.id.nav_exit:
                                finish();
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                        }

                        //fragmentTransaction = noabrirdoble(fragmentTransaction, opc);
                        replaceFragment(fragment, fragmentTransaction);

                        menuItem.setChecked(true);
                        getSupportActionBar().setTitle(menuItem.getTitle());

                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });

        navView.getMenu().getItem(0).setChecked(true);
    }

    /*
    public boolean noabrirdoble(boolean fragmentTransaction, int menuItem){

        ProfileFragment testProfile = (ProfileFragment) getSupportFragmentManager().findFragmentByTag(ProfileFragment.TAGProfile);
        MatchesFragment testMatch = (MatchesFragment) getSupportFragmentManager().findFragmentByTag(MatchesFragment.TAGMatches);
        StandingFragment testStanding = (StandingFragment) getSupportFragmentManager().findFragmentByTag(StandingFragment.TAGStandings);

        if ((testProfile != null && testProfile.isVisible()) && menuItem == 1) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            fragmentTransaction = false;
        }
        if ((testMatch != null && testMatch.isVisible()) && menuItem == 2) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            fragmentTransaction = false;
        }
        if ((testStanding != null && testStanding.isVisible()) && menuItem == 3) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            fragmentTransaction = false;
        }

        return fragmentTransaction;
    }
    */

    private void replaceFragment(Fragment fragment, boolean fragmentTransaction){
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped){ //fragment not in back stack, create it.
            if (fragmentTransaction) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, fragment, fragmentName1)
                        .addToBackStack(fragmentName1)
                        .commit();
            }
        }
    }

    public void setFragment(String fragmentName, int container, boolean backStack) {
        Fragment fragment = null;
        switch (fragmentName) {
            case HomeFragment.TAGHome:
                fragment = new HomeFragment();
                break;
            case UniversityFragment.TAGUniversity:
                fragment = new UniversityFragment();
                break;
            case ProfileFragment.TAGProfile:
                fragment = new ProfileFragment();
                break;
            case CalendarFragment.TAGCalendar:
                fragment = new CalendarFragment();
                break;
            case HorarioFragment.TAGHorario:
                fragment = new HorarioFragment();
                break;
            case GruposFragment.TAGGrupos:
                fragment = new GruposFragment();
                break;
            case MapsFragment.TAGMaps:
                fragment = new MapsFragment();
                break;
            case PhotoGalleryFragment.TAGPhotoGallery:
                fragment = new PhotoGalleryFragment();
                break;
            case WebsiteFragment.TAGWebsite:
                fragment = new WebsiteFragment();
                break;
            case InfoFragment.TAGInfo:
                fragment = new InfoFragment();
                break;
        }

        if (backStack) {
            getSupportFragmentManager().beginTransaction()
                    .replace(container, fragment, fragmentName).addToBackStack(fragmentName).commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(container, fragment, fragmentName).commit();
        }
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

    void enviardato(String nContraseña){
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        intent.putExtra("NUEVA_CONTRASEÑA", nContraseña);
        startActivity(intent);
    }
}

