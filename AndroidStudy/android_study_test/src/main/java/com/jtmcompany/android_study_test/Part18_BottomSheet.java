package com.jtmcompany.android_study_test;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class Part18_BottomSheet extends AppCompatActivity {
    Button btn;
    CoordinatorLayout coordinatorLayout;
    private BottomSheetBehavior<View> perrsistentBottomSheet;
    private BottomSheetDialog modalBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part18__bottom_sheet);

        coordinatorLayout=findViewById(R.id.coordinator18);
        btn=findViewById(R.id.button18);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }

        });

        initPersistentBottomSheet();
    }

    private void createDialog(){
        List<DataV0> list=new ArrayList<>();

        DataV0 vo=new DataV0();
        vo.title="keep";
        vo.image= ResourcesCompat.getDrawable(getResources(),R.mipmap.ic_launcher,null);
        list.add(vo);

        vo=new DataV0();
        vo.title="Inbox";
        vo.image= ResourcesCompat.getDrawable(getResources(),R.mipmap.ic_launcher,null);
        list.add(vo);

        vo=new DataV0();
        vo.title="Messanger";
        vo.image= ResourcesCompat.getDrawable(getResources(),R.mipmap.ic_launcher,null);
        list.add(vo);

        vo=new DataV0();
        vo.title="Google+";
        vo.image= ResourcesCompat.getDrawable(getResources(),R.mipmap.ic_launcher,null);
        list.add(vo);

        RecyclerViewAdapter adapter= new RecyclerViewAdapter(list);
        View view=getLayoutInflater().inflate(R.layout.modal_sheet,null); //리싸이클러뷰
        RecyclerView recyclerView=view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        modalBottomSheet=new BottomSheetDialog(this);
        modalBottomSheet.setContentView(view);
        modalBottomSheet.show();






    }

    private void initPersistentBottomSheet(){
        View bottomSheet=coordinatorLayout.findViewById(R.id.bottom_sheet);
        perrsistentBottomSheet=BottomSheetBehavior.from(bottomSheet);
    }
}
