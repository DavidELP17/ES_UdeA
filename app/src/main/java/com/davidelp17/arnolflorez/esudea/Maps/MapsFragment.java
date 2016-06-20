package com.davidelp17.arnolflorez.esudea.Maps;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.davidelp17.arnolflorez.esudea.R;
import com.davidelp17.arnolflorez.esudea.University.UniversityDetailActivity;

public class MapsFragment extends Fragment
{
    private ViewPager viewPager;
    private Toolbar toolbar;
    private int index;

    private boolean veces = true;
    View view;

    ScrollView scroll;

    public MapsFragment()
    {

    }

    public static MapsFragment newInstance(int i)
    {
        MapsFragment f = new MapsFragment();
        Bundle args = new Bundle();
        args.putInt("INDEX", i);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.maps_fragment_maps, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        scroll = (ScrollView) view.findViewById(R.id.scrollmapas);
        veces = true;
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) index = args.getInt("INDEX", 0);

        ViewCompat.setElevation(getView(), 10f);
        ViewCompat.setElevation(toolbar, 4f);

        toolbar.setTitle(MapsAdapter.PAGE_TITLES[index]);
        toolbar.inflateMenu(R.menu.maps_fragment_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent MapsActivity = new Intent(v.getContext(), com.davidelp17.arnolflorez.esudea.Maps.MapsDetailActivity.class);
                MapsActivity.putExtra(UniversityDetailActivity.EXTRA_NAME,MapsAdapter.PAGE_TITLES[index]);
                v.getContext().startActivity(MapsActivity);
            }
        });
    }
}
