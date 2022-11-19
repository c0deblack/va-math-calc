package com.nof.vamathcalculator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.nof.vamathcalculator.db.BirthDefectChild;
import com.nof.vamathcalculator.db.User;
import com.nof.vamathcalculator.model.VAMathDialogAction;
import com.nof.vamathcalculator.viewmodel.VAMathViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome_DependencySummaryDialog_BirthDefect#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome_DependencySummaryDialog_BirthDefect extends DialogFragment {
    VAMathViewModel data;
    private AlertDialog dialog;
    private View birth_defect_spina_bifida_form;
    private View birth_defect_other_form;

    private View birth_defect_spina_bifida_level_form;
    private View birth_defect_other_level_form;

    private Spinner birth_defect_type_spinner;
    private Spinner birth_defect_other_level_spinner;
    private Spinner birth_defect_spina_bifida_level_spinner;
    private Spinner birth_defect_other_gender_spinner;
    private Spinner birth_defect_spina_bifida_veteran_status_spinner;

    private EditText short_desc;
    private Button save_button;
    
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ACTION_KEY = "ACTION";
    private static final String ID_KEY = "ID";

    // TODO: Rename and change types of parameters
    private VAMathDialogAction mACTION;
    private int mID;

    public FragmentHome_DependencySummaryDialog_BirthDefect() {
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
    public static FragmentHome_DependencySummaryDialog_BirthDefect newInstance(VAMathDialogAction param1, int param2) {
        FragmentHome_DependencySummaryDialog_BirthDefect fragment = new FragmentHome_DependencySummaryDialog_BirthDefect();
        Bundle args = new Bundle();
        args.putSerializable(ACTION_KEY, param1);
        args.putInt(ID_KEY, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mACTION = (VAMathDialogAction)getArguments().getSerializable(ACTION_KEY);
            mID = getArguments().getInt(ID_KEY);
        }
        data = new ViewModelProvider(requireActivity()).get(VAMathViewModel.class);
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
                        BirthDefectChild new_bd_child = new BirthDefectChild();
                        if(birth_defect_type_spinner.getSelectedItemPosition() == 1
                        && birth_defect_spina_bifida_veteran_status_spinner.getSelectedItemPosition() == 1){
                            new_bd_child.is_spinafida = true;
                            new_bd_child.level = birth_defect_spina_bifida_level_spinner.getSelectedItemPosition() + 1;
                        } else if (birth_defect_type_spinner.getSelectedItemPosition() == 2
                        && birth_defect_other_gender_spinner.getSelectedItemPosition() == 1) {
                            new_bd_child.is_spinafida = false;
                            new_bd_child.level = birth_defect_other_level_spinner.getSelectedItemPosition() + 1;
                        }

                        new_bd_child.short_name = short_desc.getText().toString();

                        if(mACTION == VAMathDialogAction.CREATE){
                            data.insert_birth_defect(new_bd_child);
                            User user = data.getUserRecord();
                            if(user.num_child_birth_defect == null) {
                                user.num_child_birth_defect = 1;
                            } else {
                                user.num_child_birth_defect++;
                            }
                            data.update_user(user);
                        } else {
                            new_bd_child._id = mID;
                            data.update_child_with_birth_defect(new_bd_child);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FragmentHome_DependencySummaryDialog_BirthDefect.this.getDialog().cancel();
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

                birth_defect_spina_bifida_level_spinner = dialog.findViewById(R.id.birth_defect_spina_bifida_level);
                birth_defect_spina_bifida_veteran_status_spinner = dialog.findViewById(R.id.birth_defect_spina_bifida_veteran_status);

                birth_defect_other_level_spinner = dialog.findViewById(R.id.birth_defect_other_level);
                birth_defect_other_gender_spinner = dialog.findViewById(R.id.birth_defect_other_gender);

                short_desc = dialog.findViewById(R.id.birth_defect_dialog_short_desc);
                save_button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                save_button.setEnabled(false);

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        ArrayAdapter<CharSequence> birth_defect_type_adapter = ArrayAdapter.createFromResource(
                                getContext(),
                                R.array.birth_defect_types,
                                R.layout.fragment_home_spinner_depenency_list);
                        birth_defect_type_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);

                        ArrayAdapter<CharSequence> birth_defect_spina_bifida_veteran_status_adapter = ArrayAdapter.createFromResource(
                                getContext(),
                                R.array.spinner_yes_no,
                                R.layout.fragment_home_spinner_depenency_list);
                        birth_defect_spina_bifida_veteran_status_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);


                        ArrayAdapter<CharSequence> birth_defect_other_gender_adapter = ArrayAdapter.createFromResource(
                                getContext(),
                                R.array.spinner_yes_no,
                                R.layout.fragment_home_spinner_depenency_list);
                        birth_defect_other_gender_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);

                        ArrayAdapter<CharSequence> birth_defect_spina_bifida_level_adapter = ArrayAdapter.createFromResource(
                                getContext(),
                                R.array.birth_defect_levels_spina_bifida,
                                R.layout.fragment_home_spinner_depenency_list);
                        birth_defect_spina_bifida_level_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);

                        ArrayAdapter<CharSequence> birth_defect_spina_other_adapter = ArrayAdapter.createFromResource(
                                getContext(),
                                R.array.birth_defect_levels_other,
                                R.layout.fragment_home_spinner_depenency_list);
                        birth_defect_spina_other_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);

                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                birth_defect_other_level_spinner.setAdapter(birth_defect_spina_other_adapter);
                                birth_defect_spina_bifida_level_spinner.setAdapter(birth_defect_spina_bifida_level_adapter);
                                birth_defect_other_gender_spinner.setAdapter(birth_defect_other_gender_adapter);
                                birth_defect_spina_bifida_veteran_status_spinner.setAdapter(birth_defect_spina_bifida_veteran_status_adapter);
                                birth_defect_type_spinner.setAdapter(birth_defect_type_adapter);
                            }
                        });

                        short_desc.addTextChangedListener(new VAUtils.TextChangedListener<EditText>(short_desc) {
                            @Override
                            public void onTextChanged(EditText target, Editable s) {
                                set_save_button_state(s);
                            }
                        });

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
                                        set_save_button_state(short_desc.getEditableText());
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
                                        set_save_button_state(short_desc.getEditableText());
                                    }
                                });
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });

                        if(mACTION == VAMathDialogAction.EDIT){
                            requireActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    save_button.setEnabled(true);
                                }
                            });

                            BirthDefectChild bd_child = data.get_defect_from_id(mID);
                            requireActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    short_desc.setText(bd_child.short_name);
                                    if(bd_child.is_spinafida) {
                                        birth_defect_type_spinner.setSelection(1);
                                        birth_defect_spina_bifida_veteran_status_spinner
                                                .setSelection(1);
                                        birth_defect_spina_bifida_level_spinner
                                                .setSelection(bd_child.level - 1);
                                    } else {
                                        birth_defect_type_spinner.setSelection(2);
                                        birth_defect_other_gender_spinner.setSelection(1);
                                        birth_defect_other_level_spinner
                                                .setSelection(bd_child.level - 1);
                                    }
                                }
                            });
                        }
                    }
                }).start();

            }
        });

        return this.dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void set_save_button_state(Editable s){
        if(s.length() >= 3
                && birth_defect_type_spinner.getSelectedItemPosition() > 0
                && (birth_defect_spina_bifida_veteran_status_spinner.getSelectedItemPosition() == 1
                || birth_defect_other_gender_spinner.getSelectedItemPosition() == 1)){
            if(!save_button.isEnabled()) save_button.setEnabled(true);
        } else if (save_button.isEnabled()){
            if(save_button.isEnabled()) save_button.setEnabled(false);
        }
    }
}