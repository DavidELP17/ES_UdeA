package com.davidelp17.arnolflorez.esudea.Universidad;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.davidelp17.arnolflorez.esudea.Calendar.CalendarFragment;
import com.davidelp17.arnolflorez.esudea.Galeria.UI.PhotoGalleryFragment;
import com.davidelp17.arnolflorez.esudea.Grupos.GruposFragment;
import com.davidelp17.arnolflorez.esudea.HomeFragment;
import com.davidelp17.arnolflorez.esudea.Horario.HorarioFragment;
import com.davidelp17.arnolflorez.esudea.Informacion.InfoFragment;
import com.davidelp17.arnolflorez.esudea.Mapas.MapsFragment;
import com.davidelp17.arnolflorez.esudea.Perfil.ProfileFragment;
import com.davidelp17.arnolflorez.esudea.R;
import com.davidelp17.arnolflorez.esudea.SitioWeb.WebsiteFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public class MainActivityTabs extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private NavigationView navView;

    String fragmentName1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabs);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Snackbar de prueba, Usar!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        navView = (NavigationView) findViewById(R.id.nav_view);

        if (navView != null) {
            setupDrawerContent(navView);
        }

        navView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;

                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                fragment = new HomeFragment();
                                fragmentName1 = HomeFragment.TAGHome;
                                fragmentTransaction = true;
                                break;
                            case R.id.nav_university:
                                fragment = new UniversityFragment();
                                fragmentName1 = UniversityFragment.TAGUniversity;
                                fragmentTransaction = true;
                                break;
                            case R.id.nav_perfil:
                                fragment = new ProfileFragment();
                                fragmentName1 = ProfileFragment.TAGProfile;
                                fragmentTransaction = true;
                                break;
                            case R.id.nav_calendar:
                                fragment = new CalendarFragment();
                                fragmentName1 = CalendarFragment.TAGCalendar;
                                fragmentTransaction = true;
                                break;
                            case R.id.nav_horario:
                                fragment = new HorarioFragment();
                                fragmentName1 = HorarioFragment.TAGHorario;
                                fragmentTransaction = true;
                                break;
                            case R.id.nav_grupos:
                                fragment = new GruposFragment();
                                fragmentName1 = GruposFragment.TAGGrupos;
                                fragmentTransaction = true;
                                break;
                            case R.id.nav_mapas:
                                fragment = new MapsFragment();
                                fragmentName1 = MapsFragment.TAGMaps;
                                fragmentTransaction = true;
                                break;
                            case R.id.nav_galeria:
                                fragment = new PhotoGalleryFragment();
                                fragmentName1 = PhotoGalleryFragment.TAGPhotoGallery;
                                fragmentTransaction = true;
                                break;
                            case R.id.nav_sitioweb:
                                fragment = new WebsiteFragment();
                                fragmentName1 = WebsiteFragment.TAGWebsite;
                                fragmentTransaction = true;
                                break;
                            case R.id.nav_info:
                                fragment = new InfoFragment();
                                fragmentName1 = InfoFragment.TAGInfo;
                                fragmentTransaction = true;
                                break;
                            case R.id.nav_exit:
                                finish();
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                        }

                        if (fragmentTransaction) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_frame, fragment, fragmentName1)
                                    .addToBackStack(fragmentName1)
                                    .commit();

                            menuItem.setChecked(true);
                            getSupportActionBar().setTitle(menuItem.getTitle());
                        }

                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ActivityDetailFragment(), "Facultades");
        adapter.addFragment(new ActivityDetailFragment(), "Extension");
        adapter.addFragment(new ActivityDetailFragment(), "Bienestar");
        viewPager.setAdapter(adapter);
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

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
