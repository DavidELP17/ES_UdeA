package com.davidelp17.arnolflorez.esudea.Gallery.PreGallery;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.davidelp17.arnolflorez.esudea.Gallery.PreGallery.Logger.Log;
import com.davidelp17.arnolflorez.esudea.R;

public class PreGalleryAdaptador extends RecyclerView.Adapter<PreGalleryAdaptador.ViewHolder> {
    private static final String TAG = "PreGalleryAdaptador";

    int posicion;

    private String[] mDataSet;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getPosition();
                    Log.d(TAG, "Element " + i + " clicked.");

                    Intent GalleryActivity = new Intent(v.getContext(), com.davidelp17.arnolflorez.esudea.Gallery.GalleryActivity.class);

                    if(i == 0)
                    {
                        GalleryActivity.putExtra("FOTO", "Esculturas");
                    }
                    if(i == 1)
                    {
                        GalleryActivity.putExtra("FOTO", "Bloques");
                    }
                    if(i == 2)
                    {
                        GalleryActivity.putExtra("FOTO", "Sitios Celebres");
                    }
                    if(i == 3)
                    {
                        GalleryActivity.putExtra("FOTO", "Porterias");
                    }
                    if(i == 4)
                    {
                        GalleryActivity.putExtra("FOTO", "Espacios Deportivos");
                    }


                    v.getContext().startActivity(GalleryActivity);
                }
            });
            textView = (TextView) v.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public PreGalleryAdaptador(String[] dataSet) {
        mDataSet = dataSet;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.pregallery_item, viewGroup, false);

        final RelativeLayout RelativeLayout;
        RelativeLayout = (RelativeLayout) v.findViewById(R.id.fotopregallery);

        if(posicion==0)
        {
            Log.d(TAG, "Drawable " + posicion);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                RelativeLayout.setBackground(v.getResources().getDrawable(R.drawable.fotos7));
            }
        }
        if(posicion==1)
        {
            Log.d(TAG, "Drawable " + posicion);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                RelativeLayout.setBackground(v.getResources().getDrawable(R.drawable.universidad_3));
            }
        }
        if(posicion==2)
        {
            Log.d(TAG, "Drawable " + posicion);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                RelativeLayout.setBackground(v.getResources().getDrawable(R.drawable.fotos26));
            }
        }
        if(posicion==3)
        {
            Log.d(TAG, "Drawable " + posicion);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                RelativeLayout.setBackground(v.getResources().getDrawable(R.drawable.fotos24));
            }
        }
        if(posicion==4)
        {
            Log.d(TAG, "Drawable " + posicion);
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
    public void onBindViewHolder(ViewHolder viewHolder, final int position)
    {
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
        viewHolder.getTextView().setText(mDataSet[position]);
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
