package org.merachi7.accidentanalyze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.merachi7.accidentanalyze.R;
import org.merachi7.accidentanalyze.util.CsvHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MethodActivity extends AppCompatActivity {

    SharedPreferences prefs;
    String construction;
    String process;
    String hazard;
    String hazard_position;
    String damage;
    public List<String[]> dataList;

    private ListView listView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method);

        ImageButton btnSetting = (ImageButton) findViewById(R.id.btnDataSetting);
        btnSetting.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MethodDataSettingActivity.class);
            startActivity(intent);
        });

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //this.dbAdapter = new NotesDbAdapter(this);
        String path = prefs.getString("csv_upload", "");
        CsvHelper csv_helper = new CsvHelper();
        dataList = new ArrayList<>(csv_helper.readAllCsvData(path));

        construction = prefs.getString("method_construction_select", "");
        process = prefs.getString("method_process_select", "");
        hazard = prefs.getString("method_hazard_select", "");
        hazard_position = prefs.getString("method_hazard_position_select", "");
        damage = prefs.getString("method_damage_select", "");


        textView = (TextView) findViewById(R.id.textCurrentSelect) ;
        listView = (ListView) findViewById(R.id.method_list);


        textView.setText("공종분류 : " + construction + " / 작업프로세스 : " + process + "\n위험발생객체 : " + hazard + " / 위험발생위치 : " + hazard_position + "\n인적피해 : " + damage);
        List<String> method_list = new ArrayList<>();
        HashMap<String , Integer> method_cnt = new HashMap<>();

        for(int i =0; i < dataList.size(); i++){
            if(!Arrays.asList(construction).contains(dataList.get(i)[4]) || !Arrays.asList(process).contains(dataList.get(i)[11]) || !Arrays.asList(hazard).contains(dataList.get(i)[5]) || !Arrays.asList(hazard_position).contains(dataList.get(i)[6]) || !Arrays.asList(damage).contains(dataList.get(i)[13]))
                continue;

            if(!method_list.contains("사고원인 : " + dataList.get(i)[14] + "\n\n해결방안 : "  + dataList.get(i)[18])){
                method_cnt.put("사고원인 : " + dataList.get(i)[14] + "\n\n해결방안 : "  + dataList.get(i)[18] , 1);
                method_list.add("사고원인 : " + dataList.get(i)[14] + "\n\n해결방안 : "  + dataList.get(i)[18]);
            }else{
                method_cnt.put("사고원인 : " + dataList.get(i)[14] + "\n\n해결방안 : "  + dataList.get(i)[18] , method_cnt.get("사고원인 : " + dataList.get(i)[14] + "\n\n해결방안 : "  + dataList.get(i)[18]) + 1);
            }
        }

        List<String> real_method_list = new ArrayList<>();

        for(int i =0; i < method_list.size(); i++){
            String tmp_save = method_list.get(i);
            method_list.remove(i);

            real_method_list.add(tmp_save + " (" + method_cnt.get(tmp_save) + ")");
        }

        if(method_list.size() == 0){
            method_list.add("선택한 분류에 해당되는 데이터가 없습니다.");
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, real_method_list) ;

        listView.setAdapter(adapter) ;
    }


        @Override
    public void onResume() {
        super.onResume();

            prefs = PreferenceManager.getDefaultSharedPreferences(this);
            //this.dbAdapter = new NotesDbAdapter(this);
            String path = prefs.getString("csv_upload", "");
            CsvHelper csv_helper = new CsvHelper();
            dataList = new ArrayList<>(csv_helper.readAllCsvData(path));

            construction = prefs.getString("method_construction_select", "");
            process = prefs.getString("method_process_select", "");
            hazard = prefs.getString("method_hazard_select", "");
            hazard_position = prefs.getString("method_hazard_position_select", "");
            damage = prefs.getString("method_damage_select", "");


            textView = (TextView) findViewById(R.id.textCurrentSelect) ;
            listView = (ListView) findViewById(R.id.method_list);


            textView.setText("공종분류 : " + construction + " / 작업프로세스 : " + process + "\n위험발생객체 : " + hazard + " / 위험발생위치 : " + hazard_position + "\n인적피해 : " + damage);
            List<String> method_list = new ArrayList<>();
            HashMap<String , Integer> method_cnt = new HashMap<>();

            for(int i =0; i < dataList.size(); i++){
                if(!Arrays.asList(construction).contains(dataList.get(i)[4]) || !Arrays.asList(process).contains(dataList.get(i)[11]) || !Arrays.asList(hazard).contains(dataList.get(i)[5]) || !Arrays.asList(hazard_position).contains(dataList.get(i)[6]) || !Arrays.asList(damage).contains(dataList.get(i)[13]))
                    continue;

                if(!method_list.contains("사고원인 : " + dataList.get(i)[14] + "\n\n해결방안 : "  + dataList.get(i)[18])){
                    method_cnt.put("사고원인 : " + dataList.get(i)[14] + "\n\n해결방안 : "  + dataList.get(i)[18] , 1);
                    method_list.add("사고원인 : " + dataList.get(i)[14] + "\n\n해결방안 : "  + dataList.get(i)[18]);
                }else{
                    method_cnt.put("사고원인 : " + dataList.get(i)[14] + "\n\n해결방안 : "  + dataList.get(i)[18] , method_cnt.get("사고원인 : " + dataList.get(i)[14] + "\n\n해결방안 : "  + dataList.get(i)[18]) + 1);
                }
            }

            List<String> real_method_list = new ArrayList<>();

            for(int i =0; i < method_list.size(); i++){
                String tmp_save = method_list.get(i);
                method_list.remove(i);

                real_method_list.add(tmp_save + " (" + method_cnt.get(tmp_save) + ")");
            }

            if(method_list.size() == 0){
                method_list.add("선택한 분류에 해당되는 데이터가 없습니다.");
            }

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, real_method_list) ;

            listView.setAdapter(adapter) ;
    }

}