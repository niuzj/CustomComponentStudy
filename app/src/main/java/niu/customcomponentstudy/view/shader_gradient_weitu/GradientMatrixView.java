package niu.customcomponentstudy.view.shader_gradient_weitu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Qinlian_niu on 2018/2/27.
 */

public class GradientMatrixView extends View {

    private Matrix mMatrix;
    private Paint mPaint;
    private int sweepAngle;
    private Shader mShader;

    public GradientMatrixView(Context context) {
        super(context);
        init();
    }

    public GradientMatrixView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GradientMatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mMatrix = new Matrix();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mShader = new SweepGradient(300, 300, new int[]{Color.RED, Color.YELLOW, Color.GREEN}, null);
        mPaint.setShader(mShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mShader.setLocalMatrix(mMatrix);
        canvas.drawCircle(300, 300, 200, mPaint);
        sweepAngle += 3;
        sweepAngle = sweepAngle == 360 ? 0 : sweepAngle;
        mMatrix.setRotate(sweepAngle, 300, 300);
        invalidate();

    }
}
