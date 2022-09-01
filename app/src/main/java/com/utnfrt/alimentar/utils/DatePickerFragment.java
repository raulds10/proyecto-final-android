package com.utnfrt.alimentar.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;
    private int minAño;
    private int maxAño;

    public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener, int minAño, int maxAño) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setListener(listener,minAño,maxAño);
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener, int minAño, int maxAño) {
        this.listener = listener;
        this.minAño = minAño;
        this.maxAño = maxAño;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), listener, year, month, day);

        c.set(Calendar.YEAR, year - minAño);
        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
        c.set(Calendar.YEAR, year - maxAño);
        datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());

        return datePickerDialog;
    }
}