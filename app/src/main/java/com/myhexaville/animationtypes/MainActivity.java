package com.myhexaville.animationtypes;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.myhexaville.animationtypes.databinding.ActivityMainBinding;

/*
* This is a structured sample of different Android Animation approaches to fade out a view
* */
public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MainActivity";
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mBinding.toolbar);
    }

    public void animate(View view) {
        animateWithViewAnimationJava();
        animateWithViewAnimationXML();

//        animateWithValueAnimatorJava();
//        animateWithValueAnimatorXML();


//        animateWithObjectAnimatorJava();
//        animateWithObjectAnimatorXML();


//        animateWithViewPropertyAnimator();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////


    /*
    * View Animations
    * */

    private void animateWithViewAnimationJava() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        alphaAnimation.setDuration(1000);

        mBinding.square.startAnimation(alphaAnimation);
    }

    private void animateWithViewAnimationXML() {
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        mBinding.square.startAnimation(fadeOut);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////


    /*
    * Property Animations
    * Default duration is 300 ms
    * */

    /*
    * Value Animator
    * */
    private void animateWithValueAnimatorJava() {
        ValueAnimator a = ValueAnimator.ofFloat(1f, 0f);

        a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                mBinding.square.setAlpha(v);
            }
        });

        a.start();
    }

    private void animateWithValueAnimatorXML() {
        ValueAnimator a = (ValueAnimator) AnimatorInflater
                .loadAnimator(this, R.animator.value_from_one_to_zero);

        a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                mBinding.square.setAlpha(v);
            }
        });

        a.start();
    }

    /*
    * Object Animator
    * */
    private void animateWithObjectAnimatorJava() {
        ObjectAnimator.ofFloat(
                mBinding.square,
                "alpha",
                1f,
                0f)
                .start();
    }

    private void animateWithObjectAnimatorXML() {
        ObjectAnimator a = (ObjectAnimator) AnimatorInflater
                .loadAnimator(this, R.animator.object_fade_out);

        a.setTarget(mBinding.square);

        a.start();
    }

    /*
    * ViewPropertyAnimator
    * */
    private void animateWithViewPropertyAnimator() {
        mBinding.square.animate()
                .alpha(0f)
                .start();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////


    /*
    * Drawable Animation
    * */
    public void animateSonic(View view) {
        AnimationDrawable d = (AnimationDrawable) mBinding.sonic.getDrawable();
        d.stop();
        d.start();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////


    /*
    * Animated Vector Drawable
    * */
    public void animateVectorSquare(View view) {
        AnimatedVectorDrawable avd = (AnimatedVectorDrawable) mBinding.vectorSquare.getDrawable();
        avd.start();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
}

