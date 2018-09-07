package dev.zero.tiplangpdam.activity.baru;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.zero.tiplangpdam.R;
import dev.zero.tiplangpdam.helper.FormDataSaveHelper;
import dev.zero.tiplangpdam.model.Pelanggan;
import dev.zero.tiplangpdam.model.Pelanggaran;
import dev.zero.tiplangpdam.model.response.PelanggaranResponse;
import dev.zero.tiplangpdam.model.response.RealisasiResponse;
import dev.zero.tiplangpdam.service.ApiService;
import dev.zero.tiplangpdam.service.ImageSaver;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormRealisasiActivity extends AppCompatActivity {

    @BindView(R.id.tv_batd)
    TextView tvbatd;
    @BindView(R.id.edt_hasil_realisasi)
    EditText edtHasilRealisasi;
    @BindView(R.id.kodepelanggaran)
    TextView kodepelanggaran;
    @BindView(R.id.spn_pelanggaran)
    Spinner spnPelanggaran;
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
    ArrayList<Pelanggaran> listPelanggaran;
    String pelanggaranId = "";
    @BindView(R.id.cardcatatan)
    CardView cardcatatan;
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

    MultipartBody.Part pict1, pict2, pict3, pict4;

    //Uri capturedImageUri;
    //Uri capturedImageUri;
    static Uri capturedImageUri = null;
    static Uri capturedImageUri2 = null;
    static Uri capturedImageUri3 = null;
    static Uri capturedImageUri4 = null;
    //static boolean imageLoaded = false;
//    File file=null;File file2 = null;
    File imagePath1;
    File imagePath2;
    File imagePath4;
    File imagePath3;
    String BATD_ID;
    String idPel = null;
    String filename;
    String filename2;
    String filename3;
    String filename4;
    //File file;
    //String lokasiGambar = null;
    //Context context;

    float scale;
    String nolang = "12345_.jpg";
    Bitmap bitmap, bitmap2, bitmap3, bitmap4;
    int w, h;
    Matrix matrix;
    RectF r;
    @BindView(R.id.tv_tgl_ba)
    TextView tvTglBa;
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
    Pelanggan dataPelanggan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_realisasi);
        ButterKnife.bind(this);
        dataPelanggan = getIntent().getParcelableExtra("data_pelanggan");

        tvbatd.setText(dataPelanggan.getNomor_batd());
        tvTglBa.setText(dataPelanggan.getTanggal_batd());

        idPel = nolang;

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        isWriteStoragePermissionGranted();

        getPelanggaran();
    }

    @OnClick({R.id.btn_proses, R.id.btn_kirim, R.id.btn_foto1, R.id.btn_foto2, R.id.btn_foto3, R.id.btn_foto4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_proses:
                HashMap<String, String> paramsSave = new HashMap<>();
                paramsSave.put("hasil", edtHasilRealisasi.getText().toString());
                paramsSave.put("spk", dataPelanggan.getNomor_spk());
                paramsSave.put("no_batd", dataPelanggan.getNomor_batd());
                paramsSave.put("tanggal_batd", dataPelanggan.getTanggal_batd());
                paramsSave.put("no_pelanggan", dataPelanggan.getNomor_pelanggan());
                paramsSave.put("nama_pelanggan", dataPelanggan.getNama_pelanggan());
                paramsSave.put("zona", dataPelanggan.getZona());
                paramsSave.put("jalan", dataPelanggan.getJalan());
                paramsSave.put("kondisi_stan_meter", edtKondisi.getText().toString());
                paramsSave.put("catatan_stan_meter", edtCatatan.getText().toString());
                paramsSave.put("tanggal_angkat", edtTglAngkat.getText().toString());
                paramsSave.put("no_meter", edtNoMeter.getText().toString());
                paramsSave.put("ukuran_meter", edtUkuranMeter.getText().toString());
                paramsSave.put("angka_angkat", edtAngkaAngkat.getText().toString());
                paramsSave.put("merk_meter", edtMerkMeteran.getText().toString());
                paramsSave.put("batd_id", String.valueOf(dataPelanggan.getBatd_id()));
                paramsSave.put("pelanggaran_id", spnPelanggaran.getSelectedItem().toString());

                FormDataSaveHelper.addDataString(paramsSave);
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
                params.put("batd_id", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(dataPelanggan.getBatd_id())));
                params.put("pelanggaran_id", RequestBody.create(MediaType.parse("text/plain"), pelanggaranId));

//                imagePath1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "1.jpg");
//                imagePath2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "2.jpg");
//                imagePath3 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "3.jpg");
//                imagePath4 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "4.jpg");

//                params.put("pict1", RequestBody.create(MediaType.parse("image/*"), imagePath1));
//                params.put("pict2", RequestBody.create(MediaType.parse("image/*"), imagePath2));
//                params.put("pict3", RequestBody.create(MediaType.parse("image/*"), imagePath3));
//                params.put("pict4", RequestBody.create(MediaType.parse("image/*"), imagePath4));

//                RequestBody file1 = RequestBody.create(MediaType.parse("image/*"), imagePath1);
//                RequestBody file2 = RequestBody.create(MediaType.parse("image/*"), imagePath2);
//                RequestBody file3 = RequestBody.create(MediaType.parse("image/*"), imagePath3);
//                RequestBody file4 = RequestBody.create(MediaType.parse("image/*"), imagePath4);
                //HashMap<String, MultipartBody.Part> parts = new HashMap<>();

//                parts.put(MultipartBody.Part.createFormData("pict1", imagePath1.getName(), RequestBody.create(MediaType.parse("image/*"), imagePath1)));
//                parts.put(MultipartBody.Part.createFormData("pict2", imagePath2.getName(), RequestBody.create(MediaType.parse("image/*"), imagePath2));
//                parts.put(MultipartBody.Part.createFormData("pict3", imagePath3.getName(), RequestBody.create(MediaType.parse("image/*"), imagePath3));
//                parts.put(MultipartBody.Part.createFormData("pict4", imagePath4.getName(), RequestBody.create(MediaType.parse("image/*"), imagePath4));

                final ProgressDialog dialog = new ProgressDialog(this);
                dialog.setCancelable(false);
                dialog.setMessage("Loading...");
                dialog.setTitle("Mengirim data");
                dialog.show();
                ApiService.service_post.postForm(params).enqueue(new Callback<RealisasiResponse>() {
                    @Override
                    public void onResponse(Call<RealisasiResponse> call, Response<RealisasiResponse> response) {
                        dialog.dismiss();
                        if (response.code() == 200) {
                            if (response.body().getCode() == 302) {
                                Toast.makeText(FormRealisasiActivity.this, imagePath1.toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(FormRealisasiActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(FormRealisasiActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d("Send Form Realisasi", "onResponse: " + response.body().getCode());
                                Log.d("Send Form Realisasi", "onResponse: " + response.body().getMessage());
                                Log.d("Send Form Realisasi", "onResponse: " + response.body().getDescription());
                            }
                        }else {
                            Toast.makeText(FormRealisasiActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                            Log.d("Send Form Realisasi", "onResponse: " + response.code());
                            Log.d("Send Form Realisasi", "onResponse: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<RealisasiResponse> call, Throwable t) {
                        dialog.dismiss();
                        Log.e("Error Send Form", "onFailure: " + t.getMessage(), t);
                        Toast.makeText(FormRealisasiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_foto1:
                TakePicture();
                break;
            case R.id.btn_foto2:
                TakePicture2();
                break;
            case R.id.btn_foto3:
                TakePicture3();
                break;
            case R.id.btn_foto4:
                TakePicture4();
                break;
        }
    }


    private void TakePicture() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {


            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1001);
            }

        } else {
            // Check Camera
            if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
                startActivityForResult(cameraIntent, 1888);
            } else {
                Toast.makeText(getApplication(), "Camera not supported", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void TakePicture2() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {


            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1002);
            }

        } else {
            // Check Camera
            if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                Intent cameraIntent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent2.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri2);
                startActivityForResult(cameraIntent2, 1889);
            } else {
                Toast.makeText(getApplication(), "Camera not supported", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void TakePicture3() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {


            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1003);
            }

        } else {
            // Check Camera
            if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                Intent cameraIntent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent3.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri3);
                startActivityForResult(cameraIntent3, 1890);
            } else {
                Toast.makeText(getApplication(), "Camera not supported", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void TakePicture4() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {


            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1004);
            }

        } else {
            // Check Camera
            if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                Intent cameraIntent4 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent4.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri4);
                startActivityForResult(cameraIntent4, 1891);
            } else {
                Toast.makeText(getApplication(), "Camera not supported", Toast.LENGTH_LONG).show();
            }
        }
    }

    private Uri saveImageFile(Bitmap bitmap_data, String filename) {
        Uri imageUri = new ImageSaver(getApplicationContext())
                .setFileName(filename)
                .setDirectoryName("Tutup Dinas")
                .setResolution(1024, 768)
                .save(bitmap_data);
        return imageUri;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 1001) {
                if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
                    startActivityForResult(cameraIntent, 1888);
                } else {
                    Toast.makeText(getApplication(), "Camera not supported", Toast.LENGTH_LONG).show();
                }
            }
            if (requestCode == 1002) {
                if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    Intent cameraIntent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent2.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri2);
                    startActivityForResult(cameraIntent2, 1889);
                } else {
                    Toast.makeText(getApplication(), "Camera not supported", Toast.LENGTH_LONG).show();
                }
            }
            if (requestCode == 1003) {
                if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    Intent cameraIntent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent3.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri3);
                    startActivityForResult(cameraIntent3, 1890);
                } else {
                    Toast.makeText(getApplication(), "Camera not supported", Toast.LENGTH_LONG).show();
                }
            }

            if (requestCode == 1004) {
                if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    Intent cameraIntent4 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent4.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri3);
                    startActivityForResult(cameraIntent4, 1891);
                } else {
                    Toast.makeText(getApplication(), "Camera not supported", Toast.LENGTH_LONG).show();
                }
            }

            File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/");
            boolean success = true;
            if (!folder.exists()) {
                Toast.makeText(FormRealisasiActivity.this, "Directory Does Not Exist, Create It", Toast.LENGTH_SHORT).show();
                success = folder.mkdir();
            }
            if (success) {
                Toast.makeText(FormRealisasiActivity.this, "Directory Created", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(MainActivity.this, "Failed - Error", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
//                Log.v(TAG,"Permission is granted2");
                filename = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "1.jpg";
                filename2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "2.jpg";
                filename3 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "3.jpg";
                filename4 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "4.jpg";

                //Environment.getExternalStorageDirectory().getPath() + "/folder/testfile.jpg";
                capturedImageUri = Uri.fromFile(new File(filename));
                capturedImageUri2 = Uri.fromFile(new File(filename2));
                capturedImageUri3 = Uri.fromFile(new File(filename3));
                capturedImageUri4 = Uri.fromFile(new File(filename4));
                return true;
            } else {

//                Log.v(TAG,"Permission is revoked2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
//            Log.v(TAG,"Permission is granted2");
            filename = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "1.jpg";
            filename2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "2.jpg";
            filename3 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "3.jpg";
            filename4 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "4.jpg";

            //Environment.getExternalStorageDirectory().getPath() + "/folder/testfile.jpg";
            capturedImageUri = Uri.fromFile(new File(filename));
            capturedImageUri2 = Uri.fromFile(new File(filename2));
            capturedImageUri3 = Uri.fromFile(new File(filename3));
            capturedImageUri4 = Uri.fromFile(new File(filename4));
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1888) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), capturedImageUri);
            } catch (Exception e) {
                bitmap = null;
//                Log.e(TAG,"Gagal ambil data gambar");
            }
            Bitmap c = BitmapFactory.decodeFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "1.jpg");
            ivFotohasil1.setImageBitmap(c);
            saveImageFile(bitmap, tvbatd.getText().toString() + "1.jpg");
            imagePath1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "1.jpg");

        } else if (requestCode == 1889) {
            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), capturedImageUri2);
            } catch (Exception e) {
                bitmap2 = null;
//                Log.e(TAG,"Gagal ambil data gambar");
            }
            Bitmap d = BitmapFactory.decodeFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "2.jpg");
            ivFotohasil2.setImageBitmap(d);
            saveImageFile(bitmap2, tvbatd.getText().toString() + "2.jpg");
            imagePath2 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "2.jpg");

        } else if (requestCode == 1890) {
            try {
                bitmap3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), capturedImageUri3);
            } catch (Exception e) {
                bitmap3 = null;
//                Log.e(TAG,"Gagal ambil data gambar");
            }
            Bitmap e = BitmapFactory.decodeFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "3.jpg");
            ivFotohasil3.setImageBitmap(e);
            saveImageFile(bitmap3, tvbatd.getText().toString() + "3.jpg");
            imagePath3 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "3.jpg");

        } else if (requestCode == 1891) {
            try {
                bitmap4 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), capturedImageUri4);
            } catch (Exception e) {
                bitmap4 = null;
//                Log.e(TAG,"Gagal ambil data gambar");
            }
            Bitmap f = BitmapFactory.decodeFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "4.jpg");
            ivFotohasil4.setImageBitmap(f);
            saveImageFile(bitmap4, tvbatd.getText().toString() + "4.jpg");
            imagePath4 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/" + tvbatd.getText().toString() + "4.jpg");
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
}
