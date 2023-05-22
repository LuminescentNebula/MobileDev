package com.mirea.mobiledev;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "onTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.text);
        EditText editText = findViewById(R.id.edit);

        Intent intent = getIntent();
        String type = intent.getType();

        if ("text/plain".equals(type)) {
           String receivedText = intent.getStringExtra(Intent.EXTRA_TEXT);
            Log.d("TAG",receivedText);
            textView.setText(receivedText);
        }

        button.setOnClickListener(v -> {
            String shareText = String.valueOf(editText.getText());

            Intent sharingIntent = new Intent();
            sharingIntent.setType(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            startActivity(Intent.createChooser(sharingIntent, "Поделиться с помощью"));
        });
    }
}