package org.merachi7.accidentanalyze.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import org.merachi7.accidentanalyze.R;

import java.util.Set;

public class MethodDataSettingsFragment extends PreferenceFragmentCompat {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.method_data_preferences, rootKey);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = prefs.edit();

    }


}