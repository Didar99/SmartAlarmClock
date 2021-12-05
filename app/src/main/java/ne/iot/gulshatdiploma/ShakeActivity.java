package ne.iot.gulshatdiploma;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.seismic.ShakeDetector;


public class ShakeActivity extends AppCompatActivity implements ShakeDetector.Listener {

    int counter = 0;
    TextView count;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);

        count = findViewById(R.id.count);


        mp=MediaPlayer.create(getApplicationContext(), R.raw.alarm);
        mp.start();
        mp.setLooping(true);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector sd = new ShakeDetector(this);
        sd.start(sensorManager);

    }

    @Override public void hearShake() {
        counter ++;
        count.setText(String.valueOf(counter));
        if (counter == 10) {
            mp.stop();
            mp.release();
            PrefConfig.saveSW3(getApplicationContext(), 0);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (counter != 10) {
            Toast.makeText(this, "Ýerine ýetiriň!", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }
}
