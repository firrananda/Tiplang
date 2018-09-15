package dev.zero.tiplangpdam.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ImageSaver {

    public static File convertBitmapToFile(Context context, Bitmap bitmap, String name) {
        File filesDir = context.getFilesDir();
        File imageFile = new File(filesDir, name + ".jpg");

        OutputStream os;
        try {
            os = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
            return imageFile;
        } catch (Exception e) {
            Log.e(context.getClass().getSimpleName(), "Error writing bitmap", e);
            return null;
        }
    }

    public static Bitmap createImageWithBarcode(File imageFile, String batd) {
        Bitmap bitmapBarcode = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(Calendar.getInstance().getTime());
        String text = batd + dateTime;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,100,100);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmapBarcode = barcodeEncoder.createBitmap(bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        Bitmap bitmapImage = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        bitmapImage = Bitmap.createScaledBitmap(bitmapImage, 786, 1024, true);
        Bitmap bitmapImageWithBarcode = Bitmap.createBitmap(bitmapImage.getWidth(), bitmapImage.getHeight(), bitmapImage.getConfig());
        Canvas canvas = new Canvas(bitmapImageWithBarcode);
        canvas.drawBitmap(bitmapImage, new Matrix(), null);
        canvas.drawBitmap(bitmapBarcode, 686, 924, null);
        return bitmapImageWithBarcode;
    }
}
