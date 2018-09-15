package dev.zero.tiplangpdam.activity.revisi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.adapter.RevKirimPelangganAdapter;
import dev.zero.tiplangpdam.model.PelangganRevKirim;
import dev.zero.tiplangpdam.model.response.PelangganRevKirimResponse;
import dev.zero.tiplangpdam.model.response.ViewRealRevKirimResponse;
import dev.zero.tiplangpdam.service.ApiService;
import dev.zero.tiplangpdam.service.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RevKirimActivity extends AppCompatActivity {

    @BindView(R.id.rv_kirim)
    RecyclerView rvKirim;
    SessionManager sessionManager;
    RevKirimPelangganAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revisi_activity_kirim);
        ButterKnife.bind(this);

        rvKirim.setLayoutManager(new LinearLayoutManager(this));
        rvKirim.setHasFixedSize(true);
        sessionManager = new SessionManager(this);

        Log.d("test", "onCreate: " + sessionManager.getKeyId());
        ApiService.service_get.getPelangganRevKirim(sessionManager.getKeyId()).enqueue(new Callback<PelangganRevKirimResponse>() {
            @Override
            public void onResponse(Call<PelangganRevKirimResponse> call, Response<PelangganRevKirimResponse> response) {
                if (response.code() == 200 ){
                    if (response.body().getCode() == 302){
                        ArrayList<PelangganRevKirim> listPelangganRevKirim = response.body().getList();
                        Log.d("Get List", "onResponse :" + listPelangganRevKirim.size());
                        adapter = new RevKirimPelangganAdapter(listPelangganRevKirim, RevKirimActivity.this);
                        rvKirim.setAdapter(adapter);
                    }
                    else {
                        Toast.makeText(RevKirimActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("Get List" , "onResponse :" +response.body().getMessage());
                    }
                }
                else {
                    Log.d("Get List", "onResponse2 :" +response.message());
                }
            }

            @Override
            public void onFailure(Call<PelangganRevKirimResponse> call, Throwable t) {
                Toast.makeText(RevKirimActivity.this, "Gagal Menghubung Ke Server" , Toast.LENGTH_SHORT).show();
                Log.e("Get List", "OnFailure : "+t.getMessage(),t);
                call.cancel();
            }
        });
    }
}
