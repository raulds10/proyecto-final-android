package com.utnfrt.alimentar.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileMediaUtilities {

    public static File getOutputMediaFile(Context ctx) throws IOException {
        // ubicacion sdcard
        File mediaStorageDir = new File(ctx.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "miNegocioApp");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                //Log.d(TAG, "Fallo creacion"+ Config.IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        //File mediaFile; //target23
        //mediaFile = new File(mediaStorageDir.getPath() + File.separator+ "m_" + timeStamp + ".jpg");//target23

        File mediaFile = File.createTempFile("m_" + timeStamp,".jpg",mediaStorageDir);

        return mediaFile;
    }

    public static File getOutputMediaFileApi22AndLower(){

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "miNegocioApp");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator+ "m_" + timeStamp + ".jpg");

        return mediaFile;
    }

}
