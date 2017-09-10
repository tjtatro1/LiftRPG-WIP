package com.tatro.thomas.liftrpg;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tatro.thomas.liftrpg.fragments.SessionAssistanceFragment;
import com.tatro.thomas.liftrpg.fragments.SessionMainFragment;
import com.tatro.thomas.liftrpg.items.SessionItem;

import org.parceler.Parcels;

public class SessionFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String[] tabTitles = {"Main", "Assistance"};
    private Context context;
    private SessionMainFragment mainFragment;
    private SessionAssistanceFragment assistanceFragment;

    public SessionFragmentPagerAdapter(FragmentManager fm, Context context, SessionItem newSession) {
        super(fm);
        this.context = context;

        // Creates a parcelable for the newSession so it can be passed on to the fragments
        Parcelable wrappedSession = Parcels.wrap(newSession);

        mainFragment = SessionMainFragment.newInstance(wrappedSession);
        assistanceFragment = SessionAssistanceFragment.newInstance(wrappedSession);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return mainFragment;
        else
            return assistanceFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}
