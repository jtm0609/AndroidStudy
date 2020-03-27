package com.jtmcompany.androidstudy;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Part16_Looper1 extends AppCompatActivity {
    Button button;
    TextView text;
    EditText edittext;
    OneThread onThread;
    TwoThread twoThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part16__looper1);
        button=findViewById(R.id.button16);
        text=findViewById(R.id.text16);
        edittext=findViewById(R.id.edit16);
        onThread=new OneThread();
        twoThread=new TwoThread();

        onThread.start();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(twoThread.isAlive()){
                    twoThread.interrupt();
                }
                else {
                    twoThread = new TwoThread();
                    twoThread.start();
                }

                InputMethodManager manager=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                manager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
            }
        });
    }


    class OneThread extends Thread{
        Handler oneHandler;
        @Override
        public void run() {
            Log.d("TEST", "OneThread");
            super.run();
            Looper.prepare();
            oneHandler=new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    Log.d("TEST", "handleMessage");
                    super.handleMessage(msg);
                    if(msg.what==0)
                    text.setText((String)msg.obj);
                }
            };
            Looper.loop();
        }
    }

    class TwoThread extends Thread{
        @Override
        public void run() {
            Log.d("TEST", "TwoThread");
            super.run();
            Message message=new Message();
            message.what=0;
            message.obj=edittext.getText().toString();
            onThread.oneHandler.sendMessage(message);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onThread.oneHandler.getLooper().quit();
    }
}
