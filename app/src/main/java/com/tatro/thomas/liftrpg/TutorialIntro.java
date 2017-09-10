package com.tatro.thomas.liftrpg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

public class TutorialIntro extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        // addSlide(firstFragment);

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        /*
        addSlide(AppIntroFragment.newInstance(getResources().getText(R.string.test_title),
                getResources().getText(R.string.test_desc), R.mipmap.test_image, getColor(R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance(getResources().getText(R.string.test_title),
                getResources().getText(R.string.test_desc), R.mipmap.test_image, getColor(R.color.colorPrimary)));
        */

        // OPTIONAL METHODS
        // Override bar/separator color.
        // setBarColor(Color.parseColor("#3F51B5"));
        // setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(false);
        setProgressButtonEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(false);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}