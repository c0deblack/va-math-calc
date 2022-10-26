package com.nof.vamathcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import com.nof.vamathcalculator.databinding.FragmentHomeBinding;

public class FragmentHome extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();

        /**
         * Add the DependencyStatus child fragment to the nested FragmentLayout within the
         * fragment_home_scrolling.xml file.
         */
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.setReorderingAllowed(true);

        ft.replace(R.id.home_sticky_content, DependencySummaryFragment.class, null);
        ft.addToBackStack(null);


        DisabilitySummaryScrollFragment ratings_frag = new DisabilitySummaryScrollFragment();
        ft.replace(R.id.home_scroll_content, ratings_frag);
        ft.addToBackStack(null);

        CompensationSummaryFragment compensation_frag = new CompensationSummaryFragment();
        ft.replace(R.id.home_compensation_content, compensation_frag);
        ft.addToBackStack(null);

        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}