/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */


package com.nof.vamathcalculator;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.widget.DrawerLayout;

import android.view.View;
import android.widget.AdapterView;

//import android.app.Fragment;
import android.app.FragmentTransaction;

import android.view.Menu;

import android.support.v7.app.ActionBarDrawerToggle;

public class MainActivity extends Activity {

    private String[] nav_items;     // stores nav item names from the nav_items string-array
    private ListView drawer_list;   // holds a reference to the nav drawer's list view: @+id/drawer
    private DrawerLayout drawer_layout; // holds a reference to the nav drawer itself
    private ActionBarDrawerToggle drawer_toggle; // a reference to the drawer toggle event object
    private int current_position = 0;

    // Implement an onItemClickListener used by the ListView
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(
                AdapterView<?> parent,
                View view,
                int position,
                long id
        ) {

            /**
             * When a user clicks on an item in the navigation drawer,
             * the onItemClick() method gets called.
             *
             * Fragment transactions can occur after this function begins
             * execution.
             * */

            // pass the position of the item clicked to selectItem() to be processed
            selectItem(position);
        }
    }

    private void selectItem(int position) {

        current_position = position;

        TestFragment fragment;
        String testOne = "TestOne";
        String testTwo = "TestTwo";
        fragment = TestFragment.newInstance(testOne, testTwo);

        switch (position) {
            case 1:
                // set About fragment
                break;
            case 2:
                // set Help fragment
                break;
            case 3:
                // set Privacy fragment
                break;
            case 4:
                // set Resources fragment
                break;
            case 5:
                // set Settings Fragment
                break;
            case 6:
                // set Terms fragment
                break;
            default:
                // set Main fragment
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

        // Set the title of the action bar to the title of the navigation item
        setActionBarTitle(position);

        // Close the navigation drawer after a selection has been made
        drawer_layout.closeDrawer(drawer_list);
    }

    private void setActionBarTitle(int position) {
        String title;
        if (position == 0 ) {
            title = getResources().getString(R.string.app_name);
        } else {
            title = nav_items[position];
        }

        getActionBar().setTitle(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nav_items = getResources().getStringArray(R.array.nav_items);
        drawer_list = findViewById(R.id.drawer);
        drawer_layout = findViewById(R.id.drawer_layout);

        // enable the nav drawer's hamburger and back icons
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // Populate ListView from values inside the string-array
        drawer_list.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_activated_1,
                nav_items
        ));

        // attached the custom on click listener
        drawer_list.setOnItemClickListener(new DrawerItemClickListener());

        // show the home fragment by default (if there is no past saved state)
        if (savedInstanceState == null) {
            selectItem(0);
        } else {
            // if there was a previous state, recover the title that was shown in the action bar
            current_position = savedInstanceState.getInt("position");
            setActionBarTitle(current_position);
        }

        // Handle events related to the nav drawer opening and closing
        drawer_toggle = new ActionBarDrawerToggle(
                this,
                drawer_layout,
                R.string.open_drawer,
                R.string.close_drawer
        ) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
        };

        // NOTE: DrawerLayout.setDrawerListener is deprecated, use addDrawerListener instead
        drawer_layout.addDrawerListener(drawer_toggle);
    }

    // Inflate the action bar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu defined in main_menu.xml
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Handle action bar item click events
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawer_toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // sync the ActionBarToggle with the state of the navigation drawer
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawer_toggle.syncState();
    }

    // pass configuration state changes (such as rotate) to the ActionBarToggle
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        drawer_toggle.onConfigurationChanged(newConfig);
    }

    // Save state on configuration change
    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putInt("position", current_position);
    }
}