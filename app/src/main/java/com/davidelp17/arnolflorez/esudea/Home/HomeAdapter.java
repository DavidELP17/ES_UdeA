package com.davidelp17.arnolflorez.esudea.Home;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.davidelp17.arnolflorez.esudea.Home.Logger.Log;
import com.davidelp17.arnolflorez.esudea.R;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>
{
    private static final String TAG = "HomeAdapter";

    private String[] mDataGC;
    private String[] mDataGH;
    private String[] mDataGM;
    private String[] mDataGS;

    int posicion;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView GC;
        private final TextView GM;
        private final TextView GH;
        private final TextView GS;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getPosition() + " clicked.");
                    Intent EventsActivity = new Intent(v.getContext(), com.davidelp17.arnolflorez.esudea.Events.EventsActivity.class);
                    v.getContext().startActivity(EventsActivity);
                }
            });
            GC = (TextView) v.findViewById(R.id.textView);
            GH = (TextView) v.findViewById(R.id.textView_hora);
            GM = (TextView) v.findViewById(R.id.textView_lugar);
            GS = (TextView) v.findViewById(R.id.textView_fecha);
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
        public TextView getTextViewS()
        {
            return GS;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataGC String[] containing the data to populate views to be used by RecyclerView.
     */
    public HomeAdapter(String[] dataGC, String[] dataGH, String[] dataGM, String[] dataGS) {
        mDataGC = dataGC;
        mDataGH = dataGH;
        mDataGM = dataGM;
        mDataGS = dataGS;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.home_item, viewGroup, false);

        final ImageView RelativeLayout;
        RelativeLayout = (ImageView) v.findViewById(R.id.imagenhome);

        if(posicion==0)
        {
            com.davidelp17.arnolflorez.esudea.Gallery.PreGallery.Logger.Log.d(TAG, "Drawable " + posicion);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                RelativeLayout.setBackground(v.getResources().getDrawable(R.drawable.unidades17));
            }
        }
        if(posicion==1)
        {
            com.davidelp17.arnolflorez.esudea.Gallery.PreGallery.Logger.Log.d(TAG, "Drawable " + posicion);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                RelativeLayout.setBackground(v.getResources().getDrawable(R.drawable.unidades12));
            }
        }
        if(posicion==2)
        {
            com.davidelp17.arnolflorez.esudea.Gallery.PreGallery.Logger.Log.d(TAG, "Drawable " + posicion);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                RelativeLayout.setBackground(v.getResources().getDrawable(R.drawable.fotos27));
            }
        }
        if(posicion==3)
        {
            com.davidelp17.arnolflorez.esudea.Gallery.PreGallery.Logger.Log.d(TAG, "Drawable " + posicion);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                RelativeLayout.setBackground(v.getResources().getDrawable(R.drawable.universidad_3));
            }
        }
        if(posicion==4)
        {
            com.davidelp17.arnolflorez.esudea.Gallery.PreGallery.Logger.Log.d(TAG, "Drawable " + posicion);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                RelativeLayout.setBackground(v.getResources().getDrawable(R.drawable.fotos38));
            }
        }

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        if(position==0)
        {
            posicion=1;
        }
        if(position==1)
        {
            posicion=2;
        }
        if(position==2)
        {
            posicion=3;
        }
        if(position==3)
        {
            posicion=4;
        }
        if(position==4)
        {
            posicion=5;
        }
        if(position==5)
        {
            posicion=6;
        }

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getTextViewC().setText(mDataGC[position]);
        viewHolder.getTextViewH().setText(mDataGH[position]);
        viewHolder.getTextViewM().setText(mDataGM[position]);
        viewHolder.getTextViewS().setText(mDataGS[position]);
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataGC.length;
    }
}
