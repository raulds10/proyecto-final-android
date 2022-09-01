package com.utnfrt.alimentar.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtilities {

    public static String dosDigitos(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }

    public static String obtenerFechaParaHacerRequest(String dateUnformatted) {
        String date;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            calendar.setTime(mdFormat.parse(dateUnformatted));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int day_of_month = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        String stringDay;
        String stringMonth;

        if (day_of_month < 10){
            stringDay = "0" + day_of_month;
        }else{
            stringDay = "" + day_of_month;
        }

        if (month < 10){
            stringMonth = "0" + month;
        }else{
            stringMonth = "" + month;
        }


        date = year+"-"+ stringMonth+ "-"+ stringDay;

        return date;
    }

    public static String obtenerFechaDeUnRequest(String fecha){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(fecha);
            String formattedTime = output.format(d);
            return formattedTime;
        } catch (ParseException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public static String obtenerFechaDeUnRequest2(String fecha){

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date d;
        try {
            d = inputFormat.parse(fecha);
            String formattedTime = outputFormat.format(d);
            return formattedTime;
        } catch (ParseException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public static String ObtenerFechaDeGuionASlash(String fecha){
        try {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = inputFormat.parse(fecha);
            String outputDateStr = outputFormat.format(date);
            return outputDateStr;
        }catch (ParseException e) {
            e.printStackTrace();
            return e.toString();
        }
    }
}
