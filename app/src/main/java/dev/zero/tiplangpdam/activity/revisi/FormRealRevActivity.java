package dev.zero.tiplangpdam.activity.revisi;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.helper.FormDataRevSaveHelper;
import dev.zero.tiplangpdam.helper.FormDataSaveHelper;
import dev.zero.tiplangpdam.model.PelangganRev;
import dev.zero.tiplangpdam.model.Pelanggaran;
import dev.zero.tiplangpdam.model.response.PelanggaranResponse;
import dev.zero.tiplangpdam.model.response.RealisasiResponse;
import dev.zero.tiplangpdam.model.response.Update_RealisasiResponse;
import dev.zero.tiplangpdam.model.response.ViewRealRevBaruResponse;
import dev.zero.tiplangpdam.service.ApiService;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormRealRevActivity extends AppCompatActivity {

    @BindView(R.id.tv_batd)
    TextView tvBatd;
    @BindView(R.id.tv_tgl_ba)
    TextView tvTglBa;
    @BindView(R.id.tv_ket_realisasi)
    TextView tvKetRealisasi;
    @BindView(R.id.edt_hasil_realisasi)
    EditText edtHasilRealisasi;
    @BindView(R.id.kodepelanggaran)
    TextView kodepelanggaran;
    @BindView(R.id.tv_pelanggaran)
    TextView tvPelanggaran;
    @BindView(R.id.spn_pelanggaran)
    Spinner spnPelanggaran;
    ArrayList<Pelanggaran> listPelanggaran;
    String pelanggaranId = "";
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
    @BindView(R.id.iv_fotohasil1)
    ImageView ivFotohasil1;
    @BindView(R.id.iv_fotohasil2)
    ImageView ivFotohasil2;
    @BindView(R.id.iv_fotohasil3)
    ImageView ivFotohasil3;
    @BindView(R.id.iv_fotohasil4)
    ImageView ivFotohasil4;

    PelangganRev datapelrev;

    static Uri capturedImageUri = null;
    static Uri capturedImageUri2 = null;
    static Uri capturedImageUri3 = null;
    static Uri capturedImageUri4 = null;
    //static boolean imageLoaded = false;
//    File file=null;File file2 = null;

    String id_form;
    File imagePath1 = null;
    File imagePath2 = null;
    File imagePath4 = null;
    File imagePath3 = null;
    String BATD_ID;
    String idPel = null;
    String filename = null;
    String filename2 = null;
    String filename3 = null;
    String filename4 = null;
    //File file;
    //String lokasiGambar = null;
    //Context context;

    float scale;
    String nolang = "12345_.jpg";
    Bitmap bitmap, bitmap2, bitmap3, bitmap4;
    int w, h;
    Matrix matrix;
    RectF r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_real_rev);
        ButterKnife.bind(this);

        id_form = getIntent().getStringExtra("FORM_ID");
        datapelrev = getIntent().getParcelableExtra("datapel");
        tvBatd.setText(datapelrev.getNomor_batd());
        tvTglBa.setText(datapelrev.getTanggal_batd());

        getPelanggaran();

        ApiService.service_get.getViewRealRevBaru(String.valueOf(id_form)).enqueue(new Callback<ViewRealRevBaruResponse>() {
            @Override
            public void onResponse(Call<ViewRealRevBaruResponse> call, Response<ViewRealRevBaruResponse> response) {
                if (response.body().getCode() == 302){
                    tvKetRealisasi.setText(response.body().getData().getKet_realisasi());
                    edtHasilRealisasi.setText(response.body().getData().getHasil());
                    tvPelanggaran.setText(String.valueOf(response.body().getData().getPelanggaran_id()));
                    edtKondisi.setText(response.body().getData().getKondisi_stan_meter());
                    edtCatatan.setText(response.body().getData().getCatatan_stan_meter());
                    edtTglAngkat.setText(response.body().getData().getTanggal_angkat());
                    edtNoMeter.setText(String.valueOf(response.body().getData().getNo_meter()));
                    edtUkuranMeter.setText(response.body().getData().getUkuran_meter());
                    edtAngkaAngkat.setText(String.valueOf(response.body().getData().getAngka_angkat()));
                    edtMerkMeteran.setText(response.body().getData().getMerk_meter());
                }
                else {
                    Toast.makeText(FormRealRevActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ViewRealRevBaruResponse> call, Throwable t) {
                Log.e("Form Real Rev Baru", "Onfailure" + t.getMessage(), t);
            }
        });
    }

    @OnClick({R.id.btn_foto1, R.id.btn_foto2, R.id.btn_foto3, R.id.btn_foto4, R.id.btn_proses, R.id.btn_kirim})
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
            case R.id.btn_proses:
                HashMap<String, String> paramsSave = new HashMap<>();
                paramsSave.put("ket_real", tvKetRealisasi.getText().toString());
                paramsSave.put("hasil", edtHasilRealisasi.getText().toString());
                paramsSave.put("no_batd", datapelrev.getNomor_batd());
                paramsSave.put("tanggal_batd", datapelrev.getTanggal_batd());
                paramsSave.put("no_pelanggan", datapelrev.getNomor_pelanggan());
                paramsSave.put("nama_pelanggan", datapelrev.getNama());
                paramsSave.put("zona", datapelrev.getZona());
                paramsSave.put("jalan", datapelrev.getJalan());
                paramsSave.put("kondisi_stan_meter", edtKondisi.getText().toString());
                paramsSave.put("catatan_stan_meter", edtCatatan.getText().toString());
                paramsSave.put("tanggal_angkat", edtTglAngkat.getText().toString());
                paramsSave.put("no_meter", edtNoMeter.getText().toString());
                paramsSave.put("ukuran_meter", edtUkuranMeter.getText().toString());
                paramsSave.put("angka_angkat", edtAngkaAngkat.getText().toString());
                paramsSave.put("merk_meter", edtMerkMeteran.getText().toString());
                paramsSave.put("batd_id", String.valueOf(datapelrev.getId_batd()));
                paramsSave.put("pelanggaran_id", spnPelanggaran.getSelectedItem().toString());
                paramsSave.put("pict1", imagePath1.getAbsolutePath());
                paramsSave.put("pict2", imagePath2.getAbsolutePath());
                paramsSave.put("pict3", imagePath3.getAbsolutePath());
                paramsSave.put("pict4", imagePath4.getAbsolutePath());

                FormDataRevSaveHelper.addDataString(paramsSave);
                finish();
                break;
            case R.id.btn_kirim:
                HashMap<String, RequestBody> params = new HashMap<>();
                params.put("hasil", RequestBody.create(MediaType.parse("text/plain"), edtHasilRealisasi.getText().toString()));
                params.put("kondisi_stan_meter", RequestBody.create(MediaType.parse("text/plain"), edtKondisi.getText().toString()));
                params.put("catatan_stan_meter", RequestBody.create(MediaType.parse("text/plain"), edtCatatan.getText().toString()));
                params.put("tanggal_angkat", RequestBody.create(MediaType.parse("text/plain"), edtTglAngkat.getText().toString()));
                params.put("no_meter", RequestBody.create(MediaType.parse("text/plain"), edtNoMeter.getText().toString()));
                params.put("ukuran_meter", RequestBody.create(MediaType.parse("text/plain"), edtUkuranMeter.getText().toString()));
                params.put("angka_angkat", RequestBody.create(MediaType.parse("text/plain"), edtAngkaAngkat.getText().toString()));
                params.put("merk_meter", RequestBody.create(MediaType.parse("text/plain"), edtMerkMeteran.getText().toString()));
                params.put("batd_id", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(datapelrev.getId_batd())));
                params.put("pelanggaran_id", RequestBody.create(MediaType.parse("text/plain"), pelanggaranId));

//                imagePath1 = new File(filename);
//                imagePath2 = new File(filename2);
//                imagePath3 = new File(filename3);
//                imagePath4 = new File(filename4);
//
//                params.put("pict1", RequestBody.create(MediaType.parse("image/*"), imagePath1));
//                params.put("pict2", RequestBody.create(MediaType.parse("image/*"), imagePath2));
//                params.put("pict3", RequestBody.create(MediaType.parse("image/*"), imagePath3));
//                params.put("pict4", RequestBody.create(MediaType.parse("image/*"), imagePath4));

//                RequestBody file1 = RequestBody.create(MediaType.parse("image/*"), imagePath1);
//                RequestBody file2 = RequestBody.create(MediaType.parse("image/*"), imagePath2);
//                RequestBody file3 = RequestBody.create(MediaType.parse("image/*"), imagePath3);
//                RequestBody file4 = RequestBody.create(MediaType.parse("image/*"), imagePath4);
//                HashMap<String, MultipartBody.Part> parts = new HashMap<>();
//
//                parts.add(MultipartBody.Part.createFormData("pict1", imagePath1.getName(), file1));
//                parts.add(MultipartBody.Part.createFormData("foto_realisasi[]", imagePath2.getName(), file2));
//                parts.add(MultipartBody.Part.createFormData("foto_realisasi[]", imagePath3.getName(), file3));
//                parts.add(MultipartBody.Part.createFormData("foto_realisasi[]", imagePath4.getName(), file4));

                final ProgressDialog dialog = new ProgressDialog(this);
                dialog.setCancelable(false);
                dialog.setMessage("Loading...");
                dialog.setTitle("Mengirim data");
                dialog.show();
                ApiService.service_post.updateForm(String.valueOf(id_form),params).enqueue(new Callback<Update_RealisasiResponse>() {
                    @Override
                    public void onResponse(Call<Update_RealisasiResponse> call, Response<Update_RealisasiResponse> response) {
                        dialog.dismiss();
                        if (response.code() == 200) {
                            if (response.body().getCode() == 302) {
                                Toast.makeText(FormRealRevActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(FormRealRevActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d("Send Form Realisasi", "onResponse: " + response.body().getCode());
                                Log.d("Send Form Realisasi", "onResponse: " + response.body().getMessage());
                                Log.d("Send Form Realisasi", "onResponse: " + response.body().getDescription());
                            }
                        }else {
                            Toast.makeText(FormRealRevActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                            Log.d("Send Form Realisasi", "onResponse: " + response.code());
                            Log.d("Send Form Realisasi", "onResponse: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Update_RealisasiResponse> call, Throwable t) {
                        dialog.dismiss();
                        Log.e("Error Send Form", "onFailure: " + t.getMessage(), t);
                        Toast.makeText(FormRealRevActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    public void getPelanggaran() {
        ApiService.service_get.getPelanggaran().enqueue(new Callback<PelanggaranResponse>() {
            @Override
            public void onResponse(@NonNull Call<PelanggaranResponse> call, @NonNull Response<PelanggaranResponse> response) {
                if (response.body().getCode() == 302) {
                    listPelanggaran = response.body().getData();
                    ArrayList<String> pelanggaran = new ArrayList<>();
                    for (Pelanggaran listPelanggaran2 : listPelanggaran)
                        pelanggaran.add(listPelanggaran2.getKeterangan());
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(FormRealRevActivity .this, android.R.layout.simple_list_item_1, pelanggaran);
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


}
