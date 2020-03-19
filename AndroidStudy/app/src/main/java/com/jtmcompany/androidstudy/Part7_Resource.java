package com.jtmcompany.androidstudy;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Part7_Resource extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2_7__resource);
        imageView=findViewById(R.id.img);
        //에니메이션 획득
        Animation anim= AnimationUtils.loadAnimation(this,R.anim.in);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Animation anim=AnimationUtils.loadAnimation(Part7_Resource.this,R.anim.move);

                //마지막상황에서 안멈추게하기
                //안그러면 원상복귀가됨
                anim.setFillAfter(true);
                imageView.startAnimation(anim);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imageView.startAnimation(anim);
    }
}
