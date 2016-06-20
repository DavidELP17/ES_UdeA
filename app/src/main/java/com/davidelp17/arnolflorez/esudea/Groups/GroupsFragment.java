package com.davidelp17.arnolflorez.esudea.Groups;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.davidelp17.arnolflorez.esudea.DataBase.BDGrupos;
import com.davidelp17.arnolflorez.esudea.DataBase.ContracGrupos;
import com.davidelp17.arnolflorez.esudea.R;

import java.util.Objects;

public class GroupsFragment extends Fragment
{
    private static final String TAG = "GroupsFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;

    public BDGrupos helper;
    public SQLiteDatabase dbRead;
    public Cursor c;

    int veces = 0;

    GroupsActivity mGroupObject = new GroupsActivity();
    public String GC;
    public String GM;
    public String GH;
    public String GP;
    public String GS;

    public String[] GCEnviar;
    public String[] GMEnviar;
    public String[] GHEnviar;
    public String[] GPEnviar;
    public String[] GSEnviar;

    private EditText Selector;

    private static final String PREF_ID = "PREF_ID";
    private static final String EDITOR_FAC = "EDITOR_FAC";
    public SharedPreferences ID_PREF;
    private String ShR_fac;

    private enum LayoutManagerType
    {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected GroupsAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataGC;
    protected String[] mDataGH;
    protected String[] mDataGM;
    protected String[] mDataGP;
    protected String[] mDataGS;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ID_PREF = this.getActivity().getSharedPreferences(PREF_ID, Context.MODE_PRIVATE);
        android.util.Log.i(TAG, "onCreate: Preference " + ID_PREF);

        ShR_fac = ID_PREF.getString(EDITOR_FAC, "null");
        android.util.Log.i(TAG, "onCreate: Preference " + ShR_fac);

        helper = new BDGrupos(getActivity());
        dbRead = helper.getWritableDatabase();

        if(!ShR_fac.equals("null"))
        {
            GenerarGruposv();
            initDataset();
        }
        else
        {
            veces=0;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.groups_fragment_recycler, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null)
        {
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        mAdapter = new GroupsAdapter(mDataGC,mDataGH, mDataGM, mDataGP, mDataGS);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType)
    {
        int scrollPosition = 0;

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
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void GenerarGruposv()
    {
        GCEnviar = new String[60];
        GMEnviar = new String[60];
        GHEnviar = new String[60];
        GPEnviar = new String[60];
        GSEnviar = new String[60];

        String Busqueda = null;

        for (int i = 0; i < 60; i++)
        {
            GCEnviar[i] = "Inicializar" + i;
            GMEnviar[i] = "Inicializar" + i;
            GHEnviar[i] = "Inicializar" + i;
            GPEnviar[i] = "Inicializar" + i;
            GSEnviar[i] = "Inicializar" + i;
        }

        if(!ShR_fac.equals("null"))
        {
            Busqueda=ShR_fac;
        }

        String where = ContracGrupos.COLUMN_FACULTAD + "=?";
        String[] retur = new String[]{
                ContracGrupos.COLUMN_CODIGO,
                ContracGrupos.COLUMN_MATERIA,
                ContracGrupos.COLUMN_HORARIO,
                ContracGrupos.COLUMN_PROFESOR,
                ContracGrupos.COLUMN_SALON
        };

        c = dbRead.query(ContracGrupos.GRUPO_TABLE_NAME, retur, where, new String[]{Busqueda}, null, null, null);
        //c= dbRead.rawQuery("select nombre,contraseña  from Estudiantes where nombre='ju'", null);

        veces=0;
        while(c.moveToNext())
        {
            GC = c.getString(c.getColumnIndex(ContracGrupos.COLUMN_CODIGO));
            GM = c.getString(c.getColumnIndex(ContracGrupos.COLUMN_MATERIA));
            GH = c.getString(c.getColumnIndex(ContracGrupos.COLUMN_HORARIO));
            GP = c.getString(c.getColumnIndex(ContracGrupos.COLUMN_PROFESOR));
            GS = c.getString(c.getColumnIndex(ContracGrupos.COLUMN_SALON));

            GCEnviar[veces] = GC;
            GMEnviar[veces] = GM;
            GHEnviar[veces] = GH;
            GPEnviar[veces] = GP;
            GSEnviar[veces] = GS;
            veces++;
        }
        if(!c.moveToNext())
        {
            GC = "STOP";
            GM = "STOP";
            GH = "STOP";
            GP = "STOP";
            GS = "STOP";

            GCEnviar[veces] = GC;
            GMEnviar[veces] = GM;
            GHEnviar[veces] = GH;
            GPEnviar[veces] = GP;
            GSEnviar[veces] = GS;

            c.close();

            android.util.Log.i(TAG, "Veces    " + veces);
        }
        for (int i = 0; i < 7; i++)
        {
            //android.util.Log.i(TAG, "OOOOOOOOOO     " + GCEnviar[i] + "     " + i);
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initDataset()
    {
        mDataGC = new String[veces];
        mDataGH = new String[veces];
        mDataGM = new String[veces];
        mDataGP = new String[veces];
        mDataGS = new String[veces];
        for (int i = 0; i < DATASET_COUNT; i++)
        {
            if(Objects.equals(GCEnviar[i], "STOP"))
            {
                break;
            }
            mDataGC[i] = "Código: " + GCEnviar[i];
            mDataGH[i] = "Horario: " + GHEnviar[i];
            mDataGM[i] = "Curso: " + GMEnviar[i];
            mDataGP[i] = "Profesor: " + GPEnviar[i];
            mDataGS[i] = "Aula: " + GSEnviar[i];
        }
    }
}
