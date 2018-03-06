package niu.customcomponentstudy;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import niu.customcomponentstudy.databinding.ActivityPaintBinding;


public class PaintActivity extends AppCompatActivity {

    ActivityPaintBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_paint);
        mBinding.btTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawText();
            }
        });
    }

    /**
     * Canvas.drawText();
     * x,y的含义与setTextAlign()的值有关
     * 当设置为Center时，x表示文字中心点所在的x轴坐标
     * 当设置为Left时，x表示文字左边所在的x轴坐标
     * 当设置为Right时，x表示文字右边所在的x轴坐标
     * y表示文字基线所在的y轴坐标
     */
    private void drawText() {
        String text = "测试";

        Bitmap bitmap = Bitmap.createBitmap(800, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        //绘制文字
        Paint textPaint = new Paint();
        textPaint.setTextSize(80);
        textPaint.setTextAlign(Paint.Align.CENTER);
        //textPaint.setTextAlign(Paint.Align.RIGHT);
        //textPaint.setTextSkewX(0.5f);
        textPaint.setUnderlineText(true);
        textPaint.setFakeBoldText(true);
        canvas.drawText(text, 400, 100, textPaint);

        //绘制图形
        Paint rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setColor(Color.RED);
        rectPaint.setStrokeWidth(20);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeJoin(Paint.Join.BEVEL);//设置拐角样式
        rectPaint.setStrokeCap(Paint.Cap.ROUND);//设置落笔样式
        canvas.drawRect(new Rect(0, 0, 800, 400), rectPaint);

        mBinding.ivShow.setImageBitmap(bitmap);
    }

}
