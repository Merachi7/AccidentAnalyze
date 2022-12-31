package org.merachi7.accidentanalyze.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import org.merachi7.accidentanalyze.R;

import java.util.Set;

public class DataSettingsFragment extends PreferenceFragmentCompat {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.data_preferences, rootKey);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = prefs.edit();

        prefs.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void onResume() {
        super.onResume();

//설정값 변경리스너..등록
        prefs.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void onPause() {
        super.onPause();

        prefs.unregisterOnSharedPreferenceChangeListener(listener);

    }

    SharedPreferences.OnSharedPreferenceChangeListener listener= new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals("construction_select")){
               // Set<String> constructions = prefs.getStringSet(key, null);
            }else if(key.equals("process_select")){
                Set<String> process = prefs.getStringSet(key, null);
            }else if(key.equals("hazard_select")){
                Set<String> hazard = prefs.getStringSet(key, null);
            }else if(key.equals("hazard_position_select")){
                Set<String> hazard_position = prefs.getStringSet(key, null);
            }else if(key.equals("damage_select")) {
                Set<String> damage = prefs.getStringSet(key, null);
            }
        }
    };
}