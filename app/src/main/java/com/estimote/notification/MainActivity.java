package com.estimote.notification;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;


import android.widget.TimePicker;


//
// Running into any issues? Drop us an email to: contact@estimote.com
//

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TimePicker timepciker;
    private int setHour;
    private int setMin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timepciker = (TimePicker) this.findViewById(R.id.timePicker1);

        setHour = timepciker.getCurrentHour();
        setMin = timepciker.getCurrentMinute();



    }

    @Override
    protected void onResume() {
        super.onResume();

        MyApplication app = (MyApplication) getApplication();

        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            Log.e(TAG, "Can't scan for beacons, some pre-conditions were not met");
            Log.e(TAG, "Read more about what's required at: http://estimote.github.io/Android-SDK/JavaDocs/com/estimote/sdk/SystemRequirementsChecker.html");
            Log.e(TAG, "If this is fixable, you should see a popup on the app's screen right now, asking to enable what's necessary");
        } else if (!app.isBeaconNotificationsEnabled()) {
            Log.d(TAG, "Enabling beacon notifications");
            app.enableBeaconNotifications(setHour, setMin);
        }
    }
}
