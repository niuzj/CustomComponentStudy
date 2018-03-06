package niu.customcomponentstudy.view.shader_gradient_weitu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Qinlian_niu on 2018/2/26.
 */

public class FiveChessView extends View {

    private int chessSize = 100;
    private int width;
    private int height;
    private Paint mPaint;

    public FiveChessView(Context context) {
        super(context);
        init();
    }

    public FiveChessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FiveChessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = this.getWidth();
        height = this.getHeight();

        int rowCount = height / chessSize;
        int lineCount = width / chessSize;

        //绘制行线
        drawRows(canvas, rowCount);
        //绘制列数
        drawLines(canvas, lineCount);
        //绘制棋子
        drawChess(canvas, 1, 1, ChessType.BLACK);
        drawChess(canvas, 3, 1, ChessType.WHITE);
        drawChess(canvas, 2, 2, ChessType.WHITE);
        drawChess(canvas, 6, 5, ChessType.BLACK);


    }

    /**
     * 绘制行线
     *
     * @param canvas
     * @param rowCount 行数
     */
    private void drawRows(Canvas canvas, int rowCount) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.BLACK);
        //取消阴影
        mPaint.setShadowLayer(0, 0, 0, Color.BLACK);
        canvas.save();
        for (int i = 0; i <= rowCount; i++) {
            canvas.drawLine(0, 0, width, 0, mPaint);
            canvas.translate(0, chessSize);
        }
        canvas.restore();
    }

    /**
     * 绘制纵线
     *
     * @param canvas
     * @param lineCount 列数
     */
    private void drawLines(Canvas canvas, int lineCount) {
        canvas.save();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(2);
        //取消阴影
        mPaint.setShadowLayer(0, 0, 0, Color.BLACK);
        for (int i = 0; i <= lineCount; i++) {
            canvas.drawLine(0, 0, 0, height, mPaint);
            canvas.translate(chessSize, 0);
        }
        canvas.restore();
    }

    /**
     * 绘制棋子
     *
     * @param canvas
     * @param rowCount  所在行数
     * @param lineCount 所在列数
     */
    private void drawChess(Canvas canvas, int rowCount, int lineCount, ChessType chessType) {
        canvas.save();
        canvas.translate(lineCount * chessSize, rowCount * chessSize);

        //绘制棋子
        int endColor = chessType == ChessType.BLACK ? Color.BLACK : Color.GRAY;
        int startColor = Color.WHITE;
        RadialGradient gradient = new RadialGradient(0, 0, chessSize / 2, startColor, endColor, Shader.TileMode.CLAMP);
        mPaint.setShader(gradient);
        mPaint.setStyle(Paint.Style.FILL);

        //添加阴影
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);
        mPaint.setShadowLayer(6, 4, 4, endColor);

        canvas.drawCircle(0, 0, chessSize / 2, mPaint);
        canvas.restore();
    }

    enum ChessType {
        BLACK, WHITE
    }

}

