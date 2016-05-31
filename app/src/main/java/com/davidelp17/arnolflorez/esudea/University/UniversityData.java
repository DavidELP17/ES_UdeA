package com.davidelp17.arnolflorez.esudea.University;

import com.davidelp17.arnolflorez.esudea.R;

import java.util.Random;

public class UniversityData
{
    private static final Random RANDOM = new Random();

    public static int getRandomUniDrawable()
    {
        switch (RANDOM.nextInt(5))
        {
            default:
            case 0:
                return R.drawable.universidad_1;
            case 1:
                return R.drawable.universidad_2;
            case 2:
                return R.drawable.universidad_3;
            case 3:
                return R.drawable.universidad_4;
            case 4:
                return R.drawable.universidad_5;
        }
    }

    public static final String[] sUniStrings =
    {
            "Facultad de Ingenieria", "Facultad de Educacion", "Salud Publica", "Facultad de Enfermeria",
            "Instituto de Fisica", "Facultad de Quimica Farmaceutica", "Escuela de Idiomas",
            "Facultad de Ingenieria", "Facultad de Educacion", "Salud Publica", "Facultad de Enfermeria",
            "Instituto de Fisica", "Facultad de Quimica Farmaceutica", "Escuela de Idiomas",
    };

}
