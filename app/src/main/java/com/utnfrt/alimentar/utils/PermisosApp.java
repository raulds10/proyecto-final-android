package com.utnfrt.alimentar.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;

public class PermisosApp {

    public static final int PERMISSION_REQUEST = 10;

    //private static String permisos[] = {Manifest.permission.CAMERA,Manifest.permission.READ_CONTACTS,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static void permissionManagerFragment(Fragment fragment, int version, Context context, String[] permisos){
        if (version>= Build.VERSION_CODES.M){
            if (checkPermission(context,permisos)){
                fragment.requestPermissions(permisos,PERMISSION_REQUEST);
            }else {
                fragment.requestPermissions(permisos,PERMISSION_REQUEST);
            }
        }
    }

    public static void permissionManagerActivity(Activity activity, int version, Context context, String[] permisos){
        if (version>= Build.VERSION_CODES.M){
            if (checkPermission(context,permisos)){
                ActivityCompat.requestPermissions(activity,permisos,PERMISSION_REQUEST);
            }else {
                ActivityCompat.requestPermissions(activity,permisos,PERMISSION_REQUEST);
            }
        }
    }

    private static boolean checkPermission(Context context, String[] permisos){
        boolean allSuccess = true;
        for (int i =0; i < permisos.length;i++){
            if (PermissionChecker.checkCallingOrSelfPermission(context,permisos[i]) == PermissionChecker.PERMISSION_DENIED){
                allSuccess = false;
            }
        }
        return allSuccess;
    }

    public static void mostrarDialogoPermisos(final Activity activity, String titulo, String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final CharSequence[] opciones = {"Ir a Ajustes","Cancelar"};
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.setPositiveButton("Ir a Ajustes", (dialog, which) -> {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
            intent.setData(uri);
            activity.startActivity(intent);
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());
        builder.setCancelable(true);
        builder.create();
        builder.show();
    }
}
