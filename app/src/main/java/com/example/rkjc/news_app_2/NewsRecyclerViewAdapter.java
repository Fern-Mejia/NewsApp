package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {
    Context mContext;
    ArrayList<NewsItem> mNewsItems;

    public NewsRecyclerViewAdapter(Context context, ArrayList<NewsItem> NewsItems){
        this.mContext = context;
        this.mNewsItems = NewsItems;
    }

    @Override
    public NewsRecyclerViewAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsRecyclerViewAdapter.NewsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        String text;
        TextView author;
        TextView title;
        TextView description;
        TextView url;
        TextView urlToImage;
        TextView publishedAt;

        public NewsViewHolder(View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.author);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            url = (TextView) itemView.findViewById(R.id.url);
            urlToImage = (TextView) itemView.findViewById(R.id.UrlToImage);
            publishedAt = (TextView) itemView.findViewById(R.id.PublishedAt);
        }

        void bind(final int listIndex) {
            author.setText("Author: ");
            author.append(mNewsItems.get(listIndex).getAuthor());
            title.setText("Title: ");
            title.append(mNewsItems.get(listIndex).getTitle());
            description.setText(mNewsItems.get(listIndex).getDescription());
            url.setText(mNewsItems.get(listIndex).getUrl());
            urlToImage.setText(mNewsItems.get(listIndex).getUrlToImage());
            publishedAt.setText(mNewsItems.get(listIndex).getPublishedAt());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String urlString = mNewsItems.get(getAdapterPosition()).getUrl();
            Intent intent = new Intent(mContext, WebActivity.class);
            intent.putExtra("urlString", urlString);
            mContext.startActivity(intent);
        }
    }

}
