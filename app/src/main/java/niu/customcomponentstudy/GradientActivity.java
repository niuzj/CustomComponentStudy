package niu.customcomponentstudy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import niu.customcomponentstudy.databinding.ActivityGradientBinding;
import niu.customcomponentstudy.view.shader_gradient_weitu.BitmapShaderView;
import niu.customcomponentstudy.view.shader_gradient_weitu.ComposeShaderView;
import niu.customcomponentstudy.view.shader_gradient_weitu.FiveChessView;
import niu.customcomponentstudy.view.shader_gradient_weitu.GradientMatrixView;
import niu.customcomponentstudy.view.shader_gradient_weitu.GuangPanView;
import niu.customcomponentstudy.view.shader_gradient_weitu.LinearGradientView;
import niu.customcomponentstudy.view.shader_gradient_weitu.RadialGradientView;
import niu.customcomponentstudy.view.shader_gradient_weitu.SweepGradientView;


/**
 * 渐变
 */
public class GradientActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityGradientBinding mBinding;
    private String[] listItems = new String[]{"线性渐变LinearGradient", "径向渐变RadialGradient", "径向渐变之五子棋",
            "扫描渐变SweepGradient", "扫描渐变之光盘", "位图渐变BitmapShader", "混合渐变ComposeShader",
            "渐变与Matrix"};

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
            case "线性渐变LinearGradient":
                LinearGradientView linearGradientView = new LinearGradientView(this);
                linearGradientView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(linearGradientView);
                break;

            case "径向渐变RadialGradient":
                RadialGradientView radialGradientView = new RadialGradientView(this);
                radialGradientView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(radialGradientView);
                break;

            case "径向渐变之五子棋":
                FiveChessView fiveChessView = new FiveChessView(this);
                layoutParams = new LinearLayout.LayoutParams(800, 800);
                layoutParams.setMargins(100, 100, 100, 100);
                fiveChessView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(fiveChessView);
                break;

            case "扫描渐变SweepGradient":
                SweepGradientView sweepGradientView = new SweepGradientView(this);
                sweepGradientView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(sweepGradientView);
                break;

            case "扫描渐变之光盘":
                GuangPanView guangPanView = new GuangPanView(this);
                layoutParams = new LinearLayout.LayoutParams(800, 800);
                guangPanView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(guangPanView);
                break;

            case "位图渐变BitmapShader":
                BitmapShaderView bitmapShaderView = new BitmapShaderView(this);
                bitmapShaderView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(bitmapShaderView);
                break;

            case "混合渐变ComposeShader":
                ComposeShaderView composeShaderView = new ComposeShaderView(this);
                composeShaderView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(composeShaderView);
                break;

            case "渐变与Matrix":
                GradientMatrixView gradientMatrixView = new GradientMatrixView(this);
                gradientMatrixView.setLayoutParams(layoutParams);
                mBinding.llContent.addView(gradientMatrixView);
                break;
        }
    }


}
