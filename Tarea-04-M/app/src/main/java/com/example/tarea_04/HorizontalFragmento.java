package com.example.tarea_04;

import android.app.Fragment;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class HorizontalFragmento extends Fragment {
    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle b) {
        View v = li.inflate(R.layout.horizontal_fragmento, vg, false);
        String[] s = new String[] {
                "Elemento1",
                "Elemento2",
                "Elemento3"
        };
        ListView lv =  (ListView) v.findViewById(R.id.ListViewB);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_expandable_list_item_1,s
        );
        lv.setAdapter(adapter);
        return v;
    }
}
