package com.davidelp17.arnolflorez.esudea.Events;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.davidelp17.arnolflorez.esudea.R;

public class EventsFragment extends Fragment
{
    private static final String TAG = "EventsFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 5;

    private enum LayoutManagerType
    {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RadioButton mLinearLayoutRadioButton;
    protected RadioButton mGridLayoutRadioButton;

    protected RecyclerView mRecyclerView;
    protected EventsAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataGC;
    protected String[] mDataGH;
    protected String[] mDataGM;
    protected String[] mDataGS;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.events_fragment_recycler, container, false);
        rootView.setTag(TAG);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null)
        {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        mAdapter = new EventsAdapter(mDataGC,mDataGH, mDataGM, mDataGS);
        // Set EventsAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // END_INCLUDE(initializeRecyclerView)

        return rootView;
    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType)
    {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null)
        {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType)
        {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void initDataset()
    {
        mDataGC = new String[DATASET_COUNT];
        mDataGH = new String[DATASET_COUNT];
        mDataGM = new String[DATASET_COUNT];
        mDataGS = new String[DATASET_COUNT];
        for (int i = 0; i < DATASET_COUNT; i++)
        {
            if(i==0)
            {
                mDataGC[i] = "ACTUALICEMONOS EN MICROBIOLOGÍA EN INDUSTRIA DE ALIMENTOS";
                mDataGH[i] = "Hora: 9 a.m.";
                mDataGM[i] = "Lugar: Escuela de Microbiologia";
                mDataGS[i] = "Fecha: 20/06/2016";
            }
            if(i==1)
            {
                mDataGC[i] = "CONGRESO MUNDIAL SOBRE CANCER DE MAMA";
                mDataGH[i] = "Hora: 11:30 a.m.";
                mDataGM[i] = "Lugar: Facultad de Medicina";
                mDataGS[i] = "Fecha: 21/06/2016";
            }
            if(i==2)
            {
                mDataGC[i] = "CONGRESO UNIVERSITARIO SOBRE REDES SOCIALES";
                mDataGH[i] = "Hora: 7 a.m.";
                mDataGM[i] = "Lugar: Teatro Camilo Torres";
                mDataGS[i] = "Fecha: 05/07/2016";
            }
            if(i==3)
            {
                mDataGC[i] = "CONVERSATORIO BECAS FULLBRIGHT";
                mDataGH[i] = "Hora: 3 p.m.";
                mDataGM[i] = "Lugar: Teatro Facultad de Ingeniería";
                mDataGS[i] = "Fecha: 20/07/2016";
            }
            if(i==4)
            {
                mDataGC[i] = "FESTIVAL DE DANZA";
                mDataGH[i] = "Hora: 12 m.";
                mDataGM[i] = "Lugar: Coliseo Universitario";
                mDataGS[i] = "Fecha: 28/07/2016";
            }
        }
    }
}
