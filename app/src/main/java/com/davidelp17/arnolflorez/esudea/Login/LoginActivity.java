package com.davidelp17.arnolflorez.esudea.Login;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.davidelp17.arnolflorez.esudea.DataBase.BDEstudiantes;
import com.davidelp17.arnolflorez.esudea.DataBase.ContracEstudiantes;
import com.davidelp17.arnolflorez.esudea.Profile.ProfileActivity;
import com.davidelp17.arnolflorez.esudea.R;

/**
 * Created by Lenovo on 29/05/2016.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG ="Ingreso";

    private EditText mUsernameView;
    private EditText mPasswordView;

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


        mUsernameView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);

        helper =new BDEstudiantes(this);
        dbRead = helper.getReadableDatabase();

        Log.i(TAG, "onCreate: ");
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

        String where = ContracEstudiantes.Estudiantes.COLUMN_USER+ "=?" ;


        String password=null;
        c = dbRead.query(ContracEstudiantes.ESTUDIANTES_TABLE_NAME, retur,where, args, null, null, null);
        //c= dbRead.rawQuery("select nombre,contraseña  from Estudiantes where nombre='ju'", null);
        c.moveToFirst();


        if(c!= null){

            if(c.getCount()!=0) {

                password = c.getString(c.getColumnIndex(ContracEstudiantes.Estudiantes.COLUMN_CONTRASEÑA_TITLE));
                ID = c.getString(c.getColumnIndex(ContracEstudiantes.Estudiantes._ID));
                //password=c.getString(0);
                Log.i(TAG, "OnClicEntrar: Pasaword " + password);

                if (getContraseña().equals(password)) {
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    intent.putExtra("ID",ID);
                    startActivity(intent);
                    finish();
                } else {
                    Snackbar.make(view, "Contraseña Incorrecta ", Snackbar.LENGTH_LONG).show();
                    mPasswordView.setText("");
                }
            }else {
                Snackbar.make(view, "Usuario Incorrecto ", Snackbar.LENGTH_LONG).show();
                mUsernameView.setText("");
                mPasswordView.setText("");
            }

        }

    }

    public String ObtenerID(Cursor c){
        String ID;
        return ID = c.getString(c.getColumnIndex(ContracEstudiantes.Estudiantes._ID));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}

