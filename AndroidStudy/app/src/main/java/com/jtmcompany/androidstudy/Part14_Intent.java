package com.jtmcompany.androidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Part14_Intent extends AppCompatActivity {

    final static int REQUEST_CODE=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part14__intent);
        Button button= findViewById(R.id.button_intent0);

        //명시적인텐트
        //final Intent intent = new Intent(this, Part14_Intent2.class);

        //암시적인텐트
        final Intent intent=new Intent();
        intent.setAction("INTENT_ACTION");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category","category");
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

    }

    //화면이 돌아왔을때

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String location = data.getStringExtra("location");
            Toast.makeText(this, "받은내용: "+location, Toast.LENGTH_SHORT).show();
        }
    }
}
