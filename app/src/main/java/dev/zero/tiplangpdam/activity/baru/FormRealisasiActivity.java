package dev.zero.tiplangpdam.activity.baru;

import android.Manifest;
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
    @BindView(R.id.edt_tgl_ba)
    EditText edtTglBa;
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
    ArrayList<Pelanggaran> listPelanggaran;
    String pelanggaranId = "";
    String BATDId = "";
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

    //Uri capturedImageUri;
    //Uri capturedImageUri;
    static Uri capturedImageUri=null;
    static Uri capturedImageUri2=null;
    static Uri capturedImageUri3=null;
    static Uri capturedImageUri4= null;
    //static boolean imageLoaded = false;
//    File file=null;File file2 = null;
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
    String nolang="12345_.jpg";
    Bitmap bitmap,bitmap2,bitmap3,bitmap4;
    int w, h;
    Matrix matrix;
    RectF r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_realisasi);
        ButterKnife.bind(this);
        String batd = getIntent().getStringExtra("nomor BATD");
        BATD_ID = getIntent().getStringExtra("BATD_ID");
        String SPK = getIntent().getStringExtra("nomor SPK");
        tvbatd.setText(batd);

        idPel = nolang;

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        isWriteStoragePermissionGranted();

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

    @OnClick({R.id.btn_proses, R.id.btn_kirim, R.id.btn_foto1, R.id.btn_foto2, R.id.btn_foto3, R.id.btn_foto4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_proses:
                HashMap<String, String> paramsSave = new HashMap<>();
                paramsSave.put("kondisi_stan_meter", edtKondisi.getText().toString());
                paramsSave.put("catatan_stan_meter", edtCatatan.getText().toString());
                paramsSave.put("tanggal_angkat", edtTglAngkat.getText().toString());
                paramsSave.put("no_meter", edtNoMeter.getText().toString());
                paramsSave.put("ukuran_meter", edtUkuranMeter.getText().toString());
                paramsSave.put("angka_angkat", edtAngkaAngkat.getText().toString());
                paramsSave.put("merk_meter", edtMerkMeteran.getText().toString());
                paramsSave.put("batd_id", BATD_ID);
                paramsSave.put("pelanggaran", spnPelanggaran.getSelectedItem().toString());

                FormDataSaveHelper.addDataString(paramsSave);
                finish();
                break;
            case R.id.btn_kirim:
                HashMap<String, RequestBody> params = new HashMap<>();
                params.put("kondisi_stan_meter", RequestBody.create(MediaType.parse("text/plain"),edtKondisi.getText().toString()));
                params.put("catatan_stan_meter", RequestBody.create(MediaType.parse("text/plain"),edtCatatan.getText().toString()));
                params.put("tanggal_angkat", RequestBody.create(MediaType.parse("text/plain"),edtTglAngkat.getText().toString()));
                params.put("no_meter", RequestBody.create(MediaType.parse("text/plain"),edtNoMeter.getText().toString()));
                params.put("ukuran_meter", RequestBody.create(MediaType.parse("text/plain"),edtUkuranMeter.getText().toString()));
                params.put("angka_angkat", RequestBody.create(MediaType.parse("text/plain"),edtAngkaAngkat.getText().toString()));
                params.put("merk_meter", RequestBody.create(MediaType.parse("text/plain"),edtMerkMeteran.getText().toString()));
                params.put("batd_id", RequestBody.create(MediaType.parse("text/plain"),BATD_ID));
                params.put("pelanggaran", RequestBody.create(MediaType.parse("text/plain"),spnPelanggaran.getSelectedItem().toString()));

                RequestBody file1 = RequestBody.create(MediaType.parse("image/*"), imagePath1);
                RequestBody file2 = RequestBody.create(MediaType.parse("image/*"), imagePath2);
                RequestBody file3 = RequestBody.create(MediaType.parse("image/*"), imagePath3);
                RequestBody file4 = RequestBody.create(MediaType.parse("image/*"), imagePath4);

                ArrayList<MultipartBody.Part> parts = new ArrayList<>();
                parts.add(MultipartBody.Part.createFormData("foto_realisasi[]", imagePath1.getName(), file1));
                parts.add(MultipartBody.Part.createFormData("foto_realisasi[]", imagePath2.getName(), file2));
                parts.add(MultipartBody.Part.createFormData("foto_realisasi[]", imagePath3.getName(), file3));
                parts.add(MultipartBody.Part.createFormData("foto_realisasi[]", imagePath4.getName(), file4));

                ApiService.service_post.postForm(params, parts).enqueue(new Callback<RealisasiResponse>() {
                    @Override
                    public void onResponse(Call<RealisasiResponse> call, Response<RealisasiResponse> response) {
                        if (response.body().getCode() == 302) {
                            Toast.makeText(FormRealisasiActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(FormRealisasiActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RealisasiResponse> call, Throwable t) {
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

        }
        else{
            // Check Camera
            if (this.getPackageManager().hasSystemFeature( PackageManager.FEATURE_CAMERA)) {
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

        }
        else{
            // Check Camera
            if (this.getPackageManager().hasSystemFeature( PackageManager.FEATURE_CAMERA)) {
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

        }
        else{
            // Check Camera
            if (this.getPackageManager().hasSystemFeature( PackageManager.FEATURE_CAMERA)) {
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

        }
        else{
            // Check Camera
            if (this.getPackageManager().hasSystemFeature( PackageManager.FEATURE_CAMERA)) {
                Intent cameraIntent4 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent4.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri4);
                startActivityForResult(cameraIntent4, 1891);
            } else {
                Toast.makeText(getApplication(), "Camera not supported", Toast.LENGTH_LONG).show();
            }
        }
    }

    private Uri saveImageFile(Bitmap bitmap_data, String filename){
        Uri imageUri = new ImageSaver(getApplicationContext())
                .setFileName(filename)
                .setDirectoryName("Tutup Dinas")
                .setResolution(1024,768)
                .save(bitmap_data);
        return imageUri;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED){
            if(requestCode == 1001) {
                if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
                    startActivityForResult(cameraIntent, 1888);
                } else {
                    Toast.makeText(getApplication(), "Camera not supported", Toast.LENGTH_LONG).show();
                }
            }
            if(requestCode == 1002) {
                if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    Intent cameraIntent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent2.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri2);
                    startActivityForResult(cameraIntent2, 1889);
                } else {
                    Toast.makeText(getApplication(), "Camera not supported", Toast.LENGTH_LONG).show();
                }
            }
            if(requestCode == 1003) {
                if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    Intent cameraIntent3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent3.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri3);
                    startActivityForResult(cameraIntent3, 1890);
                } else {
                    Toast.makeText(getApplication(), "Camera not supported", Toast.LENGTH_LONG).show();
                }
            }

            if(requestCode == 1004) {
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
        }else{
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
//                Log.v(TAG,"Permission is granted2");
                filename = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/testfile.jpg";
                filename2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/testfile2.jpg";
                filename3 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/testfile3.jpg";
                filename4 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/testfile4.jpg";

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
        }
        else { //permission is automatically granted on sdk<23 upon installation
//            Log.v(TAG,"Permission is granted2");
            filename = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/testfile.jpg";
            filename2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/testfile2.jpg";
            filename3 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/testfile3.jpg";
            filename4 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/testfile4.jpg";

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
        if(requestCode==1888) {
//            Bitmap bitmap=null;
//            Bitmap bitmap3=null;
//            try{
//                bitmap = (Bitmap)MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),  capturedImageUri);
//                if(bitmap!=null){
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    String dateTime = sdf.format(Calendar.getInstance().getTime());
//                    //iv2.setImageResource(R.drawable.ic_ok);
//                    Bitmap c= BitmapFactory.decodeFile(imagePath+"bonc/12345_.jpg");
//                    //Bitmap c = BitmapFactory.decodeFile("/sdcard/foto/" + "12345_.jpg");
//                    Bitmap out2 = Bitmap.createScaledBitmap(c, 1280, 1024, false); //Bitmap out = Bitmap.createScaledBitmap(b, 320, 240, false);
//                    Bitmap test = out2.copy(Bitmap.Config.ARGB_8888, true);
//                    Canvas canvas = new Canvas(test);
//                    canvas.drawBitmap(test, 0, 0, null);
//
//                    h = 1024;
//                    w = 1280;
//                    float scale = 0.09f;
//                    matrix = new Matrix();
//                    //matrix.postScale(scale, scale);
//                    r = new RectF(0, 0, bitmap3.getWidth(), bitmap3.getHeight());
//                    matrix.postTranslate(w - r.width(), h - r.height());
//                    //matrix.postTranslate(w - w * scale, h - h * scale);
//
//                    Paint paint = new Paint();
//                    paint.setAntiAlias(true);
//                    paint.setColor(Color.WHITE);
//                    paint.setTextSize(30);
//                    //canvas.drawText(dateTime, 16f, 115f, paint);
//                    //canvas.drawBitmap(bitmap3, matrix, paint);
//                    canvas.drawText(dateTime, 0, (0+paint.getTextSize()), paint);
//                    bitmap3.recycle();
//                    File file4 = new File(imagePath+"/12345_.jpg");
//                    //File file4 = new File("/sdcard/foto/" + "12345_.jpg");
//                    FileOutputStream fOut2;
//                    try {
//                        fOut2 = new FileOutputStream(file4);
//                        test.compress(Bitmap.CompressFormat.JPEG, 25, fOut2);
//                        fOut2.flush();
//                        fOut2.close();
//                        c.recycle();
//                    } catch (Exception e) { // TODO
//                    }
//                    //    BitmapFactory.Options options = new BitmapFactory.Options();
//                    //    options.inSampleSize = 4;
//                    //    Bitmap bm = BitmapFactory.decodeFile("/sdcard/foto/"+getIntent().getExtras().getString("nolang") +".jpg", options);
//                    //iv2.setImageBitmap(test);
//                    //imageView.setImageResource(R.drawable.ic_ok);
//                    //    BitmapFactory.Options options = new BitmapFactory.Options();
//                    //    options.inSampleSize = 4;
//                    //    Bitmap bm1 = BitmapFactory.decodeFile("/sdcard/foto/"+getIntent().getExtras().getString("nolang") +"_.jpg", options);
//                    //   iv.setImageBitmap(bm1);
//                } else
//                    Toast.makeText(this, "gambar kosong !", Toast.LENGTH_SHORT).show();
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                Toast.makeText(this, "file ga ada!", Toast.LENGTH_SHORT).show();
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                Toast.makeText(this, "Ulangi 2!", Toast.LENGTH_SHORT).show();
//            }
//            finally {
//            }

            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), capturedImageUri);
            }
            catch (Exception e){
                bitmap = null;
//                Log.e(TAG,"Gagal ambil data gambar");
            }
            Bitmap c= BitmapFactory.decodeFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/testfile.jpg");
            ivFotohasil1.setImageBitmap(c);
            saveImageFile(bitmap, "testfile.jpg");
        }
        else if(requestCode==1889){
            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), capturedImageUri2);
            }
            catch (Exception e){
                bitmap2 = null;
//                Log.e(TAG,"Gagal ambil data gambar");
            }
            Bitmap d= BitmapFactory.decodeFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/testfile2.jpg");
            ivFotohasil2.setImageBitmap(d);
            saveImageFile(bitmap2, "testfile2.jpg");
        }
        else if(requestCode==1890){
            try {
                bitmap3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), capturedImageUri3);
            }
            catch (Exception e){
                bitmap3 = null;
//                Log.e(TAG,"Gagal ambil data gambar");
            }
            Bitmap e= BitmapFactory.decodeFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/testfile3.jpg");
            ivFotohasil3.setImageBitmap(e);
            saveImageFile(bitmap3, "testfile3.jpg");
        }
        else if(requestCode==1891){
            try {
                bitmap4 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), capturedImageUri4);
            }
            catch (Exception e){
                bitmap4 = null;
//                Log.e(TAG,"Gagal ambil data gambar");
            }
            Bitmap f= BitmapFactory.decodeFile(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Tutup Dinas/testfile4.jpg");
            ivFotohasil4.setImageBitmap(f);
            saveImageFile(bitmap4, "testfile4.jpg");
        }
    }
}
