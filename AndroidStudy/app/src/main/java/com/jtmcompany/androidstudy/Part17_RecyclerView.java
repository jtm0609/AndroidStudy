package com.jtmcompany.androidstudy;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Part17_RecyclerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_part17__recycler_view);
        RecyclerView recyclerView=findViewById(R.id.recycler);
        List<String> list=new ArrayList<>();
        for(int i=0; i<20; i++){
            list.add("Item="+i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(list));
        Log.d("tak","onCreate");
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
        private List<String> list;
        public MyAdapter(List<String>list){
            this.list=list;
            Log.d("tak","MyAdapter");
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
            Log.d("tak","onCreateViewHolder()");
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            String text=list.get(position);
            holder.title.setText(text);
        }

        @Override
        public int getItemCount() {
            Log.d("tak","개수: "+ list.size());
            return list.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public MyViewHolder(View itemView){
            super(itemView);
            title=itemView.findViewById(R.id.recycler_item_text);

        }
    }
}
