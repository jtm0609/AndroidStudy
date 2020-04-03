package com.jtmcompany.android_study_test.Part19;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "I am NotiReceiver", Toast.LENGTH_SHORT).show();
    }
}
