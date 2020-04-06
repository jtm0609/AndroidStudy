package com.jtmcompany.android_study_test.Part20;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.jtmcompany.android_study_test.R;

import java.io.File;
import java.io.IOException;

public class Part20_Service extends AppCompatActivity implements View.OnClickListener {
    Button playBtn;
    Button stopBtn;
    ProgressBar progressBar;
    TextView titleView;
    String filePath;
    boolean runThread;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0 && requestCode==100){
            if(grantResults[0]!=PackageManager.PERMISSION_GRANTED|| grantResults[1]!=PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "no Permission", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part20__service);


        playBtn=findViewById(R.id.play);
        stopBtn=findViewById(R.id.stop);
        progressBar=findViewById(R.id.progress);
        titleView=findViewById(R.id.title);

        playBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        titleView.setText("music.mp3");
        stopBtn.setEnabled(false);

        String dirPath;
        //dirPath= Environment.getExternalStorageDirectory().getAbsolutePath()+"/MyMusic";
        dirPath=getExternalFilesDir(Environment.DIRECTORY_MUSIC)+"/MyMusic";
        File dir=new File(dirPath);
        if(!dir.exists()){
            dir.mkdir();
        }

        File file=new File((dir+"/myfile.txt"));
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        filePath=dirPath+"/sing.mp3";


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
        }

        registerReceiver(receiver,new IntentFilter("Play_To_Music"));

        Intent intent=new Intent(this,PlayService.class);
        intent.putExtra("filePath",filePath);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            startForegroundService(intent);
        }else {
            startService(intent);
        }
    }

    class ProgressThread extends Thread{
        @Override
        public void run() {
            super.run();
            while(runThread){
                progressBar.incrementProgressBy(1000);  //*****
                SystemClock.sleep(1000);
                if(progressBar.getProgress()==progressBar.getMax()){
                    runThread=false;
                }
            }
        }
    }



    BroadcastReceiver receiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        String mode=intent.getStringExtra("mode");
        if(mode!=null){
            if(mode.equals("start")){
                Log.d("tak","액티비티 onReceive-start");
                int duration=intent.getIntExtra("duration",0);
                progressBar.setMax(duration);
                progressBar.setProgress(0);
            }else if(mode.equals("stop")){
                runThread=false;
                Log.d("tak","액티비티 onReceive-stop");

            }else if(mode.equals("restart")){
                Log.d("tak","액티비티 onReceive-restart");
                int duration=intent.getIntExtra("duration",0);
                int current=intent.getIntExtra("current",0);
                progressBar.setMax(duration);
                progressBar.setProgress(current);

                ProgressThread thread= new ProgressThread();
                thread.start();

                playBtn.setEnabled(false);
                stopBtn.setEnabled(true);
            }
        }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void onClick(View v) {
        if(v==playBtn){
            Intent intent=new Intent("Play_To_Service");
            intent.putExtra("mode","start");
            sendBroadcast(intent);

            runThread=true;
            ProgressThread thread=new ProgressThread();
            thread.start();
            playBtn.setEnabled(false);
            stopBtn.setEnabled(true);
        }else if(v==stopBtn){
            Intent intent= new Intent("Play_To_Service");
            intent.putExtra("mode","stop");
            sendBroadcast(intent);
            runThread=false;
            progressBar.setProgress(0);
            playBtn.setEnabled(true);
            stopBtn.setEnabled(false);

        }
    }
}
