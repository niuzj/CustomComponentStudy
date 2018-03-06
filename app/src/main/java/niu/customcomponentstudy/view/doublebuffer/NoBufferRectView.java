package niu.customcomponentstudy.view.doublebuffer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Qinlian_niu on 2018/2/24.
 * 绘制矩形，无缓存
 */

public class NoBufferRectView extends View {

    //记录要绘制的矩形的参数
    private Path mPath;
    private Paint mPaint;

    private float initX;
    private float initY;
    private float left;
    private float top;
    private float right;
    private float bottom;

    public NoBufferRectView(Context context) {
        super(context);
        init();
    }

    public NoBufferRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NoBufferRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);

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
                initX = event.getX();
                initY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                calculateRect(event);
                //一定要先reset()
                mPath.reset();
                mPath.addRect(new RectF(left, top, right, bottom), Path.Direction.CW);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                calculateRect(event);
                mPath.reset();
                mPath.addRect(new RectF(left, top, right, bottom), Path.Direction.CW);
                invalidate();
                break;
        }
        return true;
    }

    private void calculateRect(MotionEvent event) {
        if (event.getX() > initX) {
            left = initX;
            right = event.getX();
        } else {
            left = event.getX();
            right = initX;
        }

        if (event.getY() > initY) {
            top = initY;
            bottom = event.getY();
        } else {
            top = event.getY();
            bottom = initY;
        }
    }
}
