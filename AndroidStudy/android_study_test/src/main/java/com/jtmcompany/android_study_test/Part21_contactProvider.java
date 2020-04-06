package com.jtmcompany.android_study_test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Part21_contactProvider extends AppCompatActivity {

    Button button;
    TextView tv_name;
    TextView tv_phone;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100 && grantResults.length>0){
            if(grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "no Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part21_contact_provider);
        button=findViewById(R.id.lab2_btn);
        tv_name=findViewById(R.id.lab2_name);
        tv_phone=findViewById(R.id.lab2_phone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setData(Uri.parse("content://com.android.contacts/data/phones"));
                startActivityForResult(intent,10);
            }
        });

       if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{
                    Manifest.permission.READ_CONTACTS
            },100);   
       }
       
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10 &&resultCode==RESULT_OK){
            String id=Uri.parse(data.getDataString()).getLastPathSegment();
            Cursor cursor=getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER},
                    ContactsContract.Data._ID+"="+id,null,null);
            cursor.moveToFirst();
            String name=cursor.getString(0);
            String phone=cursor.getString(1);
            tv_name.setText(name);
            tv_phone.setText(phone);

        }
    }
}
