package ic.uff.br.computacaoubiqua.activities;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import ic.uff.br.computacaoubiqua.R;
import ic.uff.br.computacaoubiqua.adapters.CustomVisitAdapter;
import ic.uff.br.computacaoubiqua.database.AppDatabase;
import ic.uff.br.computacaoubiqua.database.visit.Visit;
import ic.uff.br.computacaoubiqua.database.visit.VisitDao;
import ic.uff.br.computacaoubiqua.services.BluetoothService;
import me.ithebk.barchart.BarChart;
import me.ithebk.barchart.BarChartModel;

public class UltimasVisitas extends AppCompatActivity {

    private CustomVisitAdapter adapter;
    private ListView listView;
    List<Visit> listVisits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimas_visitas);


    }



    @Override
    public void onResume(){
        super.onResume();

        listView = (ListView) findViewById(R.id.list_ultimas_visitas);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Get Data
                listVisits = AppDatabase.getInstance(UltimasVisitas.this).visitDao().getAll();
            }
        });




        listView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        adapter = new CustomVisitAdapter(UltimasVisitas.this, listVisits);
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);
        adapter.notifyDataSetChanged();
        registerForContextMenu(listView);

        ScrollView scrollViewLayout = (ScrollView) findViewById(R.id.scrool_layout_ultimas_visitas);
        scrollViewLayout.smoothScrollTo(0, 0);


    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}
