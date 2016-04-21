package com.yalantis.pulltomakesoup.refresh_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.res.ResourcesCompat;

import com.yalantis.pulltomakesoup.R;

/**
 * Created by Alexey on 21.04.2016.
 */
public class CreateBitmapFactory {

    private Context mContext;
    private BitmapFactory.Options mOptions;

    public CreateBitmapFactory(Context context) {
        mContext = context;
        mOptions = new BitmapFactory.Options();
        mOptions.inPreferredConfig = Bitmap.Config.RGB_565;
    }

    Bitmap getBitmapFromImage(@DrawableRes int id){
      return   BitmapFactory.decodeResource(mContext.getResources(), id, mOptions);
    }

    Bitmap getBitmapFromDrawable(@DrawableRes int id){
        Bitmap bitmap;
        Drawable drawable = ResourcesCompat.getDrawable(mContext.getResources(),id, mContext.getTheme());
        if(drawable == null){
            return null;
        }
        bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
