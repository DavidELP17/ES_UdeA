package com.davidelp17.arnolflorez.esudea.Perfil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.davidelp17.arnolflorez.esudea.R;

public class Data extends AppCompatActivity{

    //Feclaración de Variables

    //Informacion de Perfil
    TextView NombreTextview;
    TextView NombreUsuarioTextview;
    TextView CorreoTextview;
    private static String Nombre;
    private static String NombreUsuario;
    private static String Correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NombreTextview = (TextView) findViewById(R.id.namelabelprofile);
        CorreoTextview = (TextView) findViewById(R.id.emaillabelprofile);
        NombreUsuarioTextview = (TextView) findViewById(R.id.usernamelabelprofile);

    }

    //Informacion de Perfil
    public static String getNombre(){
        return Nombre;
    }

    public static String getUsername(){
        return NombreUsuario;
    }

    public static String getCorreo(){
        return Correo;
    }

    public static void setNombre(String nombre){
        Nombre = nombre;
    }

    public static void setUsername(String nombreDeUsuario){
        NombreUsuario = nombreDeUsuario;
    }

    public static void setCorreo(String correo){
        Correo = correo;
    }
}
