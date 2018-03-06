package niu.customcomponentstudy.view.doublebuffer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Qinlian_niu on 2018/2/8.
 * 绘制手势轨迹 只能记录一小段
 */

public class DoubleBufferTwoView extends View {

    private Paint mPaint;
    private Path mPath;
    private float preX;
    private float preY;
    private float currentX;
    private float currentY;

    public DoubleBufferTwoView(Context context) {
        super(context);
        init();
    }

    public DoubleBufferTwoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DoubleBufferTwoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                preX = event.getX();
                preY = event.getY();
                mPath.moveTo(preX, preY);
                break;

            case MotionEvent.ACTION_MOVE:
                currentX = event.getX();
                currentY = event.getY();
                mPath.lineTo(currentX, currentY);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                currentX = event.getX();
                currentY = event.getY();
                mPath.lineTo(currentX, currentY);
                invalidate();
                break;
        }
        return true;
    }
}
