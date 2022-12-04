package org.merachi7.accidentanalyze.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import org.merachi7.accidentanalyze.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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