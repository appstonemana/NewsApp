package com.appstone.news21.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.appstone.news21.DetailsActivity;
import com.appstone.news21.R;
import com.appstone.news21.model.NewsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

//    private static final String TAG = NewsAdapter.class.getSimpleName();

    private Context mContext;
    private ArrayList<NewsModel.Articles> mData;


    public NewsAdapter(Context context, ArrayList<NewsModel.Articles> data) {
        this.mContext = context;
        this.mData = data;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cell_news, viewGroup, false);

        return new NewsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        // include binding logic here
        final NewsModel.Articles news = mData.get(position);
        holder.mTvNewsTitle.setText(news.getTitle());
        Picasso.get().load(news.getUrlToImage())
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder)
                .into(holder.mIvNewsImg);
        holder.mCvRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("content", news.getContent());
                intent.putExtra("img", news.getUrlToImage());
                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView mIvNewsImg;
        TextView mTvNewsTitle;
        CardView mCvRoot;

        NewsViewHolder(View itemView) {
            super(itemView);

            mIvNewsImg = itemView.findViewById(R.id.iv_news_img);
            mTvNewsTitle = itemView.findViewById(R.id.tv_news_title);
            mCvRoot = itemView.findViewById(R.id.cv_root);
        }
    }
}