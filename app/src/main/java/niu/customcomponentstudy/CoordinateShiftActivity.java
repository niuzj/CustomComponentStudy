package niu.customcomponentstudy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import niu.customcomponentstudy.databinding.ActivityCoordinateShiftBinding;


/**
 * 坐标变换：平移、缩放、旋转
 * 坐标转换后，后面的图形绘制功能将跟随新坐标，转换前已经绘制的图形不会有任何的变化
 */
public class CoordinateShiftActivity extends AppCompatActivity {

    ActivityCoordinateShiftBinding mBinding;
    Timer mTimer;
    TimerTask mTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_coordinate_shift);
        mBinding.tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mBinding.tvStart.getText().toString()) {
                    case "开始":
                        start();
                        mBinding.tvStart.setText("结束");
                        break;

                    case "结束":
                        stop();
                        mBinding.tvStart.setText("开始");
                        break;
                }
            }
        });
    }

    private void start() {
        if (mTimer == null) {
            mTimer = new Timer();
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    mBinding.testView.postInvalidate();
                }
            };
        }
        mTimer.schedule(mTimerTask, 0, 1000);
    }

    private void stop() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            mTimerTask = null;
        }
    }

    @Override
    protected void onDestroy() {
        stop();
        super.onDestroy();
    }
}
