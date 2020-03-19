package com.jtmcompany.androidstudy.Part8_DB;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.jtmcompany.androidstudy.R;

import io.realm.Realm;

public class Part8_DBMS_Sqlite_Realm extends AppCompatActivity implements View.OnClickListener {
    Button Button;
    EditText Title;
    EditText Content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2_8__dbms__sqlite);

        Button= findViewById(R.id.Button);
        Title=findViewById(R.id.Title);
        Content=findViewById(R.id.Content);

        Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String title=Title.getText().toString();
        final String content=Content.getText().toString();

        //SQLITE
        /*
        DBHelper dbHelper= new DBHelper(this);
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        db.execSQL("insert into TakminDB (title, content) values (?,?)",
                new String[]{title,content});
        db.close();

         */

        //Realm
        Realm.init(this);
        Realm mRealm=Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                MemoVO vo= realm.createObject(MemoVO.class);
                vo.title=title;
                vo.content=content;
            }
        });
        Intent intent =new Intent(this, Part8_DBMS_Sqlite_Realm2.class);
        intent.putExtra("title",title);
        startActivity(intent);
    }
}
