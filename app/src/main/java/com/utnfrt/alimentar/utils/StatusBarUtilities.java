package com.utnfrt.alimentar.utils;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class StatusBarUtilities {

    public static void cambiarColor(AppCompatActivity appCompatActivity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int myColor = Color.parseColor("#FFFFFF");
            appCompatActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            appCompatActivity.getWindow().setStatusBarColor(myColor);
        } else{
            appCompatActivity.getWindow().setStatusBarColor(Color.BLACK);
        }
    }
}
