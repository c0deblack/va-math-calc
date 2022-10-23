package com.nof.vamathcalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * This fragment loads the Home fragment layout. It is bear now, but it will soon have references
 * to all of the views that will be present on the Home Page.
 */
public class HomeScrollingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_scrolling, container, false);
    }
}