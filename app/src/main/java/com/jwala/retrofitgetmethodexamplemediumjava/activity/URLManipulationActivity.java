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
import com.jwala.retrofitgetmethodexamplemediumjava.adapter.CommentsAdapter;
import com.jwala.retrofitgetmethodexamplemediumjava.model.CommentModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class URLManipulationActivity extends AppCompatActivity {

    RecyclerView recyclerViewIdURLManipulation;
    CommentsAdapter commentsAdapter;
    ProgressDialog progressDialog;

    /*
     Retrofit, a type-safe HTTP client for Android and Java.
     Retrofit allows easy communication with a web service by abstracting the HTTP API into a Java interface.

     In part 1 we will set up Retrofit in a new Android Studio project by adding the necessary Gradle dependencies, and then
     already do our first GET request.
     We create an interface with 1 method that we annotate with @GET and the relative URL to the API endpoint of the REST API we
     want to query. We then create a Retrofit instance, define the baseUrl, add GSON as the converter
     by passing GsonConverterFactory to the addConverterFactory method, and let Retrofit create the implementation of our
     API interface at compile time.
     The Call object that our GET method returns encapsulates a single request + response. With enqueue we can execute this request
     asynchcronously on a background thread and get our result back in the onResponse callback. OnFailure will be called if
     something in the process of communicating with the server or processing the response went wrong. With isSuccessFull we check
     if our response code is between 200 and 300 and if that's the case we can retrieve our result from the response body.
     GSON will parse the array of JSON objects into a List of the Java model class that we prepared beforehand.

    */

  /*
    In part 2 of the Retrofit tutorial, we will learn how to use replacement blocks and query parameters to access more dynamic URL
    endpoints of a REST API.
    Instead of hardcoding the relative URL into our GET requests, we can use curly braces to create placeholders, which we can then
    replace at runtime with arguments that we pass to the method and annotated with @Path.
    With the @Query annotation, we can also pass arguments that Retrofit will append to the URL as query strings with the
    correct syntax (question mark, ampersand signs, equal signs...). We can declare and pass single values, we can pass multiple
    values for the same parameter in form of a List, array or varargs, or we can define a @QueryMap where we can pass any combination
     of parameters in form of a Map.
    By passing null we can omit a parameter, and to make a primitive int nullable we wrap it into an Integer object.
    We will also learn about the @URL annotation, with which we can pass the (endpoint) URL directly as an argument instead of
    just certain parameters, and we will learn how URLs resolve and why the baseUrl needs a trailing slash in order to work.

  */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_r_l_manipulation);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait while loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        APIEndPoints service = RetrofitInstance.getRetrofitInstance().create(APIEndPoints.class);
        Call<List<CommentModel>> call = service.getAllComments(2);

        call.enqueue(new Callback<List<CommentModel>>() {
            @Override
            public void onResponse(Call<List<CommentModel>> call, Response<List<CommentModel>> response) {
                progressDialog.dismiss();
                setCommentsAdapter(response.body());
            }

            @Override
            public void onFailure(Call<List<CommentModel>> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(URLManipulationActivity.this, "Something went wrong...Please try later!"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


        recyclerViewIdURLManipulation = findViewById(R.id.recyclerViewIdURLManipulation);


    }

    private void setCommentsAdapter(List<CommentModel> commentModel){
        commentsAdapter = new CommentsAdapter(this,commentModel);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewIdURLManipulation.setLayoutManager(linearLayoutManager);
        recyclerViewIdURLManipulation.setAdapter(commentsAdapter);
    }
}
