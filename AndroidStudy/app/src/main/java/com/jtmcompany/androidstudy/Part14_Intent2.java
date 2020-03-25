package com.jtmcompany.androidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Part14_Intent2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part14__intent2);
        final Intent intent=getIntent();
        String category=intent.getStringExtra("category");
        Toast.makeText(this, "받은내용: "+ category, Toast.LENGTH_SHORT).show();
        intent.putExtra("location", "청주");
        Button button=findViewById(R.id.button_intent);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}
