package com.jtmcompany.androidstudy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class Part17_ViewPager extends AppCompatActivity implements View.OnClickListener {

    Button button1;
    Button button2;
    private ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part17__view_pager);
        button1=findViewById(R.id.button17_1);
        button2=findViewById(R.id.button17_2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);


        pager= findViewById(R.id.viewpager);
        MypagerAdapter mypagerAdapter=
                new MypagerAdapter(getSupportFragmentManager());
        pager.setAdapter(mypagerAdapter);
    }

    @Override
    public void onClick(View view) {
        if(view==button1){
            pager.setCurrentItem(0);
        }else if(view==button2){
            pager.setCurrentItem(1);

        }

    }

    class MypagerAdapter extends FragmentPagerAdapter{

        ArrayList<Fragment> fragments;
        public MypagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
            fragments =new ArrayList<>();
            fragments.add(new TwoFragment());
            fragments.add(new ThreeFragment());
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Log.d("tak","test: "+ position);
            return fragments.get(position);
    }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
