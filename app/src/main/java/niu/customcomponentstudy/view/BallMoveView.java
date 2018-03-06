package niu.customcomponentstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Qinlian_niu on 2018/2/5.
 */

public class BallMoveView extends View {


    private float centerX = 20;
    private float centerY = 100;
    private Paint mPaint;
    private float radius = 20;
    private int width = 0;
    private boolean direction = true;


    public BallMoveView(Context context) {
        super(context);
        init(context);
    }

    public BallMoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BallMoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (width == 0) {
            width = this.getMeasuredWidth();
        }

        canvas.drawCircle(centerX, centerY, radius, mPaint);

        if (centerX + 5 > width - radius) {
            direction = false;
        } else if (centerX - 5 < radius) {
            direction = true;
        }

        centerX = direction ? centerX + 5 : centerX - 5;


    }
}
