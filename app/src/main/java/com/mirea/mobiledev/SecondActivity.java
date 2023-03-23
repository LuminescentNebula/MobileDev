package com.mirea.mobiledev;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {
    public static String TEXT = "Не передалось(";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);
        Bundle extras = getIntent().getExtras();
        TEXT=extras.getString(TEXT);
        TextView textView = findViewById(R.id.tv);
        textView.setText(TEXT);
    }

}
