package com.appstone.news21;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.appstone.news21.api.ApiInterface;
import com.appstone.news21.adapter.NewsAdapter;
import com.appstone.news21.model.NewsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NewsAdapter.OnItemClickListener {

    private static final String TAG = "MainActivity";
    private RecyclerView mRvNewsList;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }


        mRvNewsList = findViewById(R.id.rv_news_list);
        mRvNewsList.setHasFixedSize(true);
        mRvNewsList.setLayoutManager(new LinearLayoutManager(this));

        String category = getIntent().getStringExtra("key");
        mToolbar.setTitle(category);


        createRetrofitInstance(category);
    }

    private void createRetrofitInstance(String category) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface ai = retrofit.create(ApiInterface.class);
        if (category.equals("Sports")){

            ai.getSportNews().enqueue(new Callback<NewsModel>() {
                @Override
                public void onResponse(@NonNull Call<NewsModel> call, @NonNull Response<NewsModel> response) {
                    dialog.dismiss();
                    assert response.body() != null;
                    if (response.body().getStatus().equals("ok")) {
                        Log.d(TAG, "onResponse: :::::::: ok");
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        ArrayList<NewsModel.Articles> articles = (ArrayList<NewsModel.Articles>) response.body().getArticles();

                        setupAdapter(articles);
                    } else {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<NewsModel> call, @NonNull Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }
        if (category.equals("Finance")){
            ai.getFinanceNews().enqueue(new Callback<NewsModel>() {
                @Override
                public void onResponse(@NonNull Call<NewsModel> call, @NonNull Response<NewsModel> response) {
                    dialog.dismiss();
                    assert response.body() != null;
                    if (response.body().getStatus().equals("ok")) {
                        Log.d(TAG, "onResponse: :::::::: ok");
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        ArrayList<NewsModel.Articles> articles = (ArrayList<NewsModel.Articles>) response.body().getArticles();
                        setupAdapter(articles);
                    } else {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<NewsModel> call, @NonNull Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }

        if (category.equals("Entertainment")){
            ai.getEntertentmentNews().enqueue(new Callback<NewsModel>() {
                @Override
                public void onResponse(@NonNull Call<NewsModel> call, @NonNull Response<NewsModel> response) {
                    dialog.dismiss();
                    assert response.body() != null;
                    if (response.body().getStatus().equals("ok")) {
                        Log.d(TAG, "onResponse: :::::::: ok");
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        ArrayList<NewsModel.Articles> articles = (ArrayList<NewsModel.Articles>) response.body().getArticles();
                        setupAdapter(articles);
                    } else {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<NewsModel> call, @NonNull Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }

        if (category.equals("Technology")){
            ai.getTechNews().enqueue(new Callback<NewsModel>() {
                @Override
                public void onResponse(@NonNull Call<NewsModel> call, @NonNull Response<NewsModel> response) {
                    dialog.dismiss();
                    assert response.body() != null;
                    if (response.body().getStatus().equals("ok")) {
                        Log.d(TAG, "onResponse: :::::::: ok");
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        ArrayList<NewsModel.Articles> articles = (ArrayList<NewsModel.Articles>) response.body().getArticles();
                        setupAdapter(articles);
                    } else {
                        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<NewsModel> call, @NonNull Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }

    }

    private void setupAdapter(ArrayList<NewsModel.Articles> articles) {
        adapter = new NewsAdapter(getApplicationContext(),articles);
        adapter.setListener(this);
        mRvNewsList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(String image, String content) {

        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("content", content);
        intent.putExtra("img", image);
        startActivity(intent);

    }
}
