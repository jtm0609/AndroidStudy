package com.jtmcompany.android_study_test.Part25;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jtmcompany.android_study_test.R;

import org.json.JSONObject;

import java.util.HashMap;

public class Part25_HTTP extends AppCompatActivity {
    TextView titleView;
    TextView dateView;
    TextView contentView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part25__http);

        titleView=findViewById(R.id.lab1_title);
        dateView=findViewById(R.id.lab1_date);
        contentView=findViewById(R.id.lab1_content);
        imageView=findViewById(R.id.lab1_image);

        HashMap<String, String> map=new HashMap<>();
        map.put("name","kkang");

        HttpRequest httpRequest=new HttpRequest();
        httpRequest.request("http://172.30.1.44:8000/files/test.json",map,httpCallback);
    }

    HttpCallback httpCallback=new HttpCallback() {
        @Override
        public void onResult(String result) {

            try{
                Log.d("tak","onResult");
                JSONObject root = new JSONObject(result);
                titleView.setText(root.getString("title"));
                dateView.setText(root.getString("date"));
                contentView.setText(root.getString("content"));

                String imageFile=root.getString("file");
                if(imageFile!=null &&!imageFile.equals("")){
                    HttpImageRequest imageRequest=new HttpImageRequest();
                    imageRequest.request("http://172.30.1.44:8000/files/"+imageFile,null,imageCallback);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    HttpImageCallback imageCallback=new HttpImageCallback() {
        @Override
        public void onResult(Bitmap d) {
            imageView.setImageBitmap(d);
        }
    };
}
