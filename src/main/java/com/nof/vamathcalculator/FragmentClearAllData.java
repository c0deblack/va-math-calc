package com.nof.vamathcalculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nof.vamathcalculator.viewmodel.VAMathViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentClearAllData#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentClearAllData extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private VAMathViewModel data;

    public FragmentClearAllData() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentClearAllData.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentClearAllData newInstance(String param1, String param2) {
        FragmentClearAllData fragment = new FragmentClearAllData();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clear_all_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // get reference to the button using either the binding or findByView API
        Button btn = getView().findViewById(R.id.btn_clear_data);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                data = new ViewModelProvider(requireActivity()).get(VAMathViewModel.class);
                data.delete_all_user_records();
            }
        });
    }
}