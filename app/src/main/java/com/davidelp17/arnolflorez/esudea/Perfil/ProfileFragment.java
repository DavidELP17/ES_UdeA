package com.davidelp17.arnolflorez.esudea.Perfil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.davidelp17.arnolflorez.esudea.MainActivity;
import com.davidelp17.arnolflorez.esudea.R;

public class ProfileFragment extends Fragment {

    public static final String TAGProfile = "FragmentProfile";

    private EditText NuevaContraseña;
    private Button BotonChangePassword;

    TextView NombreTextview;
    TextView NombreUsuarioTextview;
    TextView CorreoTextview;

    String Nueva_ContraseñaString;
    boolean cancel;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_profile, container, false);

        NuevaContraseña = (EditText) mView.findViewById(R.id.passwordchange);
        BotonChangePassword = (Button) mView.findViewById(R.id.boton_change);

        NombreTextview = (TextView) mView.findViewById(R.id.namelabelprofile);
        CorreoTextview = (TextView) mView.findViewById(R.id.emaillabelprofile);
        NombreUsuarioTextview = (TextView) mView.findViewById(R.id.usernamelabelprofile);

        Data.setNombre("Andy Perfil");
        Data.setUsername("Andy");
        Data.setCorreo("Andy@udea.edu.co");

        NombreTextview.setText(Data.getNombre());
        CorreoTextview.setText(Data.getCorreo());
        NombreUsuarioTextview.setText(Data.getUsername());

        BotonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        NuevaContraseña.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                attemptLogin();
                return true;
            }
        });
        return mView;
    }

    public String getNueva_Contraseña(){
        Nueva_ContraseñaString = NuevaContraseña.getText().toString();
        return Nueva_ContraseñaString;
    }

    private boolean tamañocontraseña(String password) {
        //La contraseña debe tener mas de 4 caracteres.
        return password.length() > 4;
    }

    private void definir_clave() {
        NuevaContraseña.setEnabled(false);
        ((MainActivity) getActivity()).setPreferencias(getNueva_Contraseña());
    }

    /**
     * Asegurarse que no haya errores
     */
    private void attemptLogin() {
        // Reiniciar Errores.
        NuevaContraseña.setError(null);

        // Lee y Almacena en variable string los valores obtenidos del EditText.
        String password = NuevaContraseña.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Si en la contraseña no se esribio nada o se escribio menos de 4 caracteres
        if (TextUtils.isEmpty(password) || !tamañocontraseña(password)) {
            NuevaContraseña.setError(getString(R.string.error_invalid_password));
            focusView = NuevaContraseña;
            cancel = true;
        }

        if (cancel) {
            // Si hubo un error no permitir iniciar sesion y mostrar primer campo con error
            focusView.requestFocus();
        } else {
            //Luego de verificar errores. Ejecuta nueva tarea para permitir inciar sesión
            definir_clave();
        }
    }
}