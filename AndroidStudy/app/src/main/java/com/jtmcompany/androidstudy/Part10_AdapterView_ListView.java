package com.jtmcompany.androidstudy;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class Part10_AdapterView_ListView extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] arrayDatas;
    ArrayList<HashMap<String,String>> simpleDatas=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part10__adapter_view__list_view);

        ListView arrayView=findViewById(R.id.main_listview_array);
        arrayView.setOnItemClickListener(this);
        ListView simpleView=findViewById(R.id.main_listview_simple);
        ListView cursorView=findViewById(R.id.main_listview_cursor);

        arrayDatas=getResources().getStringArray(R.array.arrayListItem);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,R.layout.item,R.id.item_name,arrayDatas);
        arrayView.setAdapter(arrayAdapter);

        HashMap<String,String> map=new HashMap<>();
        map.put("name", "정택민");
        map.put("content", "정택수");
        simpleDatas.add(map);

        map=new HashMap<>();
        map.put("name", "신짱구");
        map.put("content", "김철수");
        simpleDatas.add(map);

        SimpleAdapter adapter =new SimpleAdapter(this, simpleDatas,
                R.layout.item,
                new String[]{"name","content"},
                new int[]{R.id.item_name,R.id.item_content});
        simpleView.setAdapter(adapter);




    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(this, arrayDatas[position], Toast.LENGTH_SHORT).show();
    }
}
