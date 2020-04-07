package com.jtmcompany.android_study_test.Part21;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jtmcompany.android_study_test.R;

public class Part21_Glide extends AppCompatActivity {
    ImageView gifView;
    ImageView networkView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part21__glide);

        gifView=findViewById(R.id.lab3_gif);
        networkView=findViewById(R.id.lab3_network);
        Glide.with(this)
                .load(R.raw.loading)
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .override(200,200)
                .into(gifView);

        String url="https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";
        Glide.with(this)
                .load(url)
                .override(400,400)
                .error(R.drawable.error)
                .into(networkView);
    }
}
