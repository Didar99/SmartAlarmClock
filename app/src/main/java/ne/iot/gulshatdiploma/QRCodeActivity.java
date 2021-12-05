package ne.iot.gulshatdiploma;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRCodeActivity extends AppCompatActivity {

    String log_data;
    MediaPlayer mp;
    String ActCode = "kjn098hjnfd92h3fd2iojhnbc79h23jfc24uf024fhn";
    boolean detected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        scanCode();

        mp= MediaPlayer.create(getApplicationContext(), R.raw.alarm);
        mp.start();
        mp.setLooping(true);

    }
        public void scanCode() {
            IntentIntegrator intentIntegrator = new IntentIntegrator(QRCodeActivity.this);

            intentIntegrator.setPrompt("Fonarjyk ýakmak üçin SES (+) basyň");
            intentIntegrator.setBeepEnabled(true);
            intentIntegrator.setOrientationLocked(true);
            intentIntegrator.setCaptureActivity(Capture.class);
            intentIntegrator.initiateScan();
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (intentResult.getContents() != null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QRCodeActivity.this);
                builder.setTitle("Netije");
                builder.setMessage(intentResult.getContents());
                log_data = intentResult.getContents();
                Log.e("QR code => ", log_data);


                if (log_data.equals(ActCode)) {
                    Toast.makeText(QRCodeActivity.this, "Tassyklandy", Toast.LENGTH_SHORT).show();
                    mp.stop();
                    mp.release();
                    PrefConfig.saveSW2(getApplicationContext(), 0);
                    finish();
                } else {
                    Toast.makeText(QRCodeActivity.this, "Täzeden synanyş", Toast.LENGTH_SHORT).show();
                }

//                builder.setPositiveButton("Tamam", (dialog, which) -> {
//
//                    if (ActCode == null) {
//                        PrefConfig.saveQrCode(getApplicationContext(), log_data);
//                        Toast.makeText(QRCodeActivity.this, "Täze QR kod goşuldy", Toast.LENGTH_SHORT).show();
//                    } else {
//                        if (log_data.equals(ActCode)) {
//                            Toast.makeText(QRCodeActivity.this, "Tassyklandy", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(QRCodeActivity.this, "Täzeden synanyş", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    dialog.dismiss();
//                    finish();
//                });
//                builder.show();
            }
        }

    @Override
    public void onBackPressed() {
        if (!detected) {
            Toast.makeText(this, "Hiç hili QR kod skanirlenmedi", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }
    }