/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.nof.vamathcalculator.databinding.FragmentHomeDisabilitySummaryScrollContainerBinding;
import com.nof.vamathcalculator.db.Disability;

import com.nof.vamathcalculator.db.User;
import com.nof.vamathcalculator.db.VAColumns;

import com.nof.vamathcalculator.model.VAMathDialogAction;
import com.nof.vamathcalculator.viewmodel.VAMathViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentHome_DisabilitySummaryScrollFragment extends Fragment {

    private FragmentHomeDisabilitySummaryScrollContainerBinding binding;
    private static VAMathViewModel data;
    private List<Disability> ratings = new ArrayList<>();
    private CustomAdapter adapter;
    private RecyclerView rv;
    private static SortedList<Disability> sorted_list;
    private static FragmentManager fragment_manager;

    public static class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

        //private final List<Disability> localDataSet;
        private static SortedList<Disability> localDataSet;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public static class ViewHolder extends RecyclerView.ViewHolder {
            private final CardView card_view;
            private final TextView short_desc;
            private final TextView rating;
            private final TextView bilateral;
            private final Button edit_button;
            private final Button delete_button;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View
                card_view = view.findViewById(R.id.textView);
                short_desc = view.findViewById(R.id.disability_short_desc);
                rating = view.findViewById(R.id.disability_rate);
                bilateral = view.findViewById(R.id.disability_bilateral);
                edit_button = view.findViewById(R.id.disability_edit);
                delete_button = view.findViewById(R.id.disability_delete);

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        delete_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int pos = getAdapterPosition();
                                if(pos >= 0) {
                                    data.delete_disability(sorted_list.get(pos));
                                    Disability disability = localDataSet.get(pos);
                                    if(!disability.is_basic){
                                        User user = data.getUserRecord();
                                        user.smc_rating = null;
                                        user.has_smc = false;
                                        data.update_user(user);
                                    }
                                } else {
                                    Log.v("DisabilitySummary", "Delete_Button: Cannot delete disability at position " + pos);
                                }
                            }
                        });

                        edit_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int pos = getAdapterPosition();
                                if(pos >= 0) {
                                    int id = sorted_list.get(pos)._id;
                                    showDialog(VAMathDialogAction.EDIT, id);
                                } else {
                                    Log.v("DisabilitySummary", "Delete_Button: Cannot edit disability at position " + pos);
                                }
                            }
                        });
                    }
                }).start();
            }
            public CardView getTextView() {
                return card_view;
            }
            public TextView getRatingView(){
                return rating;
            }
            public TextView getDescView(){
                return short_desc;
            }
            public TextView getBilateral(){
                return bilateral;
            }
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView.
         */
        //public CustomAdapter(List<Disability> dataSet) {
        public CustomAdapter(SortedList<Disability> dataSet) {
            localDataSet = dataSet;
        }

        // Create new views (invoked by the layout manager)
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.fragment_home_disability_summary_card_view, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Disability data = localDataSet.get(viewHolder.getAdapterPosition());

                    String rating;
                    String bilat = "";
                    if(data.is_basic){
                        rating = "Rating: " + data.rating + "%";
                        bilat = (data.is_bilateral) ? "Yes" : "No";
                    } else {
                        rating = "SMC Rating: " + VAUtils.get_smc_rating(data);
                        bilat = "N/A";
                    }

                    String finalBilat = bilat;
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            viewHolder.getDescView().setText("Description: " + data.short_name);
                            viewHolder.getBilateral().setText("Bilateral: " + finalBilat);
                            viewHolder.getRatingView().setText(rating);
                        }
                    });
                }
            }).start();
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }

    public FragmentHome_DisabilitySummaryScrollFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ViewModelProvider(requireActivity()).get(VAMathViewModel.class);
        fragment_manager = getChildFragmentManager();

        sorted_list = new SortedList<>(Disability.class, new SortedList.Callback<Disability>() {
            @Override
            public int compare(Disability o1, Disability o2) {

                if (o1.rating < o2.rating) {
                    return 1;
                } else if (o1.rating > o2.rating) {
                    return -1;
                }
                return 0;
            }
            @Override
            public void onChanged(int position, int count) {
                adapter.notifyItemChanged(position);
            }
            @Override
            public boolean areContentsTheSame(Disability oldItem, Disability newItem) {
                return oldItem.is_bilateral == newItem.is_bilateral
                        && Objects.equals(oldItem.short_name, newItem.short_name)
                        && Objects.equals(oldItem.rating, newItem.rating)
                        && Objects.equals(oldItem.smc_rating, newItem.smc_rating);
            }
            @Override
            public boolean areItemsTheSame(Disability item1, Disability item2) {
                return Objects.equals(item1._id, item2._id);
            }
            @Override
            public void onInserted(int position, int count) {
                adapter.notifyItemInserted(position);
            }
            @Override
            public void onRemoved(int position, int count) {
                adapter.notifyItemRemoved(position);
            }
            @Override
            public void onMoved(int fromPosition, int toPosition) {
                adapter.notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeDisabilitySummaryScrollContainerBinding.inflate(inflater, container, false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.disability_summary_recycler_view, container, false);
                rv = binding.fragmentHomeDisabilitySummaryRecyclerView;

                adapter = new CustomAdapter(sorted_list);
                rv.setAdapter(adapter);

                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        data.get_disabilities().observe(requireActivity(), new Observer<List<Disability>>() {
                            @Override
                            public void onChanged(List<Disability> disabilities) {
                                //ratings = disabilities;
                                sorted_list.replaceAll(disabilities);
                                Log.v("Disabilities", "onChange: Disability added. "
                                        + "Total number is: " + disabilities.size());
                            }
                        });
                    }
                });

                LinearLayoutManager lm = new LinearLayoutManager(getContext());
                rv.setLayoutManager(lm);
            }
        }).start();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            int count = 0;
            @Override
            public void onClick(View view) {
                showDialog(VAMathDialogAction.CREATE,
                        null);
            }
        });
    }

    private static void showDialog(VAMathDialogAction action, Integer row_id) {

        row_id = (row_id == null) ? -1 : row_id;
        // Create and show the dialog.
        DialogFragment newFragment = FragmentHome_DisabilitySummaryDialog.newInstance(action, row_id);
        newFragment.show(fragment_manager, "dialog");
    }
}