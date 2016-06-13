package com.davidelp17.arnolflorez.esudea.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDGrupos extends SQLiteOpenHelper
{
    private static final String TAG ="Grupos";
    public static final String DATABASE_NAME = "Grupos.db";
    public static final int DATABASE_VERSION = 1;

    public BDGrupos(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(ContracGrupos.CREATE_TABLA_GROUP);
        db.execSQL(ContracGrupos.INSERT_GRUPOS_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(ContracGrupos.DELETE_GROUPS);
       onCreate(db);
    }
}
