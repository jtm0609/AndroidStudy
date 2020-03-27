package com.jtmcompany.androidstudy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class Part17_PreferenceFragmentCompay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part17__preference_fragment_compay);

        SettingPreferenceFragment settingPreferenceFragment=new SettingPreferenceFragment();
        Intent intent=getIntent();
        if(intent!=null){
            String rootKey=intent.getStringExtra("TARGET");
            if(rootKey!=null){
                Bundle args=new Bundle();
                args.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT,rootKey);
                settingPreferenceFragment.setArguments(args);
            }
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.settings_fragment,settingPreferenceFragment,null).commit();
    }
}
