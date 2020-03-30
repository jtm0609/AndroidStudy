package com.jtmcompany.android_study_test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Part18_TabLayout_Snackbar extends AppCompatActivity {
ViewPager viewPager;
FloatingActionButton fab;
RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part18__tab_layout__snackbar);

        relativeLayout=findViewById(R.id.container);
        viewPager=findViewById(R.id.viewpage);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout =findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        fab=findViewById(R.id.floatingbt);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(relativeLayout,"스낵바다 이게바로",Snackbar.LENGTH_LONG)
                        .setAction("액션선택", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        }).show();
            }
        });

    }


    class MyPagerAdapter extends FragmentPagerAdapter {
        List<Fragment>fragments =new ArrayList<Fragment>();
        private String titles[] = new String[]{"TAB1", "TAB2", "TAB3"};
        public MyPagerAdapter(FragmentManager fm){
            super(fm);
            fragments.add(new fragmentOne());
            fragments.add(new fragmenTwo());
            fragments.add(new fragmentThree());
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            Log.d("tak","Log: "+ position);
            Log.d("tak","Log: "+ fragments.get(position));
            return this.fragments.get(position);
        }



        @Override
        public int getCount() {
            return this.fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            Log.d("tak","Log2: "+ titles[position]);
            return titles[position];

        }



    }
}
