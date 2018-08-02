package dev.zero.tiplangpdam.activity.baru;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.helper.FormDataSaveHelper;
import dev.zero.tiplangpdam.model.BATD;
import dev.zero.tiplangpdam.model.Pelanggaran;
import dev.zero.tiplangpdam.model.local.FormData;
import dev.zero.tiplangpdam.model.response.BATDResponse;
import dev.zero.tiplangpdam.model.response.PelanggaranResponse;
import dev.zero.tiplangpdam.model.response.RealisasiResponse;
import dev.zero.tiplangpdam.service.ApiService;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormRealisasiActivity extends AppCompatActivity {

    @BindView(R.id.tv_batd)
    TextView tvbatd;
    @BindView(R.id.edt_tgl_ba)
    EditText edtTglBa;
//    @BindView(R.id.edt_tgl_realisasi)
//    EditText edtTglRealisasi;
    @BindView(R.id.edt_hasil_realisasi)
    EditText edtHasilRealisasi;
    @BindView(R.id.kodepelanggaran)
    TextView kodepelanggaran;
    @BindView(R.id.spn_pelanggaran)
    Spinner spnPelanggaran;
    @BindView(R.id.edt_keterangan)
    EditText edtKeterangan;
    @BindView(R.id.edt_kondisi)
    EditText edtKondisi;
    @BindView(R.id.edt_catatan)
    EditText edtCatatan;
    @BindView(R.id.edt_tgl_angkat)
    EditText edtTglAngkat;
    @BindView(R.id.edt_no_meter)
    EditText edtNoMeter;
    @BindView(R.id.edt_ukuran_meter)
    EditText edtUkuranMeter;
    @BindView(R.id.edt_angka_angkat)
    EditText edtAngkaAngkat;
    @BindView(R.id.edt_merk_meteran)
    EditText edtMerkMeteran;
    String spk;
    ArrayList<Pelanggaran> listPelanggaran;
    ArrayList<BATD> listBATD;
    String pelanggaranId = "";
    //String BATDId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_realisasi);
        ButterKnife.bind(this);
        String id = getIntent().getStringExtra("ID");
        spk = getIntent().getStringExtra("SPK");
        ApiService.service_get.getBATD(id).enqueue(new Callback<BATDResponse>() {
            @Override
            public void onResponse(@NonNull Call<BATDResponse> call, @NonNull Response<BATDResponse> response) {
                if (response.body().getCode() == 302) {
                    listBATD = response.body().getData();
                    //ArrayList<String> batd = new ArrayList<>();
                    Realm realm = Realm.getDefaultInstance();
                    List<FormData> data = realm.where(FormData.class).findAll();
                    for (BATD batdModel1 : listBATD) {
                        boolean proses = false;
                        for (FormData batd1 : data) {
                            if (batd1.getBatd().equals(batdModel1.getNomor_batd())){
                                proses = true;
                                break;
                            }
                        }
                        if (!proses) batd.add(batdModel1.getNomor_batd());
                    }
                    ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(FormRealisasiActivity.this, android.R.layout.simple_list_item_1, batd);
                    tvbatd.setAdapter(stringArrayAdapter);
                    tvbatd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            BATDId = String.valueOf(listBATD.get(i).getId());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                } else {
                    Log.d("Get BATD", "onResponse: " + response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<BATDResponse> call, Throwable t) {
                Log.d("Get BATD", "onFailure: " + t.getMessage());
            }
        });

        ApiService.service_get.getPelanggaran().enqueue(new Callback<PelanggaranResponse>() {
            @Override
            public void onResponse(@NonNull Call<PelanggaranResponse> call, @NonNull Response<PelanggaranResponse> response) {
                if (response.body().getCode() == 302) {
                    listPelanggaran = response.body().getData();
                    ArrayList<String> pelanggaran = new ArrayList<>();
                    for (Pelanggaran listPelanggaran2 : listPelanggaran)
                        pelanggaran.add(listPelanggaran2.getKeterangan());
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(FormRealisasiActivity.this, android.R.layout.simple_list_item_1, pelanggaran);
                    spnPelanggaran.setAdapter(adapter);
                    spnPelanggaran.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            pelanggaranId = String.valueOf(listPelanggaran.get(i).getId());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                    Log.d("Get Pelanggaran", "onResponse: " + response.body().getMessage());
                } else {
                    Log.d("Get Pelanggaran", "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<PelanggaranResponse> call, Throwable t) {
                Log.d("Get Pelanggaran", "onFailure: " + t.getMessage());
            }
        });
    }

    @OnClick({R.id.btn_proses, R.id.btn_kirim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_proses:
                FormDataSaveHelper.addDataString(
                        BATDId,
                        spk,
                        tvbatd.getText().toString(),
                        edtTglBa.getText().toString(),
                        //edtTglRealisasi.getText().toString(),
                        edtHasilRealisasi.getText().toString(),
                        spnPelanggaran.getSelectedItem().toString(),
                        edtKeterangan.getText().toString(),
                        edtKondisi.getText().toString(),
                        edtCatatan.getText().toString(),
                        edtTglAngkat.getText().toString(),
                        edtNoMeter.getText().toString(),
                        edtUkuranMeter.getText().toString(),
                        edtAngkaAngkat.getText().toString(),
                        edtMerkMeteran.getText().toString()
                );
                finish();
                break;
            case R.id.btn_kirim:
                ApiService.service_post.postForm(edtKondisi.getText().toString()
                        ,edtCatatan.getText().toString()
                        ,edtTglAngkat.getText().toString()
                        ,edtNoMeter.getText().toString()
                        ,edtUkuranMeter.getText().toString()
                        ,edtAngkaAngkat.getText().toString()
                        ,edtMerkMeteran.getText().toString()
                        ,BATDId
                        ,pelanggaranId
                        ,edtHasilRealisasi.getText().toString().enqueue(new Callback<RealisasiResponse>() {
                    @Override
                    public void onResponse(Call<RealisasiResponse> call ,retrofit2.Response<RealisasiResponse> response) {
                        if(response.body().getCode() == 302){
                            Toast.makeText(FormRealisasiActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(FormRealisasiActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RealisasiResponse> call, Throwable t) {
                        Toast.makeText(FormRealisasiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}
