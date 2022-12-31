package org.merachi7.accidentanalyze.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.merachi7.accidentanalyze.R;


public class MainActivity extends AppCompatActivity {
    private BarChart chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        ImageButton btnSetting = (ImageButton) findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
            startActivity(intent);
        });

        Button btnDashboard = (Button) findViewById(R.id.btnDashboard);
        btnDashboard.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(intent);
        });

        Button btnMethod = (Button) findViewById(R.id.btnMethod);
        btnMethod.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MethodActivity.class);
            startActivity(intent);
        });
    }
}