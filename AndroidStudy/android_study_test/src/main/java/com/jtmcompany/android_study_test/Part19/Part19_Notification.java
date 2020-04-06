package com.jtmcompany.android_study_test.Part19;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Person;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.jtmcompany.android_study_test.MainActivity2;
import com.jtmcompany.android_study_test.R;

public class Part19_Notification extends AppCompatActivity implements View.OnClickListener {
    Button basicBtn;
    Button bigPictureBtn;
    Button bigTextBtn;
    Button inboxBtn;
    Button progressBtn;
    Button headsupBtn;
    Button messageBtn;
    NotificationManager manager;
    NotificationCompat.Builder builder = null;
    boolean permission=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part19__notification);


        basicBtn = findViewById(R.id.lab2_basic);
        bigPictureBtn = findViewById(R.id.lab2_bigpicture);
        bigTextBtn = findViewById(R.id.lab2_bigtext);
        inboxBtn = findViewById(R.id.lab2_inbox);
        progressBtn = findViewById(R.id.lab2_progress);
        headsupBtn = findViewById(R.id.lab2_headsup);
        messageBtn = findViewById(R.id.lab2_message);


        basicBtn.setOnClickListener(this);
        bigPictureBtn.setOnClickListener(this);
        bigTextBtn.setOnClickListener(this);
        inboxBtn.setOnClickListener(this);
        progressBtn.setOnClickListener(this);
        headsupBtn.setOnClickListener(this);
        messageBtn.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "oneChannel";
            String channelName = "My Channel One";
            String channelDescription = "My Channel One Description";

            NotificationChannel channel = null;
            if (v == headsupBtn) {
                channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            } else {
                channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            }
            channel.setDescription(channelDescription);
            manager.createNotificationChannel(channel);
            builder = new NotificationCompat.Builder(this, channelId);
        } else{
            //API 26하위버젼은 이전방식이용
            builder=new NotificationCompat.Builder(this);
        }
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Content Title");
        builder.setContentText("Content Message");



        Intent intent=new Intent(this, MainActivity2.class);
        PendingIntent pIntent=PendingIntent.getActivity(this,10,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pIntent);

        PendingIntent pIntent1=PendingIntent.getBroadcast(this,0,new Intent(this,MyReceiver2.class),PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(new NotificationCompat.Action.Builder(
                R.mipmap.ic_launcher,"ACTION1",pIntent1).build());
        Bitmap largeIcon= BitmapFactory.decodeResource(getResources(),R.drawable.icon);
        builder.setLargeIcon(largeIcon);

        if(v==bigPictureBtn){
            Bitmap bigPicture = BitmapFactory.decodeResource(getResources(),R.drawable.icon);
            NotificationCompat.BigPictureStyle bigstyle=
                    new NotificationCompat.BigPictureStyle(builder);
            bigstyle.bigPicture(bigPicture);
            builder.setStyle(bigstyle);
        }else if(v==bigTextBtn){
            NotificationCompat.BigTextStyle bigTextStyle=
                    new NotificationCompat.BigTextStyle(builder);
            bigTextStyle.setSummaryText("BigText Summary");
            bigTextStyle.bigText("동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라만세!!!");
            builder.setStyle(bigTextStyle);
        }else if(v==inboxBtn){
            NotificationCompat.InboxStyle style=
                    new NotificationCompat.InboxStyle(builder);
            style.addLine("Activity");
            style.addLine("BroadCastReceiver");
            style.addLine("Service");
            style.addLine("ContentProvider");
            style.setSummaryText("Android Component");
            builder.setStyle(style);

        }else if(v==progressBtn){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=1; i<=10; i++){
                        builder.setAutoCancel(false);
                        builder.setOngoing(true);
                        builder.setProgress(10,i,false);
                        manager.notify(222,builder.build());

                        if(i>=10){
                            manager.cancel(222);
                        }
                        SystemClock.sleep(1000);

                    }
                }
            }).start();

        }else if(v==headsupBtn){
            builder.setFullScreenIntent(pIntent,true);






        }else if(v==messageBtn){
            Person sender1=new Person.Builder()
                    .setName("kkang")
                    .setIcon(Icon.createWithResource(this,R.mipmap.ic_launcher))
                    .build();

            Person sender2=new Person.Builder()
                    .setName("taek")
                    .setIcon(Icon.createWithResource(this,R.mipmap.ic_launcher))
                    .build();


        }
        //노티여러개를띄울려면 식별자를 다르게주면됨(현재초기준)
    manager.notify((int)(System.currentTimeMillis()/1000),builder.build());
    }


}