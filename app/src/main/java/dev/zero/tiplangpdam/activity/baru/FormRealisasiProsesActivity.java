package dev.zero.tiplangpdam.activity.baru;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.helper.FormDataSaveHelper;
import dev.zero.tiplangpdam.model.Pelanggaran;
import dev.zero.tiplangpdam.model.local.FormData;
import dev.zero.tiplangpdam.model.response.PelanggaranResponse;
import dev.zero.tiplangpdam.model.response.RealisasiResponse;
import dev.zero.tiplangpdam.service.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormRealisasiProsesActivity extends AppCompatActivity {

    @BindView(R.id.edt_tgl_ba)
    EditText edtTglBa;
    @BindView(R.id.edt_no_pelanggan)
    EditText edtNoPelanggan;
    @BindView(R.id.edt_nama_pelanggan)
    EditText edtNamaPelanggan;
    @BindView(R.id.edt_alamat)
    EditText edtAlamat;
    @BindView(R.id.edt_tgl_realisasi)
    EditText edtTglRealisasi;
    @BindView(R.id.edt_hasil_realisasi)
    EditText edtHasilRealisasi;
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
    FormData data;
    ArrayList<Pelanggaran> listPelanggaran;
    String pelanggaranId;

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
                            pelanggaranId = String.valueOf(listPelanggaran.get(i).getId());
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
        edtTglBa.setText(data.getTglba());
        edtNoPelanggan.setText(data.getNoPelanggan());
        edtNamaPelanggan.setText(data.getNamaPelanggan());
        edtAlamat.setText(data.getAlamat());
        edtTglRealisasi.setText(data.getTglRealisasi());
        edtHasilRealisasi.setText(data.getHasilRealisasi());
//        spnPelanggaran.getSelectedItem().toString(),
        edtKeterangan.setText(data.getKeterangan());
        edtKondisi.setText(data.getKondisi());
        edtCatatan.setText(data.getCatatan());
        edtTglAngkat.setText(data.getTglba());
        edtNoMeter.setText(data.getNoMeter());
        edtUkuranMeter.setText(data.getUkuranMeter());
        edtAngkaAngkat.setText(data.getAngkaAngkat());
        edtMerkMeteran.setText(data.getMerkMeteran());
    }

    @OnClick(R.id.btn_kirim)
    public void onViewClicked() {
        ApiService.service_post.postForm(edtKondisi.getText().toString()
                ,edtCatatan.getText().toString()
                ,edtTglAngkat.getText().toString()
                ,edtNoMeter.getText().toString()
                ,edtUkuranMeter.getText().toString()
                ,edtAngkaAngkat.getText().toString()
                ,edtMerkMeteran.getText().toString()
                ,data.getBatdId()
                ,pelanggaranId
                ,edtHasilRealisasi.getText().toString()
                ,edtTglRealisasi.getText().toString()).enqueue(new Callback<RealisasiResponse>() {
            @Override
            public void onResponse(Call<RealisasiResponse> call ,retrofit2.Response<RealisasiResponse> response) {
                if(response.body().getCode() == 302){
                    Toast.makeText(FormRealisasiProsesActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    FormDataSaveHelper.deleteDataPerBATD(data.getBatdId());
                    finish();
                }else{
                    Toast.makeText(FormRealisasiProsesActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RealisasiResponse> call, Throwable t) {
                Toast.makeText(FormRealisasiProsesActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
