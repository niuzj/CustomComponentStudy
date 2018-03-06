package niu.customcomponentstudy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;

import niu.customcomponentstudy.R;


/**
 * Created by Qinlian_niu on 2018/1/18.
 */

public class CircleProgress extends View {
    private Context mContext;
    private int defaultHeight = 300;
    private int defaultWidth = 300;
    private int realSize;
    private float progress;
    private int textSize;

    private Paint circlePaint;
    private Paint progressPaint;
    private Paint textPaint;

    public CircleProgress(Context context) {
        this(context,null);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgress);
        textSize = typedArray.getInt(R.styleable.CircleProgress_textSize,36);
        typedArray.recycle();

        init(context);
    }

    private void init(Context context) {
        mContext = context;
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.GRAY);

        progressPaint = new Paint();
        progressPaint.setAntiAlias(true);
        progressPaint.setColor(Color.RED);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(textSize);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {
            widthSize = defaultWidth;
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = defaultHeight;
        }

        widthSize = widthSize - getPaddingLeft() - getPaddingRight();
        heightSize = heightSize - getPaddingBottom() - getPaddingTop();

        realSize = widthSize > heightSize ? heightSize : widthSize;
        setMeasuredDimension(realSize, realSize);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(realSize / 2, realSize / 2, realSize / 2, circlePaint);

        canvas.drawArc(new RectF(0, 0, realSize, realSize), 0, 360 * progress, true, progressPaint);

        DecimalFormat decimalFormat = new DecimalFormat("#0.0");
        String value = decimalFormat.format(progress * 100);
        canvas.drawText(value, realSize / 2, realSize / 2, textPaint);


    }

    public void setProgress(float progress) {
        this.progress = progress;
        postInvalidate();
    }

}
