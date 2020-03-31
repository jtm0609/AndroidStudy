package com.jtmcompany.android_study_test;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ItemHolder> {
    private List<DataV0> list;
    public RecyclerViewAdapter(List<DataV0> list) {
        this.list=list;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.sheet_item,parent,false); //아이템 레이아웃
        return new ItemHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        DataV0 vo=list.get(position);
        holder.textView.setText(vo.title);
        holder.imageView.setImageDrawable(vo.image);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class ItemHolder extends RecyclerView.ViewHolder{
    TextView textView;
    ImageView imageView;
    public ItemHolder(View root) {
        super(root);
        imageView =root.findViewById(R.id.sheet_row_img);
        textView=root.findViewById(R.id.sheet_row_tv);

    }
}

class DataV0{
    String title;
    Drawable image;

}
