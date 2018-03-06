package niu.customcomponentstudy.view.doublebuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Qinlian_niu on 2018/2/8.
 * 绘制手势轨迹
 */

public class DoubleBufferOneView extends View {

    private Paint mPaint;
    private float preX;
    private float preY;
    private float currentX;
    private float currentY;
    private Canvas mCanvas;
    private Bitmap mBitmap;

    public DoubleBufferOneView(Context context) {
        super(context);
        init();
    }

    public DoubleBufferOneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DoubleBufferOneView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mBitmap == null) {
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preX = event.getX();
                preY = event.getY();
                mCanvas.drawPoint(preX, preY, mPaint);
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                currentX = event.getX();
                currentY = event.getY();
                mCanvas.drawLine(preX, preY, currentX, currentY, mPaint);
                preX = currentX;
                preY = currentY;
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                currentX = event.getX();
                currentY = event.getY();
                mCanvas.drawLine(preX, preY, currentX, currentY, mPaint);
                mCanvas.drawPoint(currentX, currentY, mPaint);
                invalidate();
                break;
        }

        return true;
    }
}
