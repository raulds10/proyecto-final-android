package com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.reporte;

import android.content.Context;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;
import java.util.List;

public class ClaimsXAxisValueFormatter extends ValueFormatter {
    Context context;
    List<String> datesList;

    public ClaimsXAxisValueFormatter(List<String> arrayOfDates, Context context) {
        this.datesList = arrayOfDates;
        this.context = context;
    }

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        return datesList.get((int) value);
    }
}
