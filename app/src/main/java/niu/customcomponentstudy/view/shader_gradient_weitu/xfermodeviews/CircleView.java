package niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import niu.customcomponentstudy.R;


/**
 * Created by Qinlian_niu on 2018/3/5.
 */

public class CircleView extends View {

    private Bitmap dst;
    private int imageWidth;
    private int imageHeight;
    private int radius;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        dst = BitmapFactory.decodeResource(getResources(), R.mipmap.beautiful);
        imageWidth = dst.getWidth();
        imageHeight = dst.getHeight();
        radius = Math.min(imageWidth, imageHeight) / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(imageWidth, imageHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        Bitmap src = getSrc(paint);

        int index = canvas.saveLayer(imageWidth / 2 - radius, 0, imageWidth / 2 + radius, imageHeight, paint, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(dst,0,0,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(src,imageWidth / 2 - radius,0,paint);
        paint.setXfermode(null);
        canvas.restoreToCount(index);


    }

    private Bitmap getSrc(Paint paint) {
        Bitmap src = Bitmap.createBitmap(radius * 2, radius * 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(src);
        canvas.drawCircle(radius, radius, radius, paint);
        return src;
    }
}
