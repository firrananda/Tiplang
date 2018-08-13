package dev.zero.tiplangpdam.activity.baru;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.model.Pelanggaran;
import dev.zero.tiplangpdam.model.local.FormData;
import dev.zero.tiplangpdam.model.response.PelanggaranResponse;
import dev.zero.tiplangpdam.model.response.RealisasiResponse;
import dev.zero.tiplangpdam.service.ApiService;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormRealisasiProsesActivity extends AppCompatActivity {

    FormData data;
    ArrayList<Pelanggaran> listPelanggaran;
    String pelanggaranId;
    @BindView(R.id.tv_batd)
    TextView tvBatd;
    @BindView(R.id.tv_tgl_ba)
    TextView tvTglBa;
    @BindView(R.id.cardbatd)
    CardView cardbatd;
    @BindView(R.id.edt_hasil_realisasi)
    EditText edtHasilRealisasi;
    @BindView(R.id.cardrealisasi)
    CardView cardrealisasi;
    @BindView(R.id.spn_pelanggaran)
    Spinner spnPelanggaran;
    @BindView(R.id.edt_kondisi)
    EditText edtKondisi;
    @BindView(R.id.edt_catatan)
    EditText edtCatatan;
    @BindView(R.id.cardcatatan)
    CardView cardcatatan;
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
    @BindView(R.id.iv_fotohasil1)
    ImageView ivFotohasil1;
    @BindView(R.id.iv_fotohasil2)
    ImageView ivFotohasil2;
    @BindView(R.id.iv_fotohasil3)
    ImageView ivFotohasil3;
    @BindView(R.id.iv_fotohasil4)
    ImageView ivFotohasil4;
    @BindView(R.id.cardtambahan)
    CardView cardtambahan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_realisasi_proses);
        ButterKnife.bind(this);
        data = getIntent().getParcelableExtra("data");
        ApiService.service_get.getPelanggaran().enqueue(new Callback<PelanggaranResponse>() {
            @Override
            public void onResponse(@NonNull Call<PelanggaranResponse> call, @NonNull Response<PelanggaranResponse> response) {
                if (response.body().getCode() == 302) {
                    listPelanggaran = response.body().getData();
                    ArrayList<String> pelanggaran = new ArrayList<>();
                    for (Pelanggaran listPelanggaran2 : listPelanggaran)
                        pelanggaran.add(listPelanggaran2.getKode_pelanggaran());
                    ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(FormRealisasiProsesActivity.this, android.R.layout.simple_list_item_1, pelanggaran);
                    spnPelanggaran.setAdapter(stringArrayAdapter);
                    spnPelanggaran.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            pelanggaranId = String.valueOf(listPelanggaran.get(i).getKeterangan());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            pelanggaranId = "";
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
        initLastData();
    }

    private void initLastData() {
        edtHasilRealisasi.setText(data.getHasil());
        spnPelanggaran.getSelectedItem().toString();
        edtKondisi.setText(data.getKondisi_stan_meter());
        edtCatatan.setText(data.getCatatan_stan_meter());
        edtTglAngkat.setText(data.getTanggal_angkat());
        edtNoMeter.setText(data.getNo_meter());
        edtUkuranMeter.setText(data.getUkuran_meter());
        edtAngkaAngkat.setText(data.getAngka_angkat());
        edtMerkMeteran.setText(data.getMerk_meter());
    }

    @OnClick(R.id.btn_kirim)
    public void onViewClicked() {
        HashMap<String, RequestBody> params = new HashMap<>();
        ArrayList<MultipartBody.Part> parts = new ArrayList<>();
        ApiService.service_post.postForm(params, parts).enqueue(new Callback<RealisasiResponse>() {
            @Override
            public void onResponse(Call<RealisasiResponse> call, Response<RealisasiResponse> response) {
                if (response.body().getCode() == 302) {
                    Toast.makeText(FormRealisasiProsesActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    FormDataSaveHelper.deleteDataPerBATD(data.getBatdId());
                    finish();
                } else {
                    Toast.makeText(FormRealisasiProsesActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RealisasiResponse> call, Throwable t) {
                Toast.makeText(FormRealisasiProsesActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.btn_foto1, R.id.btn_foto2, R.id.btn_foto3, R.id.btn_foto4, R.id.btn_kirim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_foto1:
                break;
            case R.id.btn_foto2:
                break;
            case R.id.btn_foto3:
                break;
            case R.id.btn_foto4:
                break;
            case R.id.btn_kirim:
                break;
        }
    }
}
