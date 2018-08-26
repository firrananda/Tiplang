package dev.zero.tiplangpdam.activity.baru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.adapter.KirimPelangganAdapter;
import dev.zero.tiplangpdam.adapter.KirimSPKAdapter;
import dev.zero.tiplangpdam.model.List_Realisasi;
import dev.zero.tiplangpdam.model.local.FormData;
import dev.zero.tiplangpdam.model.response.List_RealisasiResponse;
import dev.zero.tiplangpdam.service.ApiService;
import dev.zero.tiplangpdam.service.SessionManager;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KirimActivity extends AppCompatActivity {

    @BindView(R.id.rv_spkkirim)
    RecyclerView rvSPKKirim;
//    Realm realm;
//    KirimSPKAdapter adapter;
    KirimPelangganAdapter adapter;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baru_activity_kirim);
        ButterKnife.bind(this);
//        rvSPKKirim.setLayoutManager(new LinearLayoutManager(this));
//        rvSPKKirim.setHasFixedSize(true);
//        realm = Realm.getDefaultInstance();
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                RealmResults<FormData> data = realm.where(FormData.class).findAll();
//                ArrayList<FormData> listSPK = new ArrayList<>();
//                for (int i = 0 ; i < data.size() ; i++){
//                    if ( i == 0){
//                        listSPK.add(data.get(i));
//                    }else{
//                        for (int j = 0 ; j< listSPK.size() ; j++){
//                            if (!data.get(i).getSPK().equals(listSPK.get(j).getSPK())){
//                                listSPK.add(data.get(i));
//                            }
//                        }
//                    }
//                }
//                adapter = new KirimSPKAdapter(KirimActivity.this, listSPK);
//                rvSPKKirim.setAdapter(adapter);
//            }
//        });

        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);

        ApiService.service_get.getListRealisasi(sessionManager.getKeyId()).enqueue(new Callback<List_RealisasiResponse>() {
            @Override
            public void onResponse(Call<List_RealisasiResponse> call, Response<List_RealisasiResponse> response) {
                if (response.body().getCode() == 302 ){
                    ArrayList <List_Realisasi> listpelanggan = response.body().getList();
                    rvSPKKirim.setHasFixedSize(true);
                    rvSPKKirim.setLayoutManager(new LinearLayoutManager(KirimActivity.this));
                    adapter = new KirimPelangganAdapter(listpelanggan, KirimActivity.this);
                    rvSPKKirim.setAdapter(adapter);
                }
                else {
                    Toast.makeText(KirimActivity.this, response.body().getMessaage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List_RealisasiResponse> call, Throwable t) {
                Toast.makeText(KirimActivity.this, "Gagal menghubung ke server", Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });
    }
}
