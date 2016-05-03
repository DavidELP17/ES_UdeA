package com.davidelp17.arnolflorez.esudea;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davidelp17.arnolflorez.esudea.ui.PhotoGalleryFragment;

public class HomeFragment extends Fragment implements View.OnClickListener{

    public static final String TAGHome = "FragmentHome";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_home, container, false);

        mView.findViewById(R.id.home_button_universidad).setOnClickListener(this);
        mView.findViewById(R.id.home_button_perfil).setOnClickListener(this);
        mView.findViewById(R.id.home_button_calendario).setOnClickListener(this);
        mView.findViewById(R.id.home_button_horario).setOnClickListener(this);
        mView.findViewById(R.id.home_button_grupos).setOnClickListener(this);
        mView.findViewById(R.id.home_button_mapas).setOnClickListener(this);
        mView.findViewById(R.id.home_button_galeria).setOnClickListener(this);
        mView.findViewById(R.id.home_button_sitioweb).setOnClickListener(this);
        mView.findViewById(R.id.home_button_configuracion).setOnClickListener(this);

        // Inflate the layout for this fragment
        return mView;
    }

    @Override
    public void onClick(View mView) {

        switch (mView.getId()) {
            case R.id.home_button_universidad:
                ((MainActivity) getActivity()).setFragment(UniversityFragment.TAGUniversity, R.id.content_frame, false);
                break;
            case R.id.home_button_perfil:
                ((MainActivity) getActivity()).setFragment(ProfileFragment.TAGProfile, R.id.content_frame, false);
                break;
            case R.id.home_button_calendario:
                ((MainActivity) getActivity()).setFragment(CalendarFragment.TAGCalendar, R.id.content_frame, false);
                break;
            case R.id.home_button_horario:
                ((MainActivity) getActivity()).setFragment(HorarioFragment.TAGHorario, R.id.content_frame, false);
                break;
            case R.id.home_button_grupos:
                ((MainActivity) getActivity()).setFragment(GruposFragment.TAGGrupos, R.id.content_frame, false);
                break;
            case R.id.home_button_mapas:
                //((MainActivity) getActivity()).setFragment(MAPAS, R.id.content_frame, false);
                break;
            case R.id.home_button_galeria:
                ((MainActivity) getActivity()).setFragment(PhotoGalleryFragment.TAGPhotoGallery, R.id.content_frame, false);
                break;
            case R.id.home_button_sitioweb:
                ((MainActivity) getActivity()).setFragment(WebsiteFragment.TAGWebsite, R.id.content_frame, false);
                break;
            case R.id.home_button_configuracion:
                ((MainActivity) getActivity()).setFragment(InfoFragment.TAGInfo, R.id.content_frame, false);
                break;
        }
    }
}