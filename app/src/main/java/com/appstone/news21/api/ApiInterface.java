package com.appstone.news21.api;

import com.appstone.news21.model.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("top-headlines?sources=espn-cric-info&apiKey=bd7852f683354058982aacab5a694fcd")
    Call<NewsModel> getSportNews();

    @GET("top-headlines?sources=hacker-news&apiKey=bd7852f683354058982aacab5a694fcd")
    Call<NewsModel> getTechNews();

    @GET("top-headlines?country=in&category=health&apiKey=bd7852f683354058982aacab5a694fcd")
    Call<NewsModel> getFinanceNews();

    @GET("top-headlines?sources=buzzfeed&apiKey=bd7852f683354058982aacab5a694fcd")
    Call<NewsModel> getEntertentmentNews();
}
