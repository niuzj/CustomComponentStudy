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
 * Created by Qinlian_niu on 2018/2/6.
 */

public class CoordinateView extends View {

    private Paint mPaint;

    public CoordinateView(Context context) {
        super(context);
        init();
    }

    public CoordinateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CoordinateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        //平移
        for (int i = 0; i < 5; i++) {
            canvas.drawRect(new RectF(10, 10, 50, 50), mPaint);
            canvas.translate(30, 30);
        }
        canvas.restore();

        canvas.translate(0, 280);

        canvas.save();
        //缩放
        for (int i = 0; i < 5; i++) {
            canvas.drawRect(new RectF(10, 10, 410, 410), mPaint);
            canvas.scale(0.8f, 0.8f, 210f, 210f);
        }

        canvas.restore();

        canvas.translate(0, 450);

        canvas.save();
        //旋转
        for (int i = 0; i < 12; i++) {
            canvas.drawCircle(100, 100, 100, mPaint);
            canvas.drawLine(100, 0, 100, 10, mPaint);
            canvas.rotate(30, 100, 100);
        }
        canvas.restore();

    }


}
