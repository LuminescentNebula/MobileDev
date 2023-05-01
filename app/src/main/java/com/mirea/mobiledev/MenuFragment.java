package com.mirea.mobiledev;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MenuFragment extends Fragment {
    View view;
    private static final int code=0;

    private static String TAG = "TAG";

    private final   ActivityResultLauncher<String> resultContracts =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                Log.i(TAG,"registerForActivityResult");
        if (isGranted) {
            Log.i(TAG,"isGranted");
            EditText editText = view.findViewById(R.id.field);
            TextView textView = view.findViewById(R.id.text_line);
            writeFileCommon(editText.getText().toString());
            textView.setText(readFileCommon());
        } else {
            Log.i(TAG,"NotGranted");
        }
    });

    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu, container, false);


        view.findViewById(R.id.app_specific).setOnClickListener(v -> {
            EditText editText = view.findViewById(R.id.field);
            writeFile(editText.getText().toString());
            TextView textView = view.findViewById(R.id.text_line);
            textView.setText(readFileSpecific());
        });

        view.findViewById(R.id.common).setOnClickListener(v -> {
            Log.i(TAG,"Clicked");

            EditText editText = view.findViewById(R.id.field);
            TextView textView = view.findViewById(R.id.text_line);
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED
            ) {
                Log.i(TAG,"Already");
                writeFileCommon(editText.getText().toString());
                textView.setText(readFileCommon());
            } else {
                Log.i(TAG, "Before request");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                    requestPermissions(new String[]{
//                            "android.permission.READ_EXTERNAL_STORAGE",
//                            "android.permission.WRITE_EXTERNAL_STORAGE"}, code);

//                ActivityCompat.requestPermissions(this,new String[]{
//                        "android.permission.READ_EXTERNAL_STORAGE",
//                        "android.permission.WRITE_EXTERNAL_STORAGE"},code);

//                resultContracts.launch("android.permission.READ_EXTERNAL_STORAGE");
               resultContracts.launch("android.permission.WRITE_EXTERNAL_STORAGE");

                    writeFileCommon(editText.getText().toString());
                    textView.setText(readFileCommon());

                    Log.i(TAG, "After request");
                }
            }
        });

        view.findViewById(R.id.shared).setOnClickListener(v -> {

        });

        view.findViewById(R.id.room).setOnClickListener(v -> {

        });
        return view;
    }

    ;

    void writeFile(String text) {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(getContext().openFileOutput("myfile", Context.MODE_PRIVATE)));
            bw.write(text);
            bw.close();
            Log.d("FILE", "Файл записан");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String readFileSpecific() {
        StringBuilder output = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(getContext().openFileInput("myfile")));
            String str = "";
            while ((str = br.readLine()) != null) {
                output.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    void writeFileCommon(String text) {
        File file = new File(Environment.getExternalStorageDirectory(), "example");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(text);
            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    String readFileCommon() {
        File file = new File(Environment.getExternalStorageDirectory(), "example");
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();
        } catch (IOException e) {
            // You'll need to add proper error handling here
        }
        return text.toString();
    }
}
