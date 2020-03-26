package com.jtmcompany.androidstudy.Part9_Permission;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jtmcompany.androidstudy.R;

public class Part16_AsyncTask extends AppCompatActivity implements View.OnClickListener {
    Button startbt;
    Button pausebt;
    boolean isFirst=true;
    TextView textView;
    MyAsynTask asynTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part16__async_task);
        startbt=findViewById(R.id.main_startBtn);
        pausebt=findViewById(R.id.main_pauseBtn);
        textView=findViewById(R.id.main_textView2);

        startbt.setOnClickListener(this);
        pausebt.setOnClickListener(this);
        asynTask=new MyAsynTask();



    }

    @Override
    public void onClick(View view) {
        if(view==startbt){
            if(isFirst){
                asynTask.isRun=true;
                asynTask.execute();
                isFirst=false;
            }
            else{
                asynTask.isRun=true;
            }
        }else if(view==pausebt){
            asynTask.isRun=false;
        }

    }

    public class MyAsynTask extends AsyncTask<Void,Integer,String> {
        boolean loopFlag=true;
        boolean isRun;
        @Override
        protected String doInBackground(Void... params) {
            int count=10;
            while(loopFlag){
                SystemClock.sleep(1000);
                if(isRun){
                    count--;
                    publishProgress(count);
                    if(count==0){
                        loopFlag=false;
                    }
                }
            }
            return "FINISH";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textView.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
    }
}
