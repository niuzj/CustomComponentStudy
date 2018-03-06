package niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Qinlian_niu on 2018/2/28.
 */

public class BaseView extends View {

    private Paint mPaint;
    //圆形300x300 green
    private Bitmap dst;
    //矩形400x300 red
    private Bitmap src;
    //位图操作的Bitmap
    private Bitmap mBitmap;

    public BaseView(Context context) {
        super(context);
        init();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(Color.GREEN);
        dst = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
        Canvas canvasDst = new Canvas(dst);
        canvasDst.drawCircle(150, 150, 150, mPaint);


        mPaint.setColor(Color.RED);
        src = Bitmap.createBitmap(400, 300, Bitmap.Config.ARGB_8888);
        Canvas canvasSrc = new Canvas(src);
        canvasSrc.drawRect(new Rect(0, 0, 400, 300), mPaint);

        mBitmap = Bitmap.createBitmap(550, 450, Bitmap.Config.ARGB_8888);
    }

    public Bitmap getDst() {
        return dst;
    }

    public Bitmap getSrc() {
        return src;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }
}
