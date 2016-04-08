package com.app.puzzlegame;

import android.os.CountDownTimer;

/**
 * Created by nuttapong_i on 08/04/2559.
 */
public class MyCountDown extends CountDownTimer {
    public MyCountDown(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onFinish() { // เมื่อทำงานเสร็จสิ้น
        // TODO Auto-generated method stub
    }

    @Override
    public void onTick(long remain) { // ในขณะที่ทำงานทุก ๆ ครั้ง
        // TODO Auto-generated method stub
    }

}
