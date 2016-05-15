package com.davidelp17.arnolflorez.esudea.Universidad;

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import com.davidelp17.arnolflorez.esudea.R;

public class UniversityFragment extends Fragment {

    public static final String TAGUniversity = "FragmentUniversity";

    public UniversityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_university, container, false);
    }
}