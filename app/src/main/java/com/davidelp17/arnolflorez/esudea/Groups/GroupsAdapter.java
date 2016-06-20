package com.davidelp17.arnolflorez.esudea.Groups;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.davidelp17.arnolflorez.esudea.DataBase.BDGrupos;
import com.davidelp17.arnolflorez.esudea.Groups.Logger.Log;
import com.davidelp17.arnolflorez.esudea.R;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {
    private static final String TAG = "GroupsAdapter";

    private String[] mDataGC;
    private String[] mDataGH;
    private String[] mDataGM;
    private String[] mDataGP;
    private String[] mDataGS;

    public BDGrupos helper;
    public SQLiteDatabase dbRead;
    public Cursor c;

    private EditText Selector;

    private static final String PREF_ID = "PREF_ID";
    private static final String EDITOR_FAC = "EDITOR_FAC";
    public SharedPreferences ID_PREF;
    private String ShR_fac;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView GC;
        private final TextView GM;
        private final TextView GH;
        private final TextView GP;
        private final TextView GS;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getPosition() + " clicked.");
                }
            });
            GC = (TextView) v.findViewById(R.id.groupC);
            GH = (TextView) v.findViewById(R.id.groupH);
            GM = (TextView) v.findViewById(R.id.groupM);
            GP = (TextView) v.findViewById(R.id.groupP);
            GS = (TextView) v.findViewById(R.id.groupS);
        }

        public TextView getTextViewC()
        {
            return GC;
        }
        public TextView getTextViewH()
        {
            return GH;
        }
        public TextView getTextViewM()
        {
            return GM;
        }
        public TextView getTextViewP()
        {
            return GP;
        }
        public TextView getTextViewS()
        {
            return GS;
        }
    }

    public GroupsAdapter(String[] dataGC, String[] dataGH, String[] dataGM, String[] dataGP, String[] dataGS) {
        mDataGC = dataGC;
        mDataGH = dataGH;
        mDataGM = dataGM;
        mDataGP = dataGP;
        mDataGS = dataGS;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.groups_item, viewGroup, false);



        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        viewHolder.getTextViewC().setText(mDataGC[position]);
        viewHolder.getTextViewH().setText(mDataGH[position]);
        viewHolder.getTextViewM().setText(mDataGM[position]);
        viewHolder.getTextViewP().setText(mDataGP[position]);
        viewHolder.getTextViewS().setText(mDataGS[position]);
    }
    @Override
    public int getItemCount() {
        return mDataGC.length;
    }
}
