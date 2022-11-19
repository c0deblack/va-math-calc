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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.nof.vamathcalculator.db.Disability;
import com.nof.vamathcalculator.db.User;
import com.nof.vamathcalculator.db.VAColumns;
import com.nof.vamathcalculator.model.VAMathDialogAction;
import com.nof.vamathcalculator.viewmodel.VAMathViewModel;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome_DisabilitySummaryDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome_DisabilitySummaryDialog extends DialogFragment {


    private AlertDialog dialog;
    VAMathViewModel data;

    private TextView dialog_title;
    private EditText short_desc;
    private Spinner disability_type;
    private Spinner disability_rating;
    private Spinner disability_bilateral;
    private Spinner disability_rating_smc;
    private Button save_button;
    private View basic_rating_block;
    private View smc_rating_block;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ACTION_KEY = "ACTION";
    private static final String ID_KEY = "ID";

    // TODO: Rename and change types of parameters
    private VAMathDialogAction mACTION;
    private int mID;

    public FragmentHome_DisabilitySummaryDialog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param action Specify if whether a disability is being added or edited
     * @return A new instance of fragment FragmentHome_DisabilitySummaryDialogue.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHome_DisabilitySummaryDialog newInstance(VAMathDialogAction action, int id) {
        FragmentHome_DisabilitySummaryDialog fragment = new FragmentHome_DisabilitySummaryDialog();
        Bundle args = new Bundle();
        args.putSerializable(ACTION_KEY, action);
        args.putInt(ID_KEY, id);
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

/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_disability_summary_dialogue, container, false);
    }
*/
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.fragment_home_disability_summary_dialogue, null))
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //TODO: implement save
                        Disability disability;
                        if(mACTION == VAMathDialogAction.EDIT) {
                            disability = data.get_disability_from_id(mID);
                        } else {
                            disability = new Disability();
                        }

                        User user = data.getUserRecord();
                        disability.short_name = short_desc.getText().toString();
                        disability.is_basic = disability_type.getSelectedItemPosition() == 0;
                        if(!disability.is_basic){
                            disability.smc_rating = new Object(){
                                @Override
                                public String toString(){
                                    String ret_string = VAColumns.SMCColumns.SMC_L;
                                    switch(disability_rating_smc.getSelectedItemPosition()){
                                        case 0:
                                            ret_string = VAColumns.SMCColumns.SMC_L;
                                            break;
                                        case 1:
                                            ret_string =  VAColumns.SMCColumns.SMC_L_1_2;
                                            break;
                                        case 2:
                                            ret_string =  VAColumns.SMCColumns.SMC_M;
                                            break;
                                        case 3:
                                            ret_string =  VAColumns.SMCColumns.SMC_M_1_2;
                                            break;
                                        case 4:
                                            ret_string =  VAColumns.SMCColumns.SMC_N;
                                            break;
                                        case 5:
                                            ret_string =  VAColumns.SMCColumns.SMC_N_1_2;
                                            break;
                                        case 6:
                                            ret_string =  VAColumns.SMCColumns.SMC_O_P;
                                            break;
                                        case 7:
                                            ret_string =  VAColumns.SMCColumns.SMC_R_1;
                                            break;
                                        case 8:
                                            ret_string =  VAColumns.SMCColumns.SMC_R_2;
                                            break;
                                        case 9:
                                            ret_string =  VAColumns.SMCColumns.SMC_S;
                                    }
                                    return ret_string;
                                }
                            }.toString();
                            disability.rating = 100000d;
                            disability.is_bilateral = false;
                            user.smc_rating = disability.smc_rating;
                            user.has_smc = true;
                            data.update_user(user);
                        } else {
                            disability.smc_rating = null;
                            disability.rating = (disability_rating.getSelectedItemPosition() + 1) * 10.0;
                            disability.is_bilateral = disability_bilateral.getSelectedItemPosition() == 1;
                        }

                        if(mACTION == VAMathDialogAction.EDIT) {
                            data.update_disability(disability);
                        } else {
                            data.insert_disability(disability);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Objects.requireNonNull(FragmentHome_DisabilitySummaryDialog.this.getDialog())
                                .cancel();
                    }
                });
        this.dialog = builder.create();
        dialog_title = dialog.findViewById(R.id.disability_dialogue_title);
        short_desc = dialog.findViewById(R.id.disability_dialog_short_desc);
        disability_type = dialog.findViewById(R.id.disability_dialogue_rating_type_spinner);
        disability_rating = dialog.findViewById(R.id.disability_dialogue_rating_spinner);
        disability_bilateral = dialog.findViewById(R.id.disability_dialogue_bilateral_spinner);
        disability_rating_smc = dialog.findViewById(R.id.disability_dialogue_smc_rating_spinner);
        basic_rating_block = dialog.findViewById(R.id.disability_dialogue_basic_block);
        smc_rating_block = dialog.findViewById(R.id.disability_dialogue_smc_block);
        save_button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dialog_title = dialog.findViewById(R.id.disability_dialogue_title);
                        short_desc = dialog.findViewById(R.id.disability_dialog_short_desc);
                        disability_type = dialog.findViewById(R.id.disability_dialogue_rating_type_spinner);
                        disability_rating = dialog.findViewById(R.id.disability_dialogue_rating_spinner);
                        disability_bilateral = dialog.findViewById(R.id.disability_dialogue_bilateral_spinner);
                        disability_rating_smc = dialog.findViewById(R.id.disability_dialogue_smc_rating_spinner);
                        basic_rating_block = dialog.findViewById(R.id.disability_dialogue_basic_block);
                        smc_rating_block = dialog.findViewById(R.id.disability_dialogue_smc_block);
                        save_button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);

                        ArrayAdapter<CharSequence> disability_type_adapter = ArrayAdapter.createFromResource(
                                getContext(),
                                R.array.disability_type,
                                R.layout.fragment_home_spinner_depenency_list);
                        disability_type_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);

                        ArrayAdapter<CharSequence> disability_rating_adapter = ArrayAdapter.createFromResource(
                                getContext(),
                                R.array.disability_ratings,
                                R.layout.fragment_home_spinner_depenency_list);
                        disability_rating_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);


                        ArrayAdapter<CharSequence> disability_bilateral_adapter = ArrayAdapter.createFromResource(
                                getContext(),
                                R.array.spinner_yes_no,
                                R.layout.fragment_home_spinner_depenency_list);
                        disability_bilateral_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);

                        ArrayAdapter<CharSequence> disability_rating_smc_adapter = ArrayAdapter.createFromResource(
                                getContext(),
                                R.array.smc_ratings,
                                R.layout.fragment_home_spinner_depenency_list);
                        disability_rating_smc_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);

                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                disability_rating_smc.setAdapter(disability_rating_smc_adapter);
                                disability_bilateral.setAdapter(disability_bilateral_adapter);
                                disability_rating.setAdapter(disability_rating_adapter);
                                disability_type.setAdapter(disability_type_adapter);
                            }
                        });

                        disability_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Handler handler = new Handler();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        switch(position) {
                                            case 0:
                                                smc_rating_block.setVisibility(View.GONE);
                                                basic_rating_block.setVisibility(View.VISIBLE);
                                                break;
                                            case 1:
                                                basic_rating_block.setVisibility(View.GONE);
                                                smc_rating_block.setVisibility(View.VISIBLE);
                                        }
                                    }
                                });
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(mACTION == VAMathDialogAction.EDIT){
                                    save_button.setEnabled(true);
                                    Disability disability = data.get_disability_from_id(mID);
                                    dialog_title.setText("Edit Disability");
                                    short_desc.setText(disability.short_name);
                                    disability_type.setSelection(disability.is_basic ? 0 : 1);
                                    if(!disability.is_basic){
                                        disability_rating_smc.setSelection(new Object(){
                                            public int toInt(){
                                                int ret_position = 0;
                                                switch(disability.smc_rating){
                                                    case VAColumns.SMCColumns.SMC_L:
                                                        ret_position = 0;
                                                        break;
                                                    case VAColumns.SMCColumns.SMC_L_1_2:
                                                        ret_position = 1;
                                                        break;
                                                    case VAColumns.SMCColumns.SMC_M:
                                                        ret_position = 2;
                                                        break;
                                                    case VAColumns.SMCColumns.SMC_M_1_2:
                                                        ret_position = 3;
                                                        break;
                                                    case VAColumns.SMCColumns.SMC_N:
                                                        ret_position = 4;
                                                        break;
                                                    case VAColumns.SMCColumns.SMC_N_1_2:
                                                        ret_position = 5;
                                                        break;
                                                    case VAColumns.SMCColumns.SMC_O_P:
                                                        ret_position = 6;
                                                        break;
                                                    case VAColumns.SMCColumns.SMC_R_1:
                                                        ret_position = 7;
                                                        break;
                                                    case VAColumns.SMCColumns.SMC_R_2:
                                                        ret_position = 8;
                                                        break;
                                                    case VAColumns.SMCColumns.SMC_S:
                                                        ret_position = 9;
                                                }
                                                return ret_position;
                                            }
                                        }.toInt());
                                    } else {
                                        disability_bilateral.setSelection(disability.is_bilateral ? 1 : 0);
                                        disability_rating.setSelection((int)(disability.rating / 10) - 1);
                                    }
                                } else {
                                    save_button.setEnabled(false);
                                }
                            }
                        });

                        short_desc.addTextChangedListener(new VAUtils.TextChangedListener<EditText>(short_desc) {
                            @Override
                            public void onTextChanged(EditText target, Editable s) {
                                Handler handler = new Handler();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(s.length() >= 3){
                                            save_button.setEnabled(true);
                                        } else {
                                            save_button.setEnabled(false);
                                        }
                                    }
                                });
                            }
                        });

                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.findViewById(R.id.disability_dialogue_container).setVisibility(View.VISIBLE);
                            }
                        });
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

    @Override
    public void onResume() {
        super.onResume();
    }

}