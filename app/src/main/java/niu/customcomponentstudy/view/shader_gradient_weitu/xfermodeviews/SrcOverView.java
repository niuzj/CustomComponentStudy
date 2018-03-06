package niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Qinlian_niu on 2018/3/5.
 */

public class SrcOverView extends BaseView {
    public SrcOverView(Context context) {
        super(context);
    }

    public SrcOverView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SrcOverView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap dst = getDst();

        Bitmap src = getSrc();

        Bitmap bitmap = getBitmap();

        Canvas c = new Canvas(bitmap);
        c.drawBitmap(dst, 0, 0, null);
        c.drawBitmap(src, 150, 150, null);

        canvas.drawBitmap(bitmap, 0, 0, null);


    }
}
