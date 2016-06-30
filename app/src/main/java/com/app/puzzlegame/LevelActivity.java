package com.app.puzzlegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.database.DatabaseActivity;

/**
 * Created by Administrator on 01-Jul-16.
 */
public class LevelActivity extends Activity {
    private Button btnLevel1, btnLevel2, btnLevel3, btExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLevel1 = (Button)findViewById(R.id.btnLevel1);
        btnLevel2 = (Button)findViewById(R.id.btnLevel2);
        btnLevel3 = (Button)findViewById(R.id.btnLevel3);
        btExit = (Button)findViewById(R.id.btnExit);

        btnLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), GameActivity.class);
                i.putExtra("level", "1");
            }
        });
        btnLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), GameActivity.class);
                i.putExtra("level", "2");
            }
        });
        btnLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), GameActivity.class);
                i.putExtra("level", "3");
            }
        });
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MenuActivity.class);
                startActivity(i);
            }
        });
    }
}
