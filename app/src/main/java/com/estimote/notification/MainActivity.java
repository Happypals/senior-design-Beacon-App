package com.estimote.notification;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;


//
// Running into any issues? Drop us an email to: contact@estimote.com
//

public class MainActivity extends AppCompatActivity {
    private static final String MyPREFERENCES = "MyPrefs" ;
    private static final String TAG = "MainActivity";
    private TimePicker timepciker;
    private int setHour;
    private int setMin;
    private Button button;
    private TextView textView;
    public static SharedPreferences sp;
    private int prefHour;
    private int prefMin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timepciker = (TimePicker) this.findViewById(R.id.timePicker1);
        textView = (TextView) this.findViewById(R.id.tv);
        sp = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        prefHour = sp.getInt("preferred hour",10);
        prefMin = sp.getInt("preferred min", 0);
        textView.setText(new StringBuilder(Integer.toString(prefHour))+":"+new StringBuilder(Integer.toString(prefMin)));
        button = (Button) this.findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHour = timepciker.getCurrentHour();
                setMin = timepciker.getCurrentMinute();
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("preferred hour",setHour);
                editor.putInt("preferred min", setMin);
                editor.commit();
                textView.setText( new StringBuilder(Integer.toString(setHour))+":"+new StringBuilder(Integer.toString(setMin)));

            }
        });


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
            app.enableBeaconNotifications();
        }
    }
}
