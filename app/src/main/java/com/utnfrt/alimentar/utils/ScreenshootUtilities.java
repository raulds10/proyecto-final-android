package com.utnfrt.alimentar.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class ScreenshootUtilities {

    public static void capturaPantallaApp(AppCompatActivity appCompatActivity) {

        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            String mPath = Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/DCIM/Camera/" + now + ".jpg";

            View v1 = appCompatActivity.getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            Toast.makeText(appCompatActivity, "Imagen guardada en la galería de fotos", Toast.LENGTH_LONG).show();
        }catch (Throwable e) {
            e.printStackTrace();
            Toast.makeText(appCompatActivity, "Error intentando guardar imagen", Toast.LENGTH_LONG).show();
        }
    }
}
