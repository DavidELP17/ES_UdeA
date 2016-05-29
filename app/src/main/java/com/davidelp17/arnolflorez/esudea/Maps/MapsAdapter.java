package com.davidelp17.arnolflorez.esudea.Maps;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.github.nitrico.mapviewpager.MapViewPager;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.LinkedList;
import java.util.List;

public class MapsAdapter extends MapViewPager.MultiAdapter {

    public static final String[] PAGE_TITLES = { "Robledo", "Posgrados", "Ciudadela",
            "Medellin", "Facultad Derecho", "Medicina" };

    public static final String[] ENGLAND_TITLES = { "Robledo" };
    public static final String[] FRANCE_TITLES = { "Sede Posgrados" };
    public static final String[] SPAIN_TITLES = { "Bloque 19", "Biblioteca", "Coliseo" };
    public static final String[] PORTUGAL_TITLES = { };
    public static final String[] ITALY_TITLES = { "Antigua Escuela Derecho", "Edicifio Suramericana" };
    public static final String[] BELGIUM_TITLES = { "Facultad de Medicina" };

    public static final CameraPosition LONDON
            = CameraPosition.fromLatLngZoom(new LatLng(6.273132, -75.587680), 17f);
    public static final CameraPosition PARIS
            = CameraPosition.fromLatLngZoom(new LatLng(6.197664, -75.584757), 17f);
    public static final CameraPosition BARCELONA
            = CameraPosition.fromLatLngZoom(new LatLng(6.2681284, -75.5671607), 17f);
    public static final CameraPosition MADRID
            = CameraPosition.fromLatLngZoom(new LatLng(6.2668946, -75.5688609), 17f);
    public static final CameraPosition VALENCIA
            = CameraPosition.fromLatLngZoom(new LatLng(6.2694711, -75.5681615), 17f);
    public static final CameraPosition MILAN
            = CameraPosition.fromLatLngZoom(new LatLng(6.24574, -75.56341), 17f);
    public static final CameraPosition ROME
            = CameraPosition.fromLatLngZoom(new LatLng(6.2509276, -75.5700129), 17f);
    public static final CameraPosition BRUSSELS
            = CameraPosition.fromLatLngZoom(new LatLng(6.2637542, -75.5642280), 17f);

    private LinkedList<CameraPosition> england;
    private LinkedList<CameraPosition> france;
    private LinkedList<CameraPosition> spain;
    private LinkedList<CameraPosition> portugal;
    private LinkedList<CameraPosition> italy;
    private LinkedList<CameraPosition> belgium;

    public MapsAdapter(FragmentManager fm) {
        super(fm);

        // camera positions
        england = new LinkedList<>();
        france = new LinkedList<>();
        spain = new LinkedList<>();
        portugal = new LinkedList<>();
        italy = new LinkedList<>();
        belgium = new LinkedList<>();

        england.add(LONDON);
        france.add(PARIS);
        spain.add(BARCELONA);
        spain.add(MADRID);
        spain.add(VALENCIA);
        italy.add(MILAN);
        italy.add(ROME);
        belgium.add(BRUSSELS);
    }

    @Override
    public int getCount() {
        return PAGE_TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        return MapsFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return PAGE_TITLES[position];
    }

    @Override
    public String getMarkerTitle(int page, int position) {
        switch (page) {
            case 0: return ENGLAND_TITLES[position];
            case 1: return FRANCE_TITLES[position];
            case 2: return SPAIN_TITLES[position];
            case 3: return PORTUGAL_TITLES[position];
            case 4: return ITALY_TITLES[position];
            case 5: return BELGIUM_TITLES[position];
        }
        return null;
    }

    @Override
    public List<CameraPosition> getCameraPositions(int page) {
        switch (page) {
            case 0: return england;
            case 1: return france;
            case 2: return spain;
            case 3: return portugal;
            case 4: return italy;
            case 5: return belgium;
        }
        return null;
    }

}
