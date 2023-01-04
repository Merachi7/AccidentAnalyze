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
import java.util.HashSet;
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

        if(construction == null){
            construction = new HashSet<>();
        }

        if(process == null){
            process = new HashSet<>();
        }

        if(hazard == null){
            hazard = new HashSet<>();
        }

        if(hazard_position == null){
            hazard_position = new HashSet<>();
        }

        if(damage == null){
            damage = new HashSet<>();
        }

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
        leftAxis.setDrawGridLines(false);

        XAxis xLabels = chart.getXAxis();
        xLabels.setTextSize(10f);
        xLabels.setGranularity(1);
        xLabels.setLabelRotationAngle(-90);


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



        String construction_list[] = {"가설공사", "건축 토공사" , "토공사", "건축물 부대공사", "금속공사", "기계설비공사", "기타", "도장공사", "말뚝공사", "목공사", "미장공사", "방수공사", "수장공사", "전기설비공사", "조적공사", "지붕 및 홈통공사", "창호 및 유리공사", "철골공사", "철근콘크리트공사", "타일 및 돌공사", "조경공사", "지정공사" };
        String process_list[] = {"고소작업", "굴착작업", "기타", "도장작업", "마감작업", "부설 및 다짐작업", "설비작업", "설치작업", "쌓기작업", "양중작업", "용접작업", "운반작업", "이동", "인양작업", "적재작업", "조립작업", "천공작업", "타설작업", "항타 및 항발작업", "해체작업", "형틀 및 목공" , "확인 및 점검작업"};
        String hazard_list[] = {"가시설", "건설공구", "건설기계", "건설자재", "기타", "부재", "시설물", "토사 및 암반"};
        String hazard_position_list[] = {"가설계단", "강관동바리", "개구부", "거푸집", "건물", "고소작업차(고소작업대 등)", "공구류", "굴착사면", "기성말뚝", "기중기(이동식크레인) 등", "기타", "기타 가시설", "낙하물방지망", "덤프트럭", "데크플레이트", "돌담", "띠장", "방호선반", "배관", "벽체", "볼트", "비계", "사다리", "슬래브", "시스템동바리", "안전시설물", "옹벽", "와이어로프", "유증기", "자재", "작업발판", "절토사면", "지반", "창호", "천공기", "천정패널", "철골부재", "철근", "콘크리트펌프", "타워크레인", "특수거푸짐(갱폼 등)", "특수건설기계", "항타 및 항발기", "흙막이가시설"};
        String damage_list[] = {"깔림", "떨어짐", "물체에 맞음", "질식", "화상", "넘어짐", "없음", "부딪힘"};

        ArrayList<String[]> sortedData = new ArrayList<String[]>();

        if(construction.contains("상관없음")){
           construction.clear();

           for(int i = 0; i < 22; i++){
               construction.add(construction_list[i]);
           }
        }

        if(process.contains("상관없음")){
            process.clear();

            for(int i = 0; i < 22; i++){
                process.add(process_list[i]);
            }
        }

        if(hazard.contains("상관없음")){
            hazard.clear();

            for(int i =0; i< 8; i++){
                hazard.add(hazard_list[i]);
            }
        }


        if(hazard_position.contains("상관없음")){
            hazard_position.clear();

            for(int i =0; i< 44; i++){
                hazard_position.add(hazard_position_list[i]);
            }
        }

        if(damage.contains("상관없음")){
            damage.clear();

            for(int i = 0; i < 8; i++){
                damage.add(damage_list[i]);
            }
        }

        HashMap<String, HashMap<String, Integer>> map = new HashMap<String, HashMap<String, Integer>>();

        HashMap<String,Integer> innerMap = new HashMap<>();

        for(int i =0; i<damage.size();i++){
            innerMap.put((String) damage.toArray()[i], 1);
        }

        for(int i =0; i< construction.size(); i++){
            map.put((String) construction.toArray()[i], (HashMap<String, Integer>) innerMap.clone());
        }

        for(int i =0; i < dataList.size(); i++){
            if(!construction.contains(dataList.get(i)[4]) || !process.contains(dataList.get(i)[11]) || !hazard.contains(dataList.get(i)[5]) || !hazard_position.contains(dataList.get(i)[6]) || !damage.contains(dataList.get(i)[13]))
                continue;

            sortedData.add(dataList.get(i));
        }

        xLabels.setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(construction));

        for (int i = 0; i < sortedData.size(); i++) {
            map.get(sortedData.get(i)[4]).put(sortedData.get(i)[13],map.get(sortedData.get(i)[4]).get(sortedData.get(i)[13]) + 1);
        }

        xLabels.setLabelCount(construction.size());

        for(int i =0; i < construction.size(); i++){
            float val[] = new float[damage.size()];
            for(int j =0; j < damage.size(); j++){
                val[j] = (float) map.get((String) construction.toArray()[i]).get((String) damage.toArray()[j]);
            }

            values.add(new BarEntry(
                    i,
                    val));
        }

        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "");
            set1.setDrawIcons(false);
            set1.setDrawValues(false);
            set1.setColors(getColors());
            set1.setStackLabels(damage_list);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setBarWidth(0.7f);

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

        if(construction == null){
            construction = new HashSet<>();
        }

        if(process == null){
            process = new HashSet<>();
        }

        if(hazard == null){
            hazard = new HashSet<>();
        }

        if(hazard_position == null){
            hazard_position = new HashSet<>();
        }

        if(damage == null){
            damage = new HashSet<>();
        }

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
        xLabels.setTextSize(10f);
        xLabels.setGranularity(1);
        xLabels.setLabelRotationAngle(-90);


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



        String construction_list[] = {"가설공사", "건축 토공사" , "토공사", "건축물 부대공사", "금속공사", "기계설비공사", "기타", "도장공사", "말뚝공사", "목공사", "미장공사", "방수공사", "수장공사", "전기설비공사", "조적공사", "지붕 및 홈통공사", "창호 및 유리공사", "철골공사", "철근콘크리트공사", "타일 및 돌공사", "조경공사", "지정공사" };
        String process_list[] = {"고소작업", "굴착작업", "기타", "도장작업", "마감작업", "부설 및 다짐작업", "설비작업", "설치작업", "쌓기작업", "양중작업", "용접작업", "운반작업", "이동", "인양작업", "적재작업", "조립작업", "천공작업", "타설작업", "항타 및 항발작업", "해체작업", "형틀 및 목공" , "확인 및 점검작업"};
        String hazard_list[] = {"가시설", "건설공구", "건설기계", "건설자재", "기타", "부재", "시설물", "토사 및 암반"};
        String hazard_position_list[] = {"가설계단", "강관동바리", "개구부", "거푸집", "건물", "고소작업차(고소작업대 등)", "공구류", "굴착사면", "기성말뚝", "기중기(이동식크레인) 등", "기타", "기타 가시설", "낙하물방지망", "덤프트럭", "데크플레이트", "돌담", "띠장", "방호선반", "배관", "벽체", "볼트", "비계", "사다리", "슬래브", "시스템동바리", "안전시설물", "옹벽", "와이어로프", "유증기", "자재", "작업발판", "절토사면", "지반", "창호", "천공기", "천정패널", "철골부재", "철근", "콘크리트펌프", "타워크레인", "특수거푸짐(갱폼 등)", "특수건설기계", "항타 및 항발기", "흙막이가시설"};
        String damage_list[] = {"깔림", "떨어짐", "물체에 맞음", "질식", "화상", "넘어짐", "없음", "부딪힘"};

        ArrayList<String[]> sortedData = new ArrayList<String[]>();

        if(construction.contains("상관없음")){
            construction.clear();

            for(int i = 0; i < 22; i++){
                construction.add(construction_list[i]);
            }
        }

        if(process.contains("상관없음")){
            process.clear();

            for(int i = 0; i < 22; i++){
                process.add(process_list[i]);
            }
        }

        if(hazard.contains("상관없음")){
            hazard.clear();

            for(int i =0; i< 8; i++){
                hazard.add(hazard_list[i]);
            }
        }


        if(hazard_position.contains("상관없음")){
            hazard_position.clear();

            for(int i =0; i< 44; i++){
                hazard_position.add(hazard_position_list[i]);
            }
        }

        if(damage.contains("상관없음")){
            damage.clear();

            for(int i = 0; i < 8; i++){
                damage.add(damage_list[i]);
            }
        }

        HashMap<String, HashMap<String, Integer>> map = new HashMap<String, HashMap<String, Integer>>();

        HashMap<String,Integer> innerMap = new HashMap<>();

        for(int i =0; i<damage.size();i++){
            innerMap.put((String) damage.toArray()[i], 1);
        }

        for(int i =0; i< construction.size(); i++){
            map.put((String) construction.toArray()[i], (HashMap<String, Integer>) innerMap.clone());
        }

        for(int i =0; i < dataList.size(); i++){
            if(!construction.contains(dataList.get(i)[4]) || !process.contains(dataList.get(i)[11]) || !hazard.contains(dataList.get(i)[5]) || !hazard_position.contains(dataList.get(i)[6]) || !damage.contains(dataList.get(i)[13]))
                continue;

            sortedData.add(dataList.get(i));
        }

        xLabels.setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(construction));

        for (int i = 0; i < sortedData.size(); i++) {
            if(!map.containsKey(sortedData.get(i)[4]))
                continue;

            if(!map.get(sortedData.get(i)[4]).containsKey(sortedData.get(i)[13]))
                continue;

            map.get(sortedData.get(i)[4]).put(sortedData.get(i)[13],map.get(sortedData.get(i)[4]).get(sortedData.get(i)[13]) + 1);
        }

        xLabels.setLabelCount(construction.size());

        for(int i =0; i < construction.size(); i++){
            float val[] = new float[damage.size()];
            for(int j =0; j < damage.size(); j++){
                val[j] = (float) map.get((String) construction.toArray()[i]).get((String) damage.toArray()[j]);
            }

            values.add(new BarEntry(
                    i,
                    val));
        }

        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "");
            set1.setDrawIcons(false);
            set1.setDrawValues(false);
            set1.setColors(getColors());
            set1.setStackLabels(damage_list);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setBarWidth(0.7f);

            chart.setData(data);
        }

        chart.setFitBars(true);
        chart.invalidate();

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