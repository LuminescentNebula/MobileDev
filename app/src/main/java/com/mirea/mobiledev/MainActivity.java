package com.mirea.mobiledev;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

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