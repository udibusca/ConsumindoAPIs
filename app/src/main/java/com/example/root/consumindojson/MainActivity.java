package com.example.root.consumindojson;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.root.consumindojson.domain.Post;
import com.example.root.consumindojson.apiService.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    @BindView(R.id.txtId)
    EditText txtId;

    @BindView(R.id.txtPost)
    EditText txtPost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnBuscar)
    public void btnBuscarOnClick(){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        int id = Integer.parseInt(txtId.getText().toString());
        //vai na API com esse id e traz o post

        Call<Post> requestPostPorId = apiService.obtersuario(id);

        requestPostPorId.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Nenhum Post Encontrado!",Toast.LENGTH_SHORT).show();
                    Log.i("TAG","Erro : " + response.code());
                }else{
                    Post posts =  response.body();
                    txtPost.setText(posts.getTitle());
                    Log.i("RETORNO","Titulo : " + posts.getTitle());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                // Log de erro do request
                Log.i("TAG","Erro : " +t.getMessage());
            }
        });
    }

}
