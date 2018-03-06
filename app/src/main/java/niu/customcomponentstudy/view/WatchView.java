package niu.customcomponentstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * Created by Qinlian_niu on 2018/2/7.
 * 时钟view
 */

public class WatchView extends View {

    private Paint mPaint;
    private int hour;
    private int minute;
    private int second;
    private int width = 400;
    private int height = 400;
    private int radius;
    private int centerX;
    private int centerY;

    public WatchView(Context context) {
        super(context);
        init();
    }

    public WatchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = width;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = height;
        }
        radius = Math.min(widthSize, heightSize) / 2;
        setMeasuredDimension(radius * 2, radius * 2);
        centerX = radius;
        centerY = radius;
        radius -= 10;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calculate();
        drawPlate(canvas);
        drawHour(canvas);
        drawMinute(canvas);
        drawSecond(canvas);
    }

    /**
     * 计算当前时间
     */
    private void calculate() {
        long timeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
    }

    /**
     * 绘制圆盘与刻度
     */
    private void drawPlate(Canvas canvas) {
        canvas.save();
        for (int i = 0; i < 60; i++) {
            canvas.drawCircle(centerX, centerY, radius, mPaint);
            if (i % 5 == 0) {
                //0,5,10,15,20,25,30,35,40,45,50,55,60长刻度
                canvas.drawLine(centerX, 10, centerX, 30, mPaint);
            } else {
                //短刻度
                canvas.drawLine(centerX, 10, centerX, 20, mPaint);
            }
            canvas.rotate(6, centerX, centerY);
        }
        canvas.restore();
    }

    /**
     * 绘制时针
     */
    private void drawHour(Canvas canvas) {
        canvas.save();
        canvas.rotate(hour * 30, centerX, centerY);
        canvas.drawLine(centerX, centerY, centerX, centerY - 50, mPaint);
        canvas.restore();
    }

    /**
     * 绘制分针
     */
    private void drawMinute(Canvas canvas) {
        canvas.save();
        canvas.rotate(minute * 6, centerX, centerY);
        canvas.drawLine(centerX, centerY, centerX, centerY - 100, mPaint);
        canvas.restore();
    }

    /**
     * 绘制秒针
     */
    private void drawSecond(Canvas canvas) {
        canvas.save();
        canvas.rotate(second * 6, centerX, centerY);
        canvas.drawLine(centerX, centerY + 20, centerX, centerY - 150, mPaint);
        canvas.restore();
    }

}
