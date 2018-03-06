package niu.customcomponentstudy.view.shader_gradient_weitu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Qinlian_niu on 2018/2/26.
 * 扫描渐变
 */

public class SweepGradientView extends View {
    public SweepGradientView(Context context) {
        super(context);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        SweepGradient sweepGradient = new SweepGradient(300, 300, Color.RED, Color.BLUE);
        paint.setShader(sweepGradient);
        Rect rect = new Rect(100, 100, 500, 500);
        canvas.drawRect(rect, paint);

        canvas.translate(0, rect.bottom + 50);
        canvas.drawCircle(300, 300, 200, paint);

    }
}
