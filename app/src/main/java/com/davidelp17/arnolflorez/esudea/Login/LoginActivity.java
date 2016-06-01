package com.davidelp17.arnolflorez.esudea.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.davidelp17.arnolflorez.esudea.DataBase.BDEstudiantes;
import com.davidelp17.arnolflorez.esudea.DataBase.ContracEstudiantes;
import com.davidelp17.arnolflorez.esudea.Home.HomeActivity;
import com.davidelp17.arnolflorez.esudea.Profile.ProfileActivity;
import com.davidelp17.arnolflorez.esudea.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Ingreso";

    private static final String PREF_ID = "PREF_ID";
    private static final String EDITOR_ID = "EDITOR_ID";

    public SharedPreferences ID_PREF;
    public SharedPreferences.Editor editor_id;

    private EditText mUsernameView;
    private EditText mPasswordView;
    private Button mOutLogin;
    private Button mLogin;

    private NavigationView navView;
    private DrawerLayout mDrawerLayout;

    public BDEstudiantes helper;
    public SQLiteDatabase dbRead;
    public Cursor c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ID_PREF = getSharedPreferences(PREF_ID, MODE_PRIVATE);
        editor_id = ID_PREF.edit();


        mUsernameView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);
        mOutLogin = (Button) findViewById(R.id.boton_outlogin);
        mLogin = (Button) findViewById(R.id.boton_login);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);

        if (navView != null)
        {
            setupDrawerContent(navView);
        }

        helper = new BDEstudiantes(this);
        dbRead = helper.getWritableDatabase();

        String ShR_id = ID_PREF.getString(EDITOR_ID, "null");

        if(!ShR_id.equals("null")){
            mPasswordView.setVisibility(View.INVISIBLE);
            mUsernameView.setVisibility(View.INVISIBLE);
            mLogin.setVisibility(View.GONE);
            mOutLogin.setVisibility(View.VISIBLE);
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
                                Intent HomeActivity1 = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(HomeActivity1);
                                finish();
                                break;
                            case R.id.nav_university:
                                Intent UniversityActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.University.UniversityActivity.class);
                                startActivity(UniversityActivity);
                                finish();
                                break;
                            case R.id.nav_perfil:
                                Intent ProfileActivity = new Intent(getApplicationContext(), ProfileActivity.class);
                                startActivity(ProfileActivity);
                                finish();
                                break;
                            case R.id.nav_calendar:
                                Snackbar.make(navView, "Recurso en Construcción", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_horario:
                                Snackbar.make(navView, "Recurso en Construcción", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_grupos:
                                Snackbar.make(navView, "Recurso en Construcción", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_sedes:
                                Snackbar.make(navView, "Recurso en Construcción", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_mapas:
                                Intent MapsActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Maps.MapsActivity.class);
                                startActivity(MapsActivity);
                                finish();
                                break;
                            case R.id.nav_galeria:
                                Intent GalleryActivity = new Intent(getApplicationContext(), com.davidelp17.arnolflorez.esudea.Gallery.GalleryActivity.class);
                                startActivity(GalleryActivity);
                                finish();
                                break;
                            case R.id.nav_sitioweb:
                                Snackbar.make(navView, "Recurso en Construcción", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_login:
                                Snackbar.make(navView, "Ya Estás en la Inicio de Sesión", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                finish();
                                break;
                            case R.id.nav_settings:
                                Snackbar.make(navView, "Recurso en Construcción", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                break;
                            case R.id.nav_info:
                                Snackbar.make(navView, "Recurso en Construcción", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
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

    public String getUsuario() {

        String Usuario = mUsernameView.getText().toString();

        return Usuario;
    }

    public String getContraseña() {

        String Contraseña = mPasswordView.getText().toString();

        return Contraseña;
    }


    public void OnClicEntrar(View view) {


        String ID;
        String[] args = new String[]{getUsuario()};
        String[] retur = new String[]{ContracEstudiantes.Estudiantes.COLUMN_CONTRASEÑA_TITLE, ContracEstudiantes.Estudiantes._ID};

        String where = ContracEstudiantes.Estudiantes.COLUMN_USER + "=?";


        String password = null;
        c = dbRead.query(ContracEstudiantes.ESTUDIANTES_TABLE_NAME, retur, where, args, null, null, null);
        //c= dbRead.rawQuery("select nombre,contraseña  from Estudiantes where nombre='ju'", null);
        c.moveToFirst();


        if (c != null) {

            if (c.getCount() != 0) {

                password = c.getString(c.getColumnIndex(ContracEstudiantes.Estudiantes.COLUMN_CONTRASEÑA_TITLE));
                ID = c.getString(c.getColumnIndex(ContracEstudiantes.Estudiantes._ID));
                //password=c.getString(0);
                Log.i(TAG, "OnClicEntrar: Pasaword " + password);

                if (getContraseña().equals(password)) {
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    intent.putExtra("ID", ID);

                    editor_id.putString(EDITOR_ID, ID);
                    editor_id.commit();

                    startActivity(intent);
                    finish();
                } else {
                    Snackbar.make(view, "Contraseña Incorrecta ", Snackbar.LENGTH_LONG).show();
                    mPasswordView.setText("");

                    editor_id.putString(EDITOR_ID, "null");
                    editor_id.commit();

                }
            } else {
                Snackbar.make(view, "Usuario Incorrecto ", Snackbar.LENGTH_LONG).show();
                mUsernameView.setText("");
                mPasswordView.setText("");

                editor_id.putString(EDITOR_ID, "null");
                editor_id.commit();
            }

        }

    }


    public String ObtenerID(Cursor c) {
        String ID;
        return ID = c.getString(c.getColumnIndex(ContracEstudiantes.Estudiantes._ID));
    }

    public void OnClicOutlogin(View v){
        editor_id.putString(EDITOR_ID,"null");
        editor_id.commit();
        mUsernameView.setVisibility(View.VISIBLE);
        mPasswordView.setVisibility(View.VISIBLE);
        mLogin.setVisibility(View.VISIBLE);
        mOutLogin.setVisibility(View.GONE);
        Log.i(TAG, "OnClicOutlogin: " + ID_PREF.getString(EDITOR_ID, "null"));
    }

    public void OnClicProfile(View v){
        if(mOutLogin.isShown()){
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        Intent HomeActivity1 = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(HomeActivity1);
        finish();

        super.onBackPressed();
    }
}


