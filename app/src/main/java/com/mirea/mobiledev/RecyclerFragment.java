package com.mirea.mobiledev;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerFragment extends Fragment {
    View view;
    public RecyclerFragment(){
    }

    private ArrayList<String> texts=new ArrayList<>();
    private ArrayList<Integer> colors=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.recycler_layout, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        for (int i=0; i<256;i++){
            texts.add(String.valueOf(i));
            colors.add(Color.rgb(i, i, i));
        }
        CustomRecyclerAdapter adapter = new CustomRecyclerAdapter(texts.toArray(new String[0]),colors.toArray(new Integer[0]));
        adapter.setClickListener((view, position) -> {
            Log.i("RECYCLER","Click RecyclerItem Number " + position);
            Toast.makeText(view.getContext(),
                            "Click RecyclerItem Number " + position,
                            Toast.LENGTH_SHORT)
                    .show();
        });
        recyclerView.setAdapter(adapter);
        return view;
    }


}
