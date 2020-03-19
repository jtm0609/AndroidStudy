package com.jtmcompany.androidstudy.Part2_8_DB;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jtmcompany.androidstudy.R;

import io.realm.Realm;

public class Part2_8_DBMS_Sqlite_Realm2 extends AppCompatActivity {
    TextView Title;
    TextView Content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2_8__dbms__sqlite2);
        Title=findViewById(R.id.title);
        Content=findViewById(R.id.content);

        //SQLITE
        /*
        DBHelper helper=new DBHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor= db.rawQuery("select title, content from TakminDB"
       ,null);


        while (cursor.moveToNext()){
            Title.setText(cursor.getString(0));
            Content.setText(cursor.getString(1));
        }
        db.close();
         */

        //Realm
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        Realm mRealm=Realm.getDefaultInstance();
        MemoVO vo=mRealm.where(MemoVO.class).equalTo("title",title).findFirst();
        Title.setText(vo.title);
        Content.setText(vo.content);
    }
}
