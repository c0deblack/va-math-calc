package com.nof.vamathcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import com.nof.vamathcalculator.databinding.FragmentHomeDisabilitySummaryScrollContainerBinding;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome_DisabilitySummaryScrollFragment extends Fragment {

    FragmentHomeDisabilitySummaryScrollContainerBinding binding;


    private static class DisabilitySummary {
        private String m_short_desc;
        private Double m_rating;
        private Boolean m_bilateral;

        DisabilitySummary(String short_desc, Double rating, Boolean bilateral) {
            this.setDisability(short_desc, rating, bilateral);
        }
        public String getDesc() {
            return "Description: " + m_short_desc;
        }
        public String getRating() {
            return "Disability Rating: " + m_rating + "%";
        }
        public String getBilateral(){
            String bilat = (m_bilateral) ? "Yes" : "No";
            return "Bilateral: " + bilat;
        }
        public void setDisability(String short_desc, Double rating, Boolean bilateral) {
            m_short_desc = short_desc;
            m_rating = rating;
            m_bilateral = bilateral;
        }
    }


    public static class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

        private final List<DisabilitySummary> localDataSet;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public static class ViewHolder extends RecyclerView.ViewHolder {
            private final CardView card_view;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                card_view = view.findViewById(R.id.textView);
            }
            public CardView getTextView() {
                return card_view;
            }
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView.
         */
        public CustomAdapter(List<DisabilitySummary> dataSet) {
            localDataSet = dataSet;
        }

        // Create new views (invoked by the layout manager)
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.fragment_home_disability_summary_card_view, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            View view = viewHolder.getTextView();
            DisabilitySummary data = localDataSet.get(position);

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            //viewHolder.getTextView().setText(localDataSet[position]);
            TextView short_desc = view.findViewById(R.id.disability_short_desc);
            short_desc.setText(data.getDesc());

            TextView rating = view.findViewById(R.id.disability_rate);
            rating.setText(data.getRating());

            TextView bilateral = view.findViewById(R.id.disability_bilateral);
            bilateral.setText(data.getBilateral());
            //short_desc.setText("test");

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
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeDisabilitySummaryScrollContainerBinding.inflate(inflater, container, false);

        binding.fab.setOnClickListener(view -> Snackbar.make(view, "Used to add new disabilities", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        List<DisabilitySummary> ratings = new ArrayList<>();
        ratings.add(new DisabilitySummary("Short Desc1", 50d, false));
        ratings.add(new DisabilitySummary("Short Desc2", 30d, false));
        ratings.add(new DisabilitySummary("Short Desc3", 15d, false));
        ratings.add(new DisabilitySummary("Short Desc4", 10d, false));

        if (ratings != null){

            CustomAdapter adapter = new CustomAdapter(ratings);
            //RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.disability_summary_recycler_view, container, false);
            RecyclerView rv = binding.fragmentHomeDisabilitySummaryRecyclerView;
            rv.setAdapter(adapter);
            LinearLayoutManager lm = new LinearLayoutManager(getContext());
            rv.setLayoutManager(lm);
            //return rv;

        } else {
            throw new IllegalStateException("DisabilitySummary: Unable to create ratings object");
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}