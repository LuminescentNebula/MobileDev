package com.mirea.mobiledev;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    private final String TAG = "onTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("some_int", 1);
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view,
                            FirstFragment.class, bundle)
                    .commit();
        }

        getSupportFragmentManager().setFragmentResultListener("fromFirst", this, (requestKey, bundle) -> {
            Integer result = bundle.getInt("some_int", 0);
            Log.i(TAG, String.valueOf(result));
            Log.i(TAG, getSupportFragmentManager().getFragments().toString());
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view,
                            SecondFragment.class, bundle)
                    .commit();

        });

        getSupportFragmentManager().setFragmentResultListener("fromSecond", this, (requestKey, bundle) -> {
            Integer result = bundle.getInt("some_int", 0);
            Log.i(TAG, String.valueOf(result));
            Log.i(TAG, getSupportFragmentManager().getFragments().toString());
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view,
                            FirstFragment.class, bundle)
                    .commit();
        });
    }
}