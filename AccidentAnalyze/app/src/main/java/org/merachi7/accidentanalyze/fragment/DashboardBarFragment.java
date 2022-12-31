package org.merachi7.accidentanalyze.fragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.merachi7.accidentanalyze.R;
import org.merachi7.accidentanalyze.activity.DashboardActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class DashboardBarFragment extends Fragment implements OnChartValueSelectedListener{

    SharedPreferences prefs;
    Set<String> construction;
    Set<String> process;
    Set<String> hazard;
    Set<String> hazard_position;
    Set<String> damage;

    private BarChart chart;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_dashboard_bar, container, false);

        prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());

        construction = prefs.getStringSet("construction_select", null);
        process = prefs.getStringSet("process_select", null);
        hazard = prefs.getStringSet("hazard_select", null);
        hazard_position = prefs.getStringSet("hazard_position_select", null);
        damage = prefs.getStringSet("damage_select", null);

        ArrayList<String[]> dataList = new ArrayList<String[]> (((DashboardActivity)getActivity()).dataList);

        chart = (BarChart) v.findViewById(R.id.stacked_chart);
        chart.setOnChartValueSelectedListener(this);

        chart.setPinchZoom(true);

        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(false);
        chart.setHighlightFullBarEnabled(false);
        chart.getDescription().setEnabled(false);

        // change the position of the y-labels
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setAxisMinimum(0);
        chart.getAxisRight().setEnabled(false);

        XAxis xLabels = chart.getXAxis();
        xLabels.setTextSize(8f);
        xLabels.setGranularity(1);


        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);


        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(3f);
        l.setXEntrySpace(8f);

        ArrayList<BarEntry> values = new ArrayList<>();

        HashMap<String, HashMap<String, Integer>> map = new HashMap<String, HashMap<String, Integer>>();

        String construction_list[] = {"가설공사", "건축 토공사" , "토공사", "건축물 부대공사", "금속공사", "기계설비공사", "기타", "도장공사", "말뚝공사", "목공사", "미장공사", "방수공사", "수장공사", "전기설비공사", "조적공사", "지붕 및 홈통공사", "창호 및 유리공사", "철골공사", "철근콘크리트공사", "타일 및 돌공사", "조경공사", "지정공사" };
        String hazard_list[] = {"깔림", "떨어짐", "물체에 맞음", "질식", "화상", "넘어짐", "없음", "부딪힘"};

        xLabels.setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(construction_list));

        HashMap<String,Integer> innerMap = new HashMap<>();
        innerMap.put("깔림", 1);
        innerMap.put("떨어짐", 1);
        innerMap.put("물체에 맞음", 1);
        innerMap.put("질식", 1);
        innerMap.put("화상", 1);
        innerMap.put("넘어짐", 1);
        innerMap.put("없음", 1);
        innerMap.put("부딪힘", 1);


        map.put("가설공사", (HashMap<String, Integer>) innerMap.clone());
        map.put("건축 토공사", (HashMap<String, Integer>) innerMap.clone());
        map.put("토공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("건축물 부대공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("금속공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("기계설비공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("기타", (HashMap<String,Integer>) innerMap.clone());
        map.put("도장공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("말뚝공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("목공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("미장공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("방수공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("수장공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("전기설비공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("조적공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("지붕 및 홈통공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("창호 및 유리공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("철골공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("철근콘크리트공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("타일 및 돌공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("조경공사", (HashMap<String,Integer>) innerMap.clone());
        map.put("지정공사", (HashMap<String,Integer>) innerMap.clone());

        for (int i = 0; i < dataList.size(); i++) {
            if(!map.containsKey(dataList.get(i)[4]))
                continue;

            if(!map.get(dataList.get(i)[4]).containsKey(dataList.get(i)[13]))
                continue;

            map.get(dataList.get(i)[4]).put(dataList.get(i)[13],map.get(dataList.get(i)[4]).get(dataList.get(i)[13]) + 1);
        }

        if(construction.contains("상관없음")){

            xLabels.setLabelCount(22);

            for(int i =0; i < 22; i++){
                float val[] = new float[8];
                for(int j =0; j < 8; j++){
                    val[j] = (float) map.get(construction_list[i]).get(hazard_list[j]);
                }

                values.add(new BarEntry(
                        i,
                        val));
            }

        }


        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "대시보드");
            set1.setDrawIcons(false);
            set1.setDrawValues(true);
            set1.setColors(getColors());
            set1.setStackLabels(new String[]{"깔림", "떨어짐", "물체에 맞음", "질식", "화상", "넘어짐", "없음", "부딪힘"});

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setBarWidth(0.5f);

            chart.setData(data);
        }

        chart.setFitBars(true);
        chart.invalidate();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        construction = prefs.getStringSet("construction_select", null);
        process = prefs.getStringSet("process_select", null);
        hazard = prefs.getStringSet("hazard_select", null);
        hazard_position = prefs.getStringSet("hazard_position_select", null);
        damage = prefs.getStringSet("damage_select", null);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        BarEntry entry = (BarEntry) e;

        if (entry.getYVals() != null)
            Log.i("VAL SELECTED", "Value: " + entry.getYVals()[h.getStackIndex()]);
        else
            Log.i("VAL SELECTED", "Value: " + entry.getY());
    }

    @Override
    public void onNothingSelected() {

    }

    final int[] COLORFUL_COLORS = {

            Color.rgb(229, 79, 109), Color.rgb(78, 205, 196), Color.rgb(114, 78, 145),
            Color.rgb(41, 47, 54), Color.rgb(248, 198, 48), Color.rgb(72, 169, 166), Color.rgb(212, 180, 131),
            Color.rgb(179, 0, 27)
    };

    private int[] getColors() {

        // have as many colors as stack-values per entry
        int[] colors = new int[8];

        System.arraycopy(COLORFUL_COLORS, 0, colors, 0, 8);

        return colors;
    }
}