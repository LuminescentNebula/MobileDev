package com.mirea.mobiledev;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {
    View view;

    public MenuFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.menu, container, false);
        Bundle bundle = new Bundle();

        view.findViewById(R.id.list).setOnClickListener(v -> {
            if (bundle != null) {
                bundle.putBoolean("switch", false);
                getParentFragmentManager().setFragmentResult("to", bundle);
            }
        });

        view.findViewById(R.id.recycler).setOnClickListener(v -> {
            if (bundle != null) {
                bundle.putBoolean("switch", true);
                getParentFragmentManager().setFragmentResult("to", bundle);
            }
        });

        return view;

    }
}
