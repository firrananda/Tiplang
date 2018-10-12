package dev.zero.tiplangpdam.activity.revisi;

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
import dev.zero.tiplangpdam.adapter.RevPelangganAdapter;
import dev.zero.tiplangpdam.helper.FormDataRevSaveHelper;
import dev.zero.tiplangpdam.model.PelangganRev;
import dev.zero.tiplangpdam.model.local.FormDataRev;
import dev.zero.tiplangpdam.model.response.PelangganRevResponse;
import dev.zero.tiplangpdam.service.ApiService;
import dev.zero.tiplangpdam.service.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RevTerimaActivity extends AppCompatActivity {

    @BindView(R.id.rv_terima)
    RecyclerView rv_terima;
    RevPelangganAdapter adapter;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.revisi_activity_terima);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(this);

        ApiService.service_get.getPelangganRev(sessionManager.getKeyId()).enqueue(new Callback<PelangganRevResponse>() {
            @Override
            public void onResponse(Call<PelangganRevResponse> call, Response<PelangganRevResponse> response) {
                if (response.code()== 200){
                    if (response.body().getCode()== 302){
                        ArrayList<PelangganRev> listpelangganrev = new ArrayList<>();
                        Log.d("Get List", "onResponse :" + listpelangganrev.size());

                        ArrayList<FormDataRev> formDataRev = FormDataRevSaveHelper.getData();
                        for (PelangganRev pelanggan: response.body().getList()) {
                            for (int i =0 ; i<=formDataRev.size(); i++) {
                                if (i<formDataRev.size()) {
                                    if (String.valueOf(pelanggan.getNomor_pelanggan()).equals(formDataRev.get(i).getNo_pelanggan())) {
                                        break;
                                    }
                                }else{
                                    listpelangganrev.add(pelanggan);
                                }

                            }
                        }


                        rv_terima.setLayoutManager(new LinearLayoutManager(RevTerimaActivity.this));
                        rv_terima.setHasFixedSize(true);
                        adapter = new RevPelangganAdapter(listpelangganrev, RevTerimaActivity.this);
                        rv_terima.setAdapter(adapter);
                    } else {
                        Toast.makeText(RevTerimaActivity.this, response.body().getMessaage(), Toast.LENGTH_SHORT).show();
                        Log.d("Get List" , "onResponse :" +response.body().getMessaage());
                    }
                }else{
                    Log.d("Get List", "onResponse :" +response.message());
                }
            }

            @Override
            public void onFailure(Call<PelangganRevResponse> call, Throwable t) {
                Toast.makeText(RevTerimaActivity.this, "Gagal Menghubung Ke Server" , Toast.LENGTH_SHORT).show();
                Log.e("Get List", "OnResponse : "+t.getMessage(),t);
                call.cancel();
            }
        });
    }
}