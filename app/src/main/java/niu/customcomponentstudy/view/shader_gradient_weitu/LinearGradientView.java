package niu.customcomponentstudy.view.shader_gradient_weitu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Qinlian_niu on 2018/2/24.
 */

public class LinearGradientView extends View {

    private int offset = 50;

    public LinearGradientView(Context context) {
        super(context);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        Rect rect = new Rect(100, 50, 500, 250);
        LinearGradient gradient = new LinearGradient(100, 150, 500, 150, Color.RED, Color.BLUE, Shader.TileMode.CLAMP);
        paint.setShader(gradient);
        canvas.drawRect(rect, paint);

        canvas.translate(0, rect.bottom + offset);
        rect.inset(-50, -50);
        canvas.drawRect(rect, paint);

        canvas.translate(0, rect.bottom + offset);
        rect.inset(100, 100);
        canvas.drawRect(rect, paint);


    }
}
