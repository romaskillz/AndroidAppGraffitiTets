package com.example.testexam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testexam.API.APIService;
import com.example.testexam.API.Model.Example;
import com.example.testexam.Fragments.InfoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GraffitiAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.bGetGraffiti) Button mButton;

    private List<Example> mGraffiti;
    private GraffitiAdapter graffitiAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        mGraffiti = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @OnClick({R.id.bGetGraffiti})
    public void onClick(View button) {
        switch (button.getId()) {
            case R.id.bGetGraffiti:
                getData();
                showProgress();
                break;
        }
    }

    private void getData() {
        APIService apiService = APIService.retrofit.create(APIService.class);
        final Call<List<Example>> call = apiService.getData();
        call.enqueue((new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                if (response.isSuccessful()) {
                    mGraffiti = response.body();
                    graffitiAdapter = new GraffitiAdapter(mGraffiti, MainActivity.this);
                    mRecyclerView.setAdapter(graffitiAdapter);
                    mRecyclerView.getAdapter().notifyDataSetChanged();
                    hideProgress();
                    Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Что-то пошло не так",
                        Toast.LENGTH_SHORT).show();
                hideProgress();
            }
        }));
    }

    private void showProgress() {
        progressDialog = ProgressDialog.show(this, "", getString(R.string.please_wait));
    }

    private void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onItemClick(Example item) {
        Intent i = new Intent(this, InfoActivity.class);

        i.putExtra("image", item.getPhotos().get(0).getImage());
        i.putExtra("name", item.getName());
        i.putExtra("address", item.getLocation().getAddress());
        i.putExtra("description", item.getDescription());
        i.putExtra("author", item.getArtists().get(0).getName());
        i.putExtra("lat", item.getLocation().getLat());
        i.putExtra("lng", item.getLocation().getLng());

        startActivity(i);
    }
}
