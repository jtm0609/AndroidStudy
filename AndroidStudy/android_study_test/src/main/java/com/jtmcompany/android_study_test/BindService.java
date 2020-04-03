package com.jtmcompany.android_study_test;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class BindService extends Service {
    private int mCount=0;
    Boolean isService;

    @Override
    public void onCreate() {
        super.onCreate();
        isService=true;
        Log.d("tak","서비스 onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isService=false;
        Log.d("tak","서비스 onDestroy");
    }

    //서비스가종료된다고 스레드가종료되는게아님 bool함수이용
    @Override
    public int onStartCommand(Intent intent, final int flags, int startId) {
        Log.d("tak","서비스 onStartCommand");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<20; i++){
                    if(isService) { mCount++;
                    SystemClock.sleep(1000);

                    Log.d("tak","서비스 동작중: " +mCount);
                    } else
                        break;

                }
            }
        }).start();
        return START_NOT_STICKY;
    }

    public BindService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("tak","서비스 onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("tak","서비스 unBind");
        return super.onUnbind(intent);
    }

    private IBinder mBinder= new MyBinder();
    public class MyBinder extends Binder {

        public BindService getService(){
            return BindService.this;
        }

    }

    public int getCount(){
        return mCount;
    }
}
