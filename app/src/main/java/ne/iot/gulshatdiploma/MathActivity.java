package ne.iot.gulshatdiploma;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

public class MathActivity extends AppCompatActivity {

    TextView equationText;
    EditText userInput;
    Button button;
    TextView displayResult;
    TextView displayStreak;
    QuizLogic quizzer;
    String result;
    MediaPlayer mp;
    boolean detected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        equationText = findViewById(R.id.equationText);

        button = findViewById(R.id.button);

        userInput = findViewById(R.id.userInput);
        displayResult = findViewById(R.id.displayResult);
//        displayStreak = findViewById(R.id.displayStreak);


        mp= MediaPlayer.create(getApplicationContext(), R.raw.alarm);
        mp.start();
        mp.setLooping(true);

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onStart() {
        super.onStart();

        quizzer = new QuizLogic();
        showNewEquation(quizzer);

//        displayStreak.setText("Current Streak: 0");

        final Handler handler = new Handler(Looper.getMainLooper());

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                userInput.onEditorAction(EditorInfo.IME_ACTION_DONE);

                result = quizzer.answerIsCorrect(userInput.getText().toString());
                if (result.equals("Correct")) {
                    displayResult.setText("Dogry!");

                    mp.stop();
                    mp.release();
                    PrefConfig.saveSW(getApplicationContext(), 0);
                    finish();
                } else {
                    String solution = ": ".concat(Long.toString(quizzer.getSolution()));
                    displayResult.setText("Ýalňyş! " +solution);
                }

                int currentStreak = quizzer.getStreak();
//                displayStreak.setText("Current Streak: ".concat(String.valueOf(currentStreak)));

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        }
                        catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                            ex.printStackTrace();
                        }
                        showNewEquation(quizzer);
                        userInput.getText().clear();
                        displayResult.setText("");
                    }
                });
            }
        });
    }

    public void showNewEquation(QuizLogic quizzer) {

        quizzer.generateEquation();

        TextView equationText = findViewById(R.id.equationText);
        equationText.setText(String.format("%s =", quizzer.getEquation()));
        showKeyboard(userInput);
    }

    public void showKeyboard(View inputField) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(inputField, InputMethodManager.SHOW_IMPLICIT);
    }


    @Override
    public void onBackPressed() {
        if (!detected) {
            Toast.makeText(this, "Boşlugy dolduryň!", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }
}