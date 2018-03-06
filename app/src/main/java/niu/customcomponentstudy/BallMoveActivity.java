package niu.customcomponentstudy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import niu.customcomponentstudy.databinding.ActivityBallMoveBinding;


public class BallMoveActivity extends AppCompatActivity {

    ActivityBallMoveBinding mBinding;
    private Timer mTimer;
    private TimerTask mTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_ball_move);
        mBinding.btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mBinding.btStart.getText().toString()) {
                    case "开始":
                        startMove();
                        mBinding.btStart.setText("结束");
                        break;

                    case "结束":
                        stopMove();
                        mBinding.btStart.setText("开始");
                        break;
                }
            }
        });
    }

    private void startMove() {
        if (mTimer == null) {
            mTimer = new Timer();
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    mBinding.ballview.postInvalidate();
                }
            };
        }
        mTimer.schedule(mTimerTask, 0, 16);

    }

    private void stopMove() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopMove();
        mBinding.btStart.setText("开始");
    }

    @Override
    protected void onDestroy() {
        stopMove();
        super.onDestroy();
    }
}
