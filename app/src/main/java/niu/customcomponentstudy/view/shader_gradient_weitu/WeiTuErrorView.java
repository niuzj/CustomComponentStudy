package niu.customcomponentstudy.view.shader_gradient_weitu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Qinlian_niu on 2018/2/27.
 * 没有使用图层情况下的位图运算，失败案例
 */

public class WeiTuErrorView extends View {
    public WeiTuErrorView(Context context) {
        super(context);
    }

    public WeiTuErrorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WeiTuErrorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 在绘制src时，没有使用layer，得不到想要的MODE.SRC效果
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);

        //绘制圆形的Canvas，为dst
        paint.setColor(Color.GREEN);
        Bitmap dst = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
        Canvas canvasDst = new Canvas(dst);
        canvasDst.drawCircle(150, 150, 150, paint);

        //绘制矩形的Canvas，为src
        paint.setColor(Color.YELLOW);
        Bitmap src = Bitmap.createBitmap(400, 300, Bitmap.Config.ARGB_8888);
        Canvas canvasSrc = new Canvas(src);
        canvasSrc.drawRect(new Rect(0, 0, 400, 300), paint);

        //准备第三个Canvas，用来执行位图操作
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap bitmap = Bitmap.createBitmap(550, 450, Bitmap.Config.ARGB_8888);
        Canvas canvasBitmap = new Canvas(bitmap);
        //先绘制dst
        canvasBitmap.drawBitmap(dst, 0, 0, p);
        //再设置运算模式
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        //再绘制src
        canvasBitmap.drawBitmap(src, 150, 150, p);
        //清空运算模式
        p.setXfermode(null);

        canvas.drawBitmap(bitmap, 0, 0, null);


    }
}
