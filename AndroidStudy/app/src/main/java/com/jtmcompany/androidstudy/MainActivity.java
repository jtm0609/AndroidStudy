package com.jtmcompany.androidstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout= new LinearLayout(this);

        Button btn=new Button(this);
        btn.setText("버튼1");
        linearLayout.addView(btn);

        Button btn2=new Button(this);
        btn2.setText("버튼2");
        linearLayout.addView(btn2);

        setContentView(linearLayout);


    }
}