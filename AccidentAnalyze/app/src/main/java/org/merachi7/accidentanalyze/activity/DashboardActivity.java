package org.merachi7.accidentanalyze.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;

import org.merachi7.accidentanalyze.R;
import org.merachi7.accidentanalyze.util.CsvHelper;
import org.merachi7.accidentanalyze.util.NotesDbAdapter;

import java.io.InputStream;
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
        String path = prefs.getString("csv_upload", "");


        CsvHelper csv_helper = new CsvHelper();
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        path = filePath + "/" + path;
        List<String[]> dataList = csv_helper.readAllCsvData(path);



        Log.d(TAG, "DatabaseTest :: onCreate()");
        this.dbAdapter = new NotesDbAdapter(this);

    }

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

    }
}