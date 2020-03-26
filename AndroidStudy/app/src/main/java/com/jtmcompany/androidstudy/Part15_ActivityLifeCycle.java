package com.jtmcompany.androidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Part15_ActivityLifeCycle extends AppCompatActivity {

    private static final String LOG = "TAK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part15__life_cycle);
        Log.d(LOG,"onCreate");
        Button button=findViewById(R.id.button15);
        final Intent intent= new Intent(this,Part15_ActivityLifeCycle2.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(intent);
              finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG,"onStart");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String data1=savedInstanceState.getString("data1");
        int data2=savedInstanceState.getInt("data2");
        Log.d(LOG,"onRestoreInstanceState");
        Log.d(LOG,"저장된값: "+data1);
        Log.d(LOG,"저장된값: "+data2);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG,"onPause");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG,"onSaveInstanceState");
        outState.putString("data1","저장된 데이터1");
        outState.putInt("data2",100);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG,"onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG,"onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG,"onDestroy");
    }

}
