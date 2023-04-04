package com.mirea.mobiledev;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentResultListener;

public class FirstFragment extends Fragment {
    private final String TAG = "FRAGMENT1";
    View view;
    public FirstFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG,"onCreate");
        Toast.makeText(getContext(), "onCreate", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.linear_layout, container, false);
        Bundle bundle = this.getArguments();

        Button myButton = view.findViewById(R.id.linear_button);
        myButton.setOnClickListener(v -> {
            if (bundle != null) {
                bundle.putInt("some_int", bundle.getInt("some_int", 0) + 1);
                getParentFragmentManager().setFragmentResult("fromFirst", bundle);
            }
        });
        return view;

    }

    @Override
    public void onStart() {
        Log.v(TAG,"onStart");
        Toast.makeText(getContext(), "onStart", Toast.LENGTH_SHORT).show();
        super.onStart();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            TextView v = view.findViewById(R.id.linear_text);
            v.setText(String.valueOf(bundle.getInt("some_int",0)));
        }
    }

    @Override
    public void onResume() {
        Log.v(TAG,"onResume");
        Toast.makeText(getContext(), "onResume", Toast.LENGTH_SHORT).show();
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.v(TAG,"onPause");
        Toast.makeText(getContext(), "onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.v(TAG,"onStop");
        Toast.makeText(getContext(), "onStop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.v(TAG,"onDestroy");
        Toast.makeText(getContext(), "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}

