package niu.customcomponentstudy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import niu.customcomponentstudy.databinding.ActivityGradientBinding;
import niu.customcomponentstudy.view.shader_gradient_weitu.ShaderBitmapView;
import niu.customcomponentstudy.view.shader_gradient_weitu.ShaderTextView;


public class ShaderActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityGradientBinding mBinding;
    String[] listItems = new String[]{"文字阴影", "图片阴影"};

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
        switch ((String) v.getTag()) {
            case "文字阴影":
                ShaderTextView shaderTextView = new ShaderTextView(this);
                LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 200);
                shaderTextView.setLayoutParams(viewParams);
                mBinding.llContent.addView(shaderTextView);
                break;

            case "图片阴影":
                ShaderBitmapView shaderBitmapView = new ShaderBitmapView(this);
                LinearLayout.LayoutParams bitmapViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 100);
                shaderBitmapView.setLayoutParams(bitmapViewParams);
                mBinding.llContent.addView(shaderBitmapView);
                break;
        }
    }
}
