package com.davidelp17.arnolflorez.esudea;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    public static final String PREF_CLAVE = "NUEVA_CLAVE";
    public static final String PREF_CLAVE_VALOR = "Valor_Nuevo";

    private static final String[] DATOSUSUARIO = new String[]{
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        pref = getSharedPreferences(LoginActivity.PREF_CLAVE, MODE_PRIVATE);

        mUsernameView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);

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

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.boton_login);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    /**
     * Asegurarse que no haya errores
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
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
        if (TextUtils.isEmpty(password) || !tamañocontraseña(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Si el usuario no escribe nada en nombre de usuario
        if (TextUtils.isEmpty(email)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }
        if (cancel) {
            // Si hubo un error no permitir iniciar sesion y mostrar primer campo con error
            focusView.requestFocus();
        } else {
            //Luego de verificar errores. Ejecuta nueva tarea para permitir inciar sesión
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean tamañocontraseña(String password) {
        //La contraseña debe tener mas de 4 caracteres.
        return password.length() > 4;
    }

    /**
     * Autentificación del Usuario
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUsername;
        private final String mPassword;
        boolean opc;

        UserLoginTask(String email, String password) {
            mUsername = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            for (String credential : DATOSUSUARIO) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mUsername)) {
                    opc = true;
                    // La cuenta existe, retorna true si la contrseña coincide
                    return Nueva_Contraseña.equals(mPassword);
                }
                if (!pieces[0].equals(mUsername)) {
                    // La cuenta NO existe, retorna false y Error
                    opc = false;
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                Intent MainActivity1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(MainActivity1);
                finish();
            } else {
                if(!opc) {
                    mUsernameView.setError(getString(R.string.error_incorrect_password));
                    mUsernameView.requestFocus();
                }
                if(opc) {
                    mPasswordView.setError(getString(R.string.error_incorrect_password));
                    mPasswordView.requestFocus();
                }
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }
}


