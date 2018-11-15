package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import org.json.JSONException;

public class JsonUtils {

    private static String title= "Title:";
    public static ArrayList<NewsItem> parseNews(String jsonResult){
        ArrayList<NewsItem> newsList = new ArrayList<>();
        try {
            JSONObject mainJSONObject = new JSONObject(jsonResult);
            JSONArray articles = mainJSONObject.getJSONArray("articles");

            for(int i = 0; i < articles.length(); i++){
                JSONObject item = articles.getJSONObject(i);
                newsList.add(new NewsItem(item.getString("author"),(title)+item.getString("title"),
                        item.getString("description"), item.getString("url" ), item.getString("urlToImage"), item.getString(("publishedAt"))));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }
}
