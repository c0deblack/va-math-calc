/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.nof.vamathcalculator.db.User;
import com.nof.vamathcalculator.viewmodel.VAMathViewModel;

import com.nof.vamathcalculator.databinding.FragmentHomeCompensationSummaryContainerBinding;

public class FragmentHome_CompensationSummaryFragment extends Fragment {

    private VAMathViewModel data;
    private FragmentHomeCompensationSummaryContainerBinding binding;

    public FragmentHome_CompensationSummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ViewModelProvider(requireActivity()).get(VAMathViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeCompensationSummaryContainerBinding
                .inflate(inflater, container, false);

        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home_compensation_summary_container, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data.get_user().observe(requireActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}