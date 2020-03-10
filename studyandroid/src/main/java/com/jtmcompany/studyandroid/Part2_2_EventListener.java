package com.jtmcompany.studyandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Part2_2_EventListener extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private Button mVisibleBtn;
    private Button mInvisibleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_2);
        mTextView=findViewById(R.id.textVisible);
        mVisibleBtn=findViewById(R.id.visibleTrue);
        mInvisibleBtn=findViewById(R.id.visibleFalse);
        //클릭이벤트 2가지방법
        //<방법1>
        /*mTextView.setOnClickListener(new View.OnClickListener() { //매개변수 익명구현객체 사용(인터페이스)
            @Override
            public void onClick(View view) {
                Toast.makeText(Lab3_2Activity.this, "방법1. 텍스트뷰가 눌렸습니다.", Toast.LENGTH_SHORT).show();
            }
        });
         */

        //<방법2>
        //implements View.OnClickListener추가
        mTextView.setOnClickListener(this);
        mVisibleBtn.setOnClickListener(this);
        mInvisibleBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v==mTextView)
            Toast.makeText(this, "방법2. 텍스트뷰가 눌렸습니다.", Toast.LENGTH_SHORT).show();

        else if(v==mVisibleBtn) {
            Toast.makeText(this, "버튼1이 눌렸습니다.", Toast.LENGTH_SHORT).show();
            mTextView.setVisibility(View.VISIBLE);
        }
        else {
            Toast.makeText(this, "버튼2이 눌렸습니다.", Toast.LENGTH_SHORT).show();
            mTextView.setVisibility(View.INVISIBLE);
        }

    }
}
