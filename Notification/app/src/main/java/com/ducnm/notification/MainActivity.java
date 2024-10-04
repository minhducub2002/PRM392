package com.ducnm.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Date;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private Button btnNotify;
    private EditText edtData;

    private void bindingView() {
        btnNotify = findViewById(R.id.btnNotify);
        edtData = findViewById(R.id.edtData);
    }

    private void bindingAction() {
        btnNotify.setOnClickListener(this::onBtnNotifyClick);
    }

    private void onBtnNotifyClick(View view) {
        String data = edtData.getText().toString();
        sendNotification(data);
    }

    private int id_notify = 0;

    private void sendNotification(String data) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("data", data);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_IMMUTABLE);
        Notification notification = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setContentTitle("DucNM dep trai vcl")
                .setContentText(data)
                .setSmallIcon(R.drawable.ic_stat_corona)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(data))
                .build();

//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE); // Cach 1
        NotificationManager manager = getSystemService(NotificationManager.class); // Cach 2

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID", "DucNM", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
//        manager.notify(id_notify++, notification);
        manager.notify((int) new Date().getTime(), notification);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bindingView();
        bindingAction();
        onReceivedData();
    }

    private void onReceivedData() {
        Intent i = getIntent();
        String data = i.getStringExtra("data");
        if (data != null) {
            edtData.setText(data);
        }
    }
}