package com.jtmcompany.androidstudy;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.lang.reflect.Method;

public class Part12_ActionBar extends AppCompatActivity {


    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part12__action_bar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setIcon(R.drawable.icon);

        ImageView imageView=findViewById(R.id.imgview);
        registerForContextMenu(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_part12,menu);
        try{
            //오버플로우 메뉴 아이콘
            Method method=menu.getClass().getDeclaredMethod("setOptionalIconsVisible",boolean.class);
            method.setAccessible(true);
            method.invoke(menu,true);
        }catch (Exception e){
            e.printStackTrace();
        }

        MenuItem menuItem= menu.findItem(R.id.menu_main_search);
        searchView= (SearchView) menuItem.getActionView();
        searchView.setQueryHint("입력하세요");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                searchView.setQuery("",false);
                searchView.setIconified(true);
                Toast.makeText(Part12_ActionBar.this, s, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,0,0,"서버전송");
        menu.add(0,1,0,"보관함에 보관");
        menu.add(0,2,0,"삭제");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                Toast.makeText(this, "서버전송 선택", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "보관함 선택", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "삭제 선택", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            Toast.makeText(this, "Home AS UP CLICK", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
