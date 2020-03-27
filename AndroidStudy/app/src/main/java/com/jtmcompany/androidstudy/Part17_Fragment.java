package com.jtmcompany.androidstudy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Part17_Fragment extends AppCompatActivity implements View.OnClickListener {

    FragmentManager manager;
    OneFragment oneFragment;
    TwoFragment twoFragment;
    ThreeFragment threeFragment;

    Button button1;
    Button button2;
    Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part17__fragment);

        button1=findViewById(R.id.main_btn1);
        button2=findViewById(R.id.main_btn2);
        button3=findViewById(R.id.main_btn3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        manager=getSupportFragmentManager();

        oneFragment=new OneFragment();
        twoFragment=new TwoFragment();
        threeFragment= new ThreeFragment();

        FragmentTransaction ft=manager.beginTransaction();
        ft.addToBackStack(null);
        ft.add(R.id.main_container,twoFragment);
        ft.commit();

    }


    @Override
    public void onClick(View view) {
        if(view==button1){
            if(!twoFragment.isVisible()) {
                FragmentTransaction ft = manager.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.main_container, twoFragment);
                ft.commit();
            }
            }else if(view==button2){
                if(!oneFragment.isVisible())
                oneFragment.show(manager,null);
            }else if(view==button3){
                if(!threeFragment.isVisible()){
                    FragmentTransaction ft=manager.beginTransaction();
                    ft.addToBackStack(null);
                    ft.replace(R.id.main_container,threeFragment);
                    ft.commit();
                }
            }
        }
    }

