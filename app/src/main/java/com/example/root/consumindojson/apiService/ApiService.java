package com.example.root.consumindojson.apiService;

import com.example.root.consumindojson.domain.Post;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @GET("posts/{id}")
    Call<Post> obtersuario(@Path("id") int id);

    @GET("posts")
    Call<Post> listaPosts();

}