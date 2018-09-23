package dev.zero.tiplangpdam.activity.revisi;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.SignatureActivity;
import dev.zero.tiplangpdam.activity.baru.FormRealisasiActivity;
import dev.zero.tiplangpdam.activity.baru.FormRealisasiKirimActivity;
import dev.zero.tiplangpdam.helper.FormDataRevSaveHelper;
import dev.zero.tiplangpdam.model.PelangganRev;
import dev.zero.tiplangpdam.model.Pelanggaran;
import dev.zero.tiplangpdam.model.response.PelanggaranResponse;
import dev.zero.tiplangpdam.model.response.Update_RealisasiResponse;
import dev.zero.tiplangpdam.model.response.ViewRealRevBaruResponse;
import dev.zero.tiplangpdam.service.ApiService;
import dev.zero.tiplangpdam.service.ImageSaver;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pl.aprilapps.easyphotopicker.EasyImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormRealRevActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PICTURE_1 = 1;
    private static final int REQUEST_CODE_PICTURE_2 = 2;
    private static final int REQUEST_CODE_PICTURE_3 = 3;
    private static final int REQUEST_CODE_PICTURE_4 = 4;
    public static final int TandaTangan_Activity = 5;

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
    @BindView(R.id.btn_foto1)
    Button btnFoto1;
    @BindView(R.id.btn_foto2)
    Button btnFoto2;
    @BindView(R.id.btn_foto3)
    Button btnFoto3;
    @BindView(R.id.btn_foto4)
    Button btnFoto4;
    @BindView(R.id.btn_proses)
    Button btnProses;
    @BindView(R.id.btn_kirim)
    Button btnKirim;

    PelangganRev datapelrev;
    String id_form;
    MultipartBody.Part pict1, pict2, pict3, pict4;
    File imagePath1, imagePath2, imagePath3, imagePath4;
    Bitmap bitmap;
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_real_rev);
        ButterKnife.bind(this);

        id_form = getIntent().getStringExtra("FORM_ID");
        datapelrev = getIntent().getParcelableExtra("datapel");
        tvBatd.setText(datapelrev.getNomor_batd());
        tvTglBa.setText(datapelrev.getTanggal_batd());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        MY_CAMERA_REQUEST_CODE);
            }
        }

        getPelanggaran();

        ApiService.service_get.getViewRealRevBaru(String.valueOf(id_form)).enqueue(new Callback<ViewRealRevBaruResponse>() {
            @Override
            public void onResponse(Call<ViewRealRevBaruResponse> call, Response<ViewRealRevBaruResponse> response) {
                if (response.body().getCode() == 302) {
                    tvKetRealisasi.setText(response.body().getData().getKet_realisasi());
                    edtHasilRealisasi.setText(response.body().getData().getHasil());
                    //tvPelanggaran.setText(String.valueOf(response.body().getData().getPelanggaran_id()));
                    edtKondisi.setText(response.body().getData().getKondisi_stan_meter());
                    edtCatatan.setText(response.body().getData().getCatatan_stan_meter());
                    edtTglAngkat.setText(response.body().getData().getTanggal_angkat());
                    edtNoMeter.setText(String.valueOf(response.body().getData().getNo_meter()));
                    edtUkuranMeter.setText(response.body().getData().getUkuran_meter());
                    edtAngkaAngkat.setText(String.valueOf(response.body().getData().getAngka_angkat()));
                    edtMerkMeteran.setText(response.body().getData().getMerk_meter());
                    Glide.with(FormRealRevActivity.this).load(ApiService.BASE_URL+"/tiplang/" + response.body().getData().getPict1()).into(ivFotohasil1);
                    Glide.with(FormRealRevActivity.this).load(ApiService.BASE_URL+"/tiplang/" + response.body().getData().getPict1()).into(ivFotohasil2);
                    Glide.with(FormRealRevActivity.this).load(ApiService.BASE_URL+"/tiplang/" + response.body().getData().getPict1()).into(ivFotohasil3);
                    Glide.with(FormRealRevActivity.this).load(ApiService.BASE_URL+"/tiplang/" + response.body().getData().getPict1()).into(ivFotohasil4);
                } else {
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
                EasyImage.openCamera(FormRealRevActivity.this,REQUEST_CODE_PICTURE_1);
            break;
            case R.id.btn_foto2:
                EasyImage.openCamera(FormRealRevActivity.this,REQUEST_CODE_PICTURE_2);
                break;
            case R.id.btn_foto3:
                EasyImage.openCamera(FormRealRevActivity.this,REQUEST_CODE_PICTURE_3);
                break;
            case R.id.btn_foto4:
                Intent intent = new Intent(FormRealRevActivity.this, SignatureActivity.class);
                startActivityForResult(intent, TandaTangan_Activity);
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
                if(imagePath1 != null) paramsSave.put("pict1", imagePath1.getAbsolutePath());
                if(imagePath2 != null) paramsSave.put("pict2", imagePath2.getAbsolutePath());
                if(imagePath3 != null) paramsSave.put("pict3", imagePath3.getAbsolutePath());
                if(imagePath4 != null) paramsSave.put("pict4", imagePath4.getAbsolutePath());

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

                RequestBody file1 = RequestBody.create(MediaType.parse("image/*"), imagePath1);
                RequestBody file2 = RequestBody.create(MediaType.parse("image/*"), imagePath2);
                RequestBody file3 = RequestBody.create(MediaType.parse("image/*"), imagePath3);
                //RequestBody file4 = RequestBody.create(MediaType.parse("image/*"), imagePath4);

                pict1=MultipartBody.Part.createFormData("pict1",imagePath1.getName(),file1);
                pict2=MultipartBody.Part.createFormData("pict2",imagePath2.getName(),file2);
                pict3=MultipartBody.Part.createFormData("pict3",imagePath3.getName(),file3);
                //pict4=MultipartBody.Part.createFormData("pict4",imagePath4.getName(),file4);

                final ProgressDialog dialog = new ProgressDialog(this);
                dialog.setCancelable(false);
                dialog.setMessage("Loading...");
                dialog.setTitle("Mengirim data");
                dialog.show();
                ApiService.service_post.updateForm(String.valueOf(id_form),params, pict1, pict2, pict3).enqueue(new Callback<Update_RealisasiResponse>() {
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

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TandaTangan_Activity){
            String path = getIntent().getStringExtra("sign");
            Log.d("Path", "onActivityResult: " + path);
            Bitmap bitmapImage = BitmapFactory.decodeFile(path);
            imagePath4 = ImageSaver.convertBitmapToFile(this,bitmapImage,"sign");
        }else {

            EasyImage.handleActivityResult(requestCode, resultCode, data, this, new EasyImage.Callbacks() {
                @Override
                public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                    e.printStackTrace();
                }

                @Override
                public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                    switch (type) {
                        case REQUEST_CODE_PICTURE_1:
                            Glide.with(FormRealRevActivity.this)
                                    .load(imageFile)
                                    .into(ivFotohasil1);
                            bitmap = ImageSaver.createImageWithBarcode(imageFile, tvBatd.getText().toString());
                            imagePath1 = ImageSaver.convertBitmapToFile(FormRealRevActivity.this, bitmap, tvBatd.getText().toString()+"_1");
                            break;
                        case REQUEST_CODE_PICTURE_2:
                            Glide.with(FormRealRevActivity.this)
                                    .load(imageFile)
                                    .into(ivFotohasil2);
                            bitmap = ImageSaver.createImageWithBarcode(imageFile, tvBatd.getText().toString());
                            imagePath2 = ImageSaver.convertBitmapToFile(FormRealRevActivity.this, bitmap, tvBatd.getText().toString()+"_2");
                            break;
                        case REQUEST_CODE_PICTURE_3:
                            Glide.with(FormRealRevActivity.this)
                                    .load(imageFile)
                                    .into(ivFotohasil3);
                            bitmap = ImageSaver.createImageWithBarcode(imageFile, tvBatd.getText().toString());
                            imagePath3 = ImageSaver.convertBitmapToFile(FormRealRevActivity.this, bitmap, tvBatd.getText().toString()+"_3");
                            break;
//                    case REQUEST_CODE_PICTURE_4:
//                        Glide.with(FormRealisasiActivity.this)
//                                .load(imageFile)
//                                .into(ivFotohasil4);
//                        imagePath4 = ImageSaver.convertBitmapToFile(FormRealisasiActivity.this,bitmap, tvbatd.getText().toString()+ "_3");
//                        break;

                    }
                    Log.d("Path", "onImagePicked: " + imageFile.getAbsolutePath());
                }

                @Override
                public void onCanceled(EasyImage.ImageSource source, int type) {
                    Toasty.error(FormRealRevActivity.this, "Kenapa nggak jadi ambil foto kwkw");
                }
            });
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(FormRealRevActivity.this, android.R.layout.simple_list_item_1, pelanggaran);
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

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();

            }

        }
    }

}
