package com.example.cvolk.githubprofile.utils;

import android.os.Message;

import com.example.cvolk.githubprofile.model.Constants;

public class MessageUtils {

    public static String getMessage(Message message) {

        return message.getData().getString(Constants.KEY.RESULT);
    }
}
