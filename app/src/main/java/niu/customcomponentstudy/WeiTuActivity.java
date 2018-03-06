package niu.customcomponentstudy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import niu.customcomponentstudy.databinding.ActivityGradientBinding;
import niu.customcomponentstudy.view.shader_gradient_weitu.WeiTuCorrectView;
import niu.customcomponentstudy.view.shader_gradient_weitu.WeiTuErrorView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.CircleView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.ClearView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.DarkenView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.DstATopView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.DstInView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.DstOutView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.DstOverView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.DstView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.GuaGuaLeView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.LightenView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.MultiplyView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.ScreenView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.SrcATopView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.SrcInView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.SrcOutView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.SrcOverView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.SrcView;
import niu.customcomponentstudy.view.shader_gradient_weitu.xfermodeviews.XorView;


public class WeiTuActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityGradientBinding mBinding;
    String[] listItems = new String[]{"没有Layer的位图运算", "有Layer的位图运算",
            "Clear", "Src", "Dst", "SrcOver", "DstOver", "SrcIn", "DstIn",
            "SrcOut", "DstOut", "SrcATop", "DstATop", "Xor",
            "Darken", "Lighten", "Multiply", "Screen",
            "圆形", "刮刮乐"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_gradient);
        addView();
    }

    private void addView() {
        if (listItems != null && listItems.length > 0) {
            for (int i = 0; i < listItems.length; i++) {
                AppCompatButton button = new AppCompatButton(this);
                button.setText(listItems[i]);
                button.setSupportAllCaps(false);
                button.setTag(listItems[i]);
                button.setOnClickListener(this);
                mBinding.flexLayout.addView(button);
            }
        }
    }

    @Override
    public void onClick(View v) {
        mBinding.llContent.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        switch ((String) v.getTag()) {
            case "没有Layer的位图运算":
                WeiTuErrorView weiTuErrorView = new WeiTuErrorView(this);
                weiTuErrorView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(weiTuErrorView);
                break;

            case "有Layer的位图运算":
                WeiTuCorrectView weiTuCorrectView = new WeiTuCorrectView(this);
                weiTuCorrectView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(weiTuCorrectView);
                break;

            case "Clear":
                ClearView clearView = new ClearView(this);
                clearView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(clearView);
                break;

            case "Src":
                SrcView srcView = new SrcView(this);
                srcView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(srcView);
                break;

            case "Dst":
                DstView dstView = new DstView(this);
                dstView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(dstView);
                break;

            case "SrcOver":
                SrcOverView srcOverView = new SrcOverView(this);
                srcOverView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(srcOverView);
                break;

            case "DstOver":
                DstOverView dstOverView = new DstOverView(this);
                dstOverView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(dstOverView);
                break;

            case "SrcIn":
                SrcInView srcInView = new SrcInView(this);
                srcInView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(srcInView);
                break;

            case "DstIn":
                DstInView dstInView = new DstInView(this);
                dstInView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(dstInView);
                break;

            case "SrcOut":
                SrcOutView srcOutView = new SrcOutView(this);
                srcOutView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(srcOutView);
                break;

            case "DstOut":
                DstOutView dstOutView = new DstOutView(this);
                dstOutView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(dstOutView);
                break;

            case "SrcATop":
                SrcATopView srcATopView = new SrcATopView(this);
                srcATopView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(srcATopView);
                break;

            case "DstATop":
                DstATopView dstATopView = new DstATopView(this);
                dstATopView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(dstATopView);
                break;

            case "Xor":
                XorView xorView = new XorView(this);
                xorView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(xorView);
                break;

            case "Darken":
                DarkenView darkenView = new DarkenView(this);
                darkenView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(darkenView);
                break;

            case "Lighten":
                LightenView lightenView = new LightenView(this);
                lightenView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(lightenView);
                break;

            case "Multiply":
                MultiplyView multiplyView = new MultiplyView(this);
                multiplyView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(multiplyView);
                break;

            case "Screen":
                ScreenView screenView = new ScreenView(this);
                screenView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(screenView);
                break;

            case "圆形":
                CircleView circleView = new CircleView(this);
                circleView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(circleView);
                break;

            case "刮刮乐":
                GuaGuaLeView guaGuaLeView = new GuaGuaLeView(this);
                guaGuaLeView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(guaGuaLeView);
                break;


        }
    }
}
