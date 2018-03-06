package niu.customcomponentstudy;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import niu.customcomponentstudy.databinding.ActivityShaderBinding;


/**
 * 阴影、渐变和位图运算
 */
public class ShaderGradientWeiTuActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityShaderBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_shader);
        initView();
    }

    private void initView() {
        mBinding.btShader.setOnClickListener(this);
        mBinding.btGradient.setOnClickListener(this);
        mBinding.btWeiTu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btShader:
                intent = new Intent(this, ShaderActivity.class);
                break;

            case R.id.btGradient:
                intent = new Intent(this, GradientActivity.class);
                break;

            case R.id.btWeiTu:
                intent = new Intent(this,WeiTuActivity.class);
                break;
        }
        startActivity(intent);
    }
}
