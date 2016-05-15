package com.davidelp17.arnolflorez.esudea.Calendar;

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import com.davidelp17.arnolflorez.esudea.R;

public class CalendarFragment extends Fragment {

    public static final String TAGCalendar = "FragmentCalendar";

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar_one, container, false);
    }
}