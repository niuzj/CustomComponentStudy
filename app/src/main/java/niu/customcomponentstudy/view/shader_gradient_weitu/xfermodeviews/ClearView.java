package niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Qinlian_niu on 2018/2/28.
 */

public class ClearView extends BaseView {
    public ClearView(Context context) {
        super(context);
    }

    public ClearView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClearView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap dst = getDst();

        Bitmap src = getSrc();

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        int index = canvas.saveLayer(0, 0, 400, 300, paint, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(src, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawBitmap(dst, 0, 0, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(index);


    }
}
