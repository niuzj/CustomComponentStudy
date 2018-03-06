package niu.customcomponentstudy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.flexbox.FlexboxLayout;

import niu.customcomponentstudy.databinding.ActivityDoubleBufferBinding;
import niu.customcomponentstudy.view.doublebuffer.BufferRectView;
import niu.customcomponentstudy.view.doublebuffer.DoubleBufferFourView;
import niu.customcomponentstudy.view.doublebuffer.DoubleBufferOneView;
import niu.customcomponentstudy.view.doublebuffer.DoubleBufferThreeView;
import niu.customcomponentstudy.view.doublebuffer.DoubleBufferTwoView;
import niu.customcomponentstudy.view.doublebuffer.NoBufferRectView;


/**
 * 双缓存学习
 * 关键类 Bitmap Canvas Path
 */
public class DoubleBufferActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityDoubleBufferBinding mBinding;
    private AppCompatButton btBitmapCanvas;
    private AppCompatButton btCanvasPath;
    private AppCompatButton btBitmapCanvasPath;
    private AppCompatButton btBitmapCanvasPathQuard;
    private AppCompatButton btBitmapCanvasPathRect;
    private AppCompatButton btBufferRect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_double_buffer);
        btBitmapCanvas = getButton("Bitmap+Canvas实现手势轨迹");
        btCanvasPath = getButton("Canvas+Path实现手势轨迹，不保存历史轨迹");
        btBitmapCanvasPath = getButton("Bitmap+Canvas+Path实现手势轨迹");
        btBitmapCanvasPathQuard = getButton("Bitmap+Canvas+Path实现手势二阶贝塞尔曲线");
        btBitmapCanvasPathRect = getButton("无缓存绘制矩形");
        btBufferRect = getButton("缓存绘制矩形");
        mBinding.flexLayout.addView(btBitmapCanvas);
        mBinding.flexLayout.addView(btCanvasPath);
        mBinding.flexLayout.addView(btBitmapCanvasPath);
        mBinding.flexLayout.addView(btBitmapCanvasPathQuard);
        mBinding.flexLayout.addView(btBitmapCanvasPathRect);
        mBinding.flexLayout.addView(btBufferRect);
    }

    private AppCompatButton getButton(String text) {
        AppCompatButton button = new AppCompatButton(this);
        button.setSupportAllCaps(false);
        button.setText(text);
        FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(layoutParams);
        button.setTag(text);
        button.setOnClickListener(this);
        return button;
    }

    @Override
    public void onClick(View v) {
        mBinding.llContent.removeAllViews();
        switch ((String) (v.getTag())) {
            case "Bitmap+Canvas实现手势轨迹":
                DoubleBufferOneView view = new DoubleBufferOneView(this);
                LinearLayout.LayoutParams layoutParamsOne = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(layoutParamsOne);
                mBinding.llContent.addView(view);
                break;

            case "Canvas+Path实现手势轨迹，不保存历史轨迹":
                DoubleBufferTwoView twoView = new DoubleBufferTwoView(this);
                LinearLayout.LayoutParams layoutParamsTwo = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                twoView.setLayoutParams(layoutParamsTwo);
                mBinding.llContent.addView(twoView);
                break;

            case "Bitmap+Canvas+Path实现手势轨迹":
                DoubleBufferThreeView threeView = new DoubleBufferThreeView(this);
                LinearLayout.LayoutParams layoutParamsThree = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                threeView.setLayoutParams(layoutParamsThree);
                mBinding.llContent.addView(threeView);
                break;

            case "Bitmap+Canvas+Path实现手势二阶贝塞尔曲线":
                DoubleBufferFourView fourView = new DoubleBufferFourView(this);
                LinearLayout.LayoutParams layoutParamsFour = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                fourView.setLayoutParams(layoutParamsFour);
                mBinding.llContent.addView(fourView);
                break;

            case "无缓存绘制矩形":
                NoBufferRectView noBufferRectView = new NoBufferRectView(this);
                LinearLayout.LayoutParams noBufferRectParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                noBufferRectView.setLayoutParams(noBufferRectParams);
                mBinding.llContent.addView(noBufferRectView);
                break;

            case "缓存绘制矩形":
                BufferRectView bufferRectView = new BufferRectView(this);
                LinearLayout.LayoutParams bufferRectParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                bufferRectView.setLayoutParams(bufferRectParams);
                mBinding.llContent.addView(bufferRectView);
                break;
        }
    }
}
