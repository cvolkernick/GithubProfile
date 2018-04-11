package com.example.cvolk.githubprofile.clients;

import com.example.cvolk.githubprofile.utils.HandlerUtils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpHelper {

    OkHttpClient client;
    Request request;
    String BASE_URL;

    public void init(String BASE_URL, String username) {
        client = new OkHttpClient();
        request = new Request.Builder()
                .url(BASE_URL + username)
                .build();
    }

    public void executeSync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();

                    HandlerUtils.getDefault().sendMessage(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
