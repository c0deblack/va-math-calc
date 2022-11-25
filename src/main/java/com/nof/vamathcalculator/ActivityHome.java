/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.nof.vamathcalculator.databinding.ActivityHomeBinding;
import com.nof.vamathcalculator.db.DependStatus;
import com.nof.vamathcalculator.db.VAColumns;
import com.nof.vamathcalculator.viewmodel.VAMathViewModel;

public class ActivityHome extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityHomeBinding binding;
    private VAMathViewModel data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(R.style.Theme_VAMathCalculator);

        //Log.e("ActivityHome", "onCreate()");

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_activity_home);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        NavigationView navView = binding.navView;
        NavigationUI.setupWithNavController(navView, navController);

        appBarConfiguration = new AppBarConfiguration.Builder(
                //navController.getGraph()
                R.id.fragment_home,
                R.id.fragment_about,
                R.id.fragment_help,
                R.id.fragment_privacy,
                R.id.fragment_terms,
                R.id.fragment_references,
                R.id.fragment_clear
        )
                .setDrawerLayout(binding.drawerLayout)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        BottomNavigationView bottom_nav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottom_nav, navController);

        /*
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                BottomNavigationView bottom_nav = findViewById(R.id.bottom_nav);
                View bottom_nav_item_view = bottom_nav.getChildAt(0);
                Menu bottom_nav_menu = bottom_nav.getMenu();
                switch(navDestination.getId()){
                    case R.id.fragment_home:
                    case R.id.fragment_references:
                        break;
                    default:
                        MenuItem m1 = bottom_nav_menu.findItem(R.id.fragment_home);
                        MenuItem m2 = bottom_nav_menu.findItem(R.id.fragment_references);
                        m2.setChecked(false);
                        m1.setChecked(false);
                }
            }
        });
        */

        // Connect to the data
        data = new ViewModelProvider(this).get(VAMathViewModel.class);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_activity_home);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_activity_home);
        if (NavigationUI.onNavDestinationSelected(item, navController)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu defined in main_menu.xml
        getMenuInflater().inflate(R.menu.top_toolbar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
}