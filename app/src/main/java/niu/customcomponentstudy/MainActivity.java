package niu.customcomponentstudy;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.Arrays;
import java.util.List;

import niu.customcomponentstudy.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;
    List<String> listItem = Arrays.asList(new String[]{"自定义控件学习之关键类"
            , "自定义控件学习之动画", "自定义控件学习之双缓存"
            , "阴影、渐变和位图运算"});

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }

    private void initView(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItem);
        mBinding.listContent.setAdapter(adapter);
        mBinding.listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(view.getContext(), KeyClassActivity.class);
                        break;

                    case 1:
                        intent = new Intent(view.getContext(), AnimationEffectActivity.class);
                        break;

                    case 2:
                        intent = new Intent(view.getContext(), DoubleBufferActivity.class);
                        break;

                    case 3:
                        intent = new Intent(view.getContext(), ShaderGradientWeiTuActivity.class);
                        break;

                }
                startActivity(intent);
            }
        });
    }

}
