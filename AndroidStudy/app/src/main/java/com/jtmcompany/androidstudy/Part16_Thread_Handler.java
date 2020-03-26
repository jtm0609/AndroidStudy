package com.jtmcompany.androidstudy;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Part16_Thread_Handler extends AppCompatActivity implements View.OnClickListener {
    Button start_btn;
    Button stop_btn;
    TextView textView;
    MyThread thread;

    boolean loopFlag=true;
    boolean isFirst=true;
    boolean isRun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part16__thread__handler);

        start_btn=findViewById(R.id.start_btn);
        stop_btn=findViewById(R.id.stop_btn);
        textView=findViewById(R.id.main_textView);

        start_btn.setOnClickListener(this);
        stop_btn.setOnClickListener(this);
        thread=new MyThread();

    }

    @Override
    public void onClick(View v) {
        if (v==start_btn){
        if(isFirst){
            isFirst=false;
            isRun=true;
            thread.start();

        } else{
            isRun=true;
        }


        }else if(v==stop_btn){
            isRun=false;
        }

    }

    Handler handler =new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==1){
                Log.d("TAK","Test: "+msg.arg1);
                textView.setText(String.valueOf(msg.arg1));
            }else if(msg.what==2){
                textView.setText((String)msg.obj);
            }
        }
    };

    class MyThread extends Thread{
        @Override
        public void run() {
            try{
                int count=10;
                while(loopFlag){
                    Thread.sleep(1000);
                    if(isRun){
                        count--;
                        Message message=new Message();
                        message.what=1;
                        message.arg1=count;
                        handler.sendMessage(message);
                        if(count==0){
                            message=new Message();
                            message.what=2;
                            message.obj="Finish";
                            handler.sendMessage(message);
                            loopFlag=false;
                        }
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
