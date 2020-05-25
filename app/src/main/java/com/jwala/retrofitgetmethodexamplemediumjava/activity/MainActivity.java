package com.jwala.retrofitgetmethodexamplemediumjava.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jwala.retrofitgetmethodexamplemediumjava.R;

public class MainActivity extends AppCompatActivity {

    TextView getRequestActivity, urlManipulationActivity,postRequestActivity, deleteRequestActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getRequestActivity = findViewById(R.id.getRequestActivity);
        urlManipulationActivity = findViewById(R.id.urlManipulationActivity);
        postRequestActivity = findViewById(R.id.postRequestActivity);
        deleteRequestActivity = findViewById(R.id.deleteRequestActivity);

        getRequestActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GETRequestActivity.class));
            }
        });

        urlManipulationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, URLManipulationActivity.class));
            }
        });

        postRequestActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PostRequestActivity.class));
            }
        });

        deleteRequestActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DeletePostActivity.class));
            }
        });

    }


}
