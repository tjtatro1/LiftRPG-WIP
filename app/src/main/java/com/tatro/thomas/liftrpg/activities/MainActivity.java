package com.tatro.thomas.liftrpg.activities;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.orm.SugarRecord;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;
import com.tatro.thomas.liftrpg.entities.SLProgress;
import com.tatro.thomas.liftrpg.fragments.*;
import com.tatro.thomas.liftrpg.R;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private Drawer navDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SLProgress temp = new SLProgress(45, 45, 45, 45, 45);
        temp.save();

        final Fragment homeFragment = new HomeFragment();
        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.main_fragment_placeholder, homeFragment, "Home");
            ft.commit();
        }

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TODO: Add ripple effect animations to the nav drawer
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem home = new PrimaryDrawerItem().withIdentifier(1)
                .withName(R.string.drawer_item_home).withIcon(GoogleMaterial.Icon.gmd_home);
        PrimaryDrawerItem progress = new PrimaryDrawerItem().withIdentifier(2)
                .withName(R.string.drawer_item_progress).withIcon(FontAwesome.Icon.faw_line_chart);
        PrimaryDrawerItem history = new PrimaryDrawerItem().withIdentifier(3)
                .withName(R.string.drawer_item_history).withIcon(GoogleMaterial.Icon.gmd_history);
        PrimaryDrawerItem program = new PrimaryDrawerItem().withIdentifier(4)
                .withName(R.string.drawer_item_program).withIcon(GoogleMaterial.Icon.gmd_fitness_center);
        SecondaryDrawerItem settings = new SecondaryDrawerItem().withIdentifier(5)
                .withName(R.string.drawer_item_settings).withIcon(GoogleMaterial.Icon.gmd_settings);
        SecondaryDrawerItem about = new SecondaryDrawerItem().withIdentifier(6)
                .withName(R.string.drawer_item_about).withIcon(FontAwesome.Icon.faw_question_circle);

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.nav_drawer_header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Mike Penz").withEmail("Lvl 1 Brotége")
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //create the drawer and remember the `Drawer` result object
        navDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        home,
                        progress,
                        history,
                        program,
                        new DividerDrawerItem(),
                        settings,
                        about
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            Fragment fragment = null;
                            Intent intent = null;
                            String tag = null;
                            long identifier = drawerItem.getIdentifier();
                            if (identifier == 1) {
                                fragment = new HomeFragment();
                                tag = "Home";
                            }
                            else if (identifier == 2) {
                                fragment = new ProgressFragment();
                                tag = "Progress";
                            }
                            else if (identifier == 3) {
                                fragment = new HistoryFragment();
                                tag = "History";
                            }
                            else if (identifier == 4) {
                                fragment = new ProgramFragment();
                                tag = "Program";
                            }
                            else if (identifier == 5) {
                                intent = new Intent(MainActivity.this, SettingsActivity.class);
                                tag = "Settings";
                            }
                            else if (identifier == 6) {
                                fragment = new AboutFragment();
                                tag = "About";
                            }
                            //TODO: Work on transition animations
                            if (intent != null) {
                                navDrawer.closeDrawer();
                                startActivity(intent);
                            }
                            else if (fragment != null) {
                                FragmentManager fm = getSupportFragmentManager();
                                FragmentTransaction ft = fm.beginTransaction();
                                int stackCount = fm.getBackStackEntryCount();
                                if (stackCount > 0 && fm.getBackStackEntryAt(0).getName().equals(tag)) {
                                    navDrawer.closeDrawer();
                                }
                                else if (tag.equals("Home")) {
                                    if (stackCount == 0) {
                                        navDrawer.closeDrawer();
                                    }
                                    else {
                                        fm.popBackStackImmediate();
                                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                                        ft.show(homeFragment);
                                        setTitle(R.string.app_name);
                                    }
                                }
                                else if (fm.getBackStackEntryCount() > 0) {
                                    fm.popBackStackImmediate();
                                    ft.add(R.id.main_fragment_placeholder, fragment, tag).addToBackStack(tag);
                                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                    ft.hide(homeFragment).show(fragment);
                                }
                                else {
                                    ft.add(R.id.main_fragment_placeholder, fragment, tag).addToBackStack(tag);
                                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                    ft.hide(homeFragment).show(fragment);
                                }
                                ft.commit();
                            }
                        }
                        return false;
                    }
                })
                .build();
        navDrawer.closeDrawer();
    }

    @Override
    public void onBackPressed() {
        if (navDrawer.isDrawerOpen()) {
            navDrawer.closeDrawer();
        }
        else {
            super.onBackPressed();
            navDrawer.setSelection(1);
            setTitle(R.string.app_name);
        }
    }
}
