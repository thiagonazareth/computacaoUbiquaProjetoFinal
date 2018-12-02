package ic.uff.br.computacaoubiqua.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import ic.uff.br.computacaoubiqua.R;
import me.ithebk.barchart.BarChart;
import me.ithebk.barchart.BarChartModel;

public class Relatorio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relatorio);

        BarChart barChart = (BarChart) findViewById(R.id.bar_chart_vertical);
        barChart.setBarMaxValue(1000);

        BarChartModel barChartModel = new BarChartModel();
        barChartModel.setBarValue(50);
        barChartModel.setBarColor(Color.parseColor("#9C27B0"));
        barChartModel.setBarTag("Teste"); //You can set your own tag to bar model
        barChartModel.setBarText("Teste");

        BarChartModel barChartModel1 = new BarChartModel();
        barChartModel1.setBarValue(100);
        barChartModel1.setBarColor(Color.parseColor("#52B9D4"));
        barChartModel1.setBarTag("Teste1"); //You can set your own tag to bar model
        barChartModel1.setBarText("Teste1");

        barChart.addBar(barChartModel);
        barChart.addBar(barChartModel1);

        //Add mutliple bar at once as list;
        List<BarChartModel> barChartModelList = new ArrayList<>();

        //populate bar array list and add to barchart as a list.
        barChart.addBar(barChartModelList);
    }

    @Override
    public void onResume(){
        super.onResume();


    }

}
