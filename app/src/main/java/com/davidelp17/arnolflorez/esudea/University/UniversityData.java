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
                return R.drawable.unidades1;
            case 1:
                return R.drawable.unidades2;
            case 2:
                return R.drawable.unidades3;
            case 3:
                return R.drawable.unidades4;
            case 4:
                return R.drawable.unidades5;
            case 5:
                return R.drawable.unidades6;
            case 6:
                return R.drawable.unidades7;
            case 7:
                return R.drawable.unidades8;
            case 8:
                return R.drawable.unidades9;
            case 9:
                return R.drawable.unidades10;
            case 10:
                return R.drawable.unidades11;
            case 11:
                return R.drawable.unidades12;
            case 12:
                return R.drawable.unidades13;
            case 13:
                return R.drawable.unidades14;
            case 14:
                return R.drawable.unidades15;
            case 15:
                return R.drawable.unidades16;
            case 16:
                return R.drawable.unidades17;
            case 17:
                return R.drawable.unidades18;
            case 18:
                return R.drawable.unidades19;
            case 19:
                return R.drawable.unidades20;
            case 20:
                return R.drawable.unidades21;
            case 21:
                return R.drawable.unidades22;
            case 22:
                return R.drawable.unidades23;
            case 23:
                return R.drawable.unidades24;
            case 24:
                return R.drawable.unidades25;
            default:
                return R.drawable.universidad_1;
        }
    }

    public static int getRandomBieDrawable(int i)
    {
        switch (i)
        {
            case 0:
                return R.drawable.bienestar4;
            case 1:
                return R.drawable.bienestar1;
            case 2:
                return R.drawable.bienestar2;
            case 3:
                return R.drawable.bienestar3;
            case 4:
                return R.drawable.bienestar5;
            case 5:
                return R.drawable.universidad_3;
            default:
                return R.drawable.universidad_1;
        }
    }

    public static int getRandomIntDrawable(int i)
    {
        switch (i)
        {
            case 0:
                return R.drawable.screenshot_1;
            case 1:
                return R.drawable.screenshot_2;
            case 2:
                return R.drawable.screenshot_3;
            case 3:
                return R.drawable.screenshot_4;
            case 4:
                return R.drawable.screenshot_5;
            case 5:
                return R.drawable.screenshot_6;
            case 6:
                return R.drawable.screenshot_7;
            case 7:
                return R.drawable.screenshot_8;
            case 8:
                return R.drawable.screenshot_9;
            case 9:
                return R.drawable.screenshot_10;
            case 10:
                return R.drawable.screenshot_11;
            case 11:
                return R.drawable.screenshot_12;
            case 12:
                return R.drawable.screenshot_13;
            case 13:
                return R.drawable.screenshot_14;
            case 14:
                return R.drawable.screenshot_15;
            case 15:
                return R.drawable.screenshot_16;
            case 16:
                return R.drawable.screenshot_17;
            case 17:
                return R.drawable.screenshot_19;
            default:
                return R.drawable.universidad_1;
        }
    }

    public static int getRandomEveDrawable(int i)
    {
        switch (i)
        {
            case 0:
                return R.drawable.unidades17;
            case 1:
                return R.drawable.unidades12;
            case 2:
                return R.drawable.fotos27;
            case 3:
                return R.drawable.universidad_3;
            case 4:
                return R.drawable.fotos38;
            default:
                return R.drawable.universidad_1;
        }
    }

    public static int getRandomMapaDrawable(int i)
    {
        switch (i)
        {
            case 0:
                return R.drawable.mapa1;
            case 1:
                return R.drawable.mapa2;
            case 2:
                return R.drawable.universidad_1;
            case 3:
                return R.drawable.mapa4;
            case 4:
                return R.drawable.mapa5;
            case 5:
                return R.drawable.mapa6;
            default:
                return R.drawable.universidad_1;
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

    public static final String[] sIntStrings =
            {
                    "Alemania",
                    "Argentina",
                    "Belgica y Union Europea",
                    "Brasil",
                    "Canadá",
                    "Chile",
                    "Cuba",
                    "Ecuador",
                    "España",
                    "Estados Unidos",
                    "Francia",
                    "Italia",
                    "China, Corea del Sur y Japón",
                    "México",
                    "Paises Bajos",
                    "Perú",
                    "Reino Unido",
                    "Venezuela",
            };

    public static final String[] sBieStrings =
            {
                    "Apoyo Social",
                    "Bienestar con Sentido",
                    "Bienestar Deportivo",
                    "Bienestar Saludable",
                    "Bienestar Cultural",
                    "Extensión Cultural",
            };

}
