package com.estimote.notification;

import android.app.Application;
import android.view.View;

import com.estimote.notification.estimote.BeaconNotificationsManager;
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
    public void enableBeaconNotifications(int hour, int min) {
        if (beaconNotificationsEnabled) { return; }

        BeaconNotificationsManager beaconNotificationsManager = new BeaconNotificationsManager(this,hour,min);
        beaconNotificationsManager.addNotification(
                "d0995e7d651deea96ee42cbd5d09ca3d",
                "You need to take your medcine now",
                "");
        beaconNotificationsManager.startMonitoring();
        beaconNotificationsEnabled = true;
    }

    public boolean isBeaconNotificationsEnabled() {
        return beaconNotificationsEnabled;
    }
}
