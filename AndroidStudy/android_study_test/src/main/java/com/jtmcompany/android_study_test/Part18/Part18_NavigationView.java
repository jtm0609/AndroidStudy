package com.jtmcompany.android_study_test.Part18;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.jtmcompany.android_study_test.R;

public class Part18_NavigationView extends AppCompatActivity {

    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    boolean isDrawerOpend;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)) {
            Log.d("tak","false");
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part18__navigation_view);

        getSupportActionBar().setDisplayShowTitleEnabled(false); //액티비티 라벨안보이게

        drawer=findViewById(R.id.main_drawer);
        toggle= new ActionBarDrawerToggle(this, drawer, R.string.drawer_open, R.string.drawer_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //햄버거모양 화면에 보이게
        toggle.syncState();

        NavigationView navigationView=findViewById(R.id.main_drawer_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id= menuItem.getItemId();
                if(id==R.id.menu_drawer_home){
                    Toast.makeText(Part18_NavigationView.this, "menu_drawer_home", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.menu_drawer_message){
                    Toast.makeText(Part18_NavigationView.this, "menu_drawer_message", Toast.LENGTH_SHORT).show();
                }else if(id==R.id.menu_drawer_add){
                    Toast.makeText(Part18_NavigationView.this, "menu_drawer_add", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });



    }
}
