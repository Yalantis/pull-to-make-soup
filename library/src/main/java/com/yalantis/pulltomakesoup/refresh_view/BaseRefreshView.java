package com.yalantis.pulltomakesoup.refresh_view;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.yalantis.pulltomakesoup.PullToRefreshView;

/**
 * Created by Alexey on 28.01.2016.
 */
public abstract class BaseRefreshView extends Drawable implements Drawable.Callback {

    private final PullToRefreshView mRefreshLayout;

    BaseRefreshView(PullToRefreshView layout) {
        mRefreshLayout = layout;
    }

    Context getContext() {
        return mRefreshLayout != null ? mRefreshLayout.getContext() : null;

    }

    public abstract void setPercent(float percent, boolean invalidate);

    public abstract void offsetTopAndBottom(int offset);

    @Override
    public void invalidateDrawable(@NonNull Drawable who) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, what, when);
        }
    }

    @Override
    public void unscheduleDrawable(Drawable who, Runnable what) {
        final Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, what);
        }
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

}
