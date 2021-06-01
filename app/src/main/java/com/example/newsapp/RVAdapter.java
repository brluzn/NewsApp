package com.example.newsapp;

import android.content.Context;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RVAdapter {
    private Context mContext;
    private List<model_class> Allnews;

    public RVAdapter(Context mContext, List<model_class> allnews) {
        this.mContext = mContext;
        Allnews = allnews;
    }

    public class CardViewVH extends RecyclerView.ViewHolder{
        public CardView cardView;
        public TextView card_title;
        public TextView card_date;
        public TextView card_source;
    }
}
