package com.example.retrofit_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    EditText input;
    Button button;
    RepoAdapter adapter;
    List<Repo>repoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        input = findViewById(R.id.userInput);
        button = findViewById(R.id.btn_request);
        repoList = new ArrayList<>();




        adapter = new RepoAdapter(MainActivity.this,repoList);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        button.setOnClickListener(view -> {


            String userInput = input.getText().toString();
            String baseUrl = "https://api.github.com/users/" + userInput + "/";


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            Log.i("tag", baseUrl);


            API api = retrofit.create(API.class);
            Call<List<Repo>> request = api.getRepos();
            request.enqueue(new Callback<List<Repo>>() {
                @Override
                public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                    if (response.isSuccessful()) {
                        repoList.clear();
                       repoList.addAll(response.body());
                        Log.i("Tag", repoList.get(0).getName());
                        adapter.notifyDataSetChanged();

                    }
                }

                @Override
                public void onFailure(Call<List<Repo>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        });
    }
}
