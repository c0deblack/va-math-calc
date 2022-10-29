package com.nof.vamathcalculator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class FragmentHome_DependencySummaryFragment_ScrollForm extends Fragment {

    public FragmentHome_DependencySummaryFragment_ScrollForm() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_dependency_summary_fragment_content_scroll_form, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
            Create hyperlinks for certain TextViews
         */
        VAUtils.LinkTextInViewFromResource(
                getActivity(),
                view.findViewById(R.id.fragment_dependency_summary_description_text),
                R.string.dependency_summary_description_text,
                R.string.dependency_summary_description_text_to_link,
                R.string.dependency_summary_description_text_url
        );

        VAUtils.LinkTextInViewFromResource(
                getActivity(),
                view.findViewById(R.id.fragment_dependency_summary_child_education_description),
                R.string.dependency_summary_child_education_description,
                R.string.dependency_summary_child_education_description_text_to_link,
                R.string.dependency_summary_child_education_description_url
        );

        VAUtils.LinkTextInViewFromResource(
                getActivity(),
                view.findViewById(R.id.fragment_dependency_summary_birth_defect),
                R.string.dependency_summary_disabled_child_description,
                R.string.dependency_summary_disabled_child_description_text_to_link,
                R.string.dependency_summary_disabled_child_description_url
        );

        VAUtils.LinkTextInViewFromResource(
                getActivity(),
                view.findViewById(R.id.fragment_dependency_summary_child_aid_description),
                R.string.dependency_summary_child_aid_description,
                R.string.dependency_summary_child_aid_description_text_to_link,
                R.string.dependency_summary_child_aid_description_url
        );


        Spinner married_spinner = (Spinner) view.findViewById(R.id.fragment_dependency_summary_married_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> married_spinner_adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.spinner_yes_no,
                R.layout.spinner_depenency_list);
        // Specify the layout to use when the list of choices appears
        married_spinner_adapter.setDropDownViewResource(R.layout.spinner_dropdown_dependency_list);
        // Apply the adapter to the spinner
        married_spinner.setAdapter(married_spinner_adapter);


        Spinner child_spinner = (Spinner) view.findViewById(R.id.fragment_dependency_summary_child_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> child_spinner_adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.children_spinner_values,
                R.layout.spinner_depenency_list);
        // Specify the layout to use when the list of choices appears
        child_spinner_adapter.setDropDownViewResource(R.layout.spinner_dropdown_dependency_list);
        // Apply the adapter to the spinner
        child_spinner.setAdapter(child_spinner_adapter);


        Spinner education_spinner = (Spinner) view.findViewById(R.id.fragment_dependency_summary_education_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> education_spinner_adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.education_spinner_values,
                R.layout.spinner_depenency_list);
        // Specify the layout to use when the list of choices appears
        education_spinner_adapter.setDropDownViewResource(R.layout.spinner_dropdown_dependency_list);
        // Apply the adapter to the spinner
        education_spinner.setAdapter(education_spinner_adapter);


        Spinner parent_spinner = (Spinner) view.findViewById(R.id.fragment_dependency_summary_parents_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> parent_spinner_adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.parent_spinner_values,
                R.layout.spinner_depenency_list);
        // Specify the layout to use when the list of choices appears
        parent_spinner_adapter.setDropDownViewResource(R.layout.spinner_dropdown_dependency_list);
        // Apply the adapter to the spinner
        parent_spinner.setAdapter(parent_spinner_adapter);


        Spinner daily_aid_spinner = (Spinner) view.findViewById(R.id.fragment_dependency_summary_dailyaid_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> dailyaid_spinner_adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.spinner_yes_no,
                R.layout.spinner_depenency_list);
        // Specify the layout to use when the list of choices appears
        dailyaid_spinner_adapter.setDropDownViewResource(R.layout.spinner_dropdown_dependency_list);
        // Apply the adapter to the spinner
        daily_aid_spinner.setAdapter(dailyaid_spinner_adapter);


    }
}