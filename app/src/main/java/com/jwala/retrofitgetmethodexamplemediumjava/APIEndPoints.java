package com.jwala.retrofitgetmethodexamplemediumjava;

import com.jwala.retrofitgetmethodexamplemediumjava.model.CommentModel;
import com.jwala.retrofitgetmethodexamplemediumjava.model.PhotoModel;
import com.jwala.retrofitgetmethodexamplemediumjava.model.PostModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIEndPoints {

    @GET("/photos")
    Call<List<PhotoModel>> getAllPhotos();

//    @GET("/posts/1/comments")
//    Call<List<CommentModel>> getAllComments();

    @GET("/posts/{Id}/comments")
    Call<List<CommentModel>> getAllComments(@Path("Id") int postId);

    @POST("/posts")
    Call<PostModel> createPost(@Body PostModel postModel);

    @FormUrlEncoded
    @POST("/posts")
    Call<PostModel> createPost(  // did not complete because need to redesign the layout
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String body
            );

    @FormUrlEncoded
    @POST("/posts")
    Call<PostModel> createPost( @FieldMap Map<String, String> map );

    @Headers({"Static-Header1: 123", "Static-Header2: 456"}) // static header passing the header value here itself
    @PUT("/posts/{id}")
    Call<PostModel> putPost(@Header("Dynamic-Header") String header, @Path("id") int id, @Body PostModel postModel ); // dynamic header value is passed here

    @PATCH("/posts/{id}")
    Call<PostModel> patchPost( @HeaderMap Map<String, String> headers, @Path("id") int id, @Body PostModel postModel );

    @DELETE("/posts/{id}")
    Call<Void> deletePost( @Path("id") int id );



}

