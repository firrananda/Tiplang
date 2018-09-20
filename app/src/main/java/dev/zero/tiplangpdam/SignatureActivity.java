package dev.zero.tiplangpdam;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;

public class SignatureActivity extends AppCompatActivity {

    @BindView(R.id.photoEditorView)
    PhotoEditorView photoEditorView;
    @BindView(R.id.btnReset)
    Button btnReset;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.containerButton)
    LinearLayout containerButton;

    private PhotoEditor photoEditor;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        ButterKnife.bind(this);

        photoEditor = new PhotoEditor.Builder(this, photoEditorView).build();

        photoEditor.setBrushDrawingMode(true);
        photoEditor.setBrushColor(Color.BLACK);
        photoEditor.setBrushSize(13f);

    }

    @OnClick({R.id.btnReset, R.id.btnSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnReset:
                photoEditor.clearAllViews();
                break;
            case R.id.btnSave:
                saveImage();
                break;
        }
    }

    @SuppressLint("MissingPermission")
    private void saveImage() {
        if (requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            showLoading("Wait a moment...");
            File file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + ""
                    + System.currentTimeMillis() + ".png");
            try {
                file.createNewFile();
                photoEditor.saveAsFile(file.getAbsolutePath(), new PhotoEditor.OnSaveListener() {
                    @Override
                    public void onSuccess(@NonNull String imagePath) {
                        progressDialog.dismiss();
                        Intent intent = new Intent();
                        intent.putExtra("sign", imagePath);
                        setResult(RESULT_OK, intent);
                        finish();
                        //photoEditorView.getSource().setImageURI(Uri.fromFile(new File(imagePath)));
                    }

                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        progressDialog.dismiss();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean requestPermission(String permission) {
        boolean isGranted = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
        if (!isGranted) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{permission},
                    52);
        }
        return isGranted;
    }

    protected void showLoading(@NonNull String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

}

