package com.example.rkjc.news_app_2;

import android.content.Context;
import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivityDemo";
    public String newsAppResults=null;
    private RecyclerView newsRecyclerview;
    private NewsRecyclerViewAdapter mAdapter;
    public TextView textView;
    private ArrayList<NewsItem> NewsItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRecyclerview = (RecyclerView)findViewById(R.id.news_recyclerview);
        mAdapter = new NewsRecyclerViewAdapter(this, NewsItems);
        newsRecyclerview.setAdapter(mAdapter);
        newsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        NewsQueryTask task = new NewsQueryTask();
        task.execute();

    }



    @Override
    protected void onResume() {
        super.onResume();
    }


    public void populateRecyclerView(String results){
        Log.d("mycode", results);
        NewsItems = JsonUtils.parseNews(results);
        mAdapter.mNewsItems.addAll(NewsItems);
        mAdapter.notifyDataSetChanged();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_refresh) {
            NewsQueryTask task = new NewsQueryTask();
            task.execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    class NewsQueryTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {
            try{
                newsAppResults=NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildUrl());
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            populateRecyclerView(newsAppResults);

        }

    }

    public void onLoadFinished(android.support.v4.content.Loader<String> loader, String data) {
        Log.d("mycode", data);
        populateRecyclerView(data);
    }


    public void onLoaderReset(android.support.v4.content.Loader<String> loader) {}


}
