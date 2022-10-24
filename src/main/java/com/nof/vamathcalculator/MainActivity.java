/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */

package com.nof.vamathcalculator;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v4.content.ContextCompat;
import android.support.v7.view.menu.ActionMenuItemView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.widget.DrawerLayout;

import android.view.View;
import android.widget.AdapterView;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.view.Menu;

import android.support.v7.app.ActionBarDrawerToggle;

public class MainActivity extends Activity {

    private final int HOME_SCREEN = 0;
    private final int TEST_FRAG = 1;
    //private final int ABOUT_FRAG = 1;
    private final int HELP_FRAG = 2;
    private final int PRIVACY_FRAG = 3;
    private final int RESOURCE_FRAG = 4;
    private final int SETTINGS_FRAG = 5;
    private final int TERMS_FRAG = 6;


    private String[] nav_items;     // stores nav item names from the nav_items string-array
    private ListView drawer_list;   // holds a reference to the nav drawer's list view: @+id/drawer
    private DrawerLayout drawer_layout; // holds a reference to the nav drawer itself
    private ActionBarDrawerToggle drawer_toggle; // a reference to the drawer toggle event object
    private int current_position = 0;
    private Menu action_menu = null;

    /**
     * Keeps track of the state of the navigation menu.
     */
    private enum NavDrawerState {
        STATE_OPEN,
        STATE_CLOSED
    }
    NavDrawerState nav_drawer_state = NavDrawerState.STATE_CLOSED;

    /**
     * Implement an onItemClickListener used by the ListView. When an item within the nav menu
     * is clicked, its position is passed to the onItemClick() method. The position is used in the
     * selectItem() method; this method selects a fragment to display based on that position
     */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(
                AdapterView<?> parent,
                View view,
                int position,
                long id
        ) {
            // pass the position of the item clicked to selectItem() to be processed
            selectItem(position);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  // lifecyle methods require calling the base constructor

        /**
         * Load the contents of the activity_main.xml file
         */
        setContentView(R.layout.activity_main);

        /**
         * Getting references to different resources
         * nav_items is an array of string values used in the navigation bar
         * drawer_list is the ListView used within the DrawerLayout in activity_main.xml
         * drawer_layout is DrawerLayout element defined in activity_main.xml
         */
        nav_items = getResources().getStringArray(R.array.nav_items);
        drawer_list = findViewById(R.id.drawer);
        drawer_layout = findViewById(R.id.drawer_layout);

        /**
         * These calls enable icons in the navigation drawer. The hamburger icon is displayed when
         * the nav menu is closed, and a back icon is displayed when the navigation menu is open
         */
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setHomeButtonEnabled(false);

        /**
         * Array Adapters generate a ListView from an array. In this case the array nav_items holds
         * the values of the strings that will be used for the ListView within the navigation
         * drawer. The array gets its values from the string-array block in the strings.xml file.
         */
        drawer_list.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_activated_1,
                nav_items
        ));

        /**
         * This attaches the custom drawer onClick listener to the ListView with the drawer layout.
         */
        drawer_list.setOnItemClickListener(new DrawerItemClickListener());

        /**
         * savedInstanceState will be NULL if the application hasn't already started. Use this fact
         * to select the starting fragment. In this case the HOME_SCREEN is selected. If the saved
         * instance IS NOT NULL that means that the application has started and it's configuration
         * or orientation has changed - forcing recreation of the class. In this case set the
         * position and title to what it formerly was using the saved instance.
         */
        if (savedInstanceState == null) {
            selectItem(HOME_SCREEN);
        } else {
            // if there was a previous state, recover the title that was shown in the action bar
            current_position = savedInstanceState.getInt("position");
            nav_drawer_state = (NavDrawerState) savedInstanceState
                    .getSerializable("nav_drawer_state");

            setActionBarTitle(current_position);
        }

        /**
         * Handle events related to the nav drawer opening and closing. In this case the drawer
         * icon is synced when it is open/closed to show the hamburger menu or back button
         * respectively. invalidateOptionsMenu() is included to be able to modify the visibility of
         * ActionBar items in the future.
         */
        drawer_toggle = new ActionBarDrawerToggle(
                this,
                drawer_layout,
                R.string.open_drawer,
                R.string.close_drawer
        ) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //invalidateOptionsMenu();
                //drawer_toggle.syncState();
            }
            public void onDrawerOpened(View view) {
                super.onDrawerClosed(view);
                //invalidateOptionsMenu();
                //drawer_toggle.syncState();
            }
        };


        /**
         * Add a listener that waits for the navigation bar's state to change between open and
         * closed. When that occurs execute the drawer_toggle listener.
         */
        // NOTE: DrawerLayout.setDrawerListener is deprecated, use addDrawerListener instead
        // drawer_layout.setDrawerListener(drawer_toggle);
        drawer_layout.addDrawerListener(drawer_toggle);

        /**
         * The fragment onBackStackChanged listener is used to update the title when
         * the back button is pressed.
         */
        getFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {
                        FragmentManager frag_mgr = getFragmentManager();
                        Fragment fragment = frag_mgr.findFragmentByTag("last_in");
                        if (fragment instanceof HomeScrollingFragment) {
                            current_position = 0;
                        }
                        if (fragment instanceof TestFragment) {
                            current_position = 1;
                        }
                        setActionBarTitle(current_position);
                        drawer_list.setItemChecked(current_position, true);
                    }
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        nav_icon_sync_state();
    }

    /**
     * This method swaps the fragments based on the position passed in.
     * @param position the position in the navigation list view
     */
    private void selectItem(int position) {

        /**
         * Prevent duplicates fragments in the backstack.
         */
        if (position != 0 && position == current_position) return;

        current_position = position;
        Fragment fragment = null;

        switch (current_position) {
            case TEST_FRAG:
                // set About fragment
                String testOne = "TestOne";
                String testTwo = "TestTwo";
                fragment = TestFragment.newInstance(testOne, testTwo);
                changeFragment(fragment);
                break;
            case HELP_FRAG:
                // set Help fragment
                break;
            case PRIVACY_FRAG:
                // set Privacy fragment
                break;
            case RESOURCE_FRAG:
                // set Resources fragment
                break;
            case SETTINGS_FRAG:
                // set Settings Fragment
                break;
            case TERMS_FRAG:
                // set Terms fragment
                break;
            default:
                /**
                 * The default case is to select the HOME fragment, or position 0 in the menu.
                 * When this fragment is chosen the backstack is cleared. In this case, pressing
                 * the back button while the HOME fragment is displayed will exit the app.
                 */
                FragmentManager fm = getFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStackImmediate(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                fragment = new HomeScrollingFragment();
                changeFragment_noBackStack(fragment);
        }

        // Set the title of the action bar to the title of the navigation item
        setActionBarTitle(current_position);

        // Close the navigation drawer after a selection has been made
        drawer_layout.closeDrawer(drawer_list);

        // sync the nav bar icon
        nav_drawer_state = NavDrawerState.STATE_CLOSED;
        nav_icon_sync_state();
    }

    /**
     * Convenience function that swaps in a new fragment, adding it to the back stack
     * @param fragment fragment to be swapped in
     */
    private void changeFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment, "last_in");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
    /**
     * Convenience function that swaps in a new fragment, without adding it to the back stack
     * @param fragment fragment to be swapped in
     */
    private void changeFragment_noBackStack(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment, "last_in");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    /**
     * Sets the title in the action bar based on the position passed in.
     * @param position the position in the nav menu
     */
    private void setActionBarTitle(int position) {
        String title;
        if (position == 0 ) {
            title = getResources().getString(R.string.app_name);
        } else {
            title = nav_items[position];
        }

        getActionBar().setTitle(title);
    }

    /**
     * This method displays the ActionBar menu at the top of the window. The menu resource is
     * inflated from the main_menu.xml file. Additional icons, buttons, and collapsable menu can
     * be defined here or in the menu xml files
     * @param menu a reference to the menu passed in by the android system
     * @return must return true for the menu to display (i believe)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /**
         * Store a reference to the menu for later use.
         */
        this.action_menu = menu;

        // Inflate the menu defined in main_menu.xml
        getMenuInflater().inflate(R.menu.main_menu, menu);

        //View view = findViewById(android.R.id.home);
        //view.setPadding(20, 0, 10 ,0);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Handles onClick events associated with ActionBar items
     * @param item the menu item within the menu that was clicked
     * @return must return true on success
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**
         * The DrawerLayout handles its click events using the ActionBarToggle class.
         */
        if (drawer_toggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item != null && item.getItemId() == R.id.drawer_menu_icon) {
            if (drawer_layout.isDrawerOpen(Gravity.RIGHT)) {
                drawer_layout.closeDrawer(Gravity.RIGHT);
                nav_drawer_state = NavDrawerState.STATE_CLOSED;
                nav_icon_sync_state();
            } else {
               drawer_layout.openDrawer(Gravity.RIGHT);
               nav_drawer_state = NavDrawerState.STATE_OPEN;
                nav_icon_sync_state();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This lifecycle method is called after the app is completing running. As an extra precaution
     * the nav drawer's state is synced here.
     * @param savedInstanceState the activity's saved state, used to recover settings on recreation.
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //drawer_toggle.syncState();
    }

    /**
     * The DrawerLayout handles its own state when the app is destroyed and recreated during the
     * configuration change process (such as rotating the device). Passing the Configuration
     * object to the drawer_toggle's onConfigurationChanged method handles all of the details.
     * @param newConfig Configuration passed from the android system
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //drawer_toggle.onConfigurationChanged(newConfig);
    }

    // Save state on configuration change
    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putInt("position", current_position);
        state.putSerializable("nav_drawer_state", nav_drawer_state);
    }

    // Hide/show action bar items based on navigation menu state
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    private void nav_icon_sync_state() {
        if(this.action_menu != null) {
            switch(nav_drawer_state) {
                case STATE_OPEN:
                    // change R.id.drawer_menu_icon icon resource to up button
                    this.action_menu.findItem(R.id.drawer_menu_icon)
                            //.setIcon(hamburger_icon);
                            .setIcon(R.drawable.drawer_menu_up_icon);
                    break;
                case STATE_CLOSED:
                    // change R.id.drawer_menu_icon icon resource to up button
                    this.action_menu.findItem(R.id.drawer_menu_icon)
                            //.setIcon(up_icon);
                            .setIcon(R.drawable.drawer_menu_icon);
                default:
                    Log.wtf("nav_icon_sync_state", "The Nav drawer is not in a known state." +
                            "The state value = " + nav_drawer_state.toString());
            }
        } else {
            Log.e("nav_icon_sync_state", "Cannot sync the navigation icon. The menu does not exist.");
        }
    }
}