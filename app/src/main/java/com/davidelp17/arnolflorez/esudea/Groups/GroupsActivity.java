package com.davidelp17.arnolflorez.esudea.Groups;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.davidelp17.arnolflorez.esudea.DataBase.BDGrupos;
import com.davidelp17.arnolflorez.esudea.Events.EventsActivity;
import com.davidelp17.arnolflorez.esudea.Groups.Logger.ActivityBase;
import com.davidelp17.arnolflorez.esudea.Groups.Logger.Log;
import com.davidelp17.arnolflorez.esudea.Groups.Logger.LogWrapper;
import com.davidelp17.arnolflorez.esudea.Groups.Logger.MessageOnlyLogFilter;
import com.davidelp17.arnolflorez.esudea.Information.InformationActivity;
import com.davidelp17.arnolflorez.esudea.R;

public class GroupsActivity extends ActivityBase implements AppCompatCallback
{
    public static final String TAG = "GroupsActivity";

    public BDGrupos helper;
    public SQLiteDatabase dbRead;
    public Cursor c;

    public TextView GC;
    public TextView GM;
    public TextView GH;
    public TextView GP;
    private EditText Selector;

    private static final String PREF_ID = "PREF_ID";
    private static final String EDITOR_FAC = "EDITOR_FAC";
    public SharedPreferences ID_PREF;
    private String ShR_fac;

    // Whether the Log Fragment is currently shown
    private boolean mLogShown;

    private NavigationView navView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groups_activity_groups);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ID_PREF = getSharedPreferences(PREF_ID, MODE_PRIVATE);
        android.util.Log.i(TAG, "onCreate: Preference " + ID_PREF);

        ShR_fac = ID_PREF.getString(EDITOR_FAC, "null");
        android.util.Log.i(TAG, "onCreate: Preference " + ShR_fac);

        helper = new BDGrupos(this);
        dbRead = helper.getWritableDatabase();

        Selector = (EditText)findViewById(R.id.group_facultad);
        GH=(TextView)findViewById(R.id.groupH);
        GC=(TextView)findViewById(R.id.groupC);
        GM=(TextView)findViewById(R.id.groupM);
        GP=(TextView)findViewById(R.id.groupP);

        Selector.setText(ShR_fac);
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
            GroupsFragment fragment = new GroupsFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }

        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
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
                                Intent GroupsActivity = new Intent(getApplicationContext(), GroupsActivityRaw.class);
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
                        //getSupportActionBar().setTitle(R.string.app_name);

                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });

        navView.getMenu().getItem(5).setChecked(true);
    }

    public void GenerarGrupos(View v)
    {
        /*String Busqueda=null;

        if(!ShR_fac.equals("null")){
            Busqueda=ShR_fac;
        }

        String where = ContracGrupos.COLUMN_FACULTAD + "=?";
        String[] retur = new String[]{
                ContracGrupos.COLUMN_CODIGO,
                ContracGrupos.COLUMN_MATERIA,
                ContracGrupos.COLUMN_HORARIO,
                ContracGrupos.COLUMN_PROFESOR
        };

        c = dbRead.query(ContracGrupos.GRUPO_TABLE_NAME, retur, where, new String[]{Busqueda}, null, null, null);
        //c= dbRead.rawQuery("select nombre,contraseña  from Estudiantes where nombre='ju'", null);
        while (c.moveToNext()){

            GC.append("\n"+ c.getString(c.getColumnIndex(ContracGrupos.COLUMN_CODIGO)));
            GM.append("\n"+ c.getString(c.getColumnIndex(ContracGrupos.COLUMN_MATERIA)));
            GH.append("\n"+ c.getString(c.getColumnIndex(ContracGrupos.COLUMN_HORARIO)));
            GP.append("\n"+ c.getString(c.getColumnIndex(ContracGrupos.COLUMN_PROFESOR)));
        }*/
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

    @Override
    public void onBackPressed()
    {
        Intent HomeActivity1 = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Home.HomeActivity.class);
        startActivity(HomeActivity1);
        finish();

        super.onBackPressed();
    }
}
