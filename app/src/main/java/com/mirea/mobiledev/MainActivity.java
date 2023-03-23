package com.mirea.mobiledev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void onButtonClick(View view)
    {
        Log.i(TAG, "Clicked!");
    }


    private final String TAG = "onTag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint_layout);
        Log.i(TAG,"onCreate");

        TextView v= findViewById(R.id.textView2);
        v.setText("Установленный текст");
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setBackgroundResource(R.drawable.raven);
        Button button = findViewById(R.id.button2);
        button.setText(R.string.nbtn);

        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Clicked!");
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra(SecondActivity.TEXT, "Передалось!");
                startActivity(intent);
            }
        };
        button.setOnClickListener(onClickListener);

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"onDestroy");
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG,"onPause");
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.wtf(TAG,"onResume");
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }
}