package com.estimote.notification;

import android.app.Application;
import android.content.Context;

import com.estimote.coresdk.common.config.EstimoteSDK;

//
// Running into any issues? Drop us an email to: contact@estimote.com
//

public class MyApplication extends Application {

    private boolean beaconNotificationsEnabled = false;

    @Override
    public void onCreate() {
        super.onCreate();

        // TODO: put your App ID and App Token here
        // You can get them by adding your app on https://cloud.estimote.com/#/apps
        EstimoteSDK.initialize(getApplicationContext(), "dotali-outlook-com-s-notif-07y", "862cb265ebd3de85bc721f403fe4d44e");

        // uncomment to enable debug-level logging
        // it's usually only a good idea when troubleshooting issues with the Estimote SDK
//        EstimoteSDK.enableDebugLogging(true);
    }

    public BeaconNotificationsManager createBeaconManager(String id, String message, int hour, int min){
        BeaconNotificationsManager beaconNotificationsManager = new BeaconNotificationsManager(this,hour,min);
        beaconNotificationsManager.addNotification(
                id,
                message,
                null
                );
        beaconNotificationsManager.setMonitoringStatus(true);

        return beaconNotificationsManager;
    }

    public void enableBeaconNotifications(BeaconNotificationsManager manager) {

            manager.startMonitoring();

    }

    public void disableBeaconNotifications(BeaconNotificationsManager manager){

            manager.stopMonitoring();


    }


    public boolean isBeaconNotificationsEnabled() {
        return beaconNotificationsEnabled;
    }
}
