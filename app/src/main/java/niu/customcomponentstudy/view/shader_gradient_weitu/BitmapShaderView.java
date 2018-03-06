package niu.customcomponentstudy.view.shader_gradient_weitu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import niu.customcomponentstudy.R;

/**
 * Created by Qinlian_niu on 2018/2/27.
 */

public class BitmapShaderView extends View {

    private Bitmap mBitmap;

    public BitmapShaderView(Context context) {
        super(context);
        init();
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.hot);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmap != null) {
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
            paint.setShader(bitmapShader);
            canvas.drawRect(new Rect(0, 0, this.getWidth(), this.getHeight()), paint);
        }


    }
}
