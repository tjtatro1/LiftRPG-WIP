package com.tatro.thomas.liftrpg.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tatro.thomas.liftrpg.R;

public class ProgressFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Progress");
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }
}