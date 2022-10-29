package com.nof.vamathcalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.nof.vamathcalculator.databinding.FragmentHomeBinding;

import java.util.Objects;

public class FragmentHome extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        Log.i("FragmentHome", "onCreateView()");
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        /*
         * This assigns the adapter to the viewPager
         */
        FragmentHome.ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        ViewPager2 viewPager = binding.pager;
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = binding.fragmentHomeTabLayout;
        new TabLayoutMediator(
                tabLayout,
                viewPager,
                (tab, position) -> {
                    switch(position) {
                        case 1:
                            tab.setText("Dependents");
                            break;
                        case 2:
                            tab.setText("Disabilities");
                            break;
                        default:
                            tab.setText("Money");
                            break;
                    }
                }).attach();

        Log.i("FragmentHome", "onViewCreated()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        Log.e("FragmentHome", "onDestroy()");

    }

    /***
     * Implementation of PagerAdapter that uses a Fragment to manage each page. This class also
     * handles saving and restoring of fragment's state.
     *
     * When pages are not visible to the user, their entire fragment may be
     * destroyed, only keeping the saved state of that fragment. This allows the pager to hold on
     * to much less memory associated with each visited page as compared to FragmentPagerAdapter at
     * the cost of potentially more overhead when switching between pages.
     */
    private static class ViewPagerAdapter extends FragmentStateAdapter {
        public ViewPagerAdapter(Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // Return a NEW fragment instance in createFragment(int)
            Fragment fragment;
            switch(position) {
                case 0:
                    fragment = new FragmentHome_CompensationSummaryFragment();
                    break;
                case 1:
                    fragment = new FragmentHome_DependencySummaryFragment();

                    break;
                case 2:
                    fragment = new FragmentHome_DisabilitySummaryScrollFragment();
                    break;
                default:
                    String msg = "ViewPagerAdapter.createFragment() : Invalid tab position.";
                    throw new IllegalStateException(msg);
            }

            Bundle args = new Bundle();
            // Our object is just an integer :-P
            args.putInt(ViewPagerFragment.ARG_OBJECT, position + 1);
            fragment.setArguments(args);
            Log.i("ViewPagerAdapter", "createFragment()");
            return fragment;
        }

        /***
         * Instances of this class are fragments representing a single
         * object in our collection.
         */
        private static class ViewPagerFragment extends Fragment {
            public static final String ARG_OBJECT = "object";

            @Nullable
            @Override
            public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                     @Nullable Bundle savedInstanceState) {
                Log.i("ViewPagerFragment", "onCreateView()");
                Bundle args = getArguments();
                View view;
                switch(Objects.requireNonNull(args).getInt(ARG_OBJECT)) {
                    case 0:
                        view = inflater.inflate(R.layout.fragment_home_compensation_summary_container, container, false);
                        break;
                    case 1:
                        view =  inflater.inflate(R.layout.fragment_home_dependency_summary_container, container, false);
                        break;
                    case 2:
                        view =  inflater.inflate(R.layout.fragment_home_disability_summary_scroll_container, container, false);
                        break;
                    default:
                        String msg = "ViewPagerFragment.onCreateView() : Invalid position from bundle.";
                        throw new IllegalStateException(msg);
                }
                return view;
            }

            @Override
            public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
                Log.i("ViewPagerFragment", "onViewCreated()");
            }
        }

        @Override
        public int getItemCount() {
            Log.i("ViewPagerAdapter", "getItemCount()");
            return 3;
        }
    }
}



