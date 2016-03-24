package com.yalantis.pulltomakesoup.refresh_view;

import android.view.animation.AnimationSet;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;

import com.yalantis.pulltomakesoup.PullToRefreshView;
import com.yalantis.pulltomakesoup.R;
import com.yalantis.pulltomakesoup.utils.Utils;

/**
 * Created by Alexey on 28.01.2016.
 */
public class SoupRefreshView extends BaseRefreshView implements Animatable {

    private PullToRefreshView mParent;
    private Matrix mMatrix;
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
    private static final int ANIMATION_DURATION = 700;
    private static final int ANIMATION_SCALE_DURATION = 500;
    private static final int ANIMATION_FLAME_SCALE_DURATION = 100;
    private static final int ANIMATION_FLAME_BURN_DURATION = 180;
    private static final int ANIMATION_BUBBLE_DURATION = 980;
    private static final int ANIMATION_COVER_DURATION = 580;


    private static final int ANIMATION_BUBBLE1_OFFSET = 200;
    private static final int ANIMATION_BUBBLE2_OFFSET  = 300;
    private static final int ANIMATION_BUBBLE3_OFFSET  = 500;
    private static final int ANIMATION_BUBBLE4_OFFSET  = 700;
    private static final int ANIMATION_BUBBLE5_OFFSET  = 800;
    private static final int ANIMATION_COVER_OFFSET  = 20;

    private float mTop;
    private float mScreenWidth;
    private float mScreenHeight;


    private static final Interpolator BOUNCE_INTERPOLATOR = new BounceInterpolator();
    private static final Interpolator ACELERATE_INTERPOLATOR = new AccelerateInterpolator();
    private static final Interpolator DECELERATE_INTERPOLATOR = new AccelerateDecelerateInterpolator();

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

    private boolean isCoverDroped;
    private boolean isShadowDisplayed;
    private float mScale;
    private float mFlameScale;
    private float mFlameBurn;
    private float mFlameMinScale = 0.9f;
    private float mBubble1Move;
    private float mBubble2Move;
    private float mBubble3Move;
    private float mBubble4Move;
    private float mBubble5Move;
    private float mBubble6Move;
    private float mBubbleScaleOffset =  Utils.convertDpToFloatPixel(getContext(),100);
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


    public SoupRefreshView(Context context, final PullToRefreshView layout) {
        super(context, layout);
        mParent = layout;
        mMatrix = new Matrix();
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
        mScreenHeight = getContext().getResources().getDisplayMetrics().heightPixels;


        createBitmaps();

        mPanTopOffset = Utils.convertDpToFloatPixel(getContext(), 60);

        mCoverOffset = Utils.convertDpToFloatPixel(getContext(), 90);

        mCoverStartPointY = Utils.convertDpToFloatPixel(getContext(), 10);
        mCoverFinalPointY = Utils.convertDpToFloatPixel(getContext(), 70);


        mCarrotStartPointX = (mScreenWidth / 100) * 79;
        mCarrotFinalPointX = (mScreenWidth / 100) * 30.0f;


        mCarrotFinalPointY = Utils.convertDpToFloatPixel(getContext(), 245);
        mCarrotStartPointY = Utils.convertDpToFloatPixel(getContext(), 161);
        mCarrotOffset = Utils.convertDpToFloatPixel(getContext(), 90);

        mPotatoFinalPointX = (mScreenWidth / 100) * -25;
        mPotatoStartPointX = (mScreenWidth / 100) * 14.5f;


        mPotatoStartPointY = Utils.convertDpToFloatPixel(getContext(), 150);
        mPotatoFinalPointY = Utils.convertDpToFloatPixel(getContext(), 237);
        mPotatoOffset = Utils.convertDpToFloatPixel(getContext(), 90);


        mRightPeaFinalPointX = (mScreenWidth / 100) * 30.5f;
        mRightPeaStartPointX = (mScreenWidth / 100) * 88;

        mRightPeaStartPointY = Utils.convertDpToFloatPixel(getContext(), 150);
        mRightPeaFinalPointY = Utils.convertDpToFloatPixel(getContext(), 242);
        mRightPeaOffset = Utils.convertDpToFloatPixel(getContext(), 90);

        mLeftPeaStartPointX = (mScreenWidth / 100) * 7.5f;
        mLeftPeaFinalPointX = (mScreenWidth / 100) * -45;

        mLeftPeaStartPointY = Utils.convertDpToFloatPixel(getContext(), 150);
        mLeftPeaFinalPointY = Utils.convertDpToFloatPixel(getContext(), 242);
        mLeftPeaOffset = Utils.convertDpToFloatPixel(getContext(), 90);

        mFlame1TopOffset = Utils.convertDpToFloatPixel(getContext(), 134);
        mFlame1LeftOffset = (mScreenWidth / 100) * 42;

        mFlame2LeftOffset = (mScreenWidth / 100) * 45;

        mFlame3TopOffset = Utils.convertDpToFloatPixel(getContext(), 132);
        mFlame3LeftOffset = (mScreenWidth / 100) * 48.5f;

        mFlame4TopOffset = Utils.convertDpToFloatPixel(getContext(), 134);
        mFlame4LeftOffset = (mScreenWidth / 100) * 51.5f;

        mFlame5TopOffset = Utils.convertDpToFloatPixel(getContext(), 134);
        mFlame5LeftOffset = (mScreenWidth / 100) * 54f;

        mBubble1TopOffset = Utils.convertDpToFloatPixel(getContext(), 144);
        mBubble1LeftOffset = (mScreenWidth / 100) * 40;

        mBubble2TopOffset = Utils.convertDpToFloatPixel(getContext(), 144);
        mBubble2LeftOffset = (mScreenWidth / 100) * 42;

        mBubble3TopOffset = Utils.convertDpToFloatPixel(getContext(), 144);
        mBubble3LeftOffset = (mScreenWidth / 100) * 44;

        mBubble4TopOffset = Utils.convertDpToFloatPixel(getContext(), 144);
        mBubble4LeftOffset = (mScreenWidth / 100) * 46;

        mBubble5TopOffset = Utils.convertDpToFloatPixel(getContext(), 144);
        mBubble5LeftOffset = (mScreenWidth / 100) * 48;

        mBubble6TopOffset = Utils.convertDpToFloatPixel(getContext(), 144);
        mBubble6LeftOffset = (mScreenWidth / 100) * 50;

        mTop = -mParent.getTotalDragDistance();


    }

    private void createBitmaps() {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        mPan = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.pan, options);
        mCircle = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.circle, options);
        mPotato = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.potato, options);
        mCarrot = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.carrot, options);
        mRightPea = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.pea, options);
        mLeftPea = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.pea, options);
        mCover = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.pan_cover, options);
        mWater = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.water, options);
        mShadow = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.shadow, options);
        mFlame1 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.flame1, options);
        mFlame2 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.flame2, options);
        mFlame3 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.flame3, options);
        mFlame4 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.flame4, options);
        mFlame5 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.flame5, options);

        Drawable drawable = getContext().getResources().getDrawable(R.drawable.bubble);
        mBubble = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBubble);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
    }

    @Override
    public void setPercent(float percent, boolean invalidate) {
        setPercent(percent);
        if (invalidate) setBounce(percent);
    }

    @Override
    public void offsetTopAndBottom(int offset) {
        mTop += offset;
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
        drawFlame1(canvas);
        drawFlame2(canvas);
        drawFlame3(canvas);
        drawFlame5(canvas);
        drawFlame4(canvas);
        drawBubble(canvas);
        drawBubble2(canvas);
        drawBubble3(canvas);
        drawBubble4(canvas);
        drawBubble5(canvas);
        drawBubble6(canvas);

        canvas.restoreToCount(saveCount);
    }


    private void drawCover(Canvas canvas) {
        Matrix matrix = mMatrix;
        matrix.reset();
        float offsetX;
        float offsetY;

        if (isRefreshing) {
            offsetX = (mScreenWidth / 2) - (mCover.getWidth() / 2);
            offsetY = ((((mBounce * mCoverFinalPointY) - mCoverStartPointY) * ((mBounce * mCoverFinalPointY) - mCoverStartPointY)) / mCoverOffset);
            isCoverDroped = true;
            if (isShadowDisplayed) {
                matrix.postRotate(-5,0, 0);
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
        float offsetY = (mScreenHeight / 100) * -1f;
        matrix.postScale(dragPercent, dragPercent, Utils.convertDpToPixel(getContext(), 100), Utils.convertDpToPixel(getContext(), 40));
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
            offsetY = (offsetY) + Utils.convertDpToFloatPixel(getContext(), 25) * bouncePercent;
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
            offsetY = (offsetY) + Utils.convertDpToFloatPixel(getContext(), 25) * bouncePercent;
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
            offsetY = (offsetY) + Utils.convertDpToFloatPixel(getContext(), 25) * bouncePercent;
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
            offsetY = (offsetY) + Utils.convertDpToFloatPixel(getContext(), 25) * bouncePercent;
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
        final float offsetY = (mPanTopOffset * dragPercent) + Utils.convertDpToFloatPixel(getContext(), 10);
        if (isCoverDroped) {
            matrix.postScale(1, mScale, Utils.convertDpToPixel(getContext(), 48), Utils.convertDpToPixel(getContext(), 60));
            matrix.postTranslate(offsetX, offsetY);
            Paint paint = new Paint();
            canvas.drawBitmap(mWater, matrix, paint);
        }
    }

    private void drawFlame1(final Canvas canvas) {
        final Matrix matrix = mMatrix;
        matrix.reset();

        final float offsetX = mFlame1LeftOffset;
        final float offsetY = mFlame1TopOffset;
        if (isShadowDisplayed) {
            matrix.postTranslate(offsetX, offsetY);
            matrix.postScale(Math.max(mFlameMinScale, mFlameScale), Math.max(mFlameMinScale, mFlameScale), mFlame4LeftOffset - Utils.convertDpToFloatPixel(getContext(), 15), mFlame4TopOffset + 50);
            Paint paint = new Paint();
            float alpha = (Math.max(0.5f, mFlameScale)) * 255;
            paint.setAlpha((int) alpha);
            canvas.drawBitmap(mFlame1, matrix, paint);
        }
    }

    private void drawFlame2(final Canvas canvas) {
        final Matrix matrix = mMatrix;
        matrix.reset();

        final float offsetX = mFlame2LeftOffset;
        final float offsetY = mFlame1TopOffset;
        if (isShadowDisplayed) {
            matrix.postTranslate(offsetX, offsetY);
            matrix.postScale(Math.max(mFlameMinScale, mFlameBurn), Math.max(mFlameMinScale, mFlameBurn), mFlame2LeftOffset + Utils.convertDpToFloatPixel(getContext(), 10), mFlame1TopOffset + 50);
            Paint paint = new Paint();
            float alpha = (Math.max(0.5f, mFlameBurn)) * 255;
            paint.setAlpha((int) alpha);
            canvas.drawBitmap(mFlame2, matrix, paint);
        }
    }

    private void drawFlame3(final Canvas canvas) {
        final Matrix matrix = mMatrix;
        matrix.reset();

        final float offsetX = mFlame3LeftOffset;
        final float offsetY = mFlame3TopOffset;
        if (isShadowDisplayed) {
            matrix.postTranslate(offsetX, offsetY);
            matrix.postScale(Math.max(mFlameMinScale, mFlameScale), Math.max(mFlameMinScale, mFlameScale), mFlame3LeftOffset - Utils.convertDpToFloatPixel(getContext(), 11), mFlame3TopOffset + 50);
            Paint paint = new Paint();
            float alpha = (Math.max(0.5f, mFlameScale)) * 255;
            paint.setAlpha((int) alpha);
            canvas.drawBitmap(mFlame3, matrix, paint);
        }
    }

    private void drawFlame4(final Canvas canvas) {
        final Matrix matrix = mMatrix;
        matrix.reset();

        final float offsetX = mFlame4LeftOffset;
        final float offsetY = mFlame4TopOffset;
        if (isShadowDisplayed) {
            matrix.postTranslate(offsetX, offsetY);
            matrix.postScale(Math.max(mFlameMinScale, mFlameBurn), Math.max(mFlameMinScale, mFlameBurn), mFlame4LeftOffset, mFlame4TopOffset + 50);
            Paint paint = new Paint();
            float alpha = (Math.max(0.5f, mFlameBurn)) * 255;
            paint.setAlpha((int) alpha);
            canvas.drawBitmap(mFlame4, matrix, paint);
        }
    }

    private void drawFlame5(final Canvas canvas) {
        final Matrix matrix = mMatrix;
        matrix.reset();

        final float offsetX = mFlame5LeftOffset;
        final float offsetY = mFlame5TopOffset;
        if (isShadowDisplayed) {
            matrix.postTranslate(offsetX, offsetY);
            matrix.postScale(Math.max(mFlameMinScale, mFlameScale), Math.max(mFlameMinScale, mFlameScale), mFlame5LeftOffset, mFlame5TopOffset + 50);
            Paint paint = new Paint();
            float alpha = (Math.max(0.5f, mFlameScale)) * 255;
            paint.setAlpha((int) alpha);
            canvas.drawBitmap(mFlame5, matrix, paint);
        }
    }


    private void drawShadow(final Canvas canvas) {
        final Matrix matrix = mMatrix;
        matrix.reset();

        float dragPercent = Math.min(1f, Math.abs(mPercent));
        final float offsetX = (mScreenWidth / 2) - (mShadow.getWidth() / 2) + Utils.convertDpToFloatPixel(getContext(), 17f);
        if (isCoverDroped) {
            final float offsetY = mPanTopOffset * dragPercent;
            matrix.postTranslate(offsetX, offsetY);
            Paint paint = new Paint();
            float alpha = (mBounce / 2) * 500;
            paint.setAlpha((int) alpha);
            canvas.drawBitmap(mShadow, matrix, paint);
        }
    }

    private void drawBubble(final Canvas canvas) {
        final Matrix matrix = mMatrix;
        matrix.reset();

        final float offsetX = mBubble1LeftOffset;
        float offsetY = (mBubble1TopOffset - mBubbleScaleOffset) - mBubbleScaleOffset * mBubble1Move;
        if (isShadowDisplayed) {
            if (mBubble1Move < 0.48) {
                matrix.postScale(mBubble1Move, mBubble1Move, mBubble1LeftOffset - Utils.convertDpToFloatPixel(getContext(), 140), mBubble1TopOffset);
                matrix.postTranslate(offsetX, offsetY);
                Paint paint = new Paint();
                canvas.drawBitmap(mBubble, matrix, paint);
            }
        }
    }


    private void drawBubble2(final Canvas canvas) {
        final Matrix matrix = mMatrix;
        matrix.reset();

        final float offsetX = mBubble2LeftOffset;
        float offsetY = (mBubble2TopOffset - mBubbleScaleOffset) - mBubbleScaleOffset * mBubble2Move;
        if (isShadowDisplayed) {
            if (mBubble2Move < 0.48) {
                matrix.postScale(mBubble2Move, mBubble2Move, mBubble2LeftOffset - Utils.convertDpToFloatPixel(getContext(), 140), mBubble2TopOffset);
                matrix.postTranslate(offsetX, offsetY);
                Paint paint = new Paint();
                canvas.drawBitmap(mBubble, matrix, paint);
            }
        }
    }

    private void drawBubble3(final Canvas canvas) {
        final Matrix matrix = mMatrix;
        matrix.reset();

        final float offsetX = mBubble3LeftOffset;
        float offsetY = (mBubble3TopOffset - mBubbleScaleOffset) - mBubbleScaleOffset * mBubble3Move;
        if (isShadowDisplayed) {
            if (mBubble3Move < 0.48) {
                matrix.postScale(mBubble3Move, mBubble3Move, mBubble3LeftOffset - Utils.convertDpToFloatPixel(getContext(), 140), mBubble3TopOffset);
                matrix.postTranslate(offsetX, offsetY);
                Paint paint = new Paint();
                canvas.drawBitmap(mBubble, matrix, paint);
            }
        }
    }

    private void drawBubble4(final Canvas canvas) {
        final Matrix matrix = mMatrix;
        matrix.reset();

        final float offsetX = mBubble4LeftOffset;
        float offsetY = (mBubble4TopOffset - mBubbleScaleOffset) - mBubbleScaleOffset * mBubble4Move;
        if (isShadowDisplayed) {
            if (mBubble4Move < 0.48) {
                matrix.postScale(mBubble4Move, mBubble4Move, mBubble4LeftOffset - Utils.convertDpToFloatPixel(getContext(), 140), mBubble4TopOffset);
                matrix.postTranslate(offsetX, offsetY);
                Paint paint = new Paint();
                canvas.drawBitmap(mBubble, matrix, paint);
            }
        }
    }

    private void drawBubble5(final Canvas canvas) {
        final Matrix matrix = mMatrix;
        matrix.reset();

        final float offsetX = mBubble5LeftOffset;
        float offsetY = (mBubble5TopOffset - mBubbleScaleOffset) - mBubbleScaleOffset * mBubble5Move;
        if (isShadowDisplayed) {
            if (mBubble5Move < 0.48) {
                matrix.postScale(mBubble5Move, mBubble5Move, mBubble5LeftOffset - Utils.convertDpToFloatPixel(getContext(), 140), mBubble5TopOffset);
                matrix.postTranslate(offsetX, offsetY);
                Paint paint = new Paint();
                canvas.drawBitmap(mBubble, matrix, paint);
            }
        }
    }

    private void drawBubble6(final Canvas canvas) {
        final Matrix matrix = mMatrix;
        matrix.reset();

        final float offsetX = mBubble6LeftOffset;
        float offsetY = (mBubble6TopOffset - mBubbleScaleOffset) - mBubbleScaleOffset * mBubble6Move;
        if (isShadowDisplayed) {
            if (mBubble6Move < 0.48) {
                matrix.postScale(mBubble6Move, mBubble6Move, mBubble6LeftOffset - Utils.convertDpToFloatPixel(getContext(), 140), mBubble6TopOffset);
                matrix.postTranslate(offsetX, offsetY);
                Paint paint = new Paint();
                canvas.drawBitmap(mBubble, matrix, paint);
            }
        }
    }

    public void setPercent(float percent) {
        mPercent = percent;
    }

    public void setBounce(float rotate) {

        mBounce = rotate;
        invalidateSelf();
    }

    public void setScale(float scale) {

        mScale = scale;
        invalidateSelf();
    }

    public void setFlameScale(float scale) {

        mFlameScale = scale;
        invalidateSelf();
    }

    public void setBubble1Move(float bubbleMove) {
        mBubble1Move = bubbleMove;
        invalidateSelf();
    }

    public void setBubble2Move(float bubbleMove) {
        mBubble2Move = bubbleMove;
        invalidateSelf();
    }

    public void setBubble3Move(float bubbleMove) {
        mBubble3Move = bubbleMove;
        invalidateSelf();
    }

    public void setBubble4Move(float bubbleMove) {
        mBubble4Move = bubbleMove;
        invalidateSelf();
    }

    public void setBubble5Move(float bubbleMove) {
        mBubble5Move = bubbleMove;
        invalidateSelf();
    }

    public void setBubble6Move(float bubbleMove) {
        mBubble6Move = bubbleMove;
        invalidateSelf();
    }

    public void setCoverJump(float coverJump) {
        mCoverJump = coverJump;
        invalidateSelf();
    }

    public void resetOriginals() {
        setPercent(0);
        setBounce(0);
        setScale(0);
        setBubble1Move(0);
        setBubble2Move(0);
        setBubble3Move(0);
        setBubble4Move(0);
        setBubble5Move(0);
        setBubble6Move(0);
        setFlameScale(0);
        setCoverJump(0);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
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
        isCoverDroped = false;
        isShadowDisplayed = false;
        resetOriginals();
    }


    private void setupAnimations() {

        mBounceAnimation = new Animation() {
            @Override
            public void applyTransformation(float interpolatedTime, Transformation t) {
                t.setTransformationType(Transformation.TYPE_BOTH);
                setBounce(interpolatedTime);
            }

        };
        mBounceAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        mBounceAnimation.setDuration(ANIMATION_DURATION);
        mScaleAnimation = new Animation() {

            @Override
            public void applyTransformation(float interpolatedTime, Transformation t) {
                setScale(interpolatedTime);
            }
        };
        mScaleAnimation.setInterpolator(ACELERATE_INTERPOLATOR);
        mScaleAnimation.setDuration(ANIMATION_SCALE_DURATION);

        mFlameScaleAnimation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setFlameScale(interpolatedTime);
            }
        };
        mFlameScaleAnimation.setInterpolator(DECELERATE_INTERPOLATOR);
        mFlameScaleAnimation.setDuration(ANIMATION_FLAME_SCALE_DURATION);

        mFlameBurnAnimation = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                mFlameBurn = 1f - interpolatedTime;
                setFlameScale(interpolatedTime);
            }
        };
        mFlameBurnAnimation.setInterpolator(DECELERATE_INTERPOLATOR);
        mFlameBurnAnimation.setDuration(ANIMATION_FLAME_BURN_DURATION);
        mFlameBurnAnimation.setRepeatMode(Animation.REVERSE);
        mFlameBurnAnimation.setRepeatCount(Animation.INFINITE);

        mBubble1Animation = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setBubble1Move(interpolatedTime);

            }
        };
        mBubble1Animation.setInterpolator(DECELERATE_INTERPOLATOR);
        mBubble1Animation.setDuration(ANIMATION_BUBBLE_DURATION);
        mBubble1Animation.setRepeatMode(Animation.RESTART);
        mBubble1Animation.setRepeatCount(Animation.INFINITE);

        mBubble2Animation = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setBubble2Move(interpolatedTime);

            }
        };
        mBubble2Animation.setInterpolator(DECELERATE_INTERPOLATOR);
        mBubble2Animation.setDuration(ANIMATION_BUBBLE_DURATION);
        mBubble2Animation.setStartOffset(ANIMATION_BUBBLE1_OFFSET);
        mBubble2Animation.setRepeatMode(Animation.RESTART);
        mBubble2Animation.setRepeatCount(Animation.INFINITE);

        mBubble3Animation = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setBubble3Move(interpolatedTime);

            }
        };
        mBubble3Animation.setInterpolator(DECELERATE_INTERPOLATOR);
        mBubble3Animation.setDuration(ANIMATION_BUBBLE_DURATION);
        mBubble3Animation.setStartOffset(ANIMATION_BUBBLE2_OFFSET);
        mBubble3Animation.setRepeatMode(Animation.RESTART);
        mBubble3Animation.setRepeatCount(Animation.INFINITE);

        mBubble4Animation = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setBubble4Move(interpolatedTime);

            }
        };
        mBubble4Animation.setInterpolator(DECELERATE_INTERPOLATOR);
        mBubble4Animation.setDuration(ANIMATION_BUBBLE_DURATION);
        mBubble4Animation.setStartOffset(ANIMATION_BUBBLE3_OFFSET);
        mBubble4Animation.setRepeatMode(Animation.RESTART);
        mBubble4Animation.setRepeatCount(Animation.INFINITE);

        mBubble5Animation = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setBubble5Move(interpolatedTime);

            }
        };
        mBubble5Animation.setInterpolator(DECELERATE_INTERPOLATOR);
        mBubble5Animation.setDuration(ANIMATION_BUBBLE_DURATION);
        mBubble5Animation.setStartOffset(ANIMATION_BUBBLE4_OFFSET);
        mBubble5Animation.setRepeatMode(Animation.RESTART);
        mBubble5Animation.setRepeatCount(Animation.INFINITE);

        mBubble6Animation = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setBubble6Move(interpolatedTime);

            }
        };
        mBubble6Animation.setInterpolator(DECELERATE_INTERPOLATOR);
        mBubble6Animation.setDuration(ANIMATION_BUBBLE_DURATION);
        mBubble6Animation.setStartOffset(ANIMATION_BUBBLE5_OFFSET);
        mBubble6Animation.setRepeatMode(Animation.RESTART);
        mBubble6Animation.setRepeatCount(Animation.INFINITE);

        mCoverAnimation = new Animation() {

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                setCoverJump(interpolatedTime);

            }
        };
        mCoverAnimation.setInterpolator(BOUNCE_INTERPOLATOR);
        mCoverAnimation.setDuration(ANIMATION_COVER_DURATION);
        mCoverAnimation.setStartOffset(ANIMATION_COVER_OFFSET);
        mCoverAnimation.setRepeatMode(Animation.REVERSE);
        mCoverAnimation.setRepeatCount(Animation.INFINITE);
    }


}




