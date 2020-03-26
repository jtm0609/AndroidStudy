package com.jtmcompany.androidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Part15_ActivityLifeCycle2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part15__life_cycle2);
        Button button=findViewById(R.id.Button1515);
        final Intent intent=new Intent(this,Part15_ActivityLifeCycle.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                finish();
            }
        });

    }
}
