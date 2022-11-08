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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.nof.vamathcalculator.db.Disability;
import com.nof.vamathcalculator.viewmodel.VAMathViewModel;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHome_DisabilitySummaryDialogue#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHome_DisabilitySummaryDialogue extends DialogFragment {

    public enum ACTION {
        CREATE,
        EDIT
    }
    private AlertDialog dialogue;
    VAMathViewModel data;

    private TextView dialogue_title;
    private EditText short_desc;
    private Spinner disability_type;
    private Spinner disability_rating;
    private Spinner disability_bilateral;
    private Button save_button;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ACTION_KEY = "ACTION";
    private static final String ID_KEY = "ID";

    // TODO: Rename and change types of parameters
    private ACTION mACTION;
    private int mID;

    public FragmentHome_DisabilitySummaryDialogue() {
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
    public static FragmentHome_DisabilitySummaryDialogue newInstance(ACTION action, int id) {
        FragmentHome_DisabilitySummaryDialogue fragment = new FragmentHome_DisabilitySummaryDialogue();
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
            mACTION = (ACTION)getArguments().getSerializable(ACTION_KEY);
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
                        Disability disability = new Disability();
                        disability.short_name = short_desc.getText().toString();
                        disability.is_basic = disability_type.getSelectedItemPosition() == 0;
                        disability.rating = (disability_rating.getSelectedItemPosition() + 1) * 10.0;
                        disability.is_bilateral = disability_bilateral.getSelectedItemPosition() == 1;
                        data.insert_disability(disability);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Objects.requireNonNull(FragmentHome_DisabilitySummaryDialogue.this.getDialog())
                                .cancel();
                    }
                });
        this.dialogue = builder.create();
        dialogue_title = dialogue.findViewById(R.id.disability_dialogue_title);
        short_desc = dialogue.findViewById(R.id.disability_dialogue_short_desc);
        disability_type = dialogue.findViewById(R.id.disability_dialogue_rating_type_spinner);
        disability_rating = dialogue.findViewById(R.id.disability_dialogue_rating_spinner);
        disability_bilateral = dialogue.findViewById(R.id.disability_dialogue_bilateral_spinner);

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                dialogue_title = dialogue.findViewById(R.id.disability_dialogue_title);
                short_desc = dialogue.findViewById(R.id.disability_dialogue_short_desc);
                disability_type = dialogue.findViewById(R.id.disability_dialogue_rating_type_spinner);
                disability_rating = dialogue.findViewById(R.id.disability_dialogue_rating_spinner);
                disability_bilateral = dialogue.findViewById(R.id.disability_dialogue_bilateral_spinner);

                ArrayAdapter<CharSequence> disability_type_adapter = ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.disability_type,
                        R.layout.fragment_home_spinner_depenency_list);
                disability_type_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);
                disability_type.setAdapter(disability_type_adapter);

                ArrayAdapter<CharSequence> disability_rating_adapter = ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.disability_ratings,
                        R.layout.fragment_home_spinner_depenency_list);
                disability_rating_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);
                disability_rating.setAdapter(disability_rating_adapter);

                ArrayAdapter<CharSequence> disability_bilateral_adapter = ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.spinner_yes_no,
                        R.layout.fragment_home_spinner_depenency_list);
                disability_bilateral_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);
                disability_bilateral.setAdapter(disability_bilateral_adapter);

                if(mACTION == ACTION.EDIT){
                    Disability disability = data.get_disability_from_id(mID);
                    dialogue_title.setText("Edit Disability");
                    short_desc.setText(disability.short_name);
                    disability_type.setSelection(disability.is_basic ? 0 : 1);
                    disability_bilateral.setSelection(disability.is_bilateral ? 1 : 0);
                    disability_rating.setSelection((int)(disability.rating / 10) - 1);
                }

                save_button = dialogue.getButton(AlertDialog.BUTTON_POSITIVE);
                save_button.setEnabled(false);
                short_desc.addTextChangedListener(new VAUtils.TextChangedListener<EditText>(short_desc) {
                    @Override
                    public void onTextChanged(EditText target, Editable s) {
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if(s.length() >= 3 && !save_button.isEnabled()){
                                        save_button.setEnabled(true);
                                }
                            }
                        });
                    }
                });
                dialogue.findViewById(R.id.disability_dialogue_container).setVisibility(View.VISIBLE);
            }
        });
        return this.dialogue;
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