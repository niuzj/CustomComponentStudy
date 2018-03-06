package niu.customcomponentstudy;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.Arrays;
import java.util.List;

import niu.customcomponentstudy.databinding.ActivityAnimationEffectBinding;


public class AnimationEffectActivity extends AppCompatActivity {

    ActivityAnimationEffectBinding mBinding;
    List<String> listItems = Arrays.asList(new String[]{"左右移动的小球", "坐标转换"});

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_animation_effect);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mBinding.listview.setAdapter(adapter);
        mBinding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(view.getContext(), BallMoveActivity.class);
                        break;

                    case 1:
                        intent = new Intent(view.getContext(), CoordinateShiftActivity.class);
                        break;


                }
                startActivity(intent);
            }
        });
    }
}
