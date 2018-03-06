package niu.customcomponentstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Qinlian_niu on 2018/2/7.
 */

public class RulerView extends View {
    Paint mPaint;
    int defaultWidth = 800;
    int defaultHeight = 300;
    int maxLength = 150;
    int middleLength = 100;
    int minLength = 50;

    public RulerView(Context context) {
        super(context);
        init();
    }

    public RulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(14);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = defaultWidth;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = defaultHeight;
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRect(canvas);
        drawLines(canvas);
    }

    /**
     * 画最外层的框
     *
     * @param canvas
     */
    private void drawRect(Canvas canvas) {
        canvas.drawRoundRect(new RectF(10, 10, 790, 290), 10, 10, mPaint);
    }

    /**
     * 画刻度线 10cm 长度为700px 每毫米7px
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        canvas.save();
        canvas.translate(40, 0);
        for (int i = 0; i < 101; i++) {
            if (i % 5 == 0) {
                if (i % 10 == 0) {
                    //画长刻度
                    canvas.drawLine(0, 290, 0, 290 - maxLength, mPaint);
                    canvas.drawText((i / 10) + (i == 0 ? "cm" : ""), 0, 290 - maxLength - 20, mPaint);
                } else {
                    //画中刻度
                    canvas.drawLine(0, 290, 0, 290 - middleLength, mPaint);
                }
            } else {
                //普通刻度 短
                canvas.drawLine(0, 290, 0, 290 - minLength, mPaint);
            }
            //向又移动一个刻度
            canvas.translate(7, 0);
        }


        canvas.restore();
    }


}
