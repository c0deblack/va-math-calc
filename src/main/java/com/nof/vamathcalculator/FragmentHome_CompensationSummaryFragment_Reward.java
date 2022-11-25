/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nof.vamathcalculator.db.DependStatus;
import com.nof.vamathcalculator.db.Disability;
import com.nof.vamathcalculator.viewmodel.VAMathViewModel;

import java.util.List;

public class FragmentHome_CompensationSummaryFragment_Reward extends Fragment {
    private VAMathViewModel data;
    private TextView reward_text;
    public FragmentHome_CompensationSummaryFragment_Reward() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_compensation_summary_content_reward, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reward_text = view.findViewById(R.id.fragment_compensation_summary_reward_text);
        double compensation = data.getFullCompensation();
        if(compensation > 0.0) {
            reward_text.setText(String.format("$ %.2f", compensation));
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        data.get_disabilities().observe(requireActivity(), new Observer<List<Disability>>() {
            @Override
            public void onChanged(List<Disability> disability_list) {
                //VAUtils.GetCombinedRating(disability_list, false);
                //double rating = data.get_combined_rating();
                double compensation = data.getFullCompensation();
                //Log.e("Reward", "Reward is: " + compensation);
                reward_text.setText(String.format("$ %.2f", compensation));
            }
        });
    }
}