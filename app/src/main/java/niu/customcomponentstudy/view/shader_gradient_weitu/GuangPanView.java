package niu.customcomponentstudy.view.shader_gradient_weitu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Qinlian_niu on 2018/3/6.
 */

public class GuangPanView extends View {

    private Paint mPaint;
    private Matrix mMatrix;
    private float degree;

    private Shader mShaderMain;
    private Shader mShaderStroke;

    private Bitmap mBitmapSrc;
    private Canvas mCanvasSrc;

    public GuangPanView(Context context) {
        super(context);
        init();
    }

    public GuangPanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GuangPanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMatrix = new Matrix();
        mShaderMain = new SweepGradient(400, 400, new int[]{Color.RED, Color.YELLOW, Color.BLUE, Color.YELLOW
                , Color.BLUE, Color.GREEN, Color.BLUE, Color.YELLOW, Color.BLUE, Color.YELLOW, Color.RED}, null);
        mShaderStroke = new SweepGradient(400, 400, new int[]{Color.GRAY, Color.WHITE, Color.GRAY,
                Color.GRAY, Color.WHITE, Color.GRAY,
                Color.GRAY, Color.WHITE, Color.GRAY,
                Color.GRAY, Color.WHITE, Color.GRAY,}, null);
        mBitmapSrc = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
        mCanvasSrc = new Canvas(mBitmapSrc);
        mCanvasSrc.drawCircle(400, 400, 400, mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int index = canvas.saveLayer(0, 0, 800, 800, mPaint, Canvas.ALL_SAVE_FLAG);

        canvas.drawColor(Color.WHITE);

        mPaint.setStyle(Paint.Style.STROKE);
        mShaderStroke.setLocalMatrix(mMatrix);
        mPaint.setShader(mShaderStroke);
        mPaint.setStrokeWidth(20);
        canvas.drawCircle(400, 400, 390, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mShaderMain.setLocalMatrix(mMatrix);
        mPaint.setShader(mShaderMain);
        mPaint.setStrokeWidth(0);
        canvas.drawCircle(400, 400, 380, mPaint);

        mPaint.setShader(null);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(30);
        mPaint.setColor(Color.GRAY);
        canvas.drawCircle(400, 400, 60, mPaint);

        mPaint.setShader(null);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(400, 400, 40, mPaint);

        mPaint.setShader(null);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(0);
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(400, 400, 40, mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        canvas.drawBitmap(mBitmapSrc, 0, 0, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(index);


        degree += 3;
        degree = degree == 360 ? 0 : degree;
        mMatrix.setRotate(degree, 400, 400);
        invalidate();


    }
}
