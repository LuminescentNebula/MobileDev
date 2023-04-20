package com.mirea.mobiledev;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {
    View view;

    public MenuFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu, container, false);
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

        view.findViewById(R.id.notification).setOnClickListener(v -> {
            //Request POST NOTIFICATION permission, if not granted - request it again and show notification
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                showNotification();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                    showNotification();
                }
            }
        });

        view.findViewById(R.id.service).setOnClickListener(v -> {
            //start NameService
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW}, 1);
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SYSTEM_ALERT_WINDOW) == PackageManager.PERMISSION_GRANTED) {
                    Log.i("OSS", "Is true");
                }
            }
            Intent intent = new Intent(getActivity().getApplicationContext(), NameService.class);
            getActivity().startService(intent);
        });
        return view;
    };


    private void showNotification() {
        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(getContext().NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("THE_ID", "CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("CHANNEL_DESC");
            notificationManager.createNotificationChannel(channel);
        }

        Notification.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Notification.Builder(getContext(), "THE_ID")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("My Notification")
                    .setContentText("message")
                    .setAutoCancel(true);
        }

        notificationManager.notify(0, builder.build());
    }

}
