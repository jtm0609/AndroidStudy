package com.jtmcompany.androidstudy.Part9_Permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.jtmcompany.androidstudy.R;

import java.io.File;
import java.io.FileWriter;

public class Part9_Permission extends AppCompatActivity {
    boolean fileReadPermission;
    boolean fileWritePermission;
    EditText contentView;
    Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part9__permission);

        contentView=findViewById(R.id.content9);
        btn=findViewById(R.id.button9);

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            fileReadPermission=true;
        }
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)==
        PackageManager.PERMISSION_GRANTED){
            fileWritePermission=true;
        }

        if(!fileReadPermission || !fileWritePermission){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},200);
        }



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content=contentView.getText().toString();

                if(fileReadPermission & fileWritePermission){
                    FileWriter writer;
                    try{
                        String dirPath= Environment.getExternalStorageDirectory().getAbsolutePath()+ "/Part9_Permission";
                        Log.d("Tak",Environment.getExternalStorageDirectory().getAbsolutePath());
                        File dir=new File(dirPath);

                        if(!dir.exists()){
                            dir.mkdir();
                        }

                        File file=new File(dir+ "/myfile.txt");
                        if(!file.exists()){
                            file.createNewFile();
                        }

                        writer=new FileWriter(file, true);
                        writer.write(content);
                        writer.flush();
                        writer.close();

                        Intent intent = new Intent(Part9_Permission.this, ReadFileActivity.class);

                        startActivity(intent);

                    }catch (Exception e){e.printStackTrace();}
                } else{
                    Toast.makeText(Part9_Permission.this, "퍼미션이 부여가안되있습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==200 && grantResults.length>0){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                fileReadPermission=true;
            if(grantResults[1]==PackageManager.PERMISSION_GRANTED)
                fileWritePermission=true;
        }
    }
}
