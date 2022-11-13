/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.nof.vamathcalculator.databinding.FragmentHomeDependencySummaryFragmentContentScrollFormBinding;
import com.nof.vamathcalculator.db.BirthDefectChild;
import com.nof.vamathcalculator.db.User;
import com.nof.vamathcalculator.model.VAMathDialogAction;
import com.nof.vamathcalculator.viewmodel.VAMathViewModel;

import java.util.List;

public class FragmentHome_DependencySummaryFragment_ScrollForm extends Fragment {

    private FragmentHomeDependencySummaryFragmentContentScrollFormBinding binding;
    private VAMathViewModel data;
    private static FragmentManager fragment_manager;
    private ArrayAdapter<BirthDefectChild> birth_defect_adapter;
    private View rootView;

    public FragmentHome_DependencySummaryFragment_ScrollForm() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ViewModelProvider(requireActivity()).get(VAMathViewModel.class);
        this.fragment_manager = getChildFragmentManager();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeDependencySummaryFragmentContentScrollFormBinding
                .inflate(inflater, container, false);

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home_dependency_summary_fragment_content_scroll_form, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.getRoot().setVisibility(View.INVISIBLE);
        rootView = getView();
        ViewGroup viewGroup = (ViewGroup) rootView;
        ViewGroup birth_defect_container = rootView.findViewById(R.id.birth_defect_list_view_container);

        Spinner daily_aid_spinner = binding.fragmentDependencySummaryDailyaidSpinner;
        Spinner parent_spinner = binding.fragmentDependencySummaryParentsSpinner;
        Spinner education_spinner = binding.fragmentDependencySummaryEducationSpinner;
        Spinner child_spinner = binding.fragmentDependencySummaryChildSpinner;
        Spinner married_spinner = binding.fragmentDependencySummaryMarriedSpinner;
        //ListView birth_defects_list = binding.birthDefectListViewContainer;

        VAUtils.LinkTextInViewFromResource(
                requireActivity(),
                //view.findViewById(R.id.fragment_dependency_summary_description_text),
                binding.fragmentDependencySummaryDescriptionText,
                R.string.dependency_summary_description_text,
                R.string.dependency_summary_description_text_to_link,
                R.string.dependency_summary_description_text_url
        );

        VAUtils.LinkTextInViewFromResource(
                requireActivity(),
                //view.findViewById(R.id.fragment_dependency_summary_child_education_description),
                binding.fragmentDependencySummaryChildEducationDescription,
                R.string.dependency_summary_child_education_description,
                R.string.dependency_summary_child_education_description_text_to_link,
                R.string.dependency_summary_child_education_description_url
        );

        VAUtils.LinkTextInViewFromResource(
                requireActivity(),
                //view.findViewById(R.id.fragment_dependency_summary_birth_defect),
                binding.fragmentDependencySummaryBirthDefect,
                R.string.dependency_summary_disabled_child_description,
                R.string.dependency_summary_disabled_child_description_text_to_link,
                R.string.dependency_summary_disabled_child_description_url
        );

        VAUtils.LinkTextInViewFromResource(
                requireActivity(),
                //view.findViewById(R.id.fragment_dependency_summary_child_aid_description),
                binding.fragmentDependencySummaryChildAidDescription,
                R.string.dependency_summary_child_aid_description,
                R.string.dependency_summary_child_aid_description_text_to_link,
                R.string.dependency_summary_child_aid_description_url
        );

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> married_spinner_adapter = ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.spinner_yes_no,
                        R.layout.fragment_home_spinner_depenency_list);
                // Specify the layout to use when the list of choices appears
                married_spinner_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);



                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> child_spinner_adapter = ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.children_spinner_values,
                        R.layout.fragment_home_spinner_depenency_list);
                // Specify the layout to use when the list of choices appears
                child_spinner_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);


                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> education_spinner_adapter = ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.education_spinner_values,
                        R.layout.fragment_home_spinner_depenency_list);
                // Specify the layout to use when the list of choices appears
                education_spinner_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);



                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> parent_spinner_adapter = ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.parent_spinner_values,
                        R.layout.fragment_home_spinner_depenency_list);
                // Specify the layout to use when the list of choices appears
                parent_spinner_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);


                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> dailyaid_spinner_adapter = ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.spinner_yes_no,
                        R.layout.fragment_home_spinner_depenency_list);
                // Specify the layout to use when the list of choices appears
                dailyaid_spinner_adapter.setDropDownViewResource(R.layout.fragment_home_spinner_dropdown_dependency_list);

                // attach adapter that fills the birth defect list
                //birth_defects_list.setAdapter(birth_defect_adapter);

                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Apply the adapter to the spinner
                        daily_aid_spinner.setAdapter(dailyaid_spinner_adapter);
                        // Apply the adapter to the spinner
                        parent_spinner.setAdapter(parent_spinner_adapter);
                        // Apply the adapter to the spinner
                        education_spinner.setAdapter(education_spinner_adapter);
                        // Apply the adapter to the spinner
                        child_spinner.setAdapter(child_spinner_adapter);
                        // Apply the adapter to the spinner
                        married_spinner.setAdapter(married_spinner_adapter);

                        data.get_user().observe(requireActivity(), new Observer<User>() {
                            @Override
                            public void onChanged(User user) {
                                married_spinner.setSelection(!user.has_spouse ? 0 : 1);

                                child_spinner.setSelection(user.num_Child == null ? 0 : user.num_Child);

                                parent_spinner.setSelection(user.num_parents == null ? 0 : user.num_parents);

                                education_spinner.setSelection(user.num_child_education == null ? 0 : user.num_child_education);

                                daily_aid_spinner.setSelection(!user.has_aid ? 0 : 1);
                            }
                        });

                        data.get_all_children_with_birth_defects().observe(requireActivity(), new Observer<List<BirthDefectChild>>() {
                            @Override
                            public void onChanged(List<BirthDefectChild> birthDefectChildren) {

                                birth_defect_container.removeAllViews();
                                //Log.e("BirthDefectObserver","Removed Views");

                                int index = 0;
                                for(BirthDefectChild child : birthDefectChildren) {
                                    View birth_defect_list_item = View.inflate(
                                            viewGroup.getContext(),
                                            R.layout.fragment_home_dependency_summary_birth_defect,
                                            null);

                                    TextView defect_name = birth_defect_list_item.findViewById(R.id.birth_defect_name);
                                    defect_name.setText(child.short_name);

                                    TextView level = birth_defect_list_item.findViewById(R.id.birth_defect_level);
                                    String strlevel = "";
                                    switch (child.level){
                                        case 1:
                                            strlevel = "I";
                                            break;
                                        case 2:
                                            strlevel = "II";
                                            break;
                                        case 3:
                                            strlevel = "III";
                                            break;
                                        case 4:
                                            strlevel = "IV";
                                    }
                                    level.setText("Level " + strlevel);

                                    TextView type = birth_defect_list_item.findViewById(R.id.birth_defect_type);
                                    if(child.is_spinafida) {
                                        type.setText("Spina Bifida");
                                    } else {
                                        type.setText("Other");
                                    }

                                    View edit = birth_defect_list_item.findViewById(R.id.birth_defect_edit);
                                    edit.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            // call show dialog passing in the EDIT action and the ID
                                            showDialog(VAMathDialogAction.EDIT, child._id);
                                        }
                                    });
                                    View delete = birth_defect_list_item.findViewById(R.id.birth_defect_delete);
                                    delete.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            // remove element at position from the database
                                            // allow the observable to update the UI
                                            data.delete_birth_defect(child);
                                        }
                                    });
                                    birth_defect_container.addView(birth_defect_list_item);
                                    index++;
                                }

                            }
                        });
                    }
                });

                married_spinner.setOnItemSelectedListener(new married_spinner_listener());
                child_spinner.setOnItemSelectedListener(new child_spinner_listener());
                parent_spinner.setOnItemSelectedListener(new parent_spinner_listener());
                education_spinner.setOnItemSelectedListener(new education_spinner_listener());
                daily_aid_spinner.setOnItemSelectedListener(new daily_aid_spinner_listener());
                Button add_defect_btn = view.findViewById(R.id.add_disabled_child);
                add_defect_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(VAMathDialogAction.CREATE, null);
                    }
                });
            }
        }).start();

        binding.getRoot().setVisibility(View.VISIBLE);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    class married_spinner_listener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            User user = data.get_user().getValue();
            int old_selection;
            if( user != null ) {
                old_selection = !user.has_spouse ? 0 : 1;

                if(old_selection != i) {
                    user.has_spouse = i != 0;
                }
            } else {
                throw new IllegalStateException("The User record is missing in the database. Cannot update User married status.");
            }
            data.update_user(user);
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }
    class child_spinner_listener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            User user = data.get_user().getValue();
            int old_selection;
            if( user != null ) {
                old_selection = user.num_Child == null ? 0 : user.num_Child;

                if(old_selection != i) {
                    user.num_Child = i;
                }
            } else {
                throw new IllegalStateException("The User record is missing in the database. Cannot update User children.");
            }
            data.update_user(user);
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }
    class parent_spinner_listener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            User user = data.get_user().getValue();
            int old_selection;
            if( user != null ) {
                old_selection = user.num_parents == null ? 0 : user.num_parents;

                if(old_selection != i) {
                    user.num_parents = i;
                }
            } else {
                throw new IllegalStateException("The User record is missing in the database. Cannot update User parents.");
            }
            data.update_user(user);
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }
    class education_spinner_listener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            User user = data.get_user().getValue();
            int old_selection;
            if( user != null ) {
                old_selection = user.num_child_education == null ? 0 : user.num_child_education;

                if(old_selection != i) {
                    user.num_child_education = i;
                }
            } else {
                throw new IllegalStateException("The User record is missing in the database. Cannot update User's children in qualified educational program.");
            }
            data.update_user(user);
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }
    class daily_aid_spinner_listener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            User user = data.get_user().getValue();
            int old_selection;
            if( user != null ) {
                old_selection = !user.has_aid ? 0 : 1;

                if(old_selection != i) {
                    user.has_aid = i != 0;
                }
            } else {
                throw new IllegalStateException("The User record is missing in the database. Cannot update User's aid and attendance qualification.");
            }
            data.update_user(user);
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public static void showDialog(VAMathDialogAction action, Integer row_id) {
        row_id = (row_id == null) ? -1 : row_id;
        // Create and show the dialog.
        DialogFragment newFragment = FragmentHome_DependencySummaryDialog_BirthDefect.newInstance(action, row_id);
        newFragment.show(fragment_manager, "dialog");
    }


}