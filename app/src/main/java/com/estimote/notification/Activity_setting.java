package com.estimote.notification;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;



/**
 * Created by xizhouli on 2018/3/22.
 */

public class Activity_setting extends AppCompatActivity {
    SharedPreferences sp;
    private int prefHour;
    private int prefMin;
    String number;
    String id;
    EditText locationEdit;
    EditText messageEdit;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Intent intent = getIntent();
        id =intent.getStringExtra("identifier");
        number = intent.getStringExtra("number");
        final LinearLayout base = (LinearLayout) findViewById(R.id.layout);
        Button save = (Button) this.findViewById(R.id.save);
        Button cancel = (Button) this.findViewById(R.id.cancel);
        Button setReminderTime = (Button) this.findViewById(R.id.setReminderTime);
        locationEdit = (EditText) this.findViewById(R.id.location_edit);
        messageEdit = (EditText) this.findViewById(R.id.message_edit);
        TextView reminderEdit = (TextView) this.findViewById(R.id.reminder_edit);
        sp = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        locationEdit.setText(sp.getString("beaconName"+number,"bathroom"));
        messageEdit.setText(sp.getString("beaconMessage"+number,"Do you need any medcine"));
        prefHour = sp.getInt("preferred hour"+number,10);
        prefMin = sp.getInt("preferred min"+number, 0);
        String prefMinStr = (prefMin > 9 )?Integer.toString(prefMin):"0"+Integer.toString(prefMin);
        String prefHourStr=(prefHour > 9 )?Integer.toString(prefHour):"0"+Integer.toString(prefHour);
        reminderEdit.setText(prefHourStr+":"+prefMinStr);

        base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(base.getWindowToken(), 0);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnSave(v);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnCancel(v);
            }
        });
        setReminderTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnsetReminderTime(v);
            }
        });
    }

    public void clickOnSave(View v){
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString("beaconName"+number,locationEdit.getText().toString());
        editor.putString("beaconMessage"+number, messageEdit.getText().toString());
        editor.commit();
        Intent i = new Intent(this,Activity_home.class);
        startActivity(i);
    }

    public void clickOnCancel(View v){
        Intent i = new Intent(this,Activity_home.class);
        startActivity((i));
    }

    public void clickOnsetReminderTime(View v){
        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("number",number);
        startActivity((i));
    }
}
