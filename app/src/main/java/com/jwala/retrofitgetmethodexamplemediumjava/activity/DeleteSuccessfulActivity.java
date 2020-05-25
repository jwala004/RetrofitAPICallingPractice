package com.jwala.retrofitgetmethodexamplemediumjava.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jwala.retrofitgetmethodexamplemediumjava.R;

public class DeleteSuccessfulActivity extends AppCompatActivity {

    TextView tvResponseCodeReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_successful);

        tvResponseCodeReceived = findViewById(R.id.tvResponseCodeReceived);

        int responseCodeReceived = getIntent().getIntExtra("deletingPostId", 11);

        if (!TextUtils.isEmpty("" + responseCodeReceived)) {
            tvResponseCodeReceived.setText("" + responseCodeReceived + " Deleting Successful");
        }else {
            Toast.makeText(this,"something went wrong",Toast.LENGTH_SHORT).show();
        }

    }
}
