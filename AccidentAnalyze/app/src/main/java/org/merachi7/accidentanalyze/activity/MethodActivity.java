package org.merachi7.accidentanalyze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import org.merachi7.accidentanalyze.R;

public class MethodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method);

        ImageButton btnSetting = (ImageButton) findViewById(R.id.btnDataSetting);
        btnSetting.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MethodDataSettingActivity.class);
            startActivity(intent);
        });

    }
}