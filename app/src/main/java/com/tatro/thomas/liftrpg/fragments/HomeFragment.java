package com.tatro.thomas.liftrpg.fragments;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.github.clans.fab.FloatingActionMenu;
import com.rey.material.widget.ProgressView;
import com.tatro.thomas.liftrpg.R;
import com.tatro.thomas.liftrpg.activities.SessionActivity;
import com.github.clans.fab.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ProgressBar;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.app_name);
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        super.onViewCreated(view, savedInstanceState);
        final FloatingActionMenu menuFab = view.findViewById(R.id.fab_home_menu);
        FloatingActionButton startSessionFab = view.findViewById(R.id.fab_menu_item2);
        FloatingActionButton weighInFab = view.findViewById(R.id.fab_menu_item1);
        menuFab.setClosedOnTouchOutside(true);

        ProgressView progressView = view.findViewById(R.id.xp_bar_home);

        startSessionFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SessionActivity.class);
                startActivity(intent);
                menuFab.close(true);
            }
        });
        weighInFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(getContext())
                        .title("Weigh in")
                        .inputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL)
                        .inputRange(2, -1)
                        .input("Body weight", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                // Do something
                            }
                        })
                        .positiveText("OK")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                //TODO: Make it display a snackbar saying "Weight saved"
                            }
                        })
                        .negativeText("CANCEL")
                        .show();
                menuFab.close(true);
            }
        });
    }
}
