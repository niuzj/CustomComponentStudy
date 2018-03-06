package niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import niu.customcomponentstudy.R;

/**
 * Created by Qinlian_niu on 2018/3/5.
 * 设计思路：
 * 真正的图片作为背景
 * 参与位图运算的是灰色图和轨迹图，位图运算模式是CLEAR
 */

public class GuaGuaLeView extends View {

    private Bitmap bg;
    private int imageWidth;
    private int imageHeight;

    //用于保存之前的轨迹
    private Bitmap mBitmapStore;
    private Canvas mCanvasStore;
    //轨迹相关
    private Paint mPaintForPath;
    private Path mPath;

    //轨迹图
    private Bitmap mBitmapSrc;
    private Canvas mCanvasSrc;
    //灰色背景图
    private Bitmap mBitmapDst;
    private Canvas mCanvasDst;
    private Paint mPaint;

    private float curX;
    private float curY;

    public GuaGuaLeView(Context context) {
        super(context);
        init();
    }

    public GuaGuaLeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GuaGuaLeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bg = BitmapFactory.decodeResource(getResources(), R.mipmap.beautiful);
        imageWidth = bg.getWidth();
        imageHeight = bg.getHeight();

        mBitmapStore = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888);
        mCanvasStore = new Canvas(mBitmapStore);

        mBitmapDst = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888);
        mCanvasDst = new Canvas(mBitmapDst);
        mBitmapSrc = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888);
        mCanvasSrc = new Canvas(mBitmapSrc);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

        mPaintForPath = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintForPath.setStyle(Paint.Style.STROKE);
        mPaintForPath.setColor(Color.RED);
        mPaintForPath.setStrokeWidth(30);
        mPaintForPath.setStrokeCap(Paint.Cap.ROUND);
        mPaintForPath.setStrokeJoin(Paint.Join.ROUND);

        mPath = new Path();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(), event.getY());
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                curX = event.getX();
                curY = event.getY();
                mPath.lineTo(curX, curY);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                curX = event.getX();
                curY = event.getY();
                mPath.lineTo(curX, curY);
                mCanvasStore.drawPath(mPath, mPaintForPath);
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(imageWidth, imageHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bg, 0, 0, null);

        mCanvasDst.drawColor(Color.GRAY);

        mCanvasSrc.drawBitmap(mBitmapStore, 0, 0, null);
        mCanvasSrc.drawPath(mPath, mPaintForPath);

        int index = canvas.saveLayer(0, 0, imageWidth, imageHeight, mPaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mBitmapDst, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawBitmap(mBitmapSrc, 0, 0, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(index);

    }
}
