package com.jtmcompany.androidstudy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class OneFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.icon);
        builder.setTitle("다이얼로그프래그먼트");
        builder.setMessage("잘 보이지요?");
        builder.setPositiveButton("OK",null);
        AlertDialog dialog=builder.create();
        return dialog;
    }
}
