package com.mirea.mobiledev;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ListFragment extends Fragment {
    View view;
    public ListFragment(){
    }

    private ArrayList<String> texts=new ArrayList<>();
    private ArrayList<Integer> colors=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.list_layout, container, false);
        ListView listView = view.findViewById(R.id.list_view);
        for (int i=0; i<256;i++){
            texts.add(String.valueOf(i));
            colors.add(Color.rgb(i, i, i));
        }
        CustomAdapter adapter = new CustomAdapter(view.getContext(),texts.toArray(new String[0]),colors.toArray(new Integer[0]));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.i("LIST","Click ListItem Number " + position);
                Toast.makeText(view.getContext(),
                                "Click ListItem Number " + position,
                                Toast.LENGTH_SHORT)
                        .show();
            }
        });


        return view;
    }
}
