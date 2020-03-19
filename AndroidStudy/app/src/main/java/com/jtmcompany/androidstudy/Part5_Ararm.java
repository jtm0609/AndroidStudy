package com.jtmcompany.androidstudy;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Part5_Ararm extends AppCompatActivity implements View.OnClickListener {

    Button vibrationBtn;
    Button systemBeepBtn;
    Button customBeepBtn;
    Button alertDialogBtn;
    Button listBtn;
    Button customAlertBtn;
    Button dateBtn;
    Button timeBtn;

    AlertDialog customDialog;
    AlertDialog listDialog;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2_5__ararm);

        vibrationBtn = findViewById(R.id.vibrate);
        systemBeepBtn = findViewById(R.id.systemBeep);
        customBeepBtn = findViewById(R.id.customSound);

        alertDialogBtn = findViewById(R.id.alertDialogBtn);
        listBtn = findViewById(R.id.listBtn);
        customAlertBtn = findViewById(R.id.customBtn);
        dateBtn = findViewById(R.id.dateBtn);
        timeBtn = findViewById(R.id.timeBtn);


        vibrationBtn.setOnClickListener(this);
        systemBeepBtn.setOnClickListener(this);
        customBeepBtn.setOnClickListener(this);

        alertDialogBtn.setOnClickListener(this);
        listBtn.setOnClickListener(this);
        customAlertBtn.setOnClickListener(this);
        dateBtn.setOnClickListener(this);
        timeBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == vibrationBtn) {
            Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vib.vibrate(1000);
        } else if (v == systemBeepBtn) {
            Uri notification = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
            ringtone.play();
        } else if (v == customBeepBtn) {
            MediaPlayer player = MediaPlayer.create(this, R.raw.test);
            player.start();
        } else if (v == alertDialogBtn) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.test);
            builder.setTitle("알림");
            builder.setMessage("정말 종료 하시겠습니까?");
            builder.setPositiveButton("OK", dialogListener);
            alertDialog = builder.create();
            alertDialog.show();
        } else if (v == listBtn) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("알림 벨소리");
            builder.setSingleChoiceItems(R.array.dialog_array, 0, dialogListener);
            builder.setPositiveButton("확인", null);
            builder.setNegativeButton("취소", null);
            listDialog = builder.create();
            listDialog.show();
        } else if (v == dateBtn) {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    Toast.makeText(Part5_Ararm.this, year + ":" + (monthOfYear + 1) + ":" + dayOfMonth, Toast.LENGTH_SHORT).show();
                }
            }, year, month, day);
            dateDialog.show();
        } else if (v == timeBtn) {
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog timeDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    Toast.makeText(Part5_Ararm.this, hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
                }
            }, hour, minute, false);
            timeDialog.show();
        } else if (v == customAlertBtn) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.custom_dialog_layout, null);
            builder.setView(view);
            builder.setPositiveButton("확인", dialogListener);
            builder.setNegativeButton("취소",null);

            customDialog=builder.create();
            customDialog.show();
        }

    }

    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (dialog == customDialog && which == DialogInterface.BUTTON_POSITIVE) {
                Toast.makeText(Part5_Ararm.this, "custom dialog 확인 click", Toast.LENGTH_SHORT).show();
            } else if (dialog == listDialog) {
                String[] datas = getResources().getStringArray(R.array.dialog_array);
                Toast.makeText(Part5_Ararm.this, datas[which] + " 선택 하셨습니다. ", Toast.LENGTH_SHORT).show();
            } else if (dialog == alertDialog && which == DialogInterface.BUTTON_POSITIVE) {
                Toast.makeText(Part5_Ararm.this, "alert dialog ok click .....", Toast.LENGTH_SHORT).show();
            }
        }
    };
}

