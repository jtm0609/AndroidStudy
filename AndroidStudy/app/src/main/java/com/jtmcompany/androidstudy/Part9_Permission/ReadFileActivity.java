package com.jtmcompany.androidstudy.Part9_Permission;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jtmcompany.androidstudy.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file);
        TextView textView=findViewById(R.id.text);

        File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Part9_Permission/myfile.txt");
        try{
            BufferedReader reader=new BufferedReader(new FileReader(file));
            StringBuffer buffer=new StringBuffer();
            String line;
            while((line=reader.readLine())!=null){
                buffer.append(line);
            }
            textView.setText(buffer.toString());
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
