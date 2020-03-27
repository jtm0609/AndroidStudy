package com.jtmcompany.androidstudy;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Part6_Event extends AppCompatActivity {

    float initX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2_6__event);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            initX=event.getRawX();
        }else if(event.getAction()==MotionEvent.ACTION_UP){
            float diffx=initX-event.getRawX();
            if(diffx>30){
                Toast.makeText(this, "왼쪽으로 화면을 밀었습니다.", Toast.LENGTH_SHORT).show();
            }else if(diffx<-30){
                Toast.makeText(this, "오른쪽으로 화면을 밀었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    long initTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Log.d("TAK","시스템시간: "+System.currentTimeMillis());
            if(System.currentTimeMillis()-initTime>3000){
                Toast.makeText(this, "종료하려면 한 번 더 누르세요.", Toast.LENGTH_SHORT).show();
                initTime=System.currentTimeMillis();
                Log.d("TAK","누른시간: "+initTime);
            }
            else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static class Part16_AsyncTask extends AppCompatActivity implements View.OnClickListener {
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
}
