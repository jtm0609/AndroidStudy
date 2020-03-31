package com.jtmcompany.android_study_test;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Part19_BroadcastReceiver extends AppCompatActivity {
    MyAdapter myAdapter;
    List<String> list;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part19__broadcast_receiver);

        list=new ArrayList<String>();


        IntentFilter ifilter =new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus=registerReceiver(null,ifilter);

        int status=batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        boolean isCharging =status ==BatteryManager.BATTERY_STATUS_CHARGING;

        if(isCharging){
            int chargeplug=batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1);
            boolean usbcharge=chargeplug==BatteryManager.BATTERY_PLUGGED_USB;
            boolean accharge=chargeplug==BatteryManager.BATTERY_PLUGGED_AC;
            if(usbcharge){
                list.add("Battery is USB charging");
            } else if(accharge){
                list.add("Battery is AC Charging");
            }
            }

        else{
            list.add("Battery State is not Chargigng");

        }

        int level= batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale=batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
        float batteryPct=(level/(float)scale)*100;
        list.add("current Battery: "+batteryPct + "%");

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.PROCESS_OUTGOING_CALLS) !=
        PackageManager.PERMISSION_GRANTED||
        ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)!=
                PackageManager.PERMISSION_GRANTED||
        ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALL_LOG)!=
                PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_CALL_LOG},100);
        }

        registerReceiver(brOn, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(brOff, new IntentFilter(Intent.ACTION_SCREEN_OFF));
        registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_POWER_CONNECTED));
        registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));

        myAdapter=new MyAdapter(list);
        recyclerView=findViewById(R.id.broad_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100 && grantResults.length >0){
            if(grantResults[0]!=PackageManager.PERMISSION_GRANTED || grantResults[1] !=PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "no Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    BroadcastReceiver brOn=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            list.add("screen On");
            myAdapter.notifyDataSetChanged();
        }
    };

    BroadcastReceiver brOff=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            list.add("screen off...");
            myAdapter.notifyDataSetChanged();
        }
    };

    BroadcastReceiver batteryReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action= intent.getAction();
            if(action.equals(Intent.ACTION_POWER_CONNECTED)){
                list.add("On Connected");
                myAdapter.notifyDataSetChanged();
            }else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)){
                list.add("On Disconnced");
                myAdapter.notifyDataSetChanged();
            }
        }
    };


    private class MyAdapter extends RecyclerView.Adapter<Myholder>{
        List<String> lists= new ArrayList<String>();

        public MyAdapter(List lists){
            this.lists=lists;
        }
        @NonNull
        @Override
        public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           View root=LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
           return new Myholder(root);
        }

        @Override
        public void onBindViewHolder(@NonNull Myholder holder, int position) {
            String text=lists.get(position);
            holder.textView.setText(text);
        }

        @Override
        public int getItemCount() {
            return lists.size();
        }
    }

    private class Myholder extends RecyclerView.ViewHolder{
        TextView textView;
        public Myholder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.text18);
        }
    }



}
