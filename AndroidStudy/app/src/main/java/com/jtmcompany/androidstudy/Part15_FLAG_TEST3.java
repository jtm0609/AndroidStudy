package com.jtmcompany.androidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Part15_FLAG_TEST3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part15__flag__test3);

        Button button=findViewById(R.id.btn3);
        final Intent intent =new Intent(this, Part15_FLAG_TEST3.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);

            }
        });
    }
}


//<FLAG가 기본>
//액티비티2
//엑티비티3
//액티비티2
//액티비티1


//<FLAG_TEST2엑티비테 FLAG를 CLEAR_TOP을 했을때>
//액티비티2
//액티비티1


//<FLAG_TEST2엑티비티 FLAG를 _NEW_TASK를 했을때> //단 어피니티를 다른걸줘야함
//액티비티3
//액티비티2
//액티비티1

//새로운플래그목록
//액티비티2


//<FLAG_TEST2엑티비티 FLAG를 SINGLE_TOP했을때>

//엑티비티2
//엑티비티1