package com.tatro.thomas.liftrpg.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import com.tatro.thomas.liftrpg.R;
import com.tatro.thomas.liftrpg.fragments.SettingsFragment;

public class SettingsActivity extends AppCompatActivity implements PreferenceFragmentCompat.OnPreferenceStartScreenCallback {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            Fragment settingsFragment = new SettingsFragment();
            ft.add(R.id.settings_fragment_placeholder, settingsFragment, "Settings");
            ft.commit();
        }
    }

    @Override
    public boolean onPreferenceStartScreen(PreferenceFragmentCompat preferenceFragmentCompat, PreferenceScreen preferenceScreen) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT, preferenceScreen.getKey());
        fragment.setArguments(args);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.settings_fragment_placeholder, fragment, preferenceScreen.getKey());
        ft.addToBackStack(preferenceScreen.getKey());
        ft.commit();

        return true;
    }
}
