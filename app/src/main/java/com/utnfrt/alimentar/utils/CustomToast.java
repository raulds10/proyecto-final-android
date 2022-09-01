package com.utnfrt.alimentar.utils;

import android.content.Context;
import android.widget.Toast;

public class CustomToast {

    static Toast mToast;

    public static void verToastCorto(Context context, String message){
        if(mToast != null) mToast.cancel();
        mToast = Toast.makeText(context,message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void verToastLargo(Context context, String message){
        if(mToast != null) mToast.cancel();
        mToast = Toast.makeText(context,message, Toast.LENGTH_LONG);
        mToast.show();
    }
}
