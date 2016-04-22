package com.davidelp17.arnolflorez.esudea;

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

public class GruposFragment extends Fragment {

    public static final String TAGGrupos = "FragmentGrupos";

    public GruposFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grupos, container, false);
    }
}