package com.estimote.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import java.lang.Math;
import com.estimote.coresdk.recognition.packets.EstimoteLocation;
import com.estimote.coresdk.service.BeaconManager;
import com.estimote.monitoring.EstimoteMonitoring;
import com.estimote.monitoring.EstimoteMonitoringListener;
import com.estimote.monitoring.EstimoteMonitoringPacket;

import java.util.Calendar;
import java.util.List;


public class BeaconNotificationsManager {

    private static final String TAG = "BeaconNotifications";

    private BeaconManager beaconManager;
    private EstimoteMonitoring estimoteMonitoring;
    private boolean monitoring;
    private String enterMessages;
    private String exitMessages;
    private String deviceId;

    private Context context;
    private boolean withinTimeRange = false;
    private int notificationID = 0;
    private Calendar calendar = Calendar.getInstance();



    private static final int MINUTE_IN_HOUR = 60;



    public BeaconNotificationsManager(Context context, final int hour, final int min) {

        this.context = context;

        beaconManager = new BeaconManager(context);
        estimoteMonitoring = new EstimoteMonitoring();
        estimoteMonitoring.setEstimoteMonitoringListener(new EstimoteMonitoringListener() {
            @Override
            public void onEnteredRegion() {
                Log.d(TAG, "onEnteredRegion");
                String message = enterMessages;
                if (message != null) {
                    int curHour = calendar.get(Calendar.HOUR_OF_DAY);
                    int curMin = calendar.get(Calendar.MINUTE);
                    int setHour = hour;
                    int setMin = min;
                    System.out.println("curHour: "+curHour);
                    System.out.println("curMin: "+curMin);
                    System.out.println("setHour: "+setHour);
                    System.out.println("setMin: "+setMin);

                    int absolute_diff = Math.abs(MINUTE_IN_HOUR*(setHour-curHour)+(setMin-curMin));
                    System.out.println(absolute_diff);
                    Log.v("test",Integer.toString(absolute_diff));
                    if( absolute_diff < 10 ){
                        showNotification(message);
                    }

                }
            }

            @Override
            public void onExitedRegion() {
                Log.d(TAG, "onExitedRegion");
                String message = exitMessages;
                if (message != null) {
                    showNotification(message);
                }
            }
        });

        beaconManager.setLocationListener(new BeaconManager.LocationListener() {
            @Override
            public void onLocationsFound(List<EstimoteLocation> locations) {
                for (EstimoteLocation location : locations) {
                    if (location.id.toHexString().equals(deviceId)) {
                        estimoteMonitoring.startEstimoteMonitoring(new EstimoteMonitoringPacket(location.id.toHexString(), location.rssi, location.txPower, location.channel, location.timestamp));
                    }
                }
            }
        });
    }

    public void setMonitoringStatus(boolean val){
        this.monitoring = val;
    }
    public boolean getMonitoringStatus(){
        return this.monitoring;
    }
    public void addNotification(String deviceId, String enterMessage, String exitMessage) {
        this.deviceId = deviceId;
        enterMessages = enterMessage;
        exitMessages = exitMessage;

    }

    public void startMonitoring() {
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startLocationDiscovery();
            }
        });
        setMonitoringStatus(true);
    }

    public void stopMonitoring(){
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.stopLocationDiscovery();
            }
        });
        setMonitoringStatus(false);
    }

    private void showNotification(String message) {
        Intent resultIntent = new Intent(context, Activity_home.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Beacon Notifications")
                .setContentText(message)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(resultPendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++, builder.build());
    }
}
