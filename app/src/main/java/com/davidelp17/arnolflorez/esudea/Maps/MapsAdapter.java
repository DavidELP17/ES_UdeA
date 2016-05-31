package com.davidelp17.arnolflorez.esudea.Maps;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.github.nitrico.mapviewpager.MapViewPager;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.LinkedList;
import java.util.List;

public class MapsAdapter extends MapViewPager.MultiAdapter
{
    public static final String[] PAGE_TITLES = { "Robledo" , "Posgrados"       , "Ciudadela",
                                                 "Escuela Idiomas", "Medicina", "Medellin" };

    public static final String[] ROBLEDO_TITLES = { "Robledo" };
    public static final String[] POSGRADOS_TITLES = { "Sede Posgrados" };
    public static final String[] CIUDADELA_TITLES = { "Bloque 19", "Biblioteca", "Coliseo" };
    public static final String[] IDIOMASCENTRO_TITLES = { "Antigua Escuela Derecho", "Edicifio Suramericana" };
    public static final String[] MEDICINA_TITLES = { "Facultad de Medicina" };
    public static final String[] MEDELLIN_TITLES = { };

    public static final CameraPosition SEDEROBLEDO
            = CameraPosition.fromLatLngZoom(new LatLng(6.273132, -75.587680), 17f);
    public static final CameraPosition SEDEPOSGRADOS
            = CameraPosition.fromLatLngZoom(new LatLng(6.197664, -75.584757), 17f);
    public static final CameraPosition SEDECIUDBLOQUE19
            = CameraPosition.fromLatLngZoom(new LatLng(6.2681284, -75.5671607), 17f);
    public static final CameraPosition SEDECIUDBIBLIOTECA
            = CameraPosition.fromLatLngZoom(new LatLng(6.2668946, -75.5688609), 17f);
    public static final CameraPosition SEDECIUDCOLISEO
            = CameraPosition.fromLatLngZoom(new LatLng(6.2694711, -75.5681615), 17f);
    public static final CameraPosition SEDEMULTILINGUA
            = CameraPosition.fromLatLngZoom(new LatLng(6.24574, -75.56341), 17f);
    public static final CameraPosition SEDEEDSURAMERICANA
            = CameraPosition.fromLatLngZoom(new LatLng(6.2509276, -75.5700129), 17f);
    public static final CameraPosition SEDEMEDICINA
            = CameraPosition.fromLatLngZoom(new LatLng(6.2637542, -75.5642280), 17f);

    private LinkedList<CameraPosition> robledo;
    private LinkedList<CameraPosition> posgrados;
    private LinkedList<CameraPosition> ciudadela;
    private LinkedList<CameraPosition> idiomascentro;
    private LinkedList<CameraPosition> medicina;
    private LinkedList<CameraPosition> medellin;

    public MapsAdapter(FragmentManager fm)
    {
        super(fm);

        // camera positions
        robledo = new LinkedList<>();
        posgrados = new LinkedList<>();
        ciudadela = new LinkedList<>();
        idiomascentro = new LinkedList<>();
        medicina = new LinkedList<>();
        medellin = new LinkedList<>();

        robledo.add(SEDEROBLEDO);
        posgrados.add(SEDEPOSGRADOS);
        ciudadela.add(SEDECIUDBLOQUE19);
        ciudadela.add(SEDECIUDBIBLIOTECA);
        ciudadela.add(SEDECIUDCOLISEO);
        idiomascentro.add(SEDEMULTILINGUA);
        idiomascentro.add(SEDEEDSURAMERICANA);
        medicina.add(SEDEMEDICINA);
    }

    @Override
    public int getCount()
    {
        return PAGE_TITLES.length;
    }

    @Override
    public Fragment getItem(int position)
    {
        return MapsFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return PAGE_TITLES[position];
    }

    @Override
    public String getMarkerTitle(int page, int position)
    {
        switch (page)
        {
            case 0: return ROBLEDO_TITLES[position];
            case 1: return POSGRADOS_TITLES[position];
            case 2: return CIUDADELA_TITLES[position];
            case 3: return IDIOMASCENTRO_TITLES[position];
            case 4: return MEDICINA_TITLES[position];
            case 5: return MEDELLIN_TITLES[position];
        }
        return null;
    }

    @Override
    public List<CameraPosition> getCameraPositions(int page)
    {
        switch (page) {
            case 0: return robledo;
            case 1: return posgrados;
            case 2: return ciudadela;
            case 3: return idiomascentro;
            case 4: return medicina;
            case 5: return medellin;
        }
        return null;
    }
}
