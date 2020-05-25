package com.jwala.retrofitgetmethodexamplemediumjava.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.jwala.retrofitgetmethodexamplemediumjava.R;
import com.jwala.retrofitgetmethodexamplemediumjava.model.PostModel;

public class ReceivePostResponseActivity extends AppCompatActivity {

    TextView tvId, tvUserId, tvTitle, tvBody, responseCode;
//    private PostModel postModelJustToSetTextId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_post_pesponse);

        tvId = findViewById(R.id.tvId);

        tvUserId = findViewById(R.id.tvUserId);
        tvTitle = findViewById(R.id.tvTitle);
        tvBody = findViewById(R.id.tvBody);
        responseCode = findViewById(R.id.responseCode);

        PostModel postModel = getIntent().getParcelableExtra("customObject");

        tvUserId.setText("User ID :   "+postModel.getUserId());
        tvTitle.setText("Title :        "+postModel.getTitle());
        tvBody.setText("Body :       "+postModel.getBody());
        //       just setting the default text Id, which is coming from server
        tvId.setText("ID :            "+postModel.getId());


    }
}
