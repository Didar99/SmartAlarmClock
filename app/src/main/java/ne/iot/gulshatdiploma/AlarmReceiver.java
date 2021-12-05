package ne.iot.gulshatdiploma;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    // implement onReceive() method
    public void onReceive(Context context, Intent intent) {

        // we will use vibrator first
        Vibrator vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(3000);

        Toast.makeText(context, "Berlen wagt geldi!", Toast.LENGTH_LONG).show();

        Intent myIntent = new Intent(context, MathActivity.class);
        context.startActivity(myIntent);

        }
}