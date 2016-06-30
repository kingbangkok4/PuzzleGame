package com.app.puzzlegame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 4/10/2016.
 */
public class DetailActivity extends Activity {
    String answer_detail = "", level = "";
    Button  btnNext;
    RelativeLayout iDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            answer_detail = extras.getString("answer_detail");
            level = extras.getString("level");
        }

        iDetail = (RelativeLayout)findViewById(R.id.btnDetail);
        btnNext = (Button)findViewById(R.id.btnNext);

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;

        int resDetailId = getResources().getIdentifier(answer_detail, "drawable", getPackageName());
        iDetail.setBackgroundResource(resDetailId);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), GameActivity.class);
                i.putExtra("level", level);
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
