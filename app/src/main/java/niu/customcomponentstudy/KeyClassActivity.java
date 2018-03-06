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

import niu.customcomponentstudy.databinding.ActivityCustomStudyBinding;


public class KeyClassActivity extends AppCompatActivity {

    ActivityCustomStudyBinding mBinding;

    private List<String> listItems = Arrays.asList(new String[]{"Paint类", "Canvas类", "Path类"});

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_custom_study);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mBinding.listview.setAdapter(adapter);
        mBinding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(view.getContext(), PaintActivity.class);
                        break;

                    case 1:
                        intent = new Intent(view.getContext(), CanvasActivity.class);
                        break;

                    case 2:
                        intent = new Intent(view.getContext(), PathActivity.class);
                        break;

                }
                startActivity(intent);
            }
        });
    }
}
