package niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Qinlian_niu on 2018/2/28.
 */

public class SrcView extends BaseView {
    public SrcView(Context context) {
        super(context);
    }

    public SrcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SrcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap src = getSrc();

        Bitmap bitmap = getBitmap();

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Canvas c = new Canvas(bitmap);
        c.drawBitmap(src, 150, 150, paint);

        canvas.drawBitmap(bitmap, 0, 0, null);

    }
}
