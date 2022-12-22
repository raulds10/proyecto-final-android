package com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.reporte;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.DashPathEffect;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.utnfrt.alimentar.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReporteHortalizaActivity extends AppCompatActivity {
    @BindView(R.id.barcharReporte)
    BarChart chartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reposte_hortaliza);

        ButterKnife.bind(this);


        chartView.setTouchEnabled(true);
        chartView.setPinchZoom(true);

        Description description = new Description();
        description.setText("Week");
        description.setTextSize(15f);
        chartView.setDescription(description);
        ArrayList<Double> datas = new ArrayList<>();
        datas.add(15.0);
        datas.add(10.0);
        datas.add(9.0);
        datas.add(18.00);
        datas.add(12.0);
        datas.add(13.0);


        renderData(datas);
    }

    public void renderData(List<Double> allAmounts) {

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Tomate");
        xAxisLabel.add("Lechuga");
        xAxisLabel.add("Lenteja");
        xAxisLabel.add("Carne");
        xAxisLabel.add("Pimiento");
        xAxisLabel.add("Papa");

        XAxis xAxis = chartView.getXAxis();
        XAxis.XAxisPosition position = XAxis.XAxisPosition.BOTTOM;
        xAxis.setPosition(position);
        xAxis.enableGridDashedLine(2f, 10f, 0f);
        xAxis.setAxisMinimum(0f);
        xAxis.setLabelCount(xAxisLabel.size() + 1, true);
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(7f);
        xAxis.setLabelRotationAngle(315f);
        xAxis.setValueFormatter(new ClaimsXAxisValueFormatter(xAxisLabel, this));
        xAxis.setCenterAxisLabels(true);


        xAxis.setDrawLimitLinesBehindData(true);


        YAxis leftAxis = chartView.getAxisLeft();
        leftAxis.removeAllLimitLines();

        leftAxis.setAxisMaximum(allAmounts.size() + 30f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(5f, 5f, 0f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawLimitLinesBehindData(false);

        leftAxis.setValueFormatter(new ClaimsYAxisValueFormatter(" Dias"));

        chartView.getDescription().setEnabled(true);
        Description description = new Description();
        // description.setText(UISetters.getFullMonthName());//commented for weekly reporting
        description.setText("Fecha");
        description.setTextSize(10f);
        chartView.getDescription().setPosition(0f, 0f);
        chartView.setDescription(description);
        chartView.getAxisRight().setEnabled(false);
        //setData()-- allAmounts is data to display;
        setDataForWeeksWise(allAmounts);
    }


    private void setDataForWeeksWise(List<Double> amounts) {

        ArrayList<BarEntry> values = new ArrayList<>();
        for (int i = 0; i < amounts.size(); i++) {
            values.add(new BarEntry(i, amounts.get(i).floatValue()));
        }
        BarDataSet set1;
        if (chartView.getData() != null &&
                chartView.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chartView.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chartView.getData().notifyDataChanged();
            chartView.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "");
            set1.setColor(getResources().getColor(R.color.colorButtonFill));
            set1.setValueTextSize(10f);
            set1.setFormLineWidth(5f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(5.f);
            set1.setDrawValues(true);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);

            chartView.setData(data);
        }
    }

}