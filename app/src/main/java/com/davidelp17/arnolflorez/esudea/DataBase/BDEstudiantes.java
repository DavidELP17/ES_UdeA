package com.davidelp17.arnolflorez.esudea.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Lenovo on 18/05/2016.
 */
public class BDEstudiantes extends SQLiteOpenHelper {
    private static final String TAG ="Ingreso";
    public static final String DATABASE_NAME = "Estudiantes.db";
    public static final int DATABASE_VERSION = 1;

    public BDEstudiantes(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContracEstudiantes.DELETE);
        Log.i(TAG, "onCreate: Borro ");
        db.execSQL(ContracEstudiantes.CREATE_TABLE);
        Log.i(TAG, "onCreate: creo");
        db.execSQL(ContracEstudiantes.INSERT_ESTUDIANTES_SCRIPT);
        Log.i(TAG, "onCreate: Inserto");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(ContracEstudiantes.DELETE);
        Log.i(TAG, "onUpgrade: actializo");
            onCreate(db);



    }
}
