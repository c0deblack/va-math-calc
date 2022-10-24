package com.nof.vamathcalculator;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
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

        /**
         * Add the DependencyStatus child fragment to the nested FragmentLayout within the
         * fragment_home_scrolling.xml file.
         */
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();

        DependencySummaryFragment depend_frag = new DependencySummaryFragment();
        ft.replace(R.id.home_sticky_content, depend_frag);
        ft.addToBackStack(null);


        DisabilitySummaryScrollFragment ratings_frag = new DisabilitySummaryScrollFragment();
        ft.replace(R.id.home_scroll_content, ratings_frag);
        ft.addToBackStack(null);

        CompensationSummaryFragment compensation_frag = new CompensationSummaryFragment();
        ft.replace(R.id.home_compensation_content, compensation_frag);
        ft.addToBackStack(null);

        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        /**
         * Inflate the fragment_home_scrolling.xml layout file
         */
        return inflater.inflate(R.layout.fragment_home_scrolling, container, false);
    }
}