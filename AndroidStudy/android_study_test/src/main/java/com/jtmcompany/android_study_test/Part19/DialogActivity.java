package com.jtmcompany.android_study_test.Part19;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jtmcompany.android_study_test.R;

public class DialogActivity extends AppCompatActivity {
ImageView finishBtn;
TextView numberView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);

        numberView=findViewById(R.id.dialog_text);
        finishBtn=findViewById(R.id.dialog_remove_img);

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent=getIntent();
        String number=intent.getStringExtra("number");
        numberView.setText(number);


    }
}
