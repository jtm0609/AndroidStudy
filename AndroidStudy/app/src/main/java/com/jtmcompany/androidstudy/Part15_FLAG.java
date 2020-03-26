package com.jtmcompany.androidstudy;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Part15_FLAG extends AppCompatActivity implements View.OnClickListener {
Button Keyboardbutton;
EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part15__flag);
        Keyboardbutton=findViewById(R.id.toggleBtn);
        editText=findViewById(R.id.edit15);

        Keyboardbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        InputMethodManager manager=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        manager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            if(isInMultiWindowMode()){
                Toast.makeText(this, "onResume.... is InMultiWindowMode...yes", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        Toast.makeText(this, "onMultiWindowModeChanged..."+ isInMultiWindowMode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait....", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "landscape....", Toast.LENGTH_SHORT).show();
        }

    }
}
