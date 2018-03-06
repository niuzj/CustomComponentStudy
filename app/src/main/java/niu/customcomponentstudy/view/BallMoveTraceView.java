package niu.customcomponentstudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Qinlian_niu on 2018/2/5.
 * 能够记录小球轨迹的view
 */

public class BallMoveTraceView extends View {

    private float centerX = 20;
    private float centerY = 100;
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private int radius = 20;
    private List<Float> lines = null;
    private List<Float> tempLines = null;
    private Paint ballPaint;
    private Paint linePaint;
    private float duration;
    private float unitX;
    private float unitY;

    private int width = 800;
    Random mRandom = new Random();


    public BallMoveTraceView(Context context) {
        super(context);
        init();
    }

    public BallMoveTraceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BallMoveTraceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ballPaint = new Paint();
        ballPaint.setStrokeCap(Paint.Cap.ROUND);
        ballPaint.setStrokeJoin(Paint.Join.ROUND);
        ballPaint.setAntiAlias(true);
        ballPaint.setStyle(Paint.Style.FILL);
        ballPaint.setColor(Color.RED);

        linePaint = new Paint();
        linePaint.setColor(Color.BLUE);
        linePaint.setStrokeWidth(2);
        ballPaint.setStrokeCap(Paint.Cap.ROUND);
        ballPaint.setStrokeJoin(Paint.Join.ROUND);
        ballPaint.setAntiAlias(true);

        lines = new ArrayList<>();
        tempLines = new ArrayList<>();
        startX = 20;
        startY = 100;
        lines.add(startX);
        lines.add(startY);

        calculatePath();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(centerX, centerY, radius, ballPaint);
        tempLines.addAll(lines);
        tempLines.add(centerX);
        tempLines.add(centerY);
        canvas.drawLines(getLines(tempLines), linePaint);
        tempLines.clear();

        if ((unitX >= 0 && centerX + unitX > endX) || (unitX < 0 && centerX + unitX < endX)
                || (unitY >= 0 && centerY + unitY > endY) || (unitY < 0 && centerY + unitY < endY)) {
            //当小球到达边界时，重新为小球计算终点，同时把记录当前小球的中心点(不记录endX、endY是因为可能有误差)
            //drawLines时，每2各点即4个数决定一条线，第二条线的起点虽然就是第一条线的终点，但是不能直接用
            lines.add(centerX);
            lines.add(centerY);
            lines.add(centerX);
            lines.add(centerY);
            startX = centerX;
            startY = centerY;
            calculatePath();
        }

        centerX += unitX;
        centerY += unitY;

    }

    private void calculatePath() {
        endX = mRandom.nextFloat() * width;
        endY = mRandom.nextFloat() * width;
        //长的边每次变动5像素
        if (Math.abs(endX - startX) >= Math.abs(endY - startY)) {
            unitX = endX >= startX ? 5 : -5;
            duration = Math.abs(endX - startX) / 5;
            unitY = (endY - startY) / duration;
        } else {
            unitY = endY >= startY ? 5 : -5;
            duration = Math.abs(endY - startY) / 5;
            unitX = (endX - startX) / duration;
        }
    }

    private float[] getLines(List list) {
        float[] lines = new float[list.size()];
        for (int i = 0; i < list.size(); i++) {
            lines[i] = (float) list.get(i);
        }
        return lines;
    }

}
