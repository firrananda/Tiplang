package dev.zero.tiplangpdam.activity.baru;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.adapter.PelangganAdapter;
import dev.zero.tiplangpdam.helper.FormDataSaveHelper;
import dev.zero.tiplangpdam.model.Pelanggan;
import dev.zero.tiplangpdam.model.local.FormData;
import dev.zero.tiplangpdam.model.response.PelangganResponse;
import dev.zero.tiplangpdam.service.ApiService;
import dev.zero.tiplangpdam.service.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarudataPelActivity extends AppCompatActivity {

    @BindView(R.id.rv_datapel)
    RecyclerView rv_datapel;
    PelangganAdapter adapter;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barudata_pel);
        ButterKnife.bind(this);
        String id_spk = getIntent().getStringExtra("SPK_ID");
        sessionManager = new SessionManager(this);

        ApiService.service_get.getPelanggan(id_spk).enqueue(new Callback<PelangganResponse>() {
            @Override
            public void onResponse(Call<PelangganResponse> call, Response<PelangganResponse> response) {
                if (response.body().getCode() == 302){
                    ArrayList<Pelanggan> listPelanggan2 = new ArrayList<>();
                    ArrayList<FormData> formData = FormDataSaveHelper.getData();
                    for (Pelanggan pelanggan: response.body().getData()) {
                        for (int i =0 ; i<=formData.size(); i++) {
                            if (i<formData.size()) {
                                if (String.valueOf(pelanggan.getNomor_pelanggan()).equals(formData.get(i).getNo_pelanggan())) {
                                    break;
                                }
                            }else{
                                listPelanggan2.add(pelanggan);
                            }

                        }
                    }

                    rv_datapel.setHasFixedSize(true);
                    rv_datapel.setLayoutManager(new LinearLayoutManager(BarudataPelActivity.this));
                    adapter = new PelangganAdapter(BarudataPelActivity.this, listPelanggan2);
                    rv_datapel.setAdapter(adapter);
                }
                else{
                    Toast.makeText(BarudataPelActivity.this , response.body().getMessage() , Toast.LENGTH_SHORT).show();
                    Log.d("Get Pelanggan" , "OnResponse" +response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<PelangganResponse> call, Throwable t) {
                Toast.makeText(BarudataPelActivity.this , "Gagal menghubung ke server " , Toast.LENGTH_SHORT).show();
                Log.e("get Pelanggan" , "OnFailure" +t.getMessage(), t);
                call.cancel();
            }
        });
    }
}
