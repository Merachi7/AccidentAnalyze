package org.merachi7.accidentanalyze.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.merachi7.accidentanalyze.R;

import java.util.Set;

public class DashboardBarFragment extends Fragment {

    SharedPreferences prefs;
    Set<String> construction;
    Set<String> process;
    Set<String> hazard;
    Set<String> hazard_position;
    Set<String> damage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());

        construction = prefs.getStringSet("construction_select", null);
        process = prefs.getStringSet("process_select", null);
        hazard = prefs.getStringSet("hazard_select", null);
        hazard_position = prefs.getStringSet("hazard_position_select", null);
        damage = prefs.getStringSet("damage_select", null);

        return inflater.inflate(R.layout.fragment_dashboard_bar, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        construction = prefs.getStringSet("construction_select", null);
        process = prefs.getStringSet("process_select", null);
        hazard = prefs.getStringSet("hazard_select", null);
        hazard_position = prefs.getStringSet("hazard_position_select", null);
        damage = prefs.getStringSet("damage_select", null);
    }
}