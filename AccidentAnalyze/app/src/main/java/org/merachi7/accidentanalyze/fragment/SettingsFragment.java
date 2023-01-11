package org.merachi7.accidentanalyze.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;


import com.opencsv.CSVReader;

import org.merachi7.accidentanalyze.BuildConfig;
import org.merachi7.accidentanalyze.R;
import org.merachi7.accidentanalyze.activity.DashboardActivity;
import org.merachi7.accidentanalyze.util.CsvHelper;
import org.merachi7.accidentanalyze.util.NotesDbAdapter;
import org.merachi7.accidentanalyze.util.FileHelper;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SuppressWarnings("deprecation")
public class SettingsFragment extends PreferenceFragmentCompat {
    NotesDbAdapter dbAdapter;

    String path;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Uri fileUri;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = prefs.edit();

        Preference btnUpload = (Preference) findPreference("csv_upload");
        btnUpload.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                openFile();


                return true;
            }
        });


        Preference btnDelete = (Preference) findPreference("csv_delete");
        btnDelete.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                editor.putString("csv_upload", "");
                editor.commit();
                Toast.makeText(getActivity(),"데이터가 정상적으로 지워졌습니다", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        Preference btnHelp = (Preference) findPreference("csv_help");
        btnHelp.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                Toast.makeText(getActivity(),"정상적으로 작동하지 않는다면 csv 파일의 \'\\\'를 지워주세요", Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }

    public void openFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {

            if (data != null) {
                fileUri = data.getData();
                path = fileUri.getPath();
                path = path.substring(path.lastIndexOf("/") + 1);
                path = FileHelper.getPath(getActivity(), fileUri);

                editor.putString("csv_upload", path);
                editor.commit();
            }
        }
        CsvHelper csv_helper = new CsvHelper();
      //  dbAdapter = new NotesDbAdapter(getActivity());


        verifyStoragePermissions(this.getActivity());
        getActivity().deleteDatabase("data");
        List<String[]> dataList = csv_helper.readAllCsvData(path);

     //   dbAdapter.open();
     //  dbAdapter.insertAllData(dataList, dbAdapter);
     //   dbAdapter.close();
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