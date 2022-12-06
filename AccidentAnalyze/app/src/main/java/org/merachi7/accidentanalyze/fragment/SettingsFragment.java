package org.merachi7.accidentanalyze.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;

import org.merachi7.accidentanalyze.R;
import org.merachi7.accidentanalyze.util.NotesDbAdapter;

@SuppressWarnings("deprecation")
public class SettingsFragment extends PreferenceFragmentCompat {


    String path;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

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
                Uri fileUri = data.getData();
                path = fileUri.getPath();
            }

            editor.putString("csv_upload", path);
            editor.commit();
        }
    }





}