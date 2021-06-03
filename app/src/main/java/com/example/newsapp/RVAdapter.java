package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewVH>{
    private Context mContext;
    private List<model_class> Allnews;

    public RVAdapter(Context mContext, List<model_class> allnews) {
        this.mContext = mContext;
        Allnews = allnews;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public CardViewVH onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,parent,false);

        return new CardViewVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull RVAdapter.CardViewVH holder, int position) {

        String title=Allnews.get(position).getTitle();
        String date=Allnews.get(position).getPubDate();
        String source=Allnews.get(position).getSource();
        String image_url=Allnews.get(position).getImage_url();
        String link=Allnews.get(position).getLink();

        holder.card_title.setText(title);
        holder.card_date.setText(date);
        holder.card_source.setText(source);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailsActivity.class);
                i.putExtra("url",link);
                mContext.startActivity(i);
            }
        });

        Picasso.get().load(image_url).into(holder.card_image);
    }

    @Override
    public int getItemCount() {
        return Allnews.size();
    }

    public void filterList(ArrayList<model_class> filteredList){
        Allnews=filteredList;
        notifyDataSetChanged();
    }

    public class CardViewVH extends RecyclerView.ViewHolder{
        public CardView cardView;
        public TextView card_title;
        public TextView card_date;
        public TextView card_source;
        public ImageView card_image;

        public CardViewVH(View view){
            super(view);

            cardView=view.findViewById(R.id.card_view_row);
            card_title=view.findViewById(R.id.card_title);
            card_date=view.findViewById(R.id.card_date);
            card_source=view.findViewById(R.id.card_source);
            card_image=view.findViewById(R.id.card_image);
        }
    }
}
