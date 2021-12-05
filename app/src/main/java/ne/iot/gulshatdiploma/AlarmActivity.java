package ne.iot.gulshatdiploma;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    ImageView imageView12, imageView122, imageView1222, imageView4, save, save2, save3;
    String log_data, ActCode;

    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        imageView12 = findViewById(R.id.imageView12);
        imageView122 = findViewById(R.id.imageView122);
        imageView1222 = findViewById(R.id.imageView1222);
        imageView4 = findViewById(R.id.imageView4);
        save = findViewById(R.id.save);
        save2 = findViewById(R.id.save2);
        save3 = findViewById(R.id.save3);

        ActCode = PrefConfig.loadQrCode(this);
        if (ActCode == null) {
            imageView4.setVisibility(View.GONE);
        } else {
            imageView4.setVisibility(View.VISIBLE);
        }

        imageView4.setOnClickListener(v -> {
            ActCode = null;
            imageView4.setVisibility(View.GONE);
    });

        alarmTimePicker = (TimePicker) findViewById(R.id.time_picker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }
    // OnToggleClicked() method is implemented the time functionality
    public void OnToggleClicked(View view) {
        long time;
        if (((ToggleButton) view).isChecked()) {
            Toast.makeText(AlarmActivity.this, "ALARM ON", Toast.LENGTH_SHORT).show();
            Calendar calendar = Calendar.getInstance();

            // calender is called to get current time in hour and minute
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

            // using intent i have class AlarmReceiver class which inherits
            // BroadcastReceiver
            Intent intent = new Intent(this, AlarmReceiver.class);

            // we call broadcast using pendingIntent
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
            if (System.currentTimeMillis() > time) {
                // setting time as AM and PM
                if (calendar.AM_PM == 0)
                    time = time + (1000 * 60 * 60 * 12);
                else
                    time = time + (1000 * 60 * 60 * 24);
            }
            // Alarm rings continuously until toggle button is turned off
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
            // alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (time * 1000), pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(AlarmActivity.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
        }
    }
}