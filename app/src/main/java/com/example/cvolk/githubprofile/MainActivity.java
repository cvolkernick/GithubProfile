package com.example.cvolk.githubprofile;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cvolk.githubprofile.clients.OkHttpHelper;
import com.example.cvolk.githubprofile.model.ProfileInfo;
import com.example.cvolk.githubprofile.utils.HandlerUtils;
import com.example.cvolk.githubprofile.utils.MessageUtils;
import com.example.cvolk.githubprofile.utils.parser.GsonParser;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    String BASE_URL = "https://api.github.com/users/";
    private TextView tvName;
    private EditText etUsername;
    private ImageView ivAvatar;
    private OkHttpHelper okHttpHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tvName);
        etUsername = findViewById(R.id.etUsername);
        ivAvatar = findViewById(R.id.ivAvatar);
        okHttpHelper = new OkHttpHelper();
        HandlerUtils.getDefault().setReceiver(new Handler(this));
    }

    public void makingRestCalls(View view) {

        switch (view.getId()) {
            case R.id.btnGetProfile:
                okHttpHelper.init(BASE_URL, etUsername.getText().toString());
                okHttpHelper.executeSync();
                break;
            case R.id.btnGetRepos:
                break;
        }
    }

    @Override
    public boolean handleMessage(Message message) {

        ProfileInfo profileInfo = (ProfileInfo) GsonParser
                .parse(MessageUtils.getMessage(message), ProfileInfo.class);

        tvName.setText(profileInfo.getName());
        Glide.with(getApplicationContext())
                .load(profileInfo.getAvatarUrl())
                .into(ivAvatar);

        Toast.makeText(this, profileInfo.getName(), Toast.LENGTH_SHORT).show();

        return false;
    }
}
