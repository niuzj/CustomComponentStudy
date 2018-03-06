package niu.customcomponentstudy.view.shader_gradient_weitu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import niu.customcomponentstudy.R;


/**
 * Created by Qinlian_niu on 2018/2/27.
 * 混合渐变
 */

public class ComposeShaderView extends View {

    private Bitmap mBitmap;

    public ComposeShaderView(Context context) {
        super(context);
        init();
    }

    public ComposeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComposeShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.close);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //位图渐变
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        //线性渐变
        LinearGradient linearGradient = new LinearGradient(0, getHeight() / 2, getWidth(), getHeight() / 2, Color.RED, Color.BLUE, Shader.TileMode.CLAMP);

        //混合渐变
        //可以改变第三个参数实现不同的混合渐变效果
        ComposeShader composeShader = new ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.SRC_ATOP);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(composeShader);
        canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()), paint);


    }
}
