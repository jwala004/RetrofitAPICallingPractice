package com.jwala.retrofitgetmethodexamplemediumjava.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.jwala.retrofitgetmethodexamplemediumjava.APIEndPoints;
import com.jwala.retrofitgetmethodexamplemediumjava.R;
import com.jwala.retrofitgetmethodexamplemediumjava.RetrofitInstance;
import com.jwala.retrofitgetmethodexamplemediumjava.adapter.CustomAdapter;
import com.jwala.retrofitgetmethodexamplemediumjava.model.PhotoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GETRequestActivity extends AppCompatActivity {

    private CustomAdapter adapter;
    RecyclerView recycler;
    ProgressDialog progressDoalog;

    /*
    Retrofit is a type-safe HTTP client for Android and Java. It makes communicating with a web service easy, by abstracting
    the REST API into a Java interface. It uses annotation processing to autogenerate the underlying low level
    networking code for us.
    In this playlist you will learn how to add Retrofit to your Android Studio project and how to use it to make network requests
    in your app. We will explore the different HTTP methods (GET, POST, PUT, DELETE etc.), as well as other features, like
    Path and Query placeholders.
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_e_t_request);

        recycler = findViewById(R.id.recyclerViewId);


        progressDoalog = new ProgressDialog(GETRequestActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*
        enqueue() asynchronously sends the request and notifies your app with a callback when a response comes back.
        Since this request is asynchronous, Retrofit handles it on a background thread so that the main UI thread isn't blocked
        or interfered with.
        To use enqueue(), you have to implement two callback methods:
        onResponse()
        onFailure()
        */

        /*Create handle for the RetrofitInstance interface*/
        APIEndPoints service = RetrofitInstance.getRetrofitInstance().create(APIEndPoints.class);
        Call<List<PhotoModel>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(GETRequestActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<PhotoModel> photoList) {
//        recyclerView = findViewById(R.id.r);
        adapter = new CustomAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GETRequestActivity.this);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }
}
