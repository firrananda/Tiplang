package dev.zero.tiplangpdam.activity.revisi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.model.Pelanggaran;
import dev.zero.tiplangpdam.model.local.FormDataRev;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class FormRealProsesRevActivity extends AppCompatActivity {

    FormDataRev data;
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
    @BindView(R.id.tv_ket_realisasi)
    TextView tvKetRealisasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_real_proses_rev);
        ButterKnife.bind(this);

        data = getIntent().getParcelableExtra("data");
        initLastData();

    }

    private void initLastData() {
        tvBatd.setText(data.getNo_batd());
        tvTglBa.setText(data.getTanggal_batd());
        tvKetRealisasi.setText(data.getKet_realisasi());
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

    private void sendRealisasi() {
        HashMap<String, RequestBody> params = new HashMap<>();
        params.put("ket_realisasi", RequestBody.create(MediaType.parse("text/plain"), tvKetRealisasi.getText().toString()));
        params.put("hasil", RequestBody.create(MediaType.parse("text/plain"), edtHasilRealisasi.getText().toString()));
        params.put("kondisi_stan_meter", RequestBody.create(MediaType.parse("text/plain"), edtKondisi.getText().toString()));
        params.put("catatan_stan_meter", RequestBody.create(MediaType.parse("text/plain"), edtCatatan.getText().toString()));
        params.put("tanggal_angkat", RequestBody.create(MediaType.parse("text/plain"), edtTglAngkat.getText().toString()));
        params.put("no_meter", RequestBody.create(MediaType.parse("text/plain"), edtNoMeter.getText().toString()));
        params.put("ukuran_meter", RequestBody.create(MediaType.parse("text/plain"), edtUkuranMeter.getText().toString()));
        params.put("angka_angkat", RequestBody.create(MediaType.parse("text/plain"), edtAngkaAngkat.getText().toString()));
        params.put("merk_meter", RequestBody.create(MediaType.parse("text/plain"), edtMerkMeteran.getText().toString()));
        params.put("batd_id", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(data.getBatd_id())));
        params.put("pelanggaran_id", RequestBody.create(MediaType.parse("text/plain"), pelanggaranId));

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        dialog.setTitle("Mengirim data");
        dialog.show();
    }
}