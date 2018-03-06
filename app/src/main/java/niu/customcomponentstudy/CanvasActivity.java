package niu.customcomponentstudy;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import niu.customcomponentstudy.databinding.ActivityCanvasBinding;


public class CanvasActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityCanvasBinding mBinding;
    String[] listItems = new String[]{"drawBitmap", "drawScaleBitmap"
            , "drawPoint", "drawLine", "drawRect", "drawRoundRect"
            , "drawOval", "drawCircle", "drawArc", "drawPath"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_canvas);
        addView();
    }

    private void addView() {
        for (int i = 0; i < listItems.length; i++) {
            AppCompatButton button = new AppCompatButton(this);
            button.setText(listItems[i]);
            button.setTag(i);
            //设置字母是否全部为大写
            button.setSupportAllCaps(false);
            button.setOnClickListener(this);
            mBinding.flexLayout.addView(button);
        }
    }

    @Override
    public void onClick(View view) {
        switch ((int) (view.getTag())) {
            case 0:
                drawBitmap();
                break;
            case 1:
                drawScaleBitmap();
                break;
            case 2:
                drawPoint();
                break;
            case 3:
                drawLine();
                break;

            case 4:
                drawRect();
                break;

            case 5:
                drawRoundRect();
                break;

            case 6:
                drawOval();
                break;

            case 7:
                drawCircle();
                break;

            case 8:
                drawArc();
                break;

            case 9:
                drawPath();
                break;
        }
    }

    /**
     * 在Canvas上画原图
     */
    private void drawBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(500, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.redpack);
        canvas.drawBitmap(srcBitmap, 0, 0, null);
        mBinding.ivShow.setImageBitmap(bitmap);
    }

    /**
     * 在Canvas上画原图与缩放图
     */
    private void drawScaleBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.redpack);
        int srcWidth = srcBitmap.getWidth();
        int srcHeight = srcBitmap.getHeight();
        Rect srcRect = new Rect(0, 0, srcWidth, srcHeight);
        Rect dstRectN = new Rect(srcRect);//与原图一样大
        Rect dstRectScale = new Rect(srcWidth, srcHeight, srcWidth * 3 + srcWidth, srcHeight * 3 + srcHeight);//原图3倍
        canvas.drawBitmap(srcBitmap, srcRect, dstRectN, null);
        canvas.drawBitmap(srcBitmap, srcRect, dstRectScale, null);

        mBinding.ivShow.setImageBitmap(bitmap);
    }

    /**
     * 在Canvas上绘制点
     */
    private void drawPoint() {
        Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStrokeWidth(30);
        paint.setFakeBoldText(true);
        paint.setColor(Color.RED);
        canvas.drawPoint(10, 10, paint);

        paint.setColor(Color.BLACK);
        canvas.drawPoints(new float[]{60, 10, 60, 60, 60, 110, 60, 160, 60, 210}, paint);

        paint.setColor(Color.BLUE);
        canvas.drawPoints(new float[]{60, 10, 60, 60, 110, 10, 110, 60, 110, 110, 110, 160, 110, 210, 60, 160, 60, 210}, 4, 10, paint);

        mBinding.ivShow.setImageBitmap(bitmap);
    }

    /**
     * 在Canvas上绘制线
     */
    private void drawLine() {
        Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);

        canvas.drawLine(10, 10, 450, 450, paint);

        paint.setColor(Color.BLUE);
        canvas.drawLines(new float[]{10, 10, 10, 450, 10, 450, 450, 450}, paint);

        paint.setColor(Color.YELLOW);
        canvas.drawLines(new float[]{10, 10, 450, 10, 450, 10, 450, 450}, 0, 8, paint);

        mBinding.ivShow.setImageBitmap(bitmap);
    }

    /**
     * 在Canvas上绘制矩形
     */
    private void drawRect() {
        Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawRect(new Rect(0, 0, 500, 500), paint);


        mBinding.ivShow.setImageBitmap(bitmap);
    }

    /**
     * 在Canvas上绘制圆角矩形
     */
    private void drawRoundRect() {
        Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawRoundRect(new RectF(0, 0, 500, 500), 250, 250, paint);


        mBinding.ivShow.setImageBitmap(bitmap);
    }

    /**
     * 在Canvas上绘制椭圆
     */
    private void drawOval() {
        Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawOval(new RectF(0, 0, 500, 250), paint);


        mBinding.ivShow.setImageBitmap(bitmap);
    }

    /**
     * 在Canvas上绘制圆形
     */
    private void drawCircle() {
        Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        //cx,xy圆心
        //radius半径
        canvas.drawCircle(100, 100, 50, paint);


        mBinding.ivShow.setImageBitmap(bitmap);
    }

    /**
     * 在Canvas上绘制扇形
     */
    private void drawArc() {
        Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.colorGrey));
        paint.setStrokeWidth(10);
        RectF rectF = new RectF(0, 0, 200, 100);
        canvas.drawOval(rectF, paint);

        paint.setColor(Color.RED);
        canvas.drawArc(rectF, -30, -60, true, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.YELLOW);
        canvas.drawArc(rectF, 30, 60, true, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        canvas.drawArc(rectF, 120, 60, false, paint);

        mBinding.ivShow.setImageBitmap(bitmap);
    }

    private void drawPath() {
        Intent intent = new Intent(this, PathActivity.class);
        startActivity(intent);
    }

}
