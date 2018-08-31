package dev.zero.tiplangpdam.activity.baru;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.model.List_Realisasi;
import dev.zero.tiplangpdam.model.response.View_RealisasiResponse;
import dev.zero.tiplangpdam.service.ApiService;
import dev.zero.tiplangpdam.service.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormRealisasiKirimActivity extends AppCompatActivity {

    @BindView(R.id.tv_batd)
    TextView tvBatd;
    @BindView(R.id.tv_tgl_ba)
    TextView tvTglBa;
    @BindView(R.id.tv_hasil_realisasi)
    TextView tvHasilRealisasi;
    @BindView(R.id.tv_pelanggaran)
    TextView tvPelanggaran;
    @BindView(R.id.tv_kondisi)
    TextView tvKondisi;
    @BindView(R.id.tv_catatan)
    TextView tvCatatan;
    @BindView(R.id.tv_tgl_angkat)
    TextView tvTglAngkat;
    @BindView(R.id.tv_no_meter)
    TextView tvNoMeter;
    @BindView(R.id.tv_ukuran_meter)
    TextView tvUkuranMeter;
    @BindView(R.id.tv_angka_angkat)
    TextView tvAngkaAngkat;
    @BindView(R.id.tv_merk_meteran)
    TextView tvMerkMeteran;
    @BindView(R.id.iv_fotohasil1)
    ImageView ivFotohasil1;
    @BindView(R.id.iv_fotohasil2)
    ImageView ivFotohasil2;
    @BindView(R.id.iv_fotohasil3)
    ImageView ivFotohasil3;
    @BindView(R.id.iv_fotohasil4)
    ImageView ivFotohasil4;

    SessionManager sessionManager;
    List_Realisasi datapelkirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_realisasi_kirim);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        String id_form = getIntent().getStringExtra("FORM_ID");
        datapelkirim = getIntent().getParcelableExtra("datapel");
        tvBatd.setText(datapelkirim.getNomor_batd());
        tvTglBa.setText(datapelkirim.getTanggal_batd());

        //int idForm = getIntent().getIntExtra("id_form",22);
        ApiService.service_get.getViewRealisasi(String.valueOf(id_form)).enqueue(new Callback<View_RealisasiResponse>() {
            @Override
            public void onResponse(Call<View_RealisasiResponse> call, Response<View_RealisasiResponse> response) {
                Log.d("get List" , "onresponse :" +response.body().getMessage());
                if (response.body().getCode() == 302){
                    Toast.makeText(FormRealisasiKirimActivity.this, "berhasil", Toast.LENGTH_SHORT).show();
                        tvHasilRealisasi.setText(response.body().getData().getHasil());
 //                       tvPelanggaran.setText(response.body().getData().getPelanggaran_id());
                        tvKondisi.setText(response.body().getData().getKondisi_stan_meter());
                        tvCatatan.setText(response.body().getData().getCatatan_stan_meter());
                        tvTglAngkat.setText(response.body().getData().getTangal_angkat());
                        tvUkuranMeter.setText(response.body().getData().getUkuran_meter());
                        tvAngkaAngkat.setText(response.body().getData().getAngka_angkat());
                        tvMerkMeteran.setText(response.body().getData().getMerk_meter());
                }
                else {
                    Toast.makeText(FormRealisasiKirimActivity.this, "error nih", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<View_RealisasiResponse> call, Throwable t) {
                Log.e("Form Realisasi", "onFailure: " + t.getMessage(), t);
            }
        });
    }
}
