package org.merachi7.accidentanalyze.fragment;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import org.merachi7.accidentanalyze.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}