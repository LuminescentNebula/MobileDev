package com.mirea.mobiledev;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

    private final String TAG = "onTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view,
                            MenuFragment.class, null)
                    .commit();
        }

        getSupportFragmentManager().setFragmentResultListener("to", this, (requestKey, bundle) -> {
            if (bundle.getBoolean("switch")){
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view,
                                RecyclerFragment.class, bundle)
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view,
                                ListFragment.class, bundle)
                        .commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getFragments().get(0).getId()==R.layout.menu){
            super.onBackPressed();
        }
        else {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view,
                            MenuFragment.class, null)
                    .commit();
        }
    }
}