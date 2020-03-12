package com.jtmcompany.androidstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Part2_3_ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2_3__view);
        CheckBox check = findViewById(R.id.checkbox);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(Part2_3_ViewActivity.this, "체크박스이벤트발생!", Toast.LENGTH_SHORT).show();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Toast.makeText(Part2_3_ViewActivity.this, "라디오이벤트 발생!", Toast.LENGTH_SHORT).show();

            }

        });


    }
}