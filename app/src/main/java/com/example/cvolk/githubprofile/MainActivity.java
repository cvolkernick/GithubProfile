package com.example.cvolk.githubprofile;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cvolk.githubprofile.clients.OkHttpHelper;

public class MainActivity extends AppCompatActivity {

    String BASE_URL = "https://api.github.com/users/";
    private TextView tvName;
    private OkHttpHelper okHttpHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tvName);
        okHttpHelper = new OkHttpHelper();
    }

    public void makingRestCalls(View view) {

        switch (view.getId()) {
            case R.id.btnGetProfile:
                okHttpHelper.executeSync();
                break;
            case R.id.btnGetRepos:
                break;
        }
    }
}
