package com.davidelp17.arnolflorez.esudea.University;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.davidelp17.arnolflorez.esudea.Groups.GroupsActivity;
import com.davidelp17.arnolflorez.esudea.Information.InformationActivity;
import com.davidelp17.arnolflorez.esudea.R;
import com.flask.floatingactionmenu.FadingBackgroundView;
import com.flask.floatingactionmenu.FloatingActionButton;
import com.flask.floatingactionmenu.FloatingActionMenu;
import com.flask.floatingactionmenu.OnFloatingActionMenuSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UniversityActivity extends AppCompatActivity
{
    public static final String TAG = "UniversityActivity";

    private NavigationView navView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.university_activity_university);
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

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        FadingBackgroundView fadingBackgroundView = (FadingBackgroundView) findViewById(R.id.fading);
        final FloatingActionMenu floatingActionMenu = (FloatingActionMenu) findViewById(R.id.fam);
        floatingActionMenu.setFadingBackgroundView(fadingBackgroundView);

        floatingActionMenu.setLabelText(0, "Visita la pagina de Youtube de la UdeA");
        floatingActionMenu.setLabelText(1, "Visita la pagina de Facebook de la UdeA");
        floatingActionMenu.setLabelText(2, "Visita la pagina de Twitter de la UdeA");
        floatingActionMenu.setLabelText(3, "Salir del Menú");

        floatingActionMenu.setBackgroundColor(2, getResources().getColor(R.color.remainder_press), false);
        floatingActionMenu.setBackgroundColor(3, getResources().getColor(R.color.accent_material_light), false);

        floatingActionMenu.setOnFloatingActionMenuSelectedListener(new OnFloatingActionMenuSelectedListener() {
            @Override
            public void onFloatingActionMenuSelected(FloatingActionButton fab) {
                toast(fab.getLabelText());
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

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
                        //getSupportActionBar().setTitle("University");

                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });

        navView.getMenu().getItem(1).setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
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

    private void setupViewPager(ViewPager viewPager)
    {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new UniversityFragment(), "Unidades");
        adapter.addFragment(new ConveniosFragment(),  "Internacional");
        adapter.addFragment(new BienestarFragment(),  "Bienestar");
        viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem)
            {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    static class Adapter extends FragmentPagerAdapter
    {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm)
        {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title)
        {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position)
        {
            return mFragments.get(position);
        }

        @Override
        public int getCount()
        {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent HomeActivity1 = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Home.HomeActivity.class);
        startActivity(HomeActivity1);
        finish();

        super.onBackPressed();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void toast(String msg) {
        if(Objects.equals(msg, "Visita la pagina de Youtube de la UdeA"))
        {
            Uri webpage = Uri.parse("https://www.youtube.com/user/UniversidadAntioquia");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            // Verificar si hay aplicaciones disponibles
            PackageManager packageManager = getPackageManager();
            List activities = packageManager.queryIntentActivities(webIntent, 0);
            boolean isIntentSafe = activities.size() > 0;

            // Si hay, entonces ejecutamos la actividad
            if (isIntentSafe) {
                startActivity(webIntent);
            }
        }
        if(Objects.equals(msg, "Visita la pagina de Facebook de la UdeA"))
        {
            Uri webpage = Uri.parse("https://www.facebook.com/Universidad.de.Antioquia/");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            // Verificar si hay aplicaciones disponibles
            PackageManager packageManager = getPackageManager();
            List activities = packageManager.queryIntentActivities(webIntent, 0);
            boolean isIntentSafe = activities.size() > 0;

            // Si hay, entonces ejecutamos la actividad
            if (isIntentSafe) {
                startActivity(webIntent);
            }
        }
        if(Objects.equals(msg, "Visita la pagina de Twitter de la UdeA"))
        {
            Uri webpage = Uri.parse("https://twitter.com/udea");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            // Verificar si hay aplicaciones disponibles
            PackageManager packageManager = getPackageManager();
            List activities = packageManager.queryIntentActivities(webIntent, 0);
            boolean isIntentSafe = activities.size() > 0;

            // Si hay, entonces ejecutamos la actividad
            if (isIntentSafe) {
                startActivity(webIntent);
            }
        }
    }
}
