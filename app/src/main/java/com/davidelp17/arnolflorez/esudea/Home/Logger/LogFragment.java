package com.davidelp17.arnolflorez.esudea.Home.Logger;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

public class LogFragment extends Fragment
{
    private com.davidelp17.arnolflorez.esudea.Home.Logger.LogView mLogView;
    private ScrollView mScrollView;

    public LogFragment() {}

    public View inflateViews() {
        mScrollView = new ScrollView(getActivity());
        ViewGroup.LayoutParams scrollParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mScrollView.setLayoutParams(scrollParams);

        mLogView = new LogView(getActivity());
        ViewGroup.LayoutParams logParams = new ViewGroup.LayoutParams(scrollParams);
        logParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        mLogView.setLayoutParams(logParams);
        mLogView.setClickable(true);
        mLogView.setFocusable(true);
        mLogView.setTypeface(Typeface.MONOSPACE);

        // Want to set padding as 16 dips, setPadding takes pixels.  Hooray math!
        int paddingDips = 16;
        double scale = getResources().getDisplayMetrics().density;
        int paddingPixels = (int) ((paddingDips * (scale)) + .5);
        mLogView.setPadding(paddingPixels, paddingPixels, paddingPixels, paddingPixels);
        mLogView.setCompoundDrawablePadding(paddingPixels);

        mLogView.setGravity(Gravity.BOTTOM);
        mLogView.setTextAppearance(getActivity(), android.R.style.TextAppearance_Holo_Medium);

        mScrollView.addView(mLogView);
        return mScrollView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View result = inflateViews();

        mLogView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
        return result;
    }

    public LogView getLogView() {
        return mLogView;
    }
}