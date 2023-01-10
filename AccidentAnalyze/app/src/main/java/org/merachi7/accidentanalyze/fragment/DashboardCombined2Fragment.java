package org.merachi7.accidentanalyze.fragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.merachi7.accidentanalyze.R;
import org.merachi7.accidentanalyze.activity.DashboardActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DashboardCombined2Fragment extends Fragment {

    SharedPreferences prefs;
    Object[] construction;
    Object[] process;
    Object[] hazard;
    Object[] hazard_position;
    Object[] damage;

    private CombinedChart chart;
    View v;


    private String[] defaultList = {"상관없음"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_dashboard_combined1, container, false);

        ArrayList<String[]> dataList = new ArrayList<String[]> (((DashboardActivity)getActivity()).dataList);

        if(dataList.size() != 0){
            prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());

            construction = prefs.getStringSet("construction_select", new HashSet<>(Arrays.asList(defaultList))).toArray();
            process = prefs.getStringSet("process_select", new HashSet<>(Arrays.asList(defaultList))).toArray();
            hazard =  prefs.getStringSet("hazard_select", new HashSet<>(Arrays.asList(defaultList))).toArray();
            hazard_position =  prefs.getStringSet("hazard_position_select", new HashSet<>(Arrays.asList(defaultList))).toArray();
            damage =  prefs.getStringSet("damage_select", new HashSet<>(Arrays.asList(defaultList))).toArray();

            chart = (CombinedChart) v.findViewById(R.id.combined_chart);


            chart.setPinchZoom(true);

            chart.setDrawGridBackground(false);
            chart.setDrawBarShadow(false);
            chart.setDrawValueAboveBar(false);
            chart.setHighlightFullBarEnabled(false);
            chart.getDescription().setEnabled(false);

            // change the position of the y-labels
            YAxis leftAxis = chart.getAxisLeft();
            leftAxis.setAxisMinimum(0);

            YAxis rightAxis = chart.getAxisRight();
            rightAxis.setAxisMinimum(0);
            leftAxis.setDrawGridLines(false);
            rightAxis.setDrawGridLines(false);

            XAxis xLabels = chart.getXAxis();
            xLabels.setTextSize(10f);
            xLabels.setGranularity(1f);
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


            String construction_list[] = {"가설공사", "건축 토공사" , "토공사", "건축물 부대공사", "금속공사", "기계설비공사", "기타", "도장공사", "말뚝공사", "목공사", "미장공사", "방수공사", "수장공사", "전기설비공사", "조적공사", "지붕 및 홈통공사", "창호 및 유리공사", "철골공사", "철근콘크리트공사", "타일 및 돌공사", "조경공사", "지정공사" };
            String process_list[] = {"고소작업", "굴착작업", "기타", "도장작업", "마감작업", "부설 및 다짐작업", "설비작업", "설치작업", "쌓기작업", "양중작업", "용접작업", "운반작업", "이동", "인양작업", "적재작업", "조립작업", "천공작업", "타설작업", "항타 및 항발작업", "해체작업", "형틀 및 목공" , "확인 및 점검작업"};
            String hazard_list[] = {"가시설", "건설공구", "건설기계", "건설자재", "기타", "부재", "시설물", "토사 및 암반"};
            String hazard_position_list[] = {"가설계단", "강관동바리", "개구부", "거푸집", "건물", "고소작업차(고소작업대 등)", "공구류", "굴착사면", "기성말뚝", "기중기(이동식크레인 등)", "기타", "기타 가시설", "낙하물방지망", "덤프트럭", "데크플레이트", "돌담", "띠장", "방호선반", "배관", "벽체", "볼트", "비계", "사다리", "슬래브", "시스템동바리", "안전시설물", "옹벽", "와이어로프", "유증기", "자재", "작업발판", "절토사면", "지반", "창호", "천공기", "천정패널", "철골부재", "철근", "콘크리트펌프", "타워크레인", "특수거푸집(갱폼 등)", "특수건설기계", "항타 및 항발기", "흙막이가시설"};
            String damage_list[] = {"깔림", "떨어짐", "물체에 맞음", "질식", "화상", "넘어짐", "없음", "부딪힘"};

            ArrayList<String[]> sortedData = new ArrayList<String[]>();

            if(Arrays.asList(construction).contains("상관없음")){
                construction = construction_list;
            }

            if(Arrays.asList(process).contains("상관없음")){
                process = process_list;
            }

            if(Arrays.asList(hazard).contains("상관없음")){
                hazard = hazard_list;
            }

            if(Arrays.asList(hazard_position).contains("상관없음")){
                hazard_position = hazard_position_list;
            }

            if(Arrays.asList(damage).contains("상관없음")){
                damage = damage_list;
            }

            HashMap<String, Integer> possibility_score = new HashMap<String, Integer>();
            HashMap<String, Integer> severity_score = new HashMap<String, Integer>();
            HashMap<String, Integer> total_size = new HashMap<String, Integer>();
            HashMap<String, Integer> risk_score = new HashMap<String, Integer>();


            for(int i =0; i< construction.length; i++){
                possibility_score.put((String) construction[i], 0);
                severity_score.put((String) construction[i], 0);
                total_size.put((String) construction[i], 0);
                risk_score.put((String) construction[i], 0);
            }

            for(int i =0; i < dataList.size(); i++){
                if(!Arrays.asList(construction).contains(dataList.get(i)[4]) || !Arrays.asList(process).contains(dataList.get(i)[11]) || !Arrays.asList(hazard).contains(dataList.get(i)[5]) || !Arrays.asList(hazard_position).contains(dataList.get(i)[6]) || !Arrays.asList(damage).contains(dataList.get(i)[13]))
                    continue;

                sortedData.add(dataList.get(i));
            }

            for(int i =0; i < dataList.size(); i++){
                if(!Arrays.asList(construction).contains(dataList.get(i)[4]) || !Arrays.asList(process).contains(dataList.get(i)[11]) || !Arrays.asList(hazard).contains(dataList.get(i)[5]) || !Arrays.asList(hazard_position).contains(dataList.get(i)[6]) || !Arrays.asList(damage).contains(dataList.get(i)[13]))
                    continue;

                sortedData.add(dataList.get(i));
            }

            String tmp[] = new String[construction.length];
            for(int i =0; i < construction.length; i++){
                tmp[i] = (String) construction[i];
            }

            xLabels.setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(tmp));

            for (int i = 0; i < sortedData.size(); i++) {
                possibility_score.put(sortedData.get(i)[4], possibility_score.get(sortedData.get(i)[4]) + ChangeScore(sortedData.get(i)[15]));
                severity_score.put(sortedData.get(i)[4], severity_score.get(sortedData.get(i)[4]) + ChangeScore(sortedData.get(i)[16]));
                total_size.put(sortedData.get(i)[4], total_size.get(sortedData.get(i)[4]) + 1);
                risk_score.put(sortedData.get(i)[4], risk_score.get(sortedData.get(i)[4]) + ChangeScore(sortedData.get(i)[15])*ChangeScore(sortedData.get(i)[16]));
            }

            xLabels.setLabelCount(construction.length);
            xLabels.setAxisMinimum(0);
            xLabels.setAxisMaximum(xLabels.getLabelCount());
            xLabels.setCenterAxisLabels(true);
            xLabels.setGranularityEnabled(true);
            //xLabels.setAvoidFirstLastClipping(true);


            CombinedData data = new CombinedData();

            ArrayList<BarEntry> total_entries = new ArrayList<>();

            for(int i = 0 ; i < construction.length; i++){
                total_entries.add(new BarEntry(i + 0.5f, total_size.get((String) construction[i])));
            }

            BarDataSet total_set = new BarDataSet(total_entries, "합계: count (좌)");
            total_set.setColor(Color.rgb(60, 220, 78));
            total_set.setValueTextColor(Color.rgb(60, 220, 78));
            total_set.setValueTextSize(10f);
            total_set.setAxisDependency(YAxis.AxisDependency.LEFT);
            total_set.setDrawValues(false);


            BarData barData = new BarData(total_set);
            barData.setBarWidth(0.7f);

            data.setData(barData);


        /*

        set linedata
         */

            LineData lineData = new LineData();

            ArrayList<Entry> risk_score_entries = new ArrayList<>();

            for(int i = 0 ; i < construction.length; i++){
                risk_score_entries.add(new Entry(i + 0.5f, (float) risk_score.get((String) construction[i])/ total_size.get((String) construction[i])));
            }

            LineDataSet set = new LineDataSet(risk_score_entries, "평균 : RS 변환 (우)");
            set.setColor(Color.rgb(255, 0, 0));
            set.setLineWidth(2.5f);
            set.setCircleColor(Color.rgb(255, 0, 0));
            set.setCircleRadius(3f);
            set.setFillColor(Color.rgb(255, 0, 0));
            set.setMode(LineDataSet.Mode.LINEAR);
            set.setDrawValues(false);

            set.setAxisDependency(YAxis.AxisDependency.RIGHT);

            lineData.addDataSet(set);

            if(lineData.getEntryCount() != 0)
                data.setData(lineData);

            chart.setData(data);
            chart.invalidate();

        }

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        
        ArrayList<String[]> dataList = new ArrayList<String[]> (((DashboardActivity)getActivity()).dataList);

        if(dataList.size() != 0){
            prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());

            construction = prefs.getStringSet("construction_select", new HashSet<>(Arrays.asList(defaultList))).toArray();
            process = prefs.getStringSet("process_select", new HashSet<>(Arrays.asList(defaultList))).toArray();
            hazard =  prefs.getStringSet("hazard_select", new HashSet<>(Arrays.asList(defaultList))).toArray();
            hazard_position =  prefs.getStringSet("hazard_position_select", new HashSet<>(Arrays.asList(defaultList))).toArray();
            damage =  prefs.getStringSet("damage_select", new HashSet<>(Arrays.asList(defaultList))).toArray();

            chart = (CombinedChart) v.findViewById(R.id.combined_chart);


            chart.setPinchZoom(true);

            chart.setDrawGridBackground(false);
            chart.setDrawBarShadow(false);
            chart.setDrawValueAboveBar(false);
            chart.setHighlightFullBarEnabled(false);
            chart.getDescription().setEnabled(false);

            // change the position of the y-labels
            YAxis leftAxis = chart.getAxisLeft();
            leftAxis.setAxisMinimum(0);

            YAxis rightAxis = chart.getAxisRight();
            rightAxis.setAxisMinimum(0);
            leftAxis.setDrawGridLines(false);
            rightAxis.setDrawGridLines(false);

            XAxis xLabels = chart.getXAxis();
            xLabels.setTextSize(10f);
            xLabels.setGranularity(1f);
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


            String construction_list[] = {"가설공사", "건축 토공사" , "토공사", "건축물 부대공사", "금속공사", "기계설비공사", "기타", "도장공사", "말뚝공사", "목공사", "미장공사", "방수공사", "수장공사", "전기설비공사", "조적공사", "지붕 및 홈통공사", "창호 및 유리공사", "철골공사", "철근콘크리트공사", "타일 및 돌공사", "조경공사", "지정공사" };
            String process_list[] = {"고소작업", "굴착작업", "기타", "도장작업", "마감작업", "부설 및 다짐작업", "설비작업", "설치작업", "쌓기작업", "양중작업", "용접작업", "운반작업", "이동", "인양작업", "적재작업", "조립작업", "천공작업", "타설작업", "항타 및 항발작업", "해체작업", "형틀 및 목공" , "확인 및 점검작업"};
            String hazard_list[] = {"가시설", "건설공구", "건설기계", "건설자재", "기타", "부재", "시설물", "토사 및 암반"};
            String hazard_position_list[] = {"가설계단", "강관동바리", "개구부", "거푸집", "건물", "고소작업차(고소작업대 등)", "공구류", "굴착사면", "기성말뚝", "기중기(이동식크레인 등)", "기타", "기타 가시설", "낙하물방지망", "덤프트럭", "데크플레이트", "돌담", "띠장", "방호선반", "배관", "벽체", "볼트", "비계", "사다리", "슬래브", "시스템동바리", "안전시설물", "옹벽", "와이어로프", "유증기", "자재", "작업발판", "절토사면", "지반", "창호", "천공기", "천정패널", "철골부재", "철근", "콘크리트펌프", "타워크레인", "특수거푸집(갱폼 등)", "특수건설기계", "항타 및 항발기", "흙막이가시설"};
            String damage_list[] = {"깔림", "떨어짐", "물체에 맞음", "질식", "화상", "넘어짐", "없음", "부딪힘"};

            ArrayList<String[]> sortedData = new ArrayList<String[]>();

            if(Arrays.asList(construction).contains("상관없음")){
                construction = construction_list;
            }

            if(Arrays.asList(process).contains("상관없음")){
                process = process_list;
            }

            if(Arrays.asList(hazard).contains("상관없음")){
                hazard = hazard_list;
            }

            if(Arrays.asList(hazard_position).contains("상관없음")){
                hazard_position = hazard_position_list;
            }

            if(Arrays.asList(damage).contains("상관없음")){
                damage = damage_list;
            }

            HashMap<String, Integer> possibility_score = new HashMap<String, Integer>();
            HashMap<String, Integer> severity_score = new HashMap<String, Integer>();
            HashMap<String, Integer> total_size = new HashMap<String, Integer>();
            HashMap<String, Integer> risk_score = new HashMap<String, Integer>();


            for(int i =0; i< construction.length; i++){
                possibility_score.put((String) construction[i], 0);
                severity_score.put((String) construction[i], 0);
                total_size.put((String) construction[i], 0);
                risk_score.put((String) construction[i], 0);
            }

            for(int i =0; i < dataList.size(); i++){
                if(!Arrays.asList(construction).contains(dataList.get(i)[4]) || !Arrays.asList(process).contains(dataList.get(i)[11]) || !Arrays.asList(hazard).contains(dataList.get(i)[5]) || !Arrays.asList(hazard_position).contains(dataList.get(i)[6]) || !Arrays.asList(damage).contains(dataList.get(i)[13]))
                    continue;

                sortedData.add(dataList.get(i));
            }

            for(int i =0; i < dataList.size(); i++){
                if(!Arrays.asList(construction).contains(dataList.get(i)[4]) || !Arrays.asList(process).contains(dataList.get(i)[11]) || !Arrays.asList(hazard).contains(dataList.get(i)[5]) || !Arrays.asList(hazard_position).contains(dataList.get(i)[6]) || !Arrays.asList(damage).contains(dataList.get(i)[13]))
                    continue;

                sortedData.add(dataList.get(i));
            }

            String tmp[] = new String[construction.length];
            for(int i =0; i < construction.length; i++){
                tmp[i] = (String) construction[i];
            }

            xLabels.setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(tmp));

            for (int i = 0; i < sortedData.size(); i++) {
                possibility_score.put(sortedData.get(i)[4], possibility_score.get(sortedData.get(i)[4]) + ChangeScore(sortedData.get(i)[15]));
                severity_score.put(sortedData.get(i)[4], severity_score.get(sortedData.get(i)[4]) + ChangeScore(sortedData.get(i)[16]));
                total_size.put(sortedData.get(i)[4], total_size.get(sortedData.get(i)[4]) + 1);
                risk_score.put(sortedData.get(i)[4], risk_score.get(sortedData.get(i)[4]) + ChangeScore(sortedData.get(i)[15])*ChangeScore(sortedData.get(i)[16]));
            }

            xLabels.setLabelCount(construction.length);
            xLabels.setAxisMinimum(0);
            xLabels.setAxisMaximum(xLabels.getLabelCount());
            xLabels.setCenterAxisLabels(true);
            xLabels.setGranularityEnabled(true);
            //xLabels.setAvoidFirstLastClipping(true);


            CombinedData data = new CombinedData();

            ArrayList<BarEntry> total_entries = new ArrayList<>();

            for(int i = 0 ; i < construction.length; i++){
                total_entries.add(new BarEntry(i + 0.5f, total_size.get((String) construction[i])));
            }

            BarDataSet total_set = new BarDataSet(total_entries, "합계: count (좌)");
            total_set.setColor(Color.rgb(60, 220, 78));
            total_set.setValueTextColor(Color.rgb(60, 220, 78));
            total_set.setValueTextSize(10f);
            total_set.setAxisDependency(YAxis.AxisDependency.LEFT);
            total_set.setDrawValues(false);


            BarData barData = new BarData(total_set);
            barData.setBarWidth(0.7f);

            data.setData(barData);


        /*

        set linedata
         */

            LineData lineData = new LineData();

            ArrayList<Entry> risk_score_entries = new ArrayList<>();

            for(int i = 0 ; i < construction.length; i++){
                risk_score_entries.add(new Entry(i + 0.5f, (float) risk_score.get((String) construction[i])/ total_size.get((String) construction[i])));
            }

            LineDataSet set = new LineDataSet(risk_score_entries, "평균 : RS 변환 (우)");
            set.setColor(Color.rgb(255, 0, 0));
            set.setLineWidth(2.5f);
            set.setCircleColor(Color.rgb(255, 0, 0));
            set.setCircleRadius(3f);
            set.setFillColor(Color.rgb(255, 0, 0));
            set.setMode(LineDataSet.Mode.LINEAR);
            set.setDrawValues(false);

            set.setAxisDependency(YAxis.AxisDependency.RIGHT);

            lineData.addDataSet(set);

            if(lineData.getEntryCount() != 0)
                data.setData(lineData);

            chart.setData(data);
            chart.invalidate();

        }
    }

    private int ChangeScore(String str){
        if(str.equals("L(1)")){
            return 1;
        }else if(str.equals("L(2)")){
            return 2;
        }else if(str.equals("L(3)")){
            return 3;
        }else if(str.equals("L(4)")){
            return 4;
        }else if(str.equals("L(5)")){
            return 5;
        }else if(str.equals("M(1)")){
            return 6;
        }else if(str.equals("M(2)")){
            return 7;
        }else if(str.equals("M(3)")){
            return 8;
        }else if(str.equals("M(4)")){
            return 9;
        }else if(str.equals("M(5)")){
            return 10;
        }else if(str.equals("H(1)")){
            return 11;
        }else if(str.equals("H(2)")){
            return 12;
        }else if(str.equals("H(3)")){
            return 13;
        }else if(str.equals("H(4)")){
            return 14;
        }else if(str.equals("H(5)")){
            return 15;
        }else{
            return 0;
        }
    }
}
