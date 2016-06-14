package com.davidelp17.arnolflorez.esudea.Profile;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.davidelp17.arnolflorez.esudea.DataBase.BDEstudiantes;
import com.davidelp17.arnolflorez.esudea.DataBase.ContracEstudiantes;
import com.davidelp17.arnolflorez.esudea.Events.EventsActivity;
import com.davidelp17.arnolflorez.esudea.Groups.GroupsActivityRaw;
import com.davidelp17.arnolflorez.esudea.Information.InformationActivity;
import com.davidelp17.arnolflorez.esudea.Login.LoginActivity;
import com.davidelp17.arnolflorez.esudea.R;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "Profile";

    private static final String PREF_ID = "PREF_ID";
    private static final String EDITOR_ID = "EDITOR_ID";
    private static final String EDITOR_FAC = "EDITOR_FAC";
    public SharedPreferences ID_PREF;
    public SharedPreferences.Editor editor_fac;

    private NavigationView navView;
    private DrawerLayout mDrawerLayout;

    private BDEstudiantes helper;
    private SQLiteDatabase dbRead;

    private TextView TvNombre;
    private TextView TvTIP;
    private TextView TvFacultad;
    private TextView TvCarrera;
    private TextView TvEmail;
    private EditText EdMis_Datos;
    private TextView HNombre;
    private TextView HCorreo;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ID_PREF = getSharedPreferences(PREF_ID, MODE_PRIVATE);
        Log.i(TAG, "onCreate: Preference " + ID_PREF);

        String ShR_id = ID_PREF.getString(EDITOR_ID, "null");
        Log.i(TAG, "onCreate: Preference "+ShR_id);

        editor_fac=ID_PREF.edit();

        HNombre = (TextView) findViewById(R.id.namelabel);
        HCorreo = (TextView)findViewById(R.id.usernamelabel);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);

        helper = new BDEstudiantes(this);
        dbRead = helper.getReadableDatabase();

        EdMis_Datos = (EditText) findViewById(R.id.misDatos);
        TvNombre = (TextView) findViewById(R.id.namelabelprofile);
        TvTIP = (TextView) findViewById(R.id.usercclabelprofile);
        TvCarrera = (TextView) findViewById(R.id.carreralabelprofile);
        TvFacultad = (TextView) findViewById(R.id.userfacultadlabelprofile);
        TvEmail = (TextView) findViewById(R.id.emailuser);

        // Utilizacion de Extras para cargar datos a la Actividad
//        Intent ID_Usuario = getIntent();
//        Bundle bID = ID_Usuario.getExtras();
        //String sID = bID.getString("ID");
        // ActualizarDatos(sID);


        if(!ShR_id.equals("null")){
            Log.i(TAG, "onCreate: Preference IF "+ShR_id);
            ActualizarDatos(ShR_id);

        }else{

            Snackbar.make(mDrawerLayout, "No Hay Datos de Usuario ", Snackbar.LENGTH_LONG).setAction("Ir a Login", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).show();

        }

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
                                Snackbar.make(navView, "Ya Estás en Perfíl", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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

                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });

        navView.getMenu().getItem(2).setChecked(true);
    }

    public void ActualizarDatos(String sID) {
        Cursor c;
        String Celula = null;
        String Nombre = null;
        String Faculdad = null;
        String Carrera = null;
        String Email=null;

        String[] retur = new String[]{
                ContracEstudiantes.Estudiantes.COLUMN_NAME_ID,
                ContracEstudiantes.Estudiantes.COLUMN_NOMBRE,
                ContracEstudiantes.Estudiantes.COLUMN_FACULDAD_TITLE,
                ContracEstudiantes.Estudiantes.COLUMN_CARRERA_TITLE,
                ContracEstudiantes.Estudiantes.COLUMN_CORREO
        };

        String where = ContracEstudiantes.Estudiantes._ID + "=?";

        c = dbRead.query(ContracEstudiantes.ESTUDIANTES_TABLE_NAME, retur, where, new String[]{sID}, null, null, null);
        //c= dbRead.rawQuery("select nombre,contraseña  from Estudiantes where nombre='ju'", null);
        c.moveToFirst();

        if (c != null & c.getCount() != 0) {

            Celula = c.getString(c.getColumnIndex(ContracEstudiantes.Estudiantes.COLUMN_NAME_ID));
            Nombre = c.getString(c.getColumnIndex(ContracEstudiantes.Estudiantes.COLUMN_NOMBRE));
            Faculdad = c.getString(c.getColumnIndex(ContracEstudiantes.Estudiantes.COLUMN_FACULDAD_TITLE));
            Carrera = c.getString(c.getColumnIndex(ContracEstudiantes.Estudiantes.COLUMN_CARRERA_TITLE));
            Email = c.getString(c.getColumnIndex(ContracEstudiantes.Estudiantes.COLUMN_CORREO));

        }

        editor_fac.putString(EDITOR_FAC, Faculdad);
        editor_fac.commit();

        Log.i(TAG, "ActualizarDatos: Commit" + Faculdad);

        TvNombre.setText(Nombre);
        TvTIP.setText("TIP # "+Celula);
        TvFacultad.setText(Faculdad);
        TvCarrera.setText(Carrera);
        TvEmail.setText(Email);


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

   public void Actualizacion_Usuario_Header(String Nombre, String Email) {

       TextView HNombre = (TextView) findViewById(R.id.namelabel);
       TextView HCorreo = (TextView) findViewById(R.id.usernamelabel);

       HCorreo.setText(Email);
       HNombre.setText(Nombre);
   }


    public void AnimacionMenu(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
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


