package com.jtmcompany.androidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Part15_FLAG_TEST extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part15__flag__test);

        Button button=findViewById(R.id.btn1);
        final Intent intent =new Intent(this, Part15_FLAG_TEST2.class);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);

            }
        });
    }
}
