package niu.customcomponentstudy.view.doublebuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Qinlian_niu on 2018/2/9.
 * 用Bitmap、Canvas、Path实现根据手势变动的二阶贝塞尔曲线
 */

public class DoubleBufferFourView extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private float preX;
    private float preY;
    private float currentX;
    private float currentY;
    private float controlX = 500;
    private float controlY = 0;

    public DoubleBufferFourView(Context context) {
        super(context);
        init();
    }

    public DoubleBufferFourView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DoubleBufferFourView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        //绘制历史轨迹
        canvas.drawBitmap(mBitmap, 0, 0, null);
        //绘制实时轨迹
        canvas.drawPath(mPath, mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preX = currentX = event.getX();
                preY = currentY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                currentX = event.getX();
                currentY = event.getY();
                //注意设值
                mPath.reset();
                mPath.moveTo(preX, preY);
                mPath.quadTo(controlX, controlY, currentX, currentY);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                currentX = event.getX();
                currentY = event.getY();
                mPath.reset();
                mPath.moveTo(preX,preY);
                mPath.quadTo(controlX, controlY, currentX, currentY);
                //抬起手时，保存本次轨迹
                mCanvas.drawPath(mPath, mPaint);
                invalidate();
                break;
        }
        return true;
    }
}
