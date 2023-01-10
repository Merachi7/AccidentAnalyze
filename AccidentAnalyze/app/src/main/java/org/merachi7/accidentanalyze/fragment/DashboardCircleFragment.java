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

import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import org.merachi7.accidentanalyze.R;
import org.merachi7.accidentanalyze.activity.DashboardActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DashboardCircleFragment extends Fragment {

    SharedPreferences prefs;
    Object[] construction;
    Object[] process;
    Object[] hazard;
    Object[] hazard_position;
    Object[] damage;

    private BubbleChart chart;
    View v;
    private String[] defaultList = {"상관없음"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_dashboard_combined1, container, false);

        ArrayList<String[]> dataList = new ArrayList<String[]>(((DashboardActivity) getActivity()).dataList);

        if (dataList.size() != 0) {
            prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());

            construction = prefs.getStringSet("construction_select", new HashSet<>(Arrays.asList(defaultList))).toArray();
            process = prefs.getStringSet("process_select", new HashSet<>(Arrays.asList(defaultList))).toArray();
            hazard = prefs.getStringSet("hazard_select", new HashSet<>(Arrays.asList(defaultList))).toArray();
            hazard_position = prefs.getStringSet("hazard_position_select", new HashSet<>(Arrays.asList(defaultList))).toArray();
            damage = prefs.getStringSet("damage_select", new HashSet<>(Arrays.asList(defaultList))).toArray();

            chart = (BubbleChart) v.findViewById(R.id.bubble_chart);

            chart.setPinchZoom(true);

            chart.setDrawGridBackground(false);
            chart.getDescription().setEnabled(false);

            // change the position of the y-labels
            YAxis leftAxis = chart.getAxisLeft();
            leftAxis.setAxisMinimum(0);
            leftAxis.setDrawGridLines(false);

            XAxis xLabels = chart.getXAxis();
            xLabels.setTextSize(10f);

            xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);


            Legend l = chart.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setOrientation(Legend.LegendOrientation.VERTICAL);
            l.setDrawInside(false);
            l.setFormSize(8f);
            l.setFormToTextSpace(3f);
            l.setXEntrySpace(8f);

            YAxis yl = chart.getAxisLeft();

            yl.setSpaceTop(30f);
            yl.setSpaceBottom(30f);
            yl.setDrawZeroLine(false);

            chart.getAxisRight().setEnabled(false);


            String construction_list[] = {"가설공사", "건축 토공사", "토공사", "건축물 부대공사", "금속공사", "기계설비공사", "기타", "도장공사", "말뚝공사", "목공사", "미장공사", "방수공사", "수장공사", "전기설비공사", "조적공사", "지붕 및 홈통공사", "창호 및 유리공사", "철골공사", "철근콘크리트공사", "타일 및 돌공사", "조경공사", "지정공사"};
            String process_list[] = {"고소작업", "굴착작업", "기타", "도장작업", "마감작업", "부설 및 다짐작업", "설비작업", "설치작업", "쌓기작업", "양중작업", "용접작업", "운반작업", "이동", "인양작업", "적재작업", "조립작업", "천공작업", "타설작업", "항타 및 항발작업", "해체작업", "형틀 및 목공", "확인 및 점검작업"};
            String hazard_list[] = {"가시설", "건설공구", "건설기계", "건설자재", "기타", "부재", "시설물", "토사 및 암반"};
            String hazard_position_list[] = {"가설계단", "강관동바리", "개구부", "거푸집", "건물", "고소작업차(고소작업대 등)", "공구류", "굴착사면", "기성말뚝", "기중기(이동식크레인 등)", "기타", "기타 가시설", "낙하물방지망", "덤프트럭", "데크플레이트", "돌담", "띠장", "방호선반", "배관", "벽체", "볼트", "비계", "사다리", "슬래브", "시스템동바리", "안전시설물", "옹벽", "와이어로프", "유증기", "자재", "작업발판", "절토사면", "지반", "창호", "천공기", "천정패널", "철골부재", "철근", "콘크리트펌프", "타워크레인", "특수거푸집(갱폼 등)", "특수건설기계", "항타 및 항발기", "흙막이가시설"};
            String damage_list[] = {"깔림", "떨어짐", "물체에 맞음", "질식", "화상", "넘어짐", "없음", "부딪힘"};

            ArrayList<String[]> sortedData = new ArrayList<String[]>();

            if (Arrays.asList(construction).contains("상관없음")) {
                construction = construction_list;
            }

            if (Arrays.asList(process).contains("상관없음")) {
                process = process_list;
            }

            if (Arrays.asList(hazard).contains("상관없음")) {
                hazard = hazard_list;
            }

            if (Arrays.asList(hazard_position).contains("상관없음")) {
                hazard_position = hazard_position_list;
            }

            if (Arrays.asList(damage).contains("상관없음")) {
                damage = damage_list;
            }

            for (int i = 0; i < dataList.size(); i++) {
                if (!Arrays.asList(construction).contains(dataList.get(i)[4]) || !Arrays.asList(process).contains(dataList.get(i)[11]) || !Arrays.asList(hazard).contains(dataList.get(i)[5]) || !Arrays.asList(hazard_position).contains(dataList.get(i)[6]) || !Arrays.asList(damage).contains(dataList.get(i)[13]))
                    continue;

                sortedData.add(dataList.get(i));
            }

            for (int i = 0; i < dataList.size(); i++) {
                if (!Arrays.asList(construction).contains(dataList.get(i)[4]) || !Arrays.asList(process).contains(dataList.get(i)[11]) || !Arrays.asList(hazard).contains(dataList.get(i)[5]) || !Arrays.asList(hazard_position).contains(dataList.get(i)[6]) || !Arrays.asList(damage).contains(dataList.get(i)[13]))
                    continue;

                sortedData.add(dataList.get(i));
            }



            for (int i = 0; i < sortedData.size(); i++) {

            }

            xLabels.setLabelCount(15);
            xLabels.setAxisMinimum(0);
            xLabels.setAxisMaximum(15);


        }


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}