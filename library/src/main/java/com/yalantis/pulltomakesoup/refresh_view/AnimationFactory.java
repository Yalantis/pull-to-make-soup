package com.yalantis.pulltomakesoup.refresh_view;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

/**
 * Created by Alexey on 21.04.2016.
 */
public class AnimationFactory {


    private static final int ANIMATION_DURATION = 700;
    private static final int ANIMATION_SCALE_DURATION = 500;
    private static final int ANIMATION_FLAME_SCALE_DURATION = 100;
    private static final int ANIMATION_FLAME_BURN_DURATION = 180;
    private static final int ANIMATION_BUBBLE_DURATION = 980;
    private static final int ANIMATION_COVER_DURATION = 580;
    private static final int ANIMATION_COVER_OFFSET = 20;

    private static final Interpolator BOUNCE_INTERPOLATOR = new BounceInterpolator();
    private static final Interpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    private static final Interpolator DECELERATE_INTERPOLATOR = new AccelerateDecelerateInterpolator();

    /**
     * @param animation
     * This method is responsible for configuring animation that animates the bounce,when the vegetables are dropped.
     * @return Animation
     */
    Animation getBounce(Animation animation) {

        configureAnimation(animation
                , BOUNCE_INTERPOLATOR
                , ANIMATION_DURATION
                , 0
                , 0
                , 0);

        return animation;

    }

    /**
     * @param animation
     * This method is responsible for configuring animation that animates scaling of water.
     * @return Animation
     */
    Animation getScale(Animation animation) {

        configureAnimation(animation
                , ACCELERATE_INTERPOLATOR
                , ANIMATION_SCALE_DURATION
                , 0
                , 0
                , 0);

        return animation;
    }

    /**
     * @param animation
     * This method is responsible for configuring animation that animates the flame appearance.
     * @return Animation
     */
    Animation getFlameScale(Animation animation) {
        configureAnimation(animation
                , DECELERATE_INTERPOLATOR
                , ANIMATION_FLAME_SCALE_DURATION
                , 0
                , 0
                , 0);

        return animation;
    }

    /**
     * @param animation
     * This method is responsible for configuring animation that animates the flame burn;
     * @return Animation
     */
    Animation getFlameBurn(Animation animation) {
        configureAnimation(animation
                , DECELERATE_INTERPOLATOR
                , ANIMATION_FLAME_BURN_DURATION
                , 0
                , Animation.REVERSE
                , Animation.INFINITE);

        return animation;
    }

    /**
     * @param animation Animation that needs to be configured.
     * @param offset    With which offset should be started.
     *                  This method is responsible for configuring animation that animates the bubble move.
     *                  Animation can be started with some offset.
     * @return Animation
     */
    Animation getBubble(Animation animation, int offset) {
        configureAnimation(animation
                , DECELERATE_INTERPOLATOR
                , ANIMATION_BUBBLE_DURATION
                , offset
                , Animation.RESTART
                , Animation.INFINITE);
        return animation;
    }

    /**
     * @param animation
     * This method is responsible for configuring animation that animates the cover droping.
     * @return Animation
     */
    Animation getCover(Animation animation) {
        configureAnimation(animation
                , BOUNCE_INTERPOLATOR
                , ANIMATION_COVER_DURATION
                , ANIMATION_COVER_OFFSET
                , Animation.REVERSE
                , Animation.INFINITE);

        return animation;
    }


    private void configureAnimation(Animation animation, Interpolator interpolator, int duration, int startOffset, int repeatMode, int repeatCount) {
        animation.setInterpolator(interpolator);
        animation.setDuration(duration);
        animation.setStartOffset(startOffset);
        animation.setRepeatMode(repeatMode);
        animation.setRepeatCount(repeatCount);
    }

}
