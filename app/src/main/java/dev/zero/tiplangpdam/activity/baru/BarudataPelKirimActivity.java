package dev.zero.tiplangpdam.activity.baru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.adapter.KirimPelangganAdapter;
import dev.zero.tiplangpdam.model.List_Realisasi;
import dev.zero.tiplangpdam.model.response.List_RealisasiResponse;
import dev.zero.tiplangpdam.service.ApiService;
import dev.zero.tiplangpdam.service.SessionManager;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarudataPelKirimActivity extends AppCompatActivity {

    @BindView(R.id.rv_datapel)
    RecyclerView rvDatapel;
    KirimPelangganAdapter adapter;
    Realm realm;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barudata_pel_kirim);

        ButterKnife.bind(this);
        String spk_id = getIntent().getStringExtra("SPK_ID");
        sessionManager = new SessionManager(this);

        ApiService.service_get.getListRealisasi(spk_id).enqueue(new Callback<List_RealisasiResponse>() {
            @Override
            public void onResponse(Call<List_RealisasiResponse> call, Response<List_RealisasiResponse> response) {
                if (response.body().getCode() == 302 ){
                    ArrayList <List_Realisasi> listpelanggan = response.body().getList();
                    rvDatapel.setHasFixedSize(true);
                    rvDatapel.setLayoutManager(new LinearLayoutManager(BarudataPelKirimActivity.this));
                    //adapter = new KirimPelangganAdapter(BarudataPelKirimActivity.this, listpelanggan);
                    rvDatapel.setAdapter(adapter);
                }
                else {
                    Toast.makeText(BarudataPelKirimActivity.this, response.body().getMessaage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List_RealisasiResponse> call, Throwable t) {
                Toast.makeText(BarudataPelKirimActivity.this, "Gagal menghubung ke server", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }
}
