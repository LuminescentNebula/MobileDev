package com.mirea.mobiledev;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class SecondFragment extends Fragment {
    private final String TAG = "FRAGMENT2";
    View view;
    public SecondFragment(){
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
        view= inflater.inflate(R.layout.constraint_layout, container, false);

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.pulse);
        view.findViewById(R.id.imageView).startAnimation(animation);
        view.findViewById(R.id.constraint_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                if (getArguments()!=null) {
                    bundle.putInt("some_int", getArguments().getInt("some_int", 0) + 1);
                } else {
                    bundle.putInt("some_int",0);
                }
                Navigation.findNavController(view).navigate(R.id.action_second_to_third,bundle);
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
            TextView v = view.findViewById(R.id.constraint_text);
            v.setText(String.valueOf(bundle.getInt("some_int",0)));
        }
    }
}

