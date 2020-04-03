package com.jtmcompany.android_study_test;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Part20_Service_Bind extends AppCompatActivity {
    private BindService mService;
    private boolean mBound;
    Button startBt;
    Button stopBt;
    Button bindBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part20__service__bind);

        startBt=findViewById(R.id.Start);
        stopBt=findViewById(R.id.Stop);
        bindBt=findViewById(R.id.bind);

        startBt.setOnClickListener(new View.OnClickListener() {
            //Onstart()메소드로 바인드를연결하지만, 한화면에 계속있기때문에 중지 실행 반복을하기때문에 Onstart로 바인드를 할수없음
            // ->버튼을 누를때도 바인드연결을 시켜줘야함
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Part20_Service_Bind.this,BindService.class);
                startService(intent);
                if(mBound==false) {
                    bindService(intent, mConnection, BIND_AUTO_CREATE); //서비스생성 &&바인드해줌
                }

            }
        });

        stopBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Part20_Service_Bind.this,BindService.class);
                unbindService(mConnection);
                stopService(intent);

            }
        });

        bindBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCountValue();
            }
        });
    }
    //화면을 다시들어오면 바인드가 연결
    @Override
    protected void onStart() {
        Log.d("tak", "onStart 바인드연결");
        super.onStart();
        if(mBound==false) {
            Intent intent = new Intent(this, BindService.class);
            bindService(intent, mConnection, BIND_AUTO_CREATE); //서비스생성 &&바인드해줌
            mBound=true;
        }

    }



    //화면을나가면 바인드가 해제
    @Override
    protected void onStop() {
        Log.d("tak", "onStop 바인드해제");
        super.onStop();
        if (mBound){
            unbindService(mConnection);
            mBound=false;
        }
    }

    private ServiceConnection mConnection= new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("tak","서비스 onServiceConnected");
            BindService.MyBinder binder=(BindService.MyBinder) iBinder;
            mService=binder.getService();
            mBound=true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("tak","서비스 onServiceDisConnected");
            //예기치않은 종료

        }
    };
    
    
    public void getCountValue(){
        if(mBound){
            Log.d("tak","카운팅: "+mService.getCount());
        }
    }
}
