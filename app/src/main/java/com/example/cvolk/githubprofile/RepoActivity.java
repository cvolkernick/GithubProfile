package com.example.cvolk.githubprofile;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cvolk.githubprofile.adapters.MyAdapter;
import com.example.cvolk.githubprofile.clients.OkHttpHelper;
import com.example.cvolk.githubprofile.model.Repository;
import com.example.cvolk.githubprofile.utils.HandlerUtils;
import com.google.gson.Gson;

public class RepoActivity extends AppCompatActivity implements Handler.Callback {

    public static final String BASE_URL = "https://api.github.com/users/";
    Repository[] repositories;
    RecyclerView rvRepos;
    String username;
    OkHttpHelper okHttpHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);

        rvRepos = findViewById(R.id.rvRepos);
        username = getIntent().getStringExtra("username");

        HandlerUtils.getDefault().setReceiver(new Handler(this));
        okHttpHelper = new OkHttpHelper();
        okHttpHelper.init(BASE_URL, username + "/repos");
        okHttpHelper.executeSync();
    }

    @Override
    public boolean handleMessage(Message message) {
        Gson gson = new Gson();
        repositories = gson.fromJson(message.getData().getString("results"), Repository[].class);

        String[] repoNames = new String[repositories.length];

        for (int i = 0; i < repoNames.length; i++) {
            repoNames[i] = repositories[i].getName();
        }

        rvRepos.setHasFixedSize(false);
        rvRepos.setLayoutManager(new LinearLayoutManager(this));
        rvRepos.setAdapter(new MyAdapter(repoNames));

        return false;
    }
}