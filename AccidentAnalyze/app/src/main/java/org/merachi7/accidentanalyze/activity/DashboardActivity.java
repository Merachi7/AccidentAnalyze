package org.merachi7.accidentanalyze.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.merachi7.accidentanalyze.R;
import org.merachi7.accidentanalyze.fragment.DashboardBarFragment;
import org.merachi7.accidentanalyze.fragment.DashboardCircleFragment;
import org.merachi7.accidentanalyze.fragment.DashboardCombined1Fragment;
import org.merachi7.accidentanalyze.fragment.DashboardCombined2Fragment;
import org.merachi7.accidentanalyze.util.CsvHelper;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private DashboardBarFragment fragment_barchart;
    private DashboardCircleFragment fragment_circle;
    private DashboardCombined1Fragment fragment_combined1;
    private DashboardCombined2Fragment fragment_combined2;
    private FragmentTransaction transaction;
    public List<String[]> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        SharedPreferences prefs;
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //this.dbAdapter = new NotesDbAdapter(this);
        String path = prefs.getString("csv_upload", "");

        CsvHelper csv_helper = new CsvHelper();
        dataList = new ArrayList<>();
        dataList =  csv_helper.readAllCsvData(path);

        fragmentManager = getSupportFragmentManager();

        fragment_barchart = new DashboardBarFragment();
        fragment_circle = new DashboardCircleFragment();
        fragment_combined1 = new DashboardCombined1Fragment();
        fragment_combined2 = new DashboardCombined2Fragment();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment_barchart).commitAllowingStateLoss();
        
        ImageButton btnSetting = (ImageButton) findViewById(R.id.btnDataSetting);
        btnSetting.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), DataSettingActivity.class);
            startActivity(intent);
        });

    }



    public void clickHandler(View view)
    {
        transaction = fragmentManager.beginTransaction();

        switch(view.getId())
        {
            case R.id.btn_fragment_barchart:
                transaction.replace(R.id.frameLayout, fragment_barchart).commitAllowingStateLoss();
                break;
            case R.id.btn_fragment_circle:
                transaction.replace(R.id.frameLayout, fragment_circle).commitAllowingStateLoss();
                break;
            case R.id.btn_fragment_combined1:
                transaction.replace(R.id.frameLayout, fragment_combined1).commitAllowingStateLoss();
                break;
            case R.id.btn_fragment_combined2:
                transaction.replace(R.id.frameLayout, fragment_combined2).commitAllowingStateLoss();
                break;
        }
    }

}
