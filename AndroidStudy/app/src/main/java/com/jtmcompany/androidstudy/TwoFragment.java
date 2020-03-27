package com.jtmcompany.androidstudy;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TwoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("TEST","onCreateView");
        return inflater.inflate(R.layout.fragment_two,container,false);

    }

    @Override
    public void onDetach() {
        Log.d("TEST","onDetach");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TEST","onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TEST","onDestroyView");
    }

    @Override
    public void onStop() {
        Log.d("TEST","onStop");
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TEST","onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TEST","onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TEST","onStart");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TEST","onActivityCreated");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TEST","onCreate");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("TEST","onAttach");
    }
}
