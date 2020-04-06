package com.jtmcompany.android_study_test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.util.List;

public class Part21_Matisse extends AppCompatActivity {
Button button;
LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part21__matisse);
        button=findViewById(R.id.lab4_btn);
        linearLayout=findViewById(R.id.lab4_content);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },100);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Matisse.from(Part21_Matisse.this)
                        //모든 타입 선택
                        .choose(MimeType.of(MimeType.JPEG))
                        //몇 개를 선택했는지 개수가 오른쪽 아래에보인다.
                        .countable(true)
                        //최대 몇개 개수
                        .maxSelectable(9)
                        .spanCount(3)
                        .forResult(100);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && requestCode==RESULT_OK){
            List<Uri> mSelected=Matisse.obtainResult(data);

            for(Uri uri: mSelected){
                ImageView imageView=new ImageView(this);
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                linearLayout.addView(imageView,params);
                Glide.with(this).load(uri).override(400,400).into(imageView);

            }
        }
    }
}
