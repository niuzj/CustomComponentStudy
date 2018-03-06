package niu.customcomponentstudy.view.shader_gradient_weitu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Qinlian_niu on 2018/2/26.
 */

public class RadialGradientView extends View {
    public RadialGradientView(Context context) {
        super(context);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);

        RadialGradient gradient = new RadialGradient(300, 300, 200, Color.RED, Color.BLUE, Shader.TileMode.CLAMP);
        paint.setShader(gradient);
        Rect rect = new Rect(100, 100, 500, 500);
        canvas.drawRect(rect, paint);

        canvas.translate(0, rect.bottom + 100);
        canvas.drawCircle(300, 300, 200, paint);

    }
}
