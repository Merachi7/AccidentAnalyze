package org.merachi7.accidentanalyze.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;

import org.merachi7.accidentanalyze.R;
import org.merachi7.accidentanalyze.data.AccidentData;
import org.merachi7.accidentanalyze.util.NotesDbAdapter;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    NotesDbAdapter DBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }




}