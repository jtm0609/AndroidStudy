package com.jtmcompany.android_study_test.Part25_Retrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jtmcompany.android_study_test.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Part25_Retrofit extends AppCompatActivity {

    private static final String QUERY="bitcoin";
    private static final String API_KIEY="fb8e276a4de24b8d925b0e8ff7db3cf9";
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part25__retrofit);
        recyclerView=findViewById(R.id.lab3_list2);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RetrofitService networkService = RetrofitFactory.create();
        networkService.getList(QUERY, "2020-03-09", "publishedAt", API_KIEY)
                .enqueue(new Callback<PageListModel>() {
                    @Override
                    public void onResponse(Call<PageListModel> call, Response<PageListModel> response) {
                        if (response.isSuccessful()) {
                            MyAdapter adapter = new MyAdapter(response.body().articles);
                            recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<PageListModel> call, Throwable t) {

                    }
                });
    }
        class ItemViewHolder extends RecyclerView.ViewHolder {

            public TextView itemTitleView;
            public TextView itemTimeView;
            public TextView itemDescView;
            public ImageView itemImageView;

            public ItemViewHolder(View view) {
                super(view);
                itemTitleView=view.findViewById(R.id.lab3_item_title);
                itemTimeView=view.findViewById(R.id.lab3_item_time);
                itemDescView=view.findViewById(R.id.lab3_item_desc);
                itemImageView=view.findViewById(R.id.lab3_item_image);

            }
        }
        class MyAdapter extends RecyclerView.Adapter<ItemViewHolder> {
            List<itemModel> articles;
            public MyAdapter(List<itemModel> articles) {
                this.articles=articles;
            }
            @Override
            public int getItemCount() {
                return articles.size();
            }


            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_lab3, parent, false);
                return new ItemViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
                itemModel item=articles.get(position);

                String author = item.author == null || item.author.isEmpty() ? "Anonymous" : item.author;
                String titleString =  author+" - "+ item.title;
                holder.itemTitleView.setText(titleString);

                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
                Date date = null;
                try {
                    date = format1.parse(item.publishedAt);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                DateFormat sdf = new SimpleDateFormat("MMM d, yyyy");

                DateFormat sdf2 = new SimpleDateFormat("h:mm a");
                Date netDate = (date);
                holder.itemTimeView.setText(sdf.format(date)+" at "+ sdf2.format(netDate));
                holder.itemDescView.setText(item.description);
                Glide.with(Part25_Retrofit.this).load(item.urlToImage).override(250, 200).into(holder.itemImageView);

            }



        }

}
