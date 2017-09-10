package com.tatro.thomas.liftrpg.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tatro.thomas.liftrpg.R;

public class HistoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("History");
        return inflater.inflate(R.layout.fragment_history, container, false);
    }
}
