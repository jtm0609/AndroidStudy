package com.jtmcompany.androidstudy;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
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
}
