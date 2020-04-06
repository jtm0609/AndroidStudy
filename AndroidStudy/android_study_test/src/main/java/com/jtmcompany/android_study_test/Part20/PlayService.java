package com.jtmcompany.android_study_test.Part20;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.jtmcompany.android_study_test.R;

public class PlayService extends Service implements MediaPlayer.OnCompletionListener {
    MediaPlayer player;
    String filepath;

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String mode=intent.getStringExtra("mode");
            if(mode!=null){
                if(mode.equals("start")){
                    try{
                        Log.d("tak","서비스 onReceive-start");
                        if(player !=null && player.isPlaying()){
                            player.stop();
                            player.release();
                            player=null;
                        }
                        player =new MediaPlayer();
                        player.setDataSource(filepath);
                        player.prepare();
                        player.start();

                        Intent aIntent=new Intent("Play_To_Music");
                        aIntent.putExtra("mode","start");
                        aIntent.putExtra("duration",player.getDuration());
                        sendBroadcast(aIntent);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else if(mode.equals("stop")){
                    Log.d("tak","서비스 onReceive-stop");
                    if(player !=null && player.isPlaying()){
                        player.stop();
                        player.release();
                        player=null;
                    }
                }
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("tak","서비스  onCreate");
        registerReceiver(receiver,new IntentFilter("Play_To_Service"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        Log.d("tak","서비스  onDestroy");
    }

    //화면을 나갔다들어왔을때 프로그래스상태바 정보를 액티비티로 전달 ->restart
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForegroundService();
        Log.d("tak","서비스  onStartCommand");
        filepath=intent.getStringExtra("filePath");
        if(player!=null){
            Intent aIntent=new Intent("Play_To_Music");
            aIntent.putExtra("mode","restart");
            aIntent.putExtra("duration",player.getDuration());
            aIntent.putExtra("current",player.getCurrentPosition());
            sendBroadcast(aIntent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public PlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.d("tak","재생끝");
        Intent intent=new Intent("Play_To_Music");
        intent.putExtra("mode","stop");
        sendBroadcast(intent);
        stopSelf();
    }

    public void startForegroundService(){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"default");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("포그라운드서비스");
        builder.setContentText("포그라운드 서비스 실행중");
        Intent NotifiIntent=new Intent(this,Part20_Service.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,NotifiIntent,0);
        builder.setContentIntent(pendingIntent);

        NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O ) {
            manager.createNotificationChannel(new NotificationChannel("default2", "기본채널", NotificationManager.IMPORTANCE_DEFAULT));
        }
        startForeground(1,builder.build());
    }

}
