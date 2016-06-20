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
    public static final String COLUMN_SALON="salon";

    public static final String CREATE_TABLA_GROUP = "CREATE TABLE if not exists " + GRUPO_TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY " + " , " +
            COLUMN_FACULTAD + TEXT_TYPE   + " , " +
            COLUMN_CODIGO   + INT_TYPE    + " , " +
            COLUMN_MATERIA  + TEXT_TYPE   + " , " +
            COLUMN_HORARIO  + TEXT_TYPE   + " , " +
            COLUMN_SALON    + TEXT_TYPE   + " , " +
            COLUMN_PROFESOR + TEXT_TYPE   + ");";

    public static final String DELETE_GROUPS ="DROP TABLE IF EXISTS " + GRUPO_TABLE_NAME;

    public static final String INSERT_GRUPOS_SCRIPT= "INSERT INTO " + GRUPO_TABLE_NAME + " values ("+
            "1,"  + "\"Facultad de Ingenieria\"," + "1506943," + "\"Calculo Integral\","     + "\"MJ 8-10\","  + "\"19-220\"," + "\"Victor Marin\"),("     +
            "2,"  + "\"Facultad de Ingenieria\"," + "2345783," + "\"Estadistica\","          + "\"WV 10-12\"," + "\"20-318\"," + "\"Alfredo Perez\"),("    +
            "3,"  + "\"Facultad de Ingenieria\"," + "3678944," + "\"Calculo Diferencial\","  + "\"MJ 12-14\"," + "\"19-201\"," + "\"Adelaida Perez\"),("   +
            "4,"  + "\"Facultad de Ingenieria\"," + "4789564," + "\"Analisis de Sistemas\"," + "\"LW 6-8\","   + "\"18-310\"," + "\"Fernando Acevedo\"),(" +
            "5,"  + "\"Facultad de Ingenieria\"," + "5789563," + "\"Redes II\","             + "\"LV 16-18\"," + "\"19-212\"," + "\"Jaime Bustamante\"),(" +
            "6,"  + "\"Facultad de Ingenieria\"," + "6784563," + "\"Fisica Mecanica\","      + "\"WV 8-10\","  + "\"21-314\"," + "\"Carolina Gallego\"),(" +
            "7,"  + "\"Facultad de Medicina\","   + "1843635," + "\"Medicina I\","           + "\"LW 8-10\","  + "\"32-350\"," + "\"Juana Franceli\"),("   +
            "8,"  + "\"Facultad de Medicina\","   + "2542457," + "\"Anatomia\","             + "\"WV 12-14\"," + "\"32-203\"," + "\"Ana Gomez\"),("        +
            "9,"  + "\"Facultad de Medicina\","   + "3353422," + "\"Sistemas\","             + "\"LW 6-8\","   + "\"32-120\"," + "\"Luis Goss\"),("        +
            "10," + "\"Facultad de Medicina\","   + "4034358," + "\"Calculo II\","           + "\"LS 16-18\"," + "\"32-325\"," + "\"Heder Miranda\"),("    +
            "11," + "\"Facultad de Medicina\","   + "5157574," + "\"Polimeros\","            + "\"VS 8-10\","  + "\"32-220\"," + "\"Maria Perez\"),("      +
            "12," + "\"Facultad de Artes\","      + "1263342," + "\"Artes I\","              + "\"MJ 8-10\","  + "\"26-127\"," + "\"Felipe Luis\"),("      +
            "13," + "\"Facultad de Artes\","      + "2356698," + "\"Artes II\","             + "\"WV 14-16\"," + "\"26-125\"," + "\"Frank Miranda\"),("    +
            "14," + "\"Facultad de Artes\","      + "3469952," + "\"Visualizacion\","        + "\"LW 6-8\","   + "\"26-222\"," + "\"Mark Top\"),("         +
            "15," + "\"Facultad de Artes\","      + "4559491," + "\"Espectros\","            + "\"LS 9-12\","  + "\"26-328\"," + "\"Lucia Lions\"),("      +
            "16," + "\"Facultad de Artes\","      + "5692648," + "\"Campos Difusos\","       + "\"LW 12-14\", "+ "\"26-339\"," + "\"Manuela Martinez\");";
}
