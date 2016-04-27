package com.yalantis.pulltomakesoup.refresh_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.res.ResourcesCompat;

/**
 * Created by Alexey on 21.04.2016.
 */
public class CreateBitmapFactory {


    private static BitmapFactory.Options mOptions;

    {
        mOptions = new BitmapFactory.Options();
        mOptions.inPreferredConfig = Bitmap.Config.RGB_565;
    }


    static Bitmap getBitmapFromImage(@DrawableRes int id, Context context) {
        return BitmapFactory.decodeResource(context.getResources(), id, mOptions);
    }

    static Bitmap getBitmapFromDrawable(@DrawableRes int id, Context context) {
        Bitmap bitmap;
        Drawable drawable = ResourcesCompat.getDrawable(context.getResources(), id, context.getTheme());
        if (drawable == null) {
            return null;
        }
        bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
