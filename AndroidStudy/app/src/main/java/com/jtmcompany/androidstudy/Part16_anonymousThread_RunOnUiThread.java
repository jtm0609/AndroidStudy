package com.jtmcompany.androidstudy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Part16_anonymousThread_RunOnUiThread extends AppCompatActivity implements View.OnClickListener {

    Button start_btn;
    Button stop_btn;
    TextView textView;
    int count=10;
    boolean loopFlag=true;
    boolean isFirst=true;
    boolean isRun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part16_anonymous_thread__run_on_ui_thread);

        start_btn=findViewById(R.id.start_btnn);
        stop_btn=findViewById(R.id.stop_btnn);
        textView=findViewById(R.id.main_textView0);

        start_btn.setOnClickListener(this);
        stop_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v==start_btn) {
            if (isFirst) {
                isFirst = false;
                isRun = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        while (loopFlag) {
                            try {
                                Thread.sleep(1000);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (isRun) {
                                            Log.d("TAK", "TEST: " + count);
                                            textView.setText(String.valueOf(count--));
                                            if (count == -1) {
                                                loopFlag = false;
                                                textView.setText("FINISH");
                                                count=10;

                                            }
                                        }

                                    }
                                });
                            }catch (Exception e){

                            }
                        }
                    }
                }).start();

            } else {
                isRun = true;
            }
        }else if(v==stop_btn){
            isRun=false;
        }

    }


}
