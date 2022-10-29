package com.nof.vamathcalculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentHome_CompensationSummaryFragment_Description extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_compensation_summary_content_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView description_text_view = view.findViewById(R.id.fragment_home_compensation_summary_content_disclaimer);
        String description_text = getString(R.string.fragment_home_compensation_summary_content_disclaimer_text);
        String description_text_url = getString(R.string.fragment_home_compensation_summary_content_disclaimer_text_url);
        VAUtils.setHyperLinkInTextView(
                description_text_view,
                description_text,
                "www.va.gov",
                description_text_url
        );
    }
}