package com.davidelp17.arnolflorez.esudea.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.davidelp17.arnolflorez.esudea.Home.HomeActivity;
import com.davidelp17.arnolflorez.esudea.R;

public class LoginActivity extends AppCompatActivity {

    public static final String PREF_CLAVE = "NUEVA_CLAVE";
    public static final String PREF_CLAVE_VALOR = "Valor_Nuevo";

    private NavigationView navView;
    private DrawerLayout mDrawerLayout;

    private static final String[] DATOSUSUARIO = new String[]
    {
            "lololo:NombreDeUsuario"
    };

    //Realiza un seguimiento a la tarea dedicada al login(comprobacion de datos)
    private UserLoginTask mAuthTask = null;

    private EditText mUsernameView;
    private EditText mPasswordView;
    String Nueva_Contraseña;
    SharedPreferences pref;

    TextView texto;//Dejar para fines de comprobación

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        pref = getSharedPreferences(LoginActivity.PREF_CLAVE, MODE_PRIVATE);

        mUsernameView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);

        if (navView != null)
        {
            setupDrawerContent(navView);
        }

        texto = (TextView) findViewById(R.id.ingremaTextView);//Dejar para fines de comprobación

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras != null){
            Nueva_Contraseña = extras.getString("NUEVA_CONTRASEÑA");
            if(Nueva_Contraseña != null){
                SharedPreferences.Editor editor = pref.edit();
                editor.putString(PREF_CLAVE_VALOR, String.valueOf(Nueva_Contraseña));
                //texto.setText("Cambio!:"+Nueva_Contraseña);//Dejar para fines de comprobación
                editor.commit();
            }
        }
        if(Nueva_Contraseña == null){
            Nueva_Contraseña = (pref.getString(LoginActivity.PREF_CLAVE_VALOR, "popopo")); //"popopo" es la primera clave
            //texto.setText("Clave:"+Nueva_Contraseña);//Dejar para fines de comprobación
        }

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent)
            {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.boton_login);
        mEmailSignInButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                attemptLogin();
            }
        });

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
                                Snackbar.make(navView, "Ya estás en Inicio de Sesión", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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

    /**
     * Asegurarse que no haya errores
     */
    private void attemptLogin()
    {
        if (mAuthTask != null)
        {
            return;
        }

        // Reiniciar Errores.
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Lee y Almacena en variable string los valores obtenidos del EditText.
        String email = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Si en la contraseña no se esribio nada o se escribio menos de 4 caracteres
        if (TextUtils.isEmpty(password) || !tamañocontraseña(password))
        {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Si el usuario no escribe nada en nombre de usuario
        if (TextUtils.isEmpty(email))
        {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }
        if (cancel)
        {
            // Si hubo un error no permitir iniciar sesion y mostrar primer campo con error
            focusView.requestFocus();
        }
        else
        {
            //Luego de verificar errores. Ejecuta nueva tarea para permitir inciar sesión
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean tamañocontraseña(String password)
    {
        //La contraseña debe tener mas de 4 caracteres.
        return password.length() > 4;
    }

    /**
     * Autentificación del Usuario
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean>
    {
        private final String mUsername;
        private final String mPassword;
        boolean opc;

        UserLoginTask(String email, String password)
        {
            mUsername = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            for (String credential : DATOSUSUARIO)
            {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mUsername))
                {
                    opc = true;
                    // La cuenta existe, retorna true si la contrseña coincide
                    return Nueva_Contraseña.equals(mPassword);
                }
                if (!pieces[0].equals(mUsername))
                {
                    // La cuenta NO existe, retorna false y Error
                    opc = false;
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {
            mAuthTask = null;

            if (success)
            {
                Intent MainActivity1 = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(MainActivity1);
                finish();
            } else
            {
                if(!opc)
                {
                    mUsernameView.setError(getString(R.string.error_incorrect_password));
                    mUsernameView.requestFocus();
                }
                if(opc)
                {
                    mPasswordView.setError(getString(R.string.error_incorrect_password));
                    mPasswordView.requestFocus();
                }
            }
        }

        @Override
        protected void onCancelled()
        {
            mAuthTask = null;
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent HomeActivity1 = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(HomeActivity1);
        finish();

        super.onBackPressed();
    }
}


