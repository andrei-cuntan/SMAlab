package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private BroadcastReceiver PowerConnectionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String chgmode = "";
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
            int chgplug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1);
            boolean usbchg = chgplug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acchg = chgplug == BatteryManager.BATTERY_PLUGGED_AC;
            boolean ischg = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
            if (ischg){
                chgmode = "Charging via: ";
                if (usbchg)
                    chgmode = chgmode + "USB";
                if (acchg)
                    chgmode = chgmode + "AC";
            }
            else{
                chgmode = "Not charging";
            }

            float percentage = level/ (float) scale;
            TextView battText = (TextView)findViewById(R.id.battText);
            TextView chgState = (TextView)findViewById(R.id.chgState);
            battText.setText((int)((percentage)*100) + "%");
            chgState.setText(chgmode);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = "aaaa";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel("ID", name, importance);
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "ID")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(battText.getText())
                    .setContentText(chgState.getText());
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
            notificationManagerCompat.notify(1, builder.build());
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        mContext.registerReceiver(PowerConnectionReceiver,iFilter);

    }
}