package com.nof.vamathcalculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentResources#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentResources extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentResources() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentReferences.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentResources newInstance(String param1, String param2) {
        FragmentResources fragment = new FragmentResources();
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
        return inflater.inflate(R.layout.fragment_resources, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
            Create hyperlinks for certain TextViews
         */
        VAUtils.LinkTextInViewFromResource(
                requireActivity(),
                view.findViewById(R.id.message2),
                R.string.fileClaim_howTo_text_description,
                R.string.fileClaim_howTo_text_to_link,
                R.string.fileClaim_howTo_text_url
        );

        VAUtils.LinkTextInViewFromResource(
                requireActivity(),
                view.findViewById(R.id.message3),
                R.string.fileClaim_fileOnline_text_description,
                R.string.fileClaim_fileOnline_text_to_link,
                R.string.fileClaim_fileOnline_text_url
        );

        VAUtils.LinkTextInViewFromResource(
                requireActivity(),
                view.findViewById(R.id.message4),
                R.string.fileClaim_claimStatus_text_description,
                R.string.fileClaim_claimStatus_text_to_link,
                R.string.fileClaim_claimStatus_text_url
        );

        VAUtils.LinkTextInViewFromResource(
                requireActivity(),
                view.findViewById(R.id.message7),
                R.string.additionalResources_VA_text_description,
                R.string.additionalResources_VA_text_to_link,
                R.string.additionalResources_VA_text_url
        );
        VAUtils.LinkTextInViewFromResource(
                requireActivity(),
                view.findViewById(R.id.message9),
                R.string.additionalResources_stateVA_text_description,
                R.string.additionalResources_stateVA_text_to_link,
                R.string.additionalResources_stateVA_text_url
        );

        VAUtils.LinkTextInViewFromResource(
                requireActivity(),
                view.findViewById(R.id.message10),
                R.string.additionalResources_DAV_text_description,
                R.string.additionalResources_DAV_text_to_link,
                R.string.additionalResources_DAV_text_url
        );

        VAUtils.LinkTextInViewFromResource(
                requireActivity(),
                view.findViewById(R.id.message11),
                R.string.additionalResources_lifeline_text_description,
                R.string.additionalResources_lifeline_text_to_link,
                R.string.additionalResources_lifeline_text_url
        );

        VAUtils.LinkTextInViewFromResource(
                requireActivity(),
                view.findViewById(R.id.message12),
                R.string.additionalResources_suicideLifeline_text_description,
                R.string.additionalResources_suicideLifeline_text_to_link,
                R.string.additionalResources_suicideLifeline_text_url
        );
    }
}