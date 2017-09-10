package com.tatro.thomas.liftrpg.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.tatro.thomas.liftrpg.SessionFragmentPagerAdapter;
import com.tatro.thomas.liftrpg.R;
import com.tatro.thomas.liftrpg.items.SessionItem;

public class SessionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_session);

        // New workout session is created here to pass onto fragments
        SessionItem newSession = new SessionItem();

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new SessionFragmentPagerAdapter(getSupportFragmentManager(),
                SessionActivity.this, newSession));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }
}
