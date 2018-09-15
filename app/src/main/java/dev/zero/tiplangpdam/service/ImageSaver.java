package dev.zero.tiplangpdam.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ImageSaver {
    private String directoryName = "Tutup Dinas";
    private String fileName = "image.jpg";
    private static String contentqr;
    private Context context;
    private boolean external = true;
    private Uri savedImageURI = null;
    private boolean adjustResolution = false;
    private int maxWidth = 1024, maxHeight=768; //default value

    public ImageSaver(Context context) {
        this.context = context;
    }

    public ImageSaver setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public ImageSaver setExternal(boolean external) {
        this.external = external;
        return this;
    }

    public ImageSaver setContentQR (String contentqr){
        this.contentqr=contentqr;
        return this;
    }

    public ImageSaver setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
        return this;
    }

    public ImageSaver setResolution(int maxWidth, int maxHeight){
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.adjustResolution = true;
        return this;
    }

    public static String Contentqr(){
        String content=contentqr;
        return content;
    }

    private static Bitmap resize(Bitmap image, int maxWidth, int maxHeight) {

        int w, h;
        Matrix matrix;
        RectF r;
        Bitmap bitmap3=null;
        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;

            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > ratioBitmap) {
                finalWidth = (int) ((float)maxHeight * ratioBitmap);
            } else {
                finalHeight = (int) ((float)maxWidth / ratioBitmap);
            }
            image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = sdf.format(Calendar.getInstance().getTime());
            Bitmap test = image.copy(Bitmap.Config.ARGB_8888, true);
            Canvas canvas = new Canvas(test);
            canvas.drawBitmap(test, 0, 0, null);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.RED);
            paint.setTextSize(28);
            //canvas.drawText(dateTime, 16f, 115f, paint);
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(Contentqr()+ dateTime, BarcodeFormat.QR_CODE, 100, 100);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                bitmap3 = barcodeEncoder.createBitmap(bitMatrix);
            } catch (WriterException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            h = 1024;
//            w = 1280;
            float scale = 0.09f;

            matrix = new Matrix();
            //matrix.postScale(scale, scale);
            r = new RectF(0, 0, bitmap3.getWidth(), bitmap3.getHeight());
            matrix.postTranslate(finalWidth - r.width(), finalHeight - r.height());
            //matrix.postTranslate(w - w * scale, h - h * scale);
            canvas.drawText(dateTime, 0, (0+paint.getTextSize()), paint);
            canvas.drawBitmap(bitmap3,matrix,paint);
            bitmap3.recycle();
            return test;
        } else {
            return image;
        }
    }

    public Uri save(Bitmap bitmapImage) {
        FileOutputStream fileOutputStream = null;
        try {
            File file = createFile();
            this.savedImageURI = Uri.parse(file.getAbsolutePath());
            Log.d("GAMBAR", "lokasi FILE="+file.getAbsolutePath());
            fileOutputStream = new FileOutputStream(file);
            if(this.adjustResolution) {
                bitmapImage = resize(bitmapImage, this.maxWidth, this.maxHeight);
            }
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 75, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.savedImageURI;
    }

    @NonNull
    private File createFile() {
        File directory;
        if(external){
            directory = getAlbumStorageDir(directoryName);
        }
        else {
            directory = context.getDir(directoryName, Context.MODE_PRIVATE);
        }
        if(!directory.exists()){
            Log.e("ImageSaver"," " + directory+ " (Directory Not exist)");

            if(directory.mkdirs()){
                Log.d("ImageSaver","Sukses membuat directory " + directory);
            }
            else{
                Log.e("ImageSaver","Error creating directory " + directory);
            }
        }

        return new File(directory, fileName);
    }

    private File getAlbumStorageDir(String albumName) {
        return new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public Bitmap load() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(createFile());
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
