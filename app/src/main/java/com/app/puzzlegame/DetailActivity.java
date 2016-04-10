package com.app.puzzlegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 4/10/2016.
 */
public class DetailActivity extends Activity {
    String answer_detail = "";
    Button iDetail, btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            answer_detail = extras.getString("answer_detail");
        }

        iDetail = (Button)findViewById(R.id.btnDetail);
        btnNext = (Button)findViewById(R.id.btnNext);

        int resDetailId = getResources().getIdentifier(answer_detail, "drawable", getPackageName());
        iDetail.setBackgroundResource(resDetailId);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailActivity.this, GameActivity.class);
                startActivity(i);
            }
        });

        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(DetailActivity.this, GameActivity.class);
                startActivity(i);
                finish();
            }
        }, 20000);*/
    }

}
