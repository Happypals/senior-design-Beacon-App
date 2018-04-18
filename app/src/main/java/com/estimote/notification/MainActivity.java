package com.estimote.notification;


import android.content.Context;
import android.content.Intent;
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

    private TimePicker timepciker;
    private int setHour;
    private int setMin;
    private Button button;
    private TextView textView;
    public static SharedPreferences sp;
    private int prefHour;
    private int prefMin;
    private String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        number = i.getStringExtra("number");
        timepciker = (TimePicker) this.findViewById(R.id.timePicker1);
        textView = (TextView) this.findViewById(R.id.tv);
        sp = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        prefHour = sp.getInt("preferred hour"+number,10);
        prefMin = sp.getInt("preferred min"+number, 0);
        String prefMinStr = (prefMin > 9 )?Integer.toString(prefMin):"0"+Integer.toString(prefMin);
        String prefHourStr=(prefHour > 9 )?Integer.toString(prefHour):"0"+Integer.toString(prefHour);
        textView.setText(prefHourStr+":"+prefMinStr);
        button = (Button) this.findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHour = timepciker.getCurrentHour();
                setMin = timepciker.getCurrentMinute();
                SharedPreferences.Editor editor = sp.edit();

                editor.putInt("preferred hour"+number,setHour);
                editor.putInt("preferred min"+number, setMin);
                editor.commit();
                textView.setText( new StringBuilder(Integer.toString(setHour))+":"+new StringBuilder(Integer.toString(setMin)));
                clickOnSet(v);

            }
        });





    }
    public void clickOnSet(View v){
        Intent intent = new Intent(this, Activity_setting.class);
        intent.putExtra("number",number);
        startActivity(intent);
    }


}
