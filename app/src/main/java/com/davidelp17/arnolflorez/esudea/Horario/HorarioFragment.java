package com.davidelp17.arnolflorez.esudea.Horario;

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import com.davidelp17.arnolflorez.esudea.R;

public class HorarioFragment extends Fragment {

    public static final String TAGHorario = "FragmentHorario";

    public HorarioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_horario, container, false);
    }
}