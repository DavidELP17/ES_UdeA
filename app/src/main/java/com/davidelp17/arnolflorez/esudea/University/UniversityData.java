package com.davidelp17.arnolflorez.esudea.University;

import com.davidelp17.arnolflorez.esudea.R;

import java.util.Random;

public class UniversityData
{
    private static final Random RANDOM = new Random();

    public static int getRandomUniDrawable(int i)
    {
        switch (i)
        {
            case 0:
                return R.raw.fotos1;
            case 1:
                return R.raw.fotos2;
            case 2:
                return R.raw.fotos3;
            case 3:
                return R.raw.fotos4;
            case 4:
                return R.raw.fotos5;
            case 5:
                return R.raw.fotos6;
            case 6:
                return R.raw.fotos7;
            case 7:
                return R.raw.fotos8;
            case 8:
                return R.raw.fotos9;
            case 9:
                return R.raw.fotos10;
            case 10:
                return R.raw.fotos12;
            case 11:
                return R.raw.fotos13;
            case 12:
                return R.raw.fotos14;
            case 13:
                return R.raw.fotos15;
            case 14:
                return R.raw.fotos16;
            case 15:
                return R.raw.fotos17;
            case 16:
                return R.raw.fotos18;
            case 17:
                return R.raw.fotos19;
            case 18:
                return R.raw.fotos20;
            case 19:
                return R.raw.fotos21;
            case 20:
                return R.raw.fotos22;
            case 21:
                return R.raw.fotos23;
            case 22:
                return R.raw.fotos24;
            case 23:
                return R.raw.fotos25;
            case 24:
                return R.raw.fotos26;
            default:
                return R.raw.fotos27;
        }
    }

    public static final String[] sUniStrings =
    {
            "Facultad de Artes",
            "Facultad de Ciencias Agrarias",
            "Facultad de Ciencias Económicas",
            "Facultad de Ciencias Exactas y Naturales",
            "Facultad de Ciencias Farmacéuticas y Alimentarias",
            "Facultad de Ciencias Sociales y Humanas",
            "Facultad de Comunicaciones",
            "Facultad de Derecho y Ciencias Políticas",
            "Facultad de Educación",
            "Facultad de Enfermería",
            "Facultad de Ingeniería",
            "Facultad de Medicina",
            "Facultad de Odontología",
            "Facultad de Salud Pública",
            "Escuela de Idiomas",
            "Escuela de Interamericana de Bibliotecología",
            "Escuela de Microbiología",
            "Escuela de Nutrición y Dietética",
            "Instituto de Filosofía",
            "Instituto de Educación Física y Deportes",
            "Instituto de Estudios Políticos",
            "Instituto de Estudios Regionales",
            "Corporacion Ambiental",
            "Corporacion de Ciencias Básicas Biomédicas",
            "Corporacion de Patologías Tropicales",
    };

}
