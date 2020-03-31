package com.jtmcompany.android_study_test.Part18;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jtmcompany.android_study_test.R;

import java.util.ArrayList;
import java.util.List;

public class Part18_CoordinatorLayout_AppBarLayout extends AppCompatActivity {
CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part18__coordinator_layout__app_bar_layout);
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //현재 액션바가 없으니 툴바를 액션바로 대체 하겠다는 뜻이고

        RecyclerView recyclerview =findViewById(R.id.recycler);

        List<String> list= new ArrayList<>();
        for( int i=0; i<20; i++){
            list.add("Item= "+i);
        }


        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(new MyAdapter(list));
        FloatingActionButton fab=findViewById(R.id.fab2);
        coordinatorLayout=findViewById(R.id.coordinator);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(coordinatorLayout,"스낵바",Snackbar.LENGTH_LONG).setAction("확인", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();

            }
        });
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private List<String> list;
        public MyAdapter(List<String> list) {
            this.list=list;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            String text=list.get(position);
            holder.title.setText(text);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        private class MyViewHolder extends RecyclerView.ViewHolder{
            public TextView title;


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                title= itemView.findViewById(R.id.text18);
            }
        }
    }
}
