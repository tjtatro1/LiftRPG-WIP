package com.tatro.thomas.liftrpg.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.tatro.thomas.liftrpg.R;
import com.tatro.thomas.liftrpg.items.ExerciseItem;
import com.tatro.thomas.liftrpg.items.SessionItem;

import org.parceler.Parcels;

import java.util.ArrayList;

public class SessionMainFragment extends Fragment {

    private FastItemAdapter<ExerciseItem> fastAdapter;
    private SessionItem newSession;

    public static SessionMainFragment newInstance(Parcelable wrappedSession) {
        SessionMainFragment fragment = new SessionMainFragment();
        Bundle args = new Bundle();
        args.putParcelable("Session", wrappedSession);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fastAdapter = new FastItemAdapter<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Unwraps the parcelable passed in from the newInstance method
        Parcelable parcel = getArguments().getParcelable("Session");
        newSession = Parcels.unwrap(parcel);

        return inflater.inflate(R.layout.fragment_sl5x5_session, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.sl5x5_recyclerview_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(fastAdapter);

        fastAdapter.add(newSession.getMainExerciseItems());
    }

}
