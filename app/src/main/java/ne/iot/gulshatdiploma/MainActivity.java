package ne.iot.gulshatdiploma;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

public class MainActivity extends AppCompatActivity {
    Button button, confirm, confirm2, confirm3;
    ImageView avatar, more, more2, more3;
    TextView textView, textView3, time, time2, time3;
    Switch sw, sw2, sw3;
    int state, state_sw, state_sw2, state_sw3;
    View incLayout;
    String time_txt, time_txt2, time_txt3;


    TimePicker alarmTimePicker, alarmTimePicker2, alarmTimePicker3;
    PendingIntent pendingIntent, pendingIntent2, pendingIntent3;
    AlarmManager alarmManager, alarmManager2, alarmManager3;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.go_to);
        confirm = findViewById(R.id.confirm);
        confirm2 = findViewById(R.id.confirm2);
        confirm3 = findViewById(R.id.confirm3);
        avatar = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textView3 = findViewById(R.id.textView3);
        time = findViewById(R.id.time);
        time2 = findViewById(R.id.time2);
        time3 = findViewById(R.id.time3);
        sw = findViewById(R.id.switch1);
        sw2 = findViewById(R.id.switch2);
        sw3 = findViewById(R.id.switch3);
        more = findViewById(R.id.more);
        more2 = findViewById(R.id.more2);
        more3 = findViewById(R.id.more3);
        incLayout = findViewById(R.id.list);

        alarmTimePicker = (TimePicker) findViewById(R.id.time_picker);
        alarmTimePicker2 = (TimePicker) findViewById(R.id.time_picker2);
        alarmTimePicker3 = (TimePicker) findViewById(R.id.time_picker3);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager3 = (AlarmManager) getSystemService(ALARM_SERVICE);

        time_txt = PrefConfig.loadTime(this);
        time_txt2 = PrefConfig.loadTime2(this);
        time_txt3 = PrefConfig.loadTime3(this);
        state_sw = PrefConfig.loadSW(this);
        state_sw2 = PrefConfig.loadSW2(this);
        state_sw3 = PrefConfig.loadSW3(this);

        time.setText(getCurrentTime());
        time2.setText(getCurrentTime2());
        time3.setText(getCurrentTime3());

//        if (state_sw == 1) {
//            sw.setChecked(true);
//        } else if (state_sw == 0) {
//            sw.setChecked(false);
//        }
//        if (state_sw2 == 1) {
//            sw2.setChecked(true);
//        } else if (state_sw2 == 0) {
//            sw2.setChecked(false);
//        }
//        if (state_sw3 == 1) {
//            sw3.setChecked(true);
//        } else if (state_sw3 == 0) {
//            sw3.setChecked(false);
//        }

        time.setOnClickListener(v -> {
            alarmTimePicker.setVisibility(View.VISIBLE);
            confirm.setVisibility(View.VISIBLE);
        });
        time2.setOnClickListener(v -> {
            alarmTimePicker2.setVisibility(View.VISIBLE);
            confirm2.setVisibility(View.VISIBLE);
        });
        time3.setOnClickListener(v -> {
            alarmTimePicker3.setVisibility(View.VISIBLE);
            confirm3.setVisibility(View.VISIBLE);
        });

        button.setOnClickListener(v -> phantom());

        confirm.setOnClickListener(v -> {
            alarmTimePicker.setVisibility(View.GONE);
            confirm.setVisibility(View.GONE);
            time.setText(getCurrentTime());
            PrefConfig.saveTime(getApplicationContext(), getCurrentTime());

        });
        confirm2.setOnClickListener(v -> {
            alarmTimePicker2.setVisibility(View.GONE);
            confirm2.setVisibility(View.GONE);
            time2.setText(getCurrentTime2());
            PrefConfig.saveTime2(getApplicationContext(), getCurrentTime2());

        });
        confirm3.setOnClickListener(v -> {
            alarmTimePicker3.setVisibility(View.GONE);
            confirm3.setVisibility(View.GONE);
            time3.setText(getCurrentTime3());
            PrefConfig.saveTime3(getApplicationContext(), getCurrentTime3());

        });

        more.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MathActivity.class)));
        more2.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), QRCodeActivity.class)));
        more3.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), ShakeActivity.class)));

        sw.setOnClickListener(v -> {
            long time;
            if (sw.isChecked()) {
                Toast.makeText(MainActivity.this, "Jaň sagady guruldy", Toast.LENGTH_SHORT).show();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
                Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 7777, intent, FLAG_UPDATE_CURRENT);

                time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
                if (System.currentTimeMillis() > time) {
                    if (calendar.AM_PM == 0)
                        time = time + (1000 * 60 * 60 * 12);
                    else
                        time = time + (1000 * 60 * 60 * 24);
                }
                // Alarm rings continuously until toggle button is turned off
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);
                // alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (time * 1000), pendingIntent);
                PrefConfig.saveSW(getApplicationContext(), 1);
            } else {
                if (pendingIntent != null) {
                    pendingIntent.cancel();
                    alarmManager.cancel(pendingIntent);


                    Toast.makeText(MainActivity.this, "Jaň sagady öçürildi", Toast.LENGTH_SHORT).show();
                    PrefConfig.saveSW(getApplicationContext(), 0);
                } else {
                    if (pendingIntent != null) {
                        alarmManager.cancel(pendingIntent);
                        Toast.makeText(MainActivity.this, "Jaň sagady öçürildi", Toast.LENGTH_SHORT).show();
                        PrefConfig.saveSW(getApplicationContext(), 0);
                    }
                }
            }
        });
        sw2.setOnClickListener(v -> {
            long time;
            if (sw2.isChecked()) {
                Toast.makeText(MainActivity.this, "Jaň sagady guruldy", Toast.LENGTH_SHORT).show();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker2.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker2.getCurrentMinute());
                Intent intent = new Intent(getApplicationContext(), AlarmReceiver2.class);
                pendingIntent2 = PendingIntent.getBroadcast(getApplicationContext(), 8888, intent, 0);

                time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
                if (System.currentTimeMillis() > time) {
                    if (calendar.AM_PM == 0)
                        time = time + (1000 * 60 * 60 * 12);
                    else
                        time = time + (1000 * 60 * 60 * 24);
                }
                // Alarm rings continuously until toggle button is turned off
                alarmManager2.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent2);
                // alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (time * 1000), pendingIntent);
                PrefConfig.saveSW2(getApplicationContext(), 1);
            } else {
                if (pendingIntent2 != null) {
                    alarmManager2.cancel(pendingIntent2);
                    Toast.makeText(MainActivity.this, "Jaň sagady öçürildi", Toast.LENGTH_SHORT).show();
                    PrefConfig.saveSW2(getApplicationContext(), 0);
                }
            }
        });
        sw3.setOnClickListener(v -> {
            long time;
            if (sw3.isChecked()) {
                Toast.makeText(MainActivity.this, "Jaň sagady guruldy", Toast.LENGTH_SHORT).show();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker3.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker3.getCurrentMinute());
                Intent intent = new Intent(getApplicationContext(), AlarmReceiver3.class);
                pendingIntent3 = PendingIntent.getBroadcast(getApplicationContext(), 9999, intent, 0);

                time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
                if (System.currentTimeMillis() > time) {
                    if (calendar.AM_PM == 0)
                        time = time + (1000 * 60 * 60 * 12);
                    else
                        time = time + (1000 * 60 * 60 * 24);
                }
                // Alarm rings continuously until toggle button is turned off
                alarmManager3.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent3);
                // alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (time * 1000), pendingIntent);
                PrefConfig.saveSW3(getApplicationContext(), 1);
            } else {
                if (pendingIntent3 != null) {
                    alarmManager3.cancel(pendingIntent3);
                    Toast.makeText(MainActivity.this, "Jaň sagady öçürildi", Toast.LENGTH_SHORT).show();
                    PrefConfig.saveSW3(getApplicationContext(), 0);
                }
            }
        });
    }

    public void phantom() {
        avatar.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);
        textView3.setVisibility(View.GONE);
        button.setVisibility(View.GONE);
        incLayout.setVisibility(View.VISIBLE);
    }

    public String getCurrentTime(){
        String currentTime = alarmTimePicker.getCurrentHour()+":"+alarmTimePicker.getCurrentMinute();
        return currentTime;
    }
    public String getCurrentTime2(){
        String currentTime = alarmTimePicker2.getCurrentHour()+":"+alarmTimePicker2.getCurrentMinute();
        return currentTime;
    }
    public String getCurrentTime3(){
        String currentTime = alarmTimePicker3.getCurrentHour()+":"+alarmTimePicker3.getCurrentMinute();
        return currentTime;
    }

}