package dev.zero.tiplangpdam.activity.baru;

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
import dev.zero.tiplangpdam.adapter.SPKAdapter;
import dev.zero.tiplangpdam.model.SPK;
import dev.zero.tiplangpdam.model.response.SPKResponse;
import dev.zero.tiplangpdam.service.ApiService;
import dev.zero.tiplangpdam.service.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TerimaActivity extends AppCompatActivity {

    @BindView(R.id.rv_spk)
    RecyclerView rvSpk;
    SPKAdapter adapter;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baru_activity_terima);
        ButterKnife.bind(this);
        rvSpk.setLayoutManager(new LinearLayoutManager(this));
        rvSpk.setHasFixedSize(true);
        sessionManager = new SessionManager(this);
        ApiService.service_get.getSPK(sessionManager.getKeyId()).enqueue(new Callback<SPKResponse>() {
            @Override
            public void onResponse(Call<SPKResponse> call, Response<SPKResponse> response) {
                if (response.body().getCode() == 302) {
                    ArrayList<SPK> listSPK = response.body().getDataSPK();
                    adapter = new SPKAdapter(TerimaActivity.this, listSPK);
                    rvSpk.setAdapter(adapter);
                }
                else {
                    Toast.makeText(TerimaActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("Get SPK", "onResponse: " + response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<SPKResponse> call, Throwable t) {
                Toast.makeText(TerimaActivity.this, "Gagal menghubung ke server", Toast.LENGTH_SHORT).show();
                Log.e("Get SPK", "onFailure: " + t.getMessage(),t);
                call.cancel();
            }
        });
    }
}
