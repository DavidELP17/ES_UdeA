package com.davidelp17.arnolflorez.esudea.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Lenovo on 18/05/2016.
 */
public class ContracEstudiantes {

    //Metainformación de la base de datos
    public static final String ESTUDIANTES_TABLE_NAME = "Estudiantes";
    public static final String TEXT_TYPE = " text ";
    public static final String INT_TYPE = " integer ";
    private static final String COMMA_SEP = ",";



    public static abstract class Estudiantes implements BaseColumns {
        public static final String COLUMN_NAME_ID = "cedula";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_CORREO = "correo";
        public static final String COLUMN_CONTRASEÑA_TITLE = "contraseña";
        public static final String COLUMN_FACULDAD_TITLE = "faculdad";
        public static final String COLUMN_CARRERA_TITLE = "carrera";

    }
    public static final String CREATE_TABLE = "create table " + ESTUDIANTES_TABLE_NAME + "(" +
            Estudiantes._ID + " INTEGER PRIMARY KEY" + " , " +
            Estudiantes.COLUMN_NOMBRE + TEXT_TYPE + " , " +
            Estudiantes.COLUMN_NAME_ID + INT_TYPE + " , " +
            Estudiantes.COLUMN_USER + INT_TYPE + " , " +
            Estudiantes.COLUMN_CORREO + INT_TYPE + " , " +
            Estudiantes.COLUMN_CONTRASEÑA_TITLE + TEXT_TYPE + " , " +
            Estudiantes.COLUMN_FACULDAD_TITLE+ TEXT_TYPE + " , " +
            Estudiantes.COLUMN_CARRERA_TITLE+ TEXT_TYPE +");" ;



    public static final String DELETE ="DROP TABLE IF EXISTS " + ESTUDIANTES_TABLE_NAME;


    //Scripts de inserción por defecto
    public static final String INSERT_ESTUDIANTES_SCRIPT =
            "insert into "+ESTUDIANTES_TABLE_NAME+ " values ("+
                    "1," + "\"Juan Lopez\","+"\"1214567835\"," + "\"juan.lopez\","+ "\"juan.lopez@udea.edu.co\","+ "\"pw1\"," + "\"Facultad de Ingenieria\"," + "\"Ingenieria Industrial\"),(" +
                    "2," + "\"Luis Lopez\","+"\"1214798635\"," + "\"luis.lopez\","+ "\"luis.lopez@udea.edu.co\","+ "\"pw2\"," + "\"Facultad de Ingenieria\"," + "\"Ingenieria Mecanica\"),(" +
                    "3," + "\"Ana Giraldo\"," +"\"50763546\"," + "\"ana.giraldo\","+ "\"ana.giraldo@udea.edu.co\"," + "\"pw3\"," + "\"Facultad de Medicina\"," + "\"Medicina\"),(" +
                    "4," + "\"Andrea Bernal\"," +"\"50873164\"," + "\"andrea.bernal\","+ "\"andrea.bernal@udea.edu.co\","+ "\"pw4\"," + "\"Facultad de Artes\"," + "\"Artes Plasticas\");";

}
