package com.example.cvolk.githubprofile.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.cvolk.githubprofile.model.Constants;

public class HandlerUtils {

    public static HandlerUtils instance = null;
    Handler handler;

    private HandlerUtils() {

    }

    public static HandlerUtils getDefault() {
        if (instance == null) {
            instance = new HandlerUtils();
        }

        return instance;
    }

    public void setReceiver(Handler handler) {
        this.handler = handler;
    }

    public void sendMessage(String message) {

        Message handlerMessage = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY.RESULT, message);
        handlerMessage.setData(bundle);
        handler.sendMessage(handlerMessage);
    }

    public static class Mode {
        public static final String OKHTTP = "okhttp";
    }
}
