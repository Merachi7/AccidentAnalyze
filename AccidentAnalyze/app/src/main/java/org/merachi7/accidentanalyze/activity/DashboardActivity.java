package org.merachi7.accidentanalyze.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import org.merachi7.accidentanalyze.R;
import org.merachi7.accidentanalyze.util.CsvHelper;
import org.merachi7.accidentanalyze.util.NotesDbAdapter;
import org.merachi7.accidentanalyze.fragment.SettingsFragment;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private NotesDbAdapter dbAdapter;

    private static final String TAG = "NotesDbAdapter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        SharedPreferences prefs;
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //this.dbAdapter = new NotesDbAdapter(this);
        String path = prefs.getString("csv_upload", "");

        CsvHelper csv_helper = new CsvHelper();
        List<String[]> dataList = new ArrayList<>();
        dataList =  csv_helper.readAllCsvData(path);
        int a = 0;

    }

}
