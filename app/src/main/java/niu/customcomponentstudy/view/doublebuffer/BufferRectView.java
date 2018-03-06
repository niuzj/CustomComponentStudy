package niu.customcomponentstudy.view.doublebuffer;

import android.content.Context;
import android.graphics.Bitmap;
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
 */

public class BufferRectView extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;

    private float initX;
    private float initY;
    private float left;
    private float top;
    private float right;
    private float bottom;

    public BufferRectView(Context context) {
        super(context);
        init();
    }

    public BufferRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BufferRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
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
                mPath.reset();
                mPath.addRect(new RectF(left, top, right, bottom), Path.Direction.CW);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                calculateRect(event);
                mPath.reset();
                mPath.addRect(new RectF(left, top, right, bottom), Path.Direction.CW);
                invalidate();
                mCanvas.drawPath(mPath, mPaint);
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
