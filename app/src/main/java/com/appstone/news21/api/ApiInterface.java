package com.appstone.news21.api;

import com.appstone.news21.model.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("top-headlines?sources=espn-cric-info&apiKey=6cd4cc56897c46e7be3b6f1bfe8c9953")
    Call<NewsModel> getSportNews();

    @GET("top-headlines?sources=hacker-news&apiKey=6cd4cc56897c46e7be3b6f1bfe8c9953")
    Call<NewsModel> getTechNews();

    @GET("top-headlines?sources=financial-times&apiKey=6cd4cc56897c46e7be3b6f1bfe8c9953")
    Call<NewsModel> getFinanceNews();

    @GET("top-headlines?sources=buzzfeed&apiKey=6cd4cc56897c46e7be3b6f1bfe8c9953")
    Call<NewsModel> getEntertentmentNews();
}
