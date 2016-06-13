package com.davidelp17.arnolflorez.esudea.DataBase;

import android.provider.BaseColumns;

public  abstract class ContracGrupos implements BaseColumns
{
    public static final String GRUPO_TABLE_NAME = "Grupos";
    public static final String TEXT_TYPE = " text ";
    public static final String INT_TYPE = " integer ";

    public static final String COLUMN_CODIGO="codigo";
    public static final String COLUMN_MATERIA="materia";
    public static final String COLUMN_HORARIO="horario";
    public static final String COLUMN_PROFESOR="profesor";
    public static final String COLUMN_FACULTAD="facultad";

    public static final String CREATE_TABLA_GROUP = "CREATE TABLE if not exists " + GRUPO_TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY " + " , " +
            COLUMN_FACULTAD + TEXT_TYPE   + " , " +
            COLUMN_CODIGO  + INT_TYPE     + " , " +
            COLUMN_MATERIA + TEXT_TYPE    + " , " +
            COLUMN_HORARIO + TEXT_TYPE    + " , " +
            COLUMN_PROFESOR + TEXT_TYPE   + ");";

    public static final String DELETE_GROUPS ="DROP TABLE IF EXISTS " + GRUPO_TABLE_NAME;

    public static final String INSERT_GRUPOS_SCRIPT= "INSERT INTO " + GRUPO_TABLE_NAME + " values ("+
            "1,"  + "\"Facultad de Ingenieria\"," + "001," + "\"Calculo Integral\","     + "\"MJ 8-10\","  + "\"Victor Marin\"),("     +
            "2,"  + "\"Facultad de Ingenieria\"," + "001," + "\"Calculo Integral\","     + "\"WV 10-12\","  + "\"Victor Marin\"),("    +
            "3,"  + "\"Facultad de Ingenieria\"," + "002," + "\"Calculo Diferencial\","  + "\"MJ 12-14\"," + "\"Adelaida Perez\"),("   +
            "4,"  + "\"Facultad de Ingenieria\"," + "111," + "\"Analisis de Sistemas\"," + "\"LW 6-8\","   + "\"Fernando Acevedo\"),(" +
            "5,"  + "\"Facultad de Ingenieria\"," + "021," + "\"Redes II\","             + "\"LV 16-18\"," + "\"Jaime Bustamante\"),(" +
            "6,"  + "\"Facultad de Ingenieria\"," + "031," + "\"Fisica Mecanica\","      + "\"WV 8-10\","  + "\"Carolina Gallego\"),(" +
            "7,"  + "\"Facultad de Medicina\","   + "999," + "\"Medicina I\","           + "\"LW 8-10\","  + "\"Juana Franceli\"),("   +
            "8,"  + "\"Facultad de Medicina\","   + "989," + "\"Anatomia\","             + "\"WV 12-14\"," + "\"Ana Gomez\"),("        +
            "9,"  + "\"Facultad de Medicina\","   + "341," + "\"Sistemas\","             + "\"LW 6-8\","   + "\"Luis Goss\"),("        +
            "10," + "\"Facultad de Medicina\","   + "342," + "\"Calculo II\","           + "\"LS 16-18\"," + "\"Heder Miranda\"),("    +
            "11," + "\"Facultad de Medicina\","   + "345," + "\"Polimeros\","            + "\"VS 8-10\","  + "\"Maria Perez\"),("      +
            "12," + "\"Facultad de Artes\","      + "020," + "\"Artes I\","              + "\"MJ 8-10\","  + "\"Felipe Luis\"),("      +
            "13," + "\"Facultad de Artes\","      + "002," + "\"Artes II\","             + "\"WV 14-16\"," + "\"Frank Miranda\"),("    +
            "14," + "\"Facultad de Artes\","      + "870," + "\"Visualizacion\","        + "\"LW 6-8\","   + "\"Mark Top\"),("         +
            "15," + "\"Facultad de Artes\","      + "876," + "\"Espectros\","            + "\"LS 9-12\","  + "\"Lucia Lions\"),("      +
            "16," + "\"Facultad de Artes\","      + "321," + "\"Campos Difusos\","       + "\"LW 12-14\", "+ "\"Manuela Martinez\");";
}
