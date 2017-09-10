package com.tatro.thomas.liftrpg.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tatro.thomas.liftrpg.R;

public class SettingsFragment extends com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat {

    private ListPreference listPreference;

    @Override
    public void onCreatePreferencesFix(Bundle savedInstanceState, String rootKey) {
        // Indicate here the XML resource you created above that holds the preferences
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Settings");
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
