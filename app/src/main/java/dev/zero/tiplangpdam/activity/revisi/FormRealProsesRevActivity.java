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

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.SignatureActivity;
import dev.zero.tiplangpdam.activity.baru.FormRealisasiProsesActivity;
import dev.zero.tiplangpdam.helper.FormDataRevSaveHelper;
import dev.zero.tiplangpdam.model.Pelanggaran;
import dev.zero.tiplangpdam.model.local.FormDataRev;
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

    MultipartBody.Part pict1, pict2, pict3, pict4;
    File imagePath1, imagePath2, imagePath3, imagePath4;
    Bitmap bitmap;
    String id_form;
    String id_batd;

    private static final int REQUEST_CODE_PICTURE_1 = 1;
    private static final int REQUEST_CODE_PICTURE_2 = 2;
    private static final int REQUEST_CODE_PICTURE_3 = 3;
    private static final int REQUEST_CODE_PICTURE_4 = 4;
    public static final int TandaTangan_Activity = 5;
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_real_proses_rev);
        ButterKnife.bind(this);

        id_form = getIntent().getStringExtra("FORM_ID");
        id_batd = getIntent().getStringExtra("batd_id");
        data = getIntent().getParcelableExtra("data");
        Toast.makeText(this, id_form, Toast.LENGTH_SHORT).show();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        MY_CAMERA_REQUEST_CODE);
            }
        }

        getFoto();
        getPelanggaran();
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
        if (data.getPict1() != null) {
            Glide.with(this).load(new File(data.getPict1())).into(ivFotohasil1);
            bitmap = ImageSaver.createImageWithBarcode(new File(data.getPict1()),tvBatd.getText().toString());
            imagePath1 = ImageSaver.convertBitmapToFile(FormRealProsesRevActivity.this, bitmap, tvBatd.getText().toString()+"_1");
        }
        if (data.getPict2() != null) {
            Glide.with(this).load(new File(data.getPict2())).into(ivFotohasil2);
            bitmap = ImageSaver.createImageWithBarcode(new File(data.getPict2()),tvBatd.getText().toString());
            imagePath2 = ImageSaver.convertBitmapToFile(FormRealProsesRevActivity.this, bitmap, tvBatd.getText().toString()+"_2");
        }
        if (data.getPict3() != null) {
            Glide.with(this).load(new File(data.getPict3())).into(ivFotohasil3);
            bitmap = ImageSaver.createImageWithBarcode(new File(data.getPict3()),tvBatd.getText().toString());
            imagePath3 = ImageSaver.convertBitmapToFile(FormRealProsesRevActivity.this, bitmap, tvBatd.getText().toString()+"_3");
        }
        if (data.getPict4() != null) {
            Glide.with(this).load(new File(data.getPict4())).into(ivFotohasil4);
            bitmap = ImageSaver.createImageWithBarcode(new File(data.getPict4()),tvBatd.getText().toString());
            imagePath4 = ImageSaver.convertBitmapToFile(FormRealProsesRevActivity.this, bitmap, "signature"+tvBatd.getText().toString()+"_4");
        }
    }

    @OnClick({R.id.btn_foto1, R.id.btn_foto2, R.id.btn_foto3, R.id.btn_foto4, R.id.btn_kirim})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_foto1:
                EasyImage.openCamera(FormRealProsesRevActivity.this,REQUEST_CODE_PICTURE_1);
                break;
            case R.id.btn_foto2:
                EasyImage.openCamera(FormRealProsesRevActivity.this,REQUEST_CODE_PICTURE_2);
                break;
            case R.id.btn_foto3:
                EasyImage.openCamera(FormRealProsesRevActivity.this,REQUEST_CODE_PICTURE_3);
                break;
            case R.id.btn_foto4:
                Intent intent = new Intent(FormRealProsesRevActivity.this, SignatureActivity.class);
                startActivityForResult(intent, TandaTangan_Activity);
                break;
            case R.id.btn_kirim:
                sendRealisasi();
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
        params.put("real_id", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(data.getReal_id())));
        params.put("batd_id", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(data.getBatd_id())));
        params.put("pelanggaran_id", RequestBody.create(MediaType.parse("text/plain"), pelanggaranId));

        RequestBody file1 = RequestBody.create(MediaType.parse("image/*"), imagePath1);
        RequestBody file2 = RequestBody.create(MediaType.parse("image/*"), imagePath2);
        RequestBody file3 = RequestBody.create(MediaType.parse("image/*"), imagePath3);
        RequestBody file4 = RequestBody.create(MediaType.parse("image/*"), imagePath4);

        pict1=MultipartBody.Part.createFormData("pict1",imagePath1.getName(),file1);
        pict2=MultipartBody.Part.createFormData("pict2",imagePath2.getName(),file2);
        pict3=MultipartBody.Part.createFormData("pict3",imagePath3.getName(),file3);
        pict4=MultipartBody.Part.createFormData("pict4",imagePath4.getName(),file4);

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Loading...");
        dialog.setTitle("Mengirim data");
        dialog.show();

        ApiService.service_post.updateForm(String.valueOf(id_form), params, pict1, pict2, pict3, pict4).enqueue(new Callback<Update_RealisasiResponse>() {
            @Override
            public void onResponse(Call<Update_RealisasiResponse> call, Response<Update_RealisasiResponse> response) {
                dialog.dismiss();
                if (response.code() == 200){
                    if (response.body().getCode() == 302) {
                        Toast.makeText(FormRealProsesRevActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        FormDataRevSaveHelper.deleteDataPerNoPel(data.getNo_pelanggan());
                        finish();
                    } else {
                        Toast.makeText(FormRealProsesRevActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("Send Form Realisasi", "onResponse: " + response.body().getCode());
                        Log.d("Send Form Realisasi", "onResponse: " + response.body().getMessage());
                        Log.d("Send Form Realisasi", "onResponse: " + response.body().getDescription());
                    }
                }
                else {
                    Toast.makeText(FormRealProsesRevActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    Log.d("Send Form Realisasi", "onResponse: " + response.code());
                    Log.d("Send Form Realisasi", "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Update_RealisasiResponse> call, Throwable t) {
                dialog.dismiss();
                Log.e("Error Send Form", "onFailure: " + t.getMessage(), t);
                Toast.makeText(FormRealProsesRevActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
                    ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(FormRealProsesRevActivity.this, android.R.layout.simple_list_item_1, pelanggaran);
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
                call.cancel();
            }
        });
    }

    public void getFoto(){
        ApiService.service_get.getViewRealRevBaru(String.valueOf(id_form)).enqueue(new Callback<ViewRealRevBaruResponse>() {
            @Override
            public void onResponse(Call<ViewRealRevBaruResponse> call, Response<ViewRealRevBaruResponse> response) {
                if (response.body().getCode() == 302){
                    //Toast.makeText(FormRealProsesRevActivity.this, ApiService.BASE_URL+"/tiplang/" + response.body().getData().getPict1(), Toast.LENGTH_SHORT).show();
                    Glide.with(FormRealProsesRevActivity.this).load(ApiService.BASE_URL+"/tiplang/" + response.body().getData().getPict1()).into(ivFotohasil1);
                    Glide.with(FormRealProsesRevActivity.this).load(ApiService.BASE_URL+"/tiplang/" + response.body().getData().getPict2()).into(ivFotohasil2);
                    Glide.with(FormRealProsesRevActivity.this).load(ApiService.BASE_URL+"/tiplang/" + response.body().getData().getPict3()).into(ivFotohasil3);
                    Glide.with(FormRealProsesRevActivity.this).load(ApiService.BASE_URL+"/tiplang/" + response.body().getData().getPict4()).into(ivFotohasil4);
                }else{
                    Toast.makeText(FormRealProsesRevActivity.this, ApiService.BASE_URL+"/tiplang/" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ViewRealRevBaruResponse> call, Throwable t) {
                Toast.makeText(FormRealProsesRevActivity.this, "error", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TandaTangan_Activity){
            String path = data.getStringExtra("sign");
            Log.d("Path", "onActivityResult: " + path);
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            bitmap = Bitmap.createScaledBitmap(bitmap, 786, 1024, true);

            Glide.with(FormRealProsesRevActivity.this)
                    .load(bitmap)
                    .into(ivFotohasil4);
            imagePath4 = ImageSaver.convertBitmapToFile(FormRealProsesRevActivity.this,bitmap,"signature" + tvBatd.getText().toString()+"_4.jpg");
        }

        else {

            EasyImage.handleActivityResult(requestCode, resultCode, data, this, new EasyImage.Callbacks() {
                @Override
                public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                    e.printStackTrace();
                }

                @Override
                public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                    switch (type) {
                        case REQUEST_CODE_PICTURE_1:
                            Glide.with(FormRealProsesRevActivity.this)
                                    .load(imageFile)
                                    .into(ivFotohasil1);
                            bitmap = ImageSaver.createImageWithBarcode(imageFile, tvBatd.getText().toString());
                            imagePath1 = ImageSaver.convertBitmapToFile(FormRealProsesRevActivity.this, bitmap, tvBatd.getText().toString()+"_1");
                            break;
                        case REQUEST_CODE_PICTURE_2:
                            Glide.with(FormRealProsesRevActivity.this)
                                    .load(imageFile)
                                    .into(ivFotohasil2);
                            bitmap = ImageSaver.createImageWithBarcode(imageFile, tvBatd.getText().toString());
                            imagePath2 = ImageSaver.convertBitmapToFile(FormRealProsesRevActivity.this, bitmap, tvBatd.getText().toString()+"_2");
                            break;
                        case REQUEST_CODE_PICTURE_3:
                            Glide.with(FormRealProsesRevActivity.this)
                                    .load(imageFile)
                                    .into(ivFotohasil3);
                            bitmap = ImageSaver.createImageWithBarcode(imageFile, tvBatd.getText().toString());
                            imagePath3 = ImageSaver.convertBitmapToFile(FormRealProsesRevActivity.this, bitmap, tvBatd.getText().toString()+"_3");
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
                    Toasty.error(FormRealProsesRevActivity .this, "Kenapa nggak jadi ambil foto kwkw");
                }
            });
        }
    }
}