package niu.customcomponentstudy.view.shader_gradient_weitu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import niu.customcomponentstudy.R;

/**
 * Created by Qinlian_niu on 2018/2/24.
 */

public class ShaderBitmapView extends View {

    public ShaderBitmapView(Context context) {
        super(context);
    }

    public ShaderBitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShaderBitmapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, paint);
        paint.setShadowLayer(10, 5, 5, Color.BLUE);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.redpack);
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }
}
