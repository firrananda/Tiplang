package dev.zero.tiplangpdam.activity.revisi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.activity.baru.FormRealisasiKirimActivity;
import dev.zero.tiplangpdam.model.PelangganRevKirim;
import dev.zero.tiplangpdam.model.response.ViewRealRevKirimResponse;
import dev.zero.tiplangpdam.service.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormRealKirimRevActivity extends AppCompatActivity {

    PelangganRevKirim datapelkirim;
    @BindView(R.id.tv_batd)
    TextView tvBatd;
    @BindView(R.id.tv_tgl_ba)
    TextView tvTglBa;
    @BindView(R.id.tv_ket_realisasi)
    TextView tvKetRealisasi;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_real_kirim_rev);
        ButterKnife.bind(this);

        String id_form = getIntent().getStringExtra("FORM_ID");
        datapelkirim = getIntent().getParcelableExtra("datapel");
        tvBatd.setText(datapelkirim.getNomor_batd());
        tvTglBa.setText(datapelkirim.getTanggal_batd());

        ApiService.service_get.getViewRealRrevKirim(String.valueOf(id_form)).enqueue(new Callback<ViewRealRevKirimResponse>() {
            @Override
            public void onResponse(Call<ViewRealRevKirimResponse> call, Response<ViewRealRevKirimResponse> response) {
                Log.d("get List", "onResponse" + response.message());
                if (response.body().getCode() == 302){
                    tvKetRealisasi.setText(response.body().getData().getKet_realisasi());
                    tvHasilRealisasi.setText(response.body().getData().getHasil());
                    tvPelanggaran.setText(String.valueOf(response.body().getData().getPelanggaran_id()));
                    tvKondisi.setText(response.body().getData().getKondisi_stan_meter());
                    tvCatatan.setText(response.body().getData().getCatatan_stan_meter());
                    tvTglAngkat.setText(response.body().getData().getTanggal_angkat());
                    tvNoMeter.setText(String.valueOf(response.body().getData().getNo_meter()));
                    tvUkuranMeter.setText(response.body().getData().getUkuran_meter());
                    tvAngkaAngkat.setText(String.valueOf(response.body().getData().getAngka_angkat()));
                    tvMerkMeteran.setText(response.body().getData().getMerk_meter());
                }
                else {
                    Toast.makeText(FormRealKirimRevActivity.this, "error nih", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ViewRealRevKirimResponse> call, Throwable t) {
                Log.e("Form Realisasi", "onFailure: " + t.getMessage(), t);
            }
        });
    }
}
