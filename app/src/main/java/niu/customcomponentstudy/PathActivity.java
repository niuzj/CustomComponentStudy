package niu.customcomponentstudy;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import java.util.Random;

import niu.customcomponentstudy.databinding.ActivityPathBinding;


public class PathActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityPathBinding mBinding;
    String[] listItems = new String[]{"五角星", "add相关api", "二阶贝塞尔曲线"
            , "三阶贝塞尔曲线", "arcTo", "枚举OP", "textOnPath", "验证码"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_path);
        addView();
    }

    private void addView() {
        if (listItems.length > 0) {
            for (int i = 0; i < listItems.length; i++) {
                AppCompatButton button = new AppCompatButton(this);
                button.setText(listItems[i]);
                button.setSupportAllCaps(false);
                button.setTag(i);
                button.setOnClickListener(this);
                mBinding.flexLayout.addView(button);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch ((int) (v.getTag())) {
            case 0:
                //画五角星
                drawStar();
                break;

            case 1:
                addGraphics();
                break;

            case 2:
                drawQuad();
                break;

            case 3:
                drawCubic();
                break;

            case 4:
                drawArcTo();
                break;

            case 5:
                trainOp();
                break;

            case 6:
                textOnPath();
                break;

            case 7:
                showCode();
                break;
        }
    }

    private Paint getPaint() {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        return paint;
    }

    /**
     * moveTo():将画笔移动到点（x，y）的位置，使用的是绝对定位。
     * rMoveTo():将画笔移动到一个新点，新点在上一个点的基础上偏移（dx，dy），使用的是相对定位
     * lineTo():将画笔移动到点（x，y）的位置，并在上一个点与当前点之前画一条直线。使用的是绝对定位。
     * rLineTo():将画笔移动到一个新点，新点在上一个点的基础上偏移（dx，dy），使用的是相对定位
     */
    private void drawStar() {
        Bitmap bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        paint.setStrokeJoin(Paint.Join.ROUND);

        Path path = new Path();
        path.moveTo(0, 200);
        path.rLineTo(600, 0);
        path.rLineTo(-500, 400);
        path.rLineTo(200, -600);
        path.rLineTo(200, 600);
        path.close();
        canvas.drawPath(path, paint);
        paint.setColor(Color.BLUE);
        canvas.drawPoints(new float[]{0, 200, 600, 200, 100, 600, 300, 0, 500, 600}, paint);


        mBinding.ivShow.setImageBitmap(bitmap);
    }

    /**
     * 往 Path 对象中添加矩形、椭圆、圆和弧
     */
    private void addGraphics() {
        Bitmap bitmap = Bitmap.createBitmap(800, 1600, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);

        Path path = new Path();
        path.addRect(new RectF(100, 0, 700, 300), Path.Direction.CW);
        canvas.drawPath(path, paint);

        path.addCircle(50, 400, 50, Path.Direction.CW);
        canvas.drawPath(path, paint);

        path.addOval(new RectF(100, 350, 700, 450), Path.Direction.CW);
        canvas.drawPath(path, paint);

        path.addCircle(750, 400, 50, Path.Direction.CW);
        canvas.drawPath(path, paint);

        path.addArc(new RectF(0, 500, 800, 900), 30, 120);
        canvas.drawPath(path, paint);

        mBinding.ivShow.setImageBitmap(bitmap);
    }

    /**
     * 画二阶贝塞尔曲线
     */
    private void drawQuad() {
        Bitmap bitmap = Bitmap.createBitmap(800, 1600, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = getPaint();
        Path path = new Path();
        path.moveTo(200, 200);
        path.quadTo(300, 100, 400, 400);
        path.close();
        canvas.drawPath(path, paint);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(20);
        canvas.drawPoints(new float[]{200, 200, 300, 100, 400, 400}, paint);

        mBinding.ivShow.setImageBitmap(bitmap);
    }

    /**
     * 画三阶贝塞尔曲线
     * rCubicTo:是基于第一个点计算的，与rLineTo不一样
     */
    private void drawCubic() {
        Bitmap bitmap = Bitmap.createBitmap(800, 1600, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = getPaint();
        Path path = new Path();
        //一下方法效果相同
        /*path.moveTo(100, 400);
        path.cubicTo(200, 100, 300, 250, 400, 500);
        path.close();*/
        path.moveTo(100, 400);
        path.rCubicTo(100, -300, 200, -150, 300, 100);//与第一个点相比较
        path.close();
        canvas.drawPath(path, paint);
        paint.setColor(Color.BLUE);
        canvas.drawPoints(new float[]{100, 400, 200, 100, 300, 250, 400, 500}, paint);
        mBinding.ivShow.setImageBitmap(bitmap);
    }

    /**
     * arcTo()和moveTo()混用,
     * forceMoveTo:如果是true，弧线的起始点不会和 moveTo()方法定义的点进行连接，默认是false
     */
    private void drawArcTo() {
        Bitmap bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = getPaint();
        Path path = new Path();
        path.moveTo(100, 100);
        //path.arcTo(new RectF(0, 200, 300, 400), -90, 180);
        path.arcTo(new RectF(0, 200, 300, 400), -90, 180, true);
        canvas.drawPath(path, paint);
        mBinding.ivShow.setImageBitmap(bitmap);
    }

    /**
     * Path下有枚举OP：
     * DIFFERENCE：自身-交集
     * INTERSECT：交集
     * UNION：并集
     * XOR：并集-交集
     * REVERSE_DIFFERENCE：
     */
    private void trainOp() {
        Bitmap bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = getPaint();
        //Style不同，效果不同
        //初始效果
        paint.setStyle(Paint.Style.FILL);
        /*Path pathA = new Path();
        pathA.addRect(new RectF(100,200,500,500), Path.Direction.CW);
        canvas.drawPath(pathA,paint);

        Path pathB = new Path();
        pathB.addCircle(500,500,200, Path.Direction.CW);
        paint.setColor(Color.BLUE);
        canvas.drawPath(pathB,paint);*/

        //op效果
        Path pathA = new Path();
        pathA.addRect(new RectF(100, 200, 500, 500), Path.Direction.CW);
        Path pathB = new Path();
        pathB.addCircle(500, 500, 200, Path.Direction.CW);

        //pathA.op(pathB, Path.Op.DIFFERENCE);// A-A交B
        //pathA.op(pathB, Path.Op.INTERSECT);//A交B
        //pathA.op(pathB, Path.Op.UNION);//A并B
        pathA.op(pathB, Path.Op.XOR);//A并B - A交B
        canvas.drawPath(pathA, paint);


        mBinding.ivShow.setImageBitmap(bitmap);
    }

    private void textOnPath() {
        Bitmap bitmap = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
        Paint paint = getPaint();
        Canvas canvas = new Canvas(bitmap);
        Path path = new Path();
        path.addArc(new RectF(100, 100, 500, 400), -180, 180);
        canvas.drawPath(path, paint);
        paint.setTextSize(40);
        paint.setFakeBoldText(false);
        paint.setColor(Color.BLUE);
        canvas.drawTextOnPath("康师傅冰红茶，冰力十足，啦啦啦", path, 40, 50, paint);
        mBinding.ivShow.setImageBitmap(bitmap);
    }

    private void showCode() {
        Bitmap bitmap = Bitmap.createBitmap(1000, 500, Bitmap.Config.ARGB_8888);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);

        Canvas canvas = new Canvas(bitmap);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Path path = new Path();
            path.moveTo(random.nextInt(1000), random.nextInt(500));
            path.lineTo(random.nextInt(1000), random.nextInt(500));
            canvas.drawPath(path, paint);
        }

        paint.setColor(Color.BLUE);
        paint.setTextSize(64);
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < 4; i++) {
            canvas.drawText(random.nextInt(10) + "", random.nextInt(800), random.nextInt(300), paint);
        }

        mBinding.ivShow.setImageBitmap(bitmap);
    }

}
