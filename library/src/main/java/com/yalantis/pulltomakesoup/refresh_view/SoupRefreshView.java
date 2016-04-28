package com.yalantis.pulltomakesoup.refresh_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

import com.yalantis.pulltomakesoup.PullToRefreshView;
import com.yalantis.pulltomakesoup.R;
import com.yalantis.pulltomakesoup.utils.Utils;

/**
 * Created by Alexey on 28.01.2016.
 */
public class SoupRefreshView extends Drawable implements Animatable, Drawable.Callback {

    private final PullToRefreshView mParent;
    private final Matrix mMatrix;
    private Animation mBounceAnimation;
    private Animation mScaleAnimation;
    private Animation mFlameScaleAnimation;
    private Animation mFlameBurnAnimation;
    private Animation mBubble1Animation;
    private Animation mBubble2Animation;
    private Animation mBubble3Animation;
    private Animation mBubble4Animation;
    private Animation mBubble5Animation;
    private Animation mBubble6Animation;
    private Animation mCoverAnimation;

    private static final int ANIMATION_BUBBLE1_OFFSET = 200;
    private static final int ANIMATION_BUBBLE2_OFFSET = 300;
    private static final int ANIMATION_BUBBLE3_OFFSET = 500;
    private static final int ANIMATION_BUBBLE4_OFFSET = 700;
    private static final int ANIMATION_BUBBLE5_OFFSET = 800;


    private float mScreenWidth;
    private float mScreenHeight;

    private float mPanTopOffset;

    private Bitmap mPan;
    private Bitmap mPotato;
    private Bitmap mCircle;
    private Bitmap mCarrot;
    private Bitmap mRightPea;
    private Bitmap mLeftPea;
    private Bitmap mCover;
    private Bitmap mWater;
    private Bitmap mShadow;
    private Bitmap mFlame1;
    private Bitmap mFlame2;
    private Bitmap mFlame3;
    private Bitmap mFlame4;
    private Bitmap mFlame5;
    private Bitmap mBubble;

    private float mPercent = 0.0f;
    private float mBounce = 0.0f;

    private boolean isRefreshing = false;

    private float mCoverOffset;
    private float mCoverStartPointY;
    private float mCoverFinalPointY;

    private boolean isCoverDropped;
    private boolean isShadowDisplayed;
    private float mScale;
    private float mFlameScale;
    private float mFlameBurn;
    private float mBubble1Move;
    private float mBubble2Move;
    private float mBubble3Move;
    private float mBubble4Move;
    private float mBubble5Move;
    private float mBubble6Move;
    private Context mContext = getContext();
    private float mBubbleScaleOffset;
    private float mCoverJump;


    private float mCarrotFinalPointX;
    private float mCarrotStartPointX;

    private float mCarrotFinalPointY;
    private float mCarrotStartPointY;
    private float mCarrotOffset;

    private float mPotatoFinalPointX;
    private float mPotatoStartPointX;

    private float mPotatoFinalPointY;
    private float mPotatoStartPointY;
    private float mPotatoOffset;

    private float mRightPeaFinalPointX;
    private float mRightPeaStartPointX;

    private float mRightPeaFinalPointY;
    private float mRightPeaStartPointY;
    private float mRightPeaOffset;


    private float mLeftPeaFinalPointX;
    private float mLeftPeaStartPointX;

    private float mLeftPeaFinalPointY;
    private float mLeftPeaStartPointY;

    private float mLeftPeaOffset;

    private float mFlame1TopOffset;
    private float mFlame1LeftOffset;

    private float mFlame2LeftOffset;

    private float mFlame3TopOffset;
    private float mFlame3LeftOffset;

    private float mFlame4TopOffset;
    private float mFlame4LeftOffset;

    private float mFlame5TopOffset;
    private float mFlame5LeftOffset;

    private float mBubble1TopOffset;
    private float mBubble1LeftOffset;

    private float mBubble2TopOffset;
    private float mBubble2LeftOffset;

    private float mBubble3TopOffset;
    private float mBubble3LeftOffset;

    private float mBubble4TopOffset;
    private float mBubble4LeftOffset;

    private float mBubble5TopOffset;
    private float mBubble5LeftOffset;

    private float mBubble6TopOffset;
    private float mBubble6LeftOffset;


    private float mCirclePivotX;
    private float mCirclePivotY;
    private float mBubble1PivotX;
    private float mBubble2PivotX;
    private float mBubble3PivotX;
    private float mBubble4PivotX;
    private float mBubble5PivotX;
    private float mBubble6PivotX;


    public SoupRefreshView(final PullToRefreshView layout) {
        mParent = layout;
        mMatrix = new Matrix();
        mContext = layout.getContext();
        setupAnimations();
        layout.post(new Runnable() {
            @Override
            public void run() {
                initiateDimens(layout.getWidth());
            }
        });

    }

    private void initiateDimens(int viewWidth) {
        if (viewWidth <= 0 || viewWidth == mScreenWidth) return;
        mScreenWidth = viewWidth;
        mScreenHeight = mContext.getResources().getDisplayMetrics().heightPixels;


        createBitmaps();

        mPanTopOffset = Utils.convertDpToFloatPixel(mContext, 60);

        mCoverOffset = Utils.convertDpToFloatPixel(mContext, 90);

        mCoverStartPointY = Utils.convertDpToFloatPixel(mContext, 10);
        mCoverFinalPointY = Utils.convertDpToFloatPixel(mContext, 70);


        mCarrotStartPointX = (mScreenWidth / 100) * 79;
        mCarrotFinalPointX = (mScreenWidth / 100) * 30.0f;


        mCarrotFinalPointY = Utils.convertDpToFloatPixel(mContext, 245);
        mCarrotStartPointY = Utils.convertDpToFloatPixel(mContext, 161);
        mCarrotOffset = Utils.convertDpToFloatPixel(mContext, 90);

        mPotatoFinalPointX = (mScreenWidth / 100) * -25;
        mPotatoStartPointX = (mScreenWidth / 100) * 14.5f;


        mPotatoStartPointY = Utils.convertDpToFloatPixel(mContext, 150);
        mPotatoFinalPointY = Utils.convertDpToFloatPixel(mContext, 237);
        mPotatoOffset = Utils.convertDpToFloatPixel(mContext, 90);


        mRightPeaFinalPointX = (mScreenWidth / 100) * 30.5f;
        mRightPeaStartPointX = (mScreenWidth / 100) * 88;

        mRightPeaStartPointY = Utils.convertDpToFloatPixel(mContext, 150);
        mRightPeaFinalPointY = Utils.convertDpToFloatPixel(mContext, 242);
        mRightPeaOffset = Utils.convertDpToFloatPixel(mContext, 90);

        mLeftPeaStartPointX = (mScreenWidth / 100) * 7.5f;
        mLeftPeaFinalPointX = (mScreenWidth / 100) * -45;

        mLeftPeaStartPointY = Utils.convertDpToFloatPixel(mContext, 150);
        mLeftPeaFinalPointY = Utils.convertDpToFloatPixel(mContext, 242);
        mLeftPeaOffset = Utils.convertDpToFloatPixel(mContext, 90);

        mFlame1TopOffset = Utils.convertDpToFloatPixel(mContext, 134);
        mFlame1LeftOffset = (mScreenWidth / 100) * 42;

        mFlame2LeftOffset = (mScreenWidth / 100) * 45;

        mFlame3TopOffset = Utils.convertDpToFloatPixel(mContext, 132);
        mFlame3LeftOffset = (mScreenWidth / 100) * 48.5f;

        mFlame4TopOffset = Utils.convertDpToFloatPixel(mContext, 134);
        mFlame4LeftOffset = (mScreenWidth / 100) * 51.5f;

        mFlame5TopOffset = Utils.convertDpToFloatPixel(mContext, 134);
        mFlame5LeftOffset = (mScreenWidth / 100) * 54f;

        mBubble1TopOffset = Utils.convertDpToFloatPixel(mContext, 144);
        mBubble1LeftOffset = (mScreenWidth / 100) * 40;

        mBubble2TopOffset = Utils.convertDpToFloatPixel(mContext, 144);
        mBubble2LeftOffset = (mScreenWidth / 100) * 42;

        mBubble3TopOffset = Utils.convertDpToFloatPixel(mContext, 144);
        mBubble3LeftOffset = (mScreenWidth / 100) * 44;

        mBubble4TopOffset = Utils.convertDpToFloatPixel(mContext, 144);
        mBubble4LeftOffset = (mScreenWidth / 100) * 46;

        mBubble5TopOffset = Utils.convertDpToFloatPixel(mContext, 144);
        mBubble5LeftOffset = (mScreenWidth / 100) * 48;

        mBubble6TopOffset = Utils.convertDpToFloatPixel(mContext, 144);
        mBubble6LeftOffset = (mScreenWidth / 100) * 50;

        mCirclePivotX = Utils.convertDpToPixel(mContext, 100);
        mCirclePivotY = Utils.convertDpToPixel(mContext, 40);

        mBubbleScaleOffset = Utils.convertDpToFloatPixel(mContext, 100);

        setBubblesPivot(140);

    }


    private void createBitmaps() {

        mPan = CreateBitmapFactory.getBitmapFromImage(R.drawable.pan, mContext);
        mCircle = CreateBitmapFactory.getBitmapFromImage(R.drawable.circle, mContext);
        mPotato = CreateBitmapFactory.getBitmapFromImage(R.drawable.potato, mContext);
        mCarrot = CreateBitmapFactory.getBitmapFromImage(R.drawable.carrot, mContext);
        mRightPea = CreateBitmapFactory.getBitmapFromImage(R.drawable.pea, mContext);
        mLeftPea = CreateBitmapFactory.getBitmapFromImage(R.drawable.pea, mContext);
        mCover = CreateBitmapFactory.getBitmapFromImage(R.drawable.pan_cover, mContext);
        mWater = CreateBitmapFactory.getBitmapFromImage(R.drawable.water, mContext);
        mShadow = CreateBitmapFactory.getBitmapFromImage(R.drawable.shadow, mContext);
        mFlame1 = CreateBitmapFactory.getBitmapFromImage(R.drawable.flame1, mContext);
        mFlame2 = CreateBitmapFactory.getBitmapFromImage(R.drawable.flame2, mContext);
        mFlame3 = CreateBitmapFactory.getBitmapFromImage(R.drawable.flame3, mContext);
        mFlame4 = CreateBitmapFactory.getBitmapFromImage(R.drawable.flame4, mContext);
        mFlame5 = CreateBitmapFactory.getBitmapFromImage(R.drawable.flame5, mContext);
        mBubble = CreateBitmapFactory.getBitmapFromDrawable(R.drawable.bubble, mContext);

    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, top);
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void start() {
        mBounceAnimation.reset();
        mScaleAnimation.reset();
        mFlameScaleAnimation.reset();
        mFlameBurnAnimation.reset();
        mBubble1Animation.reset();
        mCoverAnimation.reset();
        isRefreshing = true;

        final AnimationSet animatorSet = new AnimationSet(false);
        animatorSet.addAnimation(mFlameBurnAnimation);
        animatorSet.addAnimation(mBubble1Animation);
        animatorSet.addAnimation(mBubble2Animation);
        animatorSet.addAnimation(mBubble3Animation);
        animatorSet.addAnimation(mBubble4Animation);
        animatorSet.addAnimation(mBubble5Animation);
        animatorSet.addAnimation(mBubble6Animation);
        animatorSet.addAnimation(mCoverAnimation);
        mParent.startAnimation(mBounceAnimation);

        mBounceAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mParent.startAnimation(mScaleAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isShadowDisplayed = true;
                mParent.startAnimation(mFlameScaleAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mFlameScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mParent.startAnimation(animatorSet);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    public void stop() {
        mParent.clearAnimation();
        isRefreshing = false;
        isCoverDropped = false;
        isShadowDisplayed = false;
        resetOriginals();
    }


    public void setPercent(float percent, boolean invalidate) {
        setPercent(percent);
        if (invalidate)   mBounce = setVariable(percent);
    }


    public void offsetTopAndBottom(int offset) {
        invalidateSelf();
    }

    @Override
    public void draw(Canvas canvas) {

        if (mScreenWidth <= 0) return;

        final int saveCount = canvas.save();

        canvas.translate(0, 0);
        canvas.clipRect(0, -mParent.getTotalDragDistance(), mScreenWidth, mParent.getTotalDragDistance());
        drawCircle(canvas);
        drawShadow(canvas);
        drawPan(canvas);
        drawPotato(canvas);
        drawCarrot(canvas);
        drawRightPea(canvas);
        drawLeftPea(canvas);
        drawCover(canvas);
        drawWater(canvas);
        drawFlame(canvas, mFlame1, mFlame1LeftOffset, mFlame1TopOffset, mFlameScale, mFlame4LeftOffset - Utils.convertDpToFloatPixel(mContext, 15), mFlame4TopOffset + 50);
        drawFlame(canvas, mFlame2, mFlame2LeftOffset, mFlame1TopOffset, mFlameBurn, mFlame2LeftOffset + Utils.convertDpToFloatPixel(mContext, 10), mFlame1TopOffset + 50);
        drawFlame(canvas, mFlame3, mFlame3LeftOffset, mFlame3TopOffset, mFlameScale, mFlame3LeftOffset - Utils.convertDpToFloatPixel(mContext, 11), mFlame3TopOffset + 50);
        drawFlame(canvas, mFlame4, mFlame4LeftOffset, mFlame4TopOffset, mFlameBurn, mFlame4LeftOffset, mFlame4TopOffset + 50);
        drawFlame(canvas, mFlame5, mFlame5LeftOffset, mFlame5TopOffset, mFlameScale, mFlame5LeftOffset, mFlame5TopOffset + 50);

        drawBubble(canvas, mBubble1LeftOffset, mBubble1TopOffset, mBubble1Move, mBubble1PivotX);
        drawBubble(canvas, mBubble2LeftOffset, mBubble2TopOffset, mBubble2Move, mBubble2PivotX);
        drawBubble(canvas, mBubble3LeftOffset, mBubble3TopOffset, mBubble3Move, mBubble3PivotX);
        drawBubble(canvas, mBubble4LeftOffset, mBubble4TopOffset, mBubble4Move, mBubble4PivotX);
        drawBubble(canvas, mBubble5LeftOffset, mBubble5TopOffset, mBubble5Move, mBubble5PivotX);
        drawBubble(canvas, mBubble6LeftOffset, mBubble6TopOffset, mBubble6Move, mBubble6PivotX);

        canvas.restoreToCount(saveCount);
    }

    Context getContext() {
        return mParent != null ? mParent.getContext() : null;

    }

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

    //No need for implementation,we don't need this method.
    @Override
    public void setAlpha(int alpha) {
    }

    //No need for implementation,we don't need this method.
    @Override
    public void setColorFilter(ColorFilter cf) {
    }

    private void drawCover(Canvas canvas) {
        Matrix matrix = mMatrix;
        matrix.reset();
        float offsetX;
        float offsetY;

        if (isRefreshing) {
            offsetX = (mScreenWidth / 2) - (mCover.getWidth() / 2);
            offsetY = ((((mBounce * mCoverFinalPointY) - mCoverStartPointY) * ((mBounce * mCoverFinalPointY) - mCoverStartPointY)) / mCoverOffset);
            isCoverDropped = true;
            if (isShadowDisplayed) {
                matrix.postRotate(-5, 0, 0);
            }
            matrix.postRotate(mCoverJump * 5, 0, 0);
            matrix.postTranslate(offsetX, offsetY);
            Paint paint = new Paint();
            float alpha = (mBounce / 2) * 500;
            paint.setAlpha((int) alpha);
            canvas.drawBitmap(mCover, matrix, paint);
        }
    }

    private void drawPan(Canvas canvas) {
        Matrix matrix = mMatrix;
        matrix.reset();
        float dragPercent = Math.min(1f, Math.abs(mPercent));
        float offsetY;
        float offsetX = (mScreenWidth / 2) - (mPan.getWidth() / 2);
        offsetY = mPanTopOffset * dragPercent;
        matrix.postTranslate(offsetX, offsetY);

        Paint paint = new Paint();
        float alpha = (dragPercent / 2) * 500;
        paint.setAlpha((int) alpha);
        canvas.drawBitmap(mPan, matrix, paint);
    }

    private void drawCircle(Canvas canvas) {
        Matrix matrix = mMatrix;
        matrix.reset();

        float dragPercent = Math.min(0.85f, Math.abs(mPercent));

        float offsetX = (mScreenWidth / 2) - (mCircle.getWidth() / 2);
        float offsetY = -(mScreenHeight / 100);

        matrix.postScale(dragPercent, dragPercent, mCirclePivotX, mCirclePivotY);
        matrix.postTranslate(offsetX, offsetY);

        Paint paint = new Paint();
        float alpha = (dragPercent / 2) * 500;
        paint.setAlpha((int) alpha);

        canvas.drawBitmap(mCircle, matrix, paint);
    }

    private void drawPotato(Canvas canvas) {
        Matrix matrix = mMatrix;
        matrix.reset();
        float dragPercent = Math.min(1f, Math.abs(mPercent));

        float offsetX;
        float offsetY;

        offsetX = -(dragPercent * mPotatoFinalPointX) + mPotatoStartPointX;
        offsetY = (((dragPercent * mPotatoFinalPointY) - mPotatoStartPointY) * ((dragPercent * mPotatoFinalPointY) - mPotatoStartPointY)) / mPotatoOffset;
        if (isRefreshing) {
            float bouncePercent = Math.min(1f, Math.abs(mBounce));
            offsetY = (offsetY) + Utils.convertDpToFloatPixel(mContext, 25) * bouncePercent;
        }

        matrix.postTranslate(offsetX, offsetY);

        Paint paint = new Paint();
        float alpha = (dragPercent / 2) * 500;
        paint.setAlpha((int) alpha);
        canvas.drawBitmap(mPotato, matrix, paint);
    }

    private void drawCarrot(Canvas canvas) {
        Matrix matrix = mMatrix;
        matrix.reset();
        float dragPercent = Math.min(1f, Math.abs(mPercent));

        float offsetX;
        float offsetY;

        offsetX = -(dragPercent * mCarrotFinalPointX) + mCarrotStartPointX;
        offsetY = (((dragPercent * mCarrotFinalPointY) - mCarrotStartPointY) * ((dragPercent * mCarrotFinalPointY) - mCarrotStartPointY)) / mCarrotOffset;

        if (isRefreshing) {
            float bouncePercent = Math.min(1f, Math.abs(mBounce));
            offsetY = (offsetY) + Utils.convertDpToFloatPixel(mContext, 25) * bouncePercent;
            matrix.postRotate(bouncePercent * (-30));
        }


        matrix.postRotate(dragPercent * (-330));
        matrix.postTranslate(offsetX, offsetY);
        Paint paint = new Paint();
        float alpha = (dragPercent / 2) * 500;
        paint.setAlpha((int) alpha);
        canvas.drawBitmap(mCarrot, matrix, paint);
    }

    private void drawRightPea(Canvas canvas) {
        Matrix matrix = mMatrix;
        matrix.reset();
        float dragPercent = Math.min(1f, Math.abs(mPercent));

        float offsetX;
        float offsetY;

        offsetX = -(dragPercent * mRightPeaFinalPointX) + mRightPeaStartPointX;
        offsetY = (((dragPercent * mRightPeaFinalPointY) - mRightPeaStartPointY) * ((dragPercent * mRightPeaFinalPointY) - mRightPeaStartPointY)) / mRightPeaOffset;

        if (isRefreshing) {
            float bouncePercent = Math.min(1f, Math.abs(mBounce));
            offsetY = (offsetY) + Utils.convertDpToFloatPixel(mContext, 25) * bouncePercent;
        }

        matrix.postTranslate(offsetX, offsetY);
        Paint paint = new Paint();
        float alpha = (dragPercent / 2) * 500;
        paint.setAlpha((int) alpha);
        canvas.drawBitmap(mRightPea, matrix, paint);
    }

    private void drawLeftPea(Canvas canvas) {
        Matrix matrix = mMatrix;
        matrix.reset();
        float dragPercent = Math.min(1f, Math.abs(mPercent));

        float offsetX;
        float offsetY;

        offsetX = -(dragPercent * mLeftPeaFinalPointX) + mLeftPeaStartPointX;
        offsetY = (((dragPercent * mLeftPeaFinalPointY) - mLeftPeaStartPointY) * ((dragPercent * mLeftPeaFinalPointY) - mLeftPeaStartPointY)) / mLeftPeaOffset;

        if (isRefreshing) {
            float bouncePercent = Math.min(1f, Math.abs(mBounce));
            offsetY = (offsetY) + Utils.convertDpToFloatPixel(mContext, 25) * bouncePercent;
        }


        matrix.postTranslate(offsetX, offsetY);

        Paint paint = new Paint();
        float alpha = (dragPercent / 2) * 500;
        paint.setAlpha((int) alpha);
        canvas.drawBitmap(mLeftPea, matrix, paint);
    }

    private void drawWater(final Canvas canvas) {
        final Matrix matrix = mMatrix;
        matrix.reset();

        float dragPercent = Math.min(1f, Math.abs(mPercent));
        final float offsetX = (mScreenWidth / 2) - (mWater.getWidth() / 2);
        final float offsetY = (mPanTopOffset * dragPercent) + Utils.convertDpToFloatPixel(mContext, 10);
        if (isCoverDropped) {
            matrix.postScale(1, mScale, Utils.convertDpToPixel(mContext, 48), Utils.convertDpToPixel(mContext, 60));
            matrix.postTranslate(offsetX, offsetY);
            Paint paint = new Paint();
            canvas.drawBitmap(mWater, matrix, paint);
        }
    }

    private void drawFlame(final Canvas canvas, Bitmap bitmap, float flameOffsetX, float flameOffsetY, float scaleY, float pivotX, float pivotY) {
        final Matrix matrix = mMatrix;
        matrix.reset();

        if (isShadowDisplayed) {
            matrix.postTranslate(flameOffsetX, flameOffsetY);
            float flameMinScale = 0.9f;
            matrix.postScale(Math.max(flameMinScale, scaleY), Math.max(flameMinScale, scaleY), pivotX, pivotY);
            Paint paint = new Paint();
            float alpha = (Math.max(0.5f, mFlameScale)) * 255;
            paint.setAlpha((int) alpha);
            canvas.drawBitmap(bitmap, matrix, paint);
        }
    }

    private void drawShadow(final Canvas canvas) {
        final Matrix matrix = mMatrix;
        matrix.reset();

        float dragPercent = Math.min(1f, Math.abs(mPercent));
        final float offsetX = (mScreenWidth / 2) - (mShadow.getWidth() / 2) + Utils.convertDpToFloatPixel(mContext, 17f);
        if (isCoverDropped) {
            final float offsetY = mPanTopOffset * dragPercent;
            matrix.postTranslate(offsetX, offsetY);
            Paint paint = new Paint();
            float alpha = (mBounce / 2) * 500;
            paint.setAlpha((int) alpha);
            canvas.drawBitmap(mShadow, matrix, paint);
        }
    }

    private void drawBubble(final Canvas canvas, float bubbleOffsetX, float bubbleOffsetY, float move, float pivotX) {
        final Matrix matrix = mMatrix;
        matrix.reset();

        float offsetY = (bubbleOffsetY - mBubbleScaleOffset) - mBubbleScaleOffset * move;
        if (isShadowDisplayed) {
            if (move < 0.48) {
                matrix.postScale(move, move, pivotX, bubbleOffsetY);
                matrix.postTranslate(bubbleOffsetX, offsetY);
                Paint paint = new Paint();
                canvas.drawBitmap(mBubble, matrix, paint);
            }
        }
    }

    private void setPercent(float percent) {
        mPercent = percent;
    }

    private float setVariable(float value) {
        invalidateSelf();
        return value;
    }

    /**
     * @param dp The offset of pivot to make bubbles move straight upward, while scaling.
     */
    private void setBubblesPivot(float dp) {
        mBubble1PivotX = mBubble1LeftOffset - Utils.convertDpToFloatPixel(mContext, dp);
        mBubble2PivotX = mBubble2LeftOffset - Utils.convertDpToFloatPixel(mContext, dp);
        mBubble3PivotX = mBubble3LeftOffset - Utils.convertDpToFloatPixel(mContext, dp);
        mBubble4PivotX = mBubble4LeftOffset - Utils.convertDpToFloatPixel(mContext, dp);
        mBubble5PivotX = mBubble5LeftOffset - Utils.convertDpToFloatPixel(mContext, dp);
        mBubble6PivotX = mBubble6LeftOffset - Utils.convertDpToFloatPixel(mContext, dp);
    }

    private void resetOriginals() {
        setPercent(0);
        mBounce = setVariable(0);
        mScale = setVariable(0);
        mBubble1Move = setVariable(0);
        mBubble2Move = setVariable(0);
        mBubble3Move = setVariable(0);
        mBubble4Move = setVariable(0);
        mBubble5Move = setVariable(0);
        mBubble6Move = setVariable(0);
        mFlameScale = setVariable(0);
        mCoverJump = setVariable(0);
    }

    private void setupAnimations() {
        AnimationFactory animationFactory = new AnimationFactory();
        mBounceAnimation = animationFactory.getBounce(new Animation() {
            @Override
            public void applyTransformation(float interpolatedTime, Transformation t) {
                t.setTransformationType(Transformation.TYPE_BOTH);
                mBounce = setVariable(interpolatedTime);
            }

        });

        mScaleAnimation = animationFactory.getScale(new Animation() {

            @Override
            public void applyTransformation(float interpolatedTime, Transformation t) {
                mScale = setVariable(interpolatedTime);
            }
        });


        mFlameScaleAnimation = animationFactory.getFlameScale(new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                mFlameScale = setVariable(interpolatedTime);
            }
        });


        mFlameBurnAnimation = animationFactory.getFlameBurn(new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                mFlameBurn = setVariable(1f - interpolatedTime);
                mFlameScale = setVariable(interpolatedTime);
            }
        });


        mBubble1Animation = animationFactory.getBubble(new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                mBubble1Move = setVariable(interpolatedTime);

            }
        }, 0);

        mBubble2Animation = animationFactory.getBubble(new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                mBubble2Move = setVariable(interpolatedTime);

            }
        }, ANIMATION_BUBBLE1_OFFSET);

        mBubble3Animation = animationFactory.getBubble(new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                mBubble3Move = setVariable(interpolatedTime);

            }
        }, ANIMATION_BUBBLE2_OFFSET);

        mBubble4Animation = animationFactory.getBubble(new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                mBubble4Move = setVariable(interpolatedTime);

            }
        }, ANIMATION_BUBBLE3_OFFSET);

        mBubble5Animation = animationFactory.getBubble(new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                mBubble5Move = setVariable(interpolatedTime);

            }
        }, ANIMATION_BUBBLE4_OFFSET);

        mBubble6Animation = animationFactory.getBubble(new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                mBubble6Move = setVariable(interpolatedTime);

            }
        }, ANIMATION_BUBBLE5_OFFSET);

        mCoverAnimation = animationFactory.getCover(new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                mCoverJump = setVariable(interpolatedTime);

            }
        });

    }


}




