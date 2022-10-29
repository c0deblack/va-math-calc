package com.nof.vamathcalculator;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.nof.vamathcalculator.databinding.ActivityHomeBinding;

public class ActivityHome extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Log.e("ActivityHome", "onCreate()");

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_activity_home);
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
                R.id.fragment_settings,
                R.id.fragment_clear
        )
                .setDrawerLayout(binding.drawerLayout)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        BottomNavigationView bottom_nav = findViewById(R.id.bottom_nav);
        NavigationUI.setupWithNavController(bottom_nav, navController);
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