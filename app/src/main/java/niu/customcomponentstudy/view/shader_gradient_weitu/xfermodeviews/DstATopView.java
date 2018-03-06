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
 * Created by Qinlian_niu on 2018/3/5.
 */

public class DstATopView extends BaseView {
    public DstATopView(Context context) {
        super(context);
    }

    public DstATopView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DstATopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap dst = getDst();

        Bitmap src = getSrc();

        Bitmap bitmap = getBitmap();
        Canvas c = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        int index = c.saveLayer(150, 150, 550, 450, paint, Canvas.ALL_SAVE_FLAG);
        c.drawBitmap(dst, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
        c.drawBitmap(src, 150, 150, paint);
        paint.setXfermode(null);
        c.restoreToCount(index);

        canvas.drawBitmap(bitmap, 0, 0, null);

    }
}
