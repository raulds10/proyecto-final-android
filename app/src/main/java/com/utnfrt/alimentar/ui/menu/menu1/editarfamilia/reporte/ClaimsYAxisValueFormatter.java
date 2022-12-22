package com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.reporte;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class ClaimsYAxisValueFormatter  extends ValueFormatter {
    String prefix;

    public ClaimsYAxisValueFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        return value + prefix;
    }
}
