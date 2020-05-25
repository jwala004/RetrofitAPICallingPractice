package com.jwala.retrofitgetmethodexamplemediumjava.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jwala.retrofitgetmethodexamplemediumjava.APIEndPoints;
import com.jwala.retrofitgetmethodexamplemediumjava.R;
import com.jwala.retrofitgetmethodexamplemediumjava.RetrofitInstance;
import com.jwala.retrofitgetmethodexamplemediumjava.model.PostModel;

import java.util.HashMap;
import java.util.Map;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRequestActivity extends AppCompatActivity {

    EditText etUserId, etTitle, etBody;
    TextView responseCode;
    Button buttonSubmit;
    PostModel postModel;
    ProgressDialog progressDialog;

/*
    In part 3 of the Retrofit tutorial, we will learn how to make HTTP POST requests on a REST API to create new resources.
    For this we use the @POST annotation in our interface, define the relative URL endpoint and pass the object that we want
    to send to the server as an argument that we annotate with @Body. The GSON converter will then serialize this object into
    the JSON format before it is uploaded to the server. If we want to serialize the object into
    another format (like XML or String), we can use a different ConverterFactory.
    The same as in a GET request, the POST method returns a Call object that we can enqueue to execute it asynchronously.
    A successful POST request will return a 201 (Created) response code together with a response body in the onResponse callback.
    Retrofit also supports form-urlencoded format (application/x-www-form-urlencoded). For this we have to annotate a
    POST method with @FormUrlEncoded and pass either single @Field arguments or a @FieldMap.

*/


/*

    PUT and PATCH are the HTTP methods that we use to update data on a server.
    While PATCH only changes the specific fields we send over, PUT completely replaces the existing entity with the one in the
    request body (or alternatively creates it).
    A DELETE request deletes an existing resource.
    PUT, PATCH and DELETE are usually applied on a single item rather than a collection as a whole.
    As usual, we declare these methods in our API Java interface and annotate them with their corresponding
    HTTP call: @PUT, @PATCH or @DELETE. With @Path placeholders we dynamically specify the URL endpoint on which we want to
    modify a resource. PUT and PATCH both send a @Body annotated request body, which the GSON converter serializes into the
    JSON format. By calling serializeNulls on a GsonBuilder and passing the Gson instance to the GsonConverterFactory's
    create method in addConverterFactory, we can tell GSON to not drop null values in the serialzation process. Alternatively
    we can send single fields @FormUrlEncoded.
    DELETE returns a Call of type Void, with which we specify that we don't want to read the response body. A successful delete
    operation is indicated by a 200 (OK) response code.
    As usual, we run our network requests on a background thread, by calling enqueue instead of execute, and handle the results
    in the onResponse and onFailure callbacks.

*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_request);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait Loading...");

        etUserId = findViewById(R.id.etUserId);
        etTitle = findViewById(R.id.etTitle);
        etBody = findViewById(R.id.etBody);

        responseCode = findViewById(R.id.responseCode);

        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                validateDetails(etUserId.getText().toString(), etTitle.getText().toString(), etBody.getText().toString());
            }
        });





    }
// this part is for, directly sending the post object.

    private void createPost() {

        postModel = new PostModel(Integer.valueOf(etUserId.getText().toString()), etTitle.getText().toString(), etBody.getText().toString());

        APIEndPoints apiEndPoints = RetrofitInstance.getRetrofitInstance().create(APIEndPoints.class);
        Call<PostModel> postModelCall = apiEndPoints.createPost(postModel); // commented it, it is for directly sending the postModelby putting the data in constructor at once

//       code of FormUrlEncoding starts here.
//        Call<PostModel> postModelCall = apiEndPoints.createPost(etId.getId(), etTitle.getText().toString(), etBody.getText().toString());
//       code of FormUrlEncoding ends here.

        postModelCall.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {

                progressDialog.dismiss();

                if (!response.isSuccessful()) {
                    Toast.makeText(PostRequestActivity.this, "Something went wrong", Toast.LENGTH_SHORT);    // response.message()
                    return;
                }

//   if you want to set the data for formUrlEncoding, then create the xml field for textViews, and set the data here, in onResponse


                postModel = response.body();
                Bundle sendBundleModel = new Bundle();
                sendBundleModel.putParcelable("customObject", postModel);

                Intent sendIntent = new Intent(PostRequestActivity.this, ReceivePostResponseActivity.class);
                sendIntent.putExtras(sendBundleModel);
                startActivity(sendIntent);
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PostRequestActivity.this, t.getMessage(), Toast.LENGTH_SHORT);    // response.message()
            }

        });


    }

// null data is going, when sending using HashMap on next screen.

//    private void createPost() {
//
//        HashMap<String, String> map = new HashMap<>();
//        map.put("userId", "Integer.valueOf(etId.getText().toString())");
//        map.put("title", "etTitle.getText().toString()");
//        map.put("body", "etBody.getText().toString()");
//
//        postModel = new PostModel(map);
//
//        APIEndPoints apiEndPoints = RetrofitInstance.getRetrofitInstance().create(APIEndPoints.class);
//        Call<PostModel> postModelCall = apiEndPoints.createPost(postModel); // commented it, it is for directly sending the postModelby putting the data in constructor at once
//
//        postModelCall.enqueue(new Callback<PostModel>() {
//            @Override
//            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
//
//                progressDialog.dismiss();
//
//                if (!response.isSuccessful()) {
//                    Toast.makeText(PostRequestActivity.this, "Something went wrong", Toast.LENGTH_SHORT);    // response.message()
//                    return;
//                }
//
////                responseCode.setText("Response Code : " + response.code() );
//
//                postModel = response.body();
//                Bundle sendBundleModel = new Bundle();
//                sendBundleModel.putParcelable("customObject", postModel);
//
//                Intent sendIntent = new Intent(PostRequestActivity.this, ReceivePostResponseActivity.class);
//                sendIntent.putExtras(sendBundleModel);
//                startActivity(sendIntent);
//            }
//
//            @Override
//            public void onFailure(Call<PostModel> call, Throwable t) {
//                progressDialog.dismiss();
//                Toast.makeText(PostRequestActivity.this, t.getMessage(), Toast.LENGTH_SHORT);    // response.message()
//            }
//
//        });
//
//
//    }

    private void validateDetails(String id, String title, String body) {

        if (TextUtils.isEmpty(id)) { //  ,etId.getText().toString().isEmpty()
            etUserId.setError("Please enter Id");
            progressDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(title)) {
            etTitle.setError("Please enter title");
            progressDialog.dismiss();
            return;
        }
        if (TextUtils.isEmpty(body)) {
            etBody.setError("Please enter body");
            progressDialog.dismiss();
            return;
        }

        if (!TextUtils.isEmpty(etUserId.getText()) && !TextUtils.isEmpty(etTitle.getText()) && !TextUtils.isEmpty(etBody.getText())) { // && !TextUtils.isEmpty(etTitle.getText())
//            createPost();
            updatePost();
        }

    }

    private void updatePost(){

        postModel = new PostModel(Integer.valueOf(etUserId.getText().toString()), etTitle.getText().toString(), etBody.getText().toString());

        APIEndPoints apiEndPoints = RetrofitInstance.getRetrofitInstance().create(APIEndPoints.class);



//        Put request ( for PUT just uncomment the below line )
        Call<PostModel> postModelCall = apiEndPoints.putPost("abc",5,postModel);

//        Patch request
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Map-Header1", "def");
//        headers.put("Map-Header2", "ghi");
//        Call<PostModel> postModelCall = apiEndPoints.patchPost(headers,5,postModel);

        postModelCall.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {

                progressDialog.dismiss();

                if (!response.isSuccessful()) {
                    Toast.makeText(PostRequestActivity.this, "Something went wrong", Toast.LENGTH_SHORT);    // response.message()
                    return;
                }

                postModel = response.body();
                Bundle sendBundleModel = new Bundle();
                sendBundleModel.putParcelable("customObject", postModel);

                Intent sendIntent = new Intent(PostRequestActivity.this, ReceivePostResponseActivity.class);
                sendIntent.putExtras(sendBundleModel);
                startActivity(sendIntent);
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PostRequestActivity.this, t.getMessage(), Toast.LENGTH_SHORT);
            }

        });

    }




}
