package com.nof.vamathcalculator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome_DependencySummaryDialogue_BirthDefect#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome_DependencySummaryDialogue_BirthDefect extends DialogFragment {
    private AlertDialog dialog;
    private View birth_defect_spina_bifida_form;
    private View birth_defect_other_form;

    private View birth_defect_spina_bifida_level_form;
    private View birth_defect_other_level_form;

    private Spinner birth_defect_type_spinner;
    private Spinner birth_defect_spina_other_spinner;
    private Spinner birth_defect_spina_bifida_level_spinner;
    private Spinner birth_defect_other_gender_spinner;
    private Spinner birth_defect_spina_bifida_veteran_status_spinner;
    
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHome_DependencySummaryDialogue_BirthDefect() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentHome_DependencySummaryDialogue_BirthDefect.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome_DependencySummaryDialogue_BirthDefect newInstance(String param1, String param2) {
        FragmentHome_DependencySummaryDialogue_BirthDefect fragment = new FragmentHome_DependencySummaryDialogue_BirthDefect();
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
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.fragment_home_dependency_summary_dialogue_birth_defect, null))
                // Add action buttons
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FragmentHome_DependencySummaryDialogue_BirthDefect.this.getDialog().cancel();
                    }
                });

        this.dialog = builder.create();


        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                birth_defect_spina_bifida_form = dialog.findViewById(R.id.birth_defect_spina_bifida_form);
                birth_defect_other_form = dialog.findViewById(R.id.birth_defect_other_form);

                birth_defect_spina_bifida_level_form = dialog.findViewById(R.id.birth_defect_spina_bifida_level_form);
                birth_defect_other_level_form = dialog.findViewById(R.id.birth_defect_other_level_form);

                birth_defect_type_spinner = dialog.findViewById(R.id.birth_defect_type_spinner);
                birth_defect_spina_other_spinner = dialog.findViewById(R.id.birth_defect_other_level);
                birth_defect_spina_bifida_level_spinner = dialog.findViewById(R.id.birth_defect_spina_bifida_level);
                birth_defect_other_gender_spinner = dialog.findViewById(R.id.birth_defect_other_gender);
                birth_defect_spina_bifida_veteran_status_spinner = dialog.findViewById(R.id.birth_defect_spina_bifida_veteran_status);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        birth_defect_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                new Handler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        switch(position) {
                                            case 0:
                                                //select none
                                                birth_defect_other_form.setVisibility(View.GONE);
                                                birth_defect_spina_bifida_form.setVisibility(View.GONE);
                                                break;
                                            case 1:
                                                // select Spina Bifida
                                                birth_defect_other_form.setVisibility(View.GONE);
                                                birth_defect_spina_bifida_form.setVisibility(View.VISIBLE);
                                                break;
                                            case 2:
                                                // select other
                                                birth_defect_spina_bifida_form.setVisibility(View.GONE);
                                                birth_defect_other_form.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                        birth_defect_spina_bifida_veteran_status_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                new Handler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        switch(position) {
                                            case 0:
                                                //select not veteran of korean or vietnam war
                                                birth_defect_spina_bifida_level_form.setVisibility(View.GONE);
                                                break;
                                            case 1:
                                                // select yes, veteran of korean or vietnam war
                                                birth_defect_spina_bifida_level_form.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                        birth_defect_other_gender_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                new Handler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        switch(position) {
                                            case 0:
                                                //select not veteran of korean or vietnam war
                                                birth_defect_other_level_form.setVisibility(View.GONE);
                                                break;
                                            case 1:
                                                // select yes, veteran of korean or vietnam war
                                                birth_defect_other_level_form.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });

                    }
                }).start();

                   ArrayAdapter<CharSequence> birth_defect_type_adapter = ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.birth_defect_types,
                        R.layout.fragment_home_spinner_depenency_list);
                birth_defect_type_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);
                birth_defect_type_spinner.setAdapter(birth_defect_type_adapter);

                ArrayAdapter<CharSequence> birth_defect_spina_bifida_veteran_status_adapter = ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.spinner_yes_no,
                        R.layout.fragment_home_spinner_depenency_list);
                birth_defect_spina_bifida_veteran_status_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);
                birth_defect_spina_bifida_veteran_status_spinner.setAdapter(birth_defect_spina_bifida_veteran_status_adapter);

                ArrayAdapter<CharSequence> birth_defect_other_gender_adapter = ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.spinner_yes_no,
                        R.layout.fragment_home_spinner_depenency_list);
                birth_defect_other_gender_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);
                birth_defect_other_gender_spinner.setAdapter(birth_defect_other_gender_adapter);

                ArrayAdapter<CharSequence> birth_defect_spina_bifida_level_adapter = ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.birth_defect_levels_spina_bifida,
                        R.layout.fragment_home_spinner_depenency_list);
                birth_defect_spina_bifida_level_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);
                birth_defect_spina_bifida_level_spinner.setAdapter(birth_defect_spina_bifida_level_adapter);

                ArrayAdapter<CharSequence> birth_defect_spina_other_adapter = ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.birth_defect_levels_other,
                        R.layout.fragment_home_spinner_depenency_list);
                birth_defect_spina_other_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);
                birth_defect_spina_other_spinner.setAdapter(birth_defect_spina_other_adapter);
            }
        });

        return this.dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("onViewCreated", "Test");
    }
}