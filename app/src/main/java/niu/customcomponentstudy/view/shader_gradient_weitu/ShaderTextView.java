package niu.customcomponentstudy.view.shader_gradient_weitu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Qinlian_niu on 2018/2/24.
 * 计算文字宽高，基线位置
 * 绘制阴影
 */

public class ShaderTextView extends View {

    private Paint mPaint;

    public ShaderTextView(Context context) {
        super(context);
        init();
    }

    public ShaderTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShaderTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(100);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);

        mPaint.setColor(Color.BLUE);
        mPaint.setShadowLayer(10, 1, 1, Color.RED);
        String text1 = "Android开发";
        //测量文字宽高
        Rect rect = new Rect();
        mPaint.getTextBounds(text1, 0, text1.length(), rect);
        //Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
        //top表示基线距离最上面的距离，是负数，bottom表示基线距离最下面的距离，是正数
        canvas.drawText(text1, rect.width() / 2, -mPaint.getFontMetricsInt().top, mPaint);
        int height = rect.height();

        mPaint.setColor(Color.RED);
        mPaint.setShadowLayer(10, 5, 5, Color.BLUE);
        String text2 = "Android绘图技术";
        mPaint.getTextBounds(text2, 0, text2.length(), rect);
        canvas.drawText(text2, rect.width() / 2, height - mPaint.getFontMetricsInt().top, mPaint);


    }
}
