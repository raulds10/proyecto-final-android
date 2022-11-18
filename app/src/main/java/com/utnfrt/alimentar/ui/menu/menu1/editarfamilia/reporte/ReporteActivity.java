package com.utnfrt.alimentar.ui.menu.menu1.editarfamilia.reporte;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.Utils;
import com.utnfrt.alimentar.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReporteActivity extends AppCompatActivity {
    @BindView(R.id.chart1)
    LineChart chartView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reporte);
        ButterKnife.bind(this);


        chartView.setTouchEnabled(true);
        chartView.setPinchZoom(true);

        Description description = new Description();
        description.setText("Week");
        description.setTextSize(15f);
        chartView.setDescription(description);
        ArrayList<Double> datas = new ArrayList<>();
        datas.add(65.0);
        datas.add(67.0);
        datas.add(68.5);
        datas.add(67.5);
        datas.add(66.5);

        renderData(datas);


//        chartView.setData(new LineData());
    }

    public void renderData(List<Double> allAmounts) {

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("15-06-2022");
        xAxisLabel.add("15-07-2022");
        xAxisLabel.add("13-08-2022");
        xAxisLabel.add("12-09-2022");
        xAxisLabel.add("15-10-2022");
        xAxisLabel.add("17-11-2022");

        XAxis xAxis = chartView.getXAxis();
        XAxis.XAxisPosition position = XAxis.XAxisPosition.BOTTOM;
        xAxis.setPosition(position);
        xAxis.enableGridDashedLine(2f, 10f, 0f);
        xAxis.setAxisMinimum(0f);
        xAxis.setLabelCount(xAxisLabel.size()+1, true);
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(7f);
        xAxis.setLabelRotationAngle(315f);
        xAxis.setValueFormatter(new ClaimsXAxisValueFormatter(xAxisLabel));

        xAxis.setCenterAxisLabels(true);


        xAxis.setDrawLimitLinesBehindData(true);




        YAxis leftAxis = chartView.getAxisLeft();
        leftAxis.removeAllLimitLines();

        leftAxis.setAxisMaximum(allAmounts.size() + 100f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawLimitLinesBehindData(false);
        //XAxis xAxis = mBarChart.getXAxis();
        leftAxis.setValueFormatter(new ClaimsYAxisValueFormatter());

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
    public class ClaimsXAxisValueFormatter extends ValueFormatter {

        List<String> datesList;

        public ClaimsXAxisValueFormatter(List<String> arrayOfDates) {
            this.datesList = arrayOfDates;
        }


        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            return datesList.get((int) value);
        }
    }



    private void setDataForWeeksWise(List<Double> amounts) {

        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0;i<amounts.size();i++){
            values.add(new Entry(i+1, amounts.get(i).floatValue()));
        }


        LineDataSet set1;
        if (chartView.getData() != null &&
                chartView.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chartView.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chartView.getData().notifyDataChanged();
            chartView.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values, "");
            set1.setDrawCircles(true);
            set1.enableDashedLine(10f, 0f, 0f);
            set1.enableDashedHighlightLine(10f, 0f, 0f);
            set1.setColor(getResources().getColor(R.color.colorBlack));
            set1.setCircleColor(getResources().getColor(R.color.colorGray));
            set1.setLineWidth(2f);//line size
            set1.setCircleRadius(5f);
            set1.setDrawCircleHole(true);
            set1.setValueTextSize(10f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(5f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(5.f);

            set1.setFillColor(Color.WHITE);
            set1.setDrawValues(true);
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            LineData data = new LineData(dataSets);

            chartView.setData(data);
        }
    }


    public class ClaimsYAxisValueFormatter extends ValueFormatter {
        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            return value + "KG";
        }

    }
}