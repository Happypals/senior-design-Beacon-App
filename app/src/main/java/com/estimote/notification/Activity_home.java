package com.estimote.notification;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;

/**
 * Created by xizhouli on 2018/3/7.
 */

public class Activity_home extends AppCompatActivity {


    private Button set1;
    private Button set2;
    private Button set3;
    private Switch switch1;
    private Switch switch2;
    private Switch switch3;

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    private static final String TAG = "HomeActivity";
    private final static String MyPrefs = "MyPrefs";

    public static SharedPreferences sp;

    private static final String ID_1 = "8798f5f08f6a13687c594696ce302a2a";
    private static final String ID_2 = "cc55acc54830c49acc8b92ea90ea8608";
    private static final String ID_3 = "d0995e7d651deea96ee42cbd5d09ca3d";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        set1 = (Button) this.findViewById(R.id.set1);
        set2 = (Button) this.findViewById(R.id.set2);
        set3 = (Button) this.findViewById(R.id.set3);
        switch1 = (Switch) this.findViewById(R.id.switch1);
        switch2 = (Switch) this.findViewById(R.id.switch2);
        switch3 = (Switch) this.findViewById(R.id.switch3);

        sp = getSharedPreferences(MyPrefs, Context.MODE_PRIVATE);


        tv1 = (TextView) findViewById(R.id.textView2);
        tv2 = (TextView) findViewById(R.id.textView4);
        tv3 = (TextView) findViewById(R.id.textView5);

        tv1.setText(sp.getString("beaconName1", "bathroom"));
        tv2.setText(sp.getString("beaconName2", "bedroom"));
        tv3.setText(sp.getString("beaconName3", "kitchen"));

        set1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnSet1(v);
            }
        });
        set2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnSet2(v);
            }
        });
        set3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnSet3(v);
            }
        });

        switch1.setChecked(sp.getBoolean("BeaconOn1", true));
        switch2.setChecked(sp.getBoolean("BeaconOn2", true));
        switch3.setChecked(sp.getBoolean("BeaconOn3", true));

    }

    public void clickOnSet1(View v){
        Intent intent = new Intent(this, Activity_setting.class);
        intent.putExtra("identifier",ID_1);
        intent.putExtra("number","1");
        startActivity(intent);
    }

    public void clickOnSet2(View v){
        Intent intent = new Intent(this, Activity_setting.class);
        intent.putExtra("identifier",ID_2);
        intent.putExtra("number","2");
        startActivity(intent);
    }

    public void clickOnSet3(View v){
        Intent intent = new Intent(this, Activity_setting.class);
        intent.putExtra("identifier",ID_3);
        intent.putExtra("number","3");
        startActivity(intent);
    }
    @Override
    protected void onResume() {

        super.onResume();
        final MyApplication app = (MyApplication) getApplication();

        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            Log.e(TAG, "Can't scan for beacons, some pre-conditions were not met");
            Log.e(TAG, "Read more about what's required at: http://estimote.github.io/Android-SDK/JavaDocs/com/estimote/sdk/SystemRequirementsChecker.html");
            Log.e(TAG, "If this is fixable, you should see a popup on the app's screen right now, asking to enable what's necessary");
        } else {
            Log.d(TAG, "Enabling beacon notifications");
            final BeaconNotificationsManager manager1 = app.createBeaconManager(ID_1,sp.getString("beaconMessage1","Do you remember to take the insulin"),sp.getInt("preferred hour1",10),sp.getInt("preferred min1",0));
            final BeaconNotificationsManager manager2 = app.createBeaconManager(ID_2,sp.getString("beaconMessage2","Do you want to take some medicine right now"),sp.getInt("preferred hour2",10),sp.getInt("preferred min2",0));
            final BeaconNotificationsManager manager3 = app.createBeaconManager(ID_3,sp.getString("beaconMessage3","Eat some thing healthy today!"),sp.getInt("preferred hour3",10),sp.getInt("preferred min3",0));

            switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked){
                        app.enableBeaconNotifications(manager1);
                    }else{
                        app.disableBeaconNotifications(manager2);
                    }
                    SharedPreferences.Editor editor = sp.edit();

                    editor.putBoolean("BeaconOn1",isChecked);
                    editor.commit();
                }
            });

            switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked){
                        app.enableBeaconNotifications(manager2);
                    }else{
                        app.disableBeaconNotifications(manager2);
                    }
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("BeaconOn2",isChecked);
                    editor.commit();
                }
            });

            switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked){
                        app.enableBeaconNotifications(manager3);
                    }else{
                        app.disableBeaconNotifications(manager3);
                    }
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("BeaconOn3",isChecked);
                    editor.commit();
                }
            });


        }
    }
}
