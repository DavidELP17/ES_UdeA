package com.davidelp17.arnolflorez.esudea.Profile;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.davidelp17.arnolflorez.esudea.DataBase.BDEstudiantes;
import com.davidelp17.arnolflorez.esudea.DataBase.ContracEstudiantes;
import com.davidelp17.arnolflorez.esudea.Login.LoginActivity;
import com.davidelp17.arnolflorez.esudea.R;
import com.davidelp17.arnolflorez.esudea.University.UniversityActivity;

public class ProfileActivity extends AppCompatActivity {

    private NavigationView navView;
    private DrawerLayout mDrawerLayout;

    private BDEstudiantes helper;
    private SQLiteDatabase dbRead;
    private View Login;

    private TextView TvNombre;
    private TextView TvTIP;
    private TextView TvFacultad;
    private TextView TvCarrera;
    private TextView TvEmail;
    private EditText EdMis_Datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity_profile);


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

        Intent ID_Usuario = getIntent();
        Bundle bID = ID_Usuario.getExtras();

        if(bID!=null){
            String sID = bID.getString("ID");
            ActualizarDatos(sID);
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

        if (navView != null) {
            setupDrawerContent(navView);
        }

        navView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                Snackbar.make(navView, "Ya Estás en la Pantalla Principal", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_university:
                                Intent UniversityActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.University.UniversityActivity.class);
                                startActivity(UniversityActivity);
                                break;
                            case R.id.nav_perfil:
                                Intent ProfileActivity = new Intent(getApplicationContext(), ProfileActivity.class);
                                startActivity(ProfileActivity);
                                break;
                            case R.id.nav_calendar:
                                break;
                            case R.id.nav_horario:
                                break;
                            case R.id.nav_grupos:
                                break;
                            case R.id.nav_mapas:
                                Intent MapsActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Maps.MapsActivity.class);
                                startActivity(MapsActivity);
                                break;
                            case R.id.nav_galeria:
                                break;
                            case R.id.nav_sitioweb:
                                break;
                            case R.id.nav_login:
                                Intent LoginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(LoginActivity);
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
}


