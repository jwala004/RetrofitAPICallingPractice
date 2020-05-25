package com.jwala.retrofitgetmethodexamplemediumjava.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jwala.retrofitgetmethodexamplemediumjava.APIEndPoints;
import com.jwala.retrofitgetmethodexamplemediumjava.R;
import com.jwala.retrofitgetmethodexamplemediumjava.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeletePostActivity extends AppCompatActivity {

    EditText etUserId;
    Button deletePostButton;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_post);

        etUserId = findViewById(R.id.etUserId);
        deletePostButton = findViewById(R.id.deletePostButton);


        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait Loading...");

        deletePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDetailsForDeleteAPI(etUserId.getText().toString());
            }
        });



    }

    private void deletePost(){

        APIEndPoints apiEndPoints = RetrofitInstance.getRetrofitInstance().create(APIEndPoints.class);

        Call<Void> deletePostCall = apiEndPoints.deletePost(Integer.valueOf(etUserId.getText().toString()));

        deletePostCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                progressDialog.dismiss();

//                if (!response.isSuccessful()) {
//                    Toast.makeText(DeletePostActivity.this, "Something went wrong", Toast.LENGTH_SHORT);    // response.message()
//                    return;
//                }

//                if( response.isSuccessful()){

                    int responseCode = response.code();

//                    if(! TextUtils.isEmpty(""+responseCode)){

                        Intent deletePostIntent = new Intent(DeletePostActivity.this, DeleteSuccessfulActivity.class);

                        deletePostIntent.putExtra("deletingPostId",responseCode);
                        startActivity(deletePostIntent);

//                    }

//                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DeletePostActivity.this, t.getMessage(), Toast.LENGTH_SHORT);
            }

        });

    }

    private void validateDetailsForDeleteAPI(String id) {

        if (TextUtils.isEmpty(id)) {
            etUserId.setError("Please enter Id");
            progressDialog.dismiss();
            return;
        }

        if ( !TextUtils.isEmpty(etUserId.getText()) ){
            deletePost();
        }

    }

}
