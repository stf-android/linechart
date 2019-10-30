package detongs.hbqianze.him.linechart;


import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import detongs.hbqianze.him.linechart.chart.MyValueFormatter;
import detongs.hbqianze.him.linechart.chart.ValueFormatter;

public class MainActivity extends AppCompatActivity {



    private BarChart chart;
    private TextView te_cache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        chart = findViewById(R.id.chart1);
        te_cache = findViewById(R.id.te_cache);


        chart.getDescription().setEnabled(false);

        //设置最大值条目，超出之后不会有值
        chart.setMaxVisibleValueCount(60);

        //分别在x轴和y轴上进行缩放
        chart.setPinchZoom(true);
        //设置剩余统计图的阴影
        chart.setDrawBarShadow(false);
        //设置网格布局
        chart.setDrawGridBackground(true);
        //通过自定义一个x轴标签来实现2,015 有分割符符bug
        ValueFormatter custom = new MyValueFormatter(" ");
        //获取x轴线
        XAxis xAxis = chart.getXAxis();

        //设置x轴的显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置网格布局
        xAxis.setDrawGridLines(true);
        //图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setAvoidFirstLastClipping(false);
        //绘制标签  指x轴上的对应数值 默认true
        xAxis.setDrawLabels(true);
        xAxis.setValueFormatter(custom);
        //缩放后x 轴数据重叠问题
        xAxis.setGranularityEnabled(true);
        //获取右边y标签
        YAxis axisRight = chart.getAxisRight();
        axisRight.setStartAtZero(true);
        //获取左边y轴的标签
        YAxis axisLeft = chart.getAxisLeft();
        //设置Y轴数值 从零开始
        axisLeft.setStartAtZero(true);

        chart.getAxisLeft().setDrawGridLines(false);
        //设置动画时间
         chart.animateXY(600,600);

         chart.getLegend().setEnabled(true);

        getData();
        //设置柱形统计图上的值
        chart.getData().setValueTextSize(10);
        for (IDataSet set : chart.getData().getDataSets()){
            set.setDrawValues(!set.isDrawValuesEnabled());
        }



    }



    public void getData(){
    ArrayList<BarEntry> values = new ArrayList<>();
        Float aFloat = Float.valueOf("2015");
        Log.v("xue","aFloat+++++"+aFloat);
        BarEntry barEntry = new BarEntry(aFloat,Float.valueOf("100"));
    BarEntry barEntry1 = new BarEntry(Float.valueOf("2016"),Float.valueOf("210"));
    BarEntry barEntry2 = new BarEntry(Float.valueOf("2017"),Float.valueOf("300"));
    BarEntry barEntry3 = new BarEntry(Float.valueOf("2018"),Float.valueOf("450"));
    BarEntry barEntry4 = new BarEntry(Float.valueOf("2019"),Float.valueOf("300"));
        BarEntry barEntry5 = new BarEntry(Float.valueOf("2020"),Float.valueOf("650"));
        BarEntry barEntry6 = new BarEntry(Float.valueOf("2021"),Float.valueOf("740"));
    values.add(barEntry);
    values.add(barEntry1);
    values.add(barEntry2);
    values.add(barEntry3);
    values.add(barEntry4);
    values.add(barEntry5);
        values.add(barEntry6);
    BarDataSet set1;

    if (chart.getData() != null &&
            chart.getData().getDataSetCount() > 0) {
        set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
        set1.setValues(values);
        chart.getData().notifyDataChanged();
        chart.notifyDataSetChanged();
    } else {
        set1 = new BarDataSet(values, "点折水");
        set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set1.setDrawValues(false);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);
        chart.setData(data);

        chart.setFitBars(true);
    }
        //绘制图表
    chart.invalidate();

}

}
