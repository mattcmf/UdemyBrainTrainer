package demos.udemybraintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static demos.udemybraintrainer.TimerActions.setTimerFormat;

public class MainActivity extends AppCompatActivity {

    TextView timerDisplay;
    final int GameLength = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerDisplay = (TextView) findViewById(R.id.txtTimer);
    }

    public void startGame(View view){
        int offsetForCountDownTimer = 100;

        Button startButton = (Button) findViewById(R.id.btnStartGame);
        startButton.setVisibility(View.INVISIBLE);
        StartTimer(GameLength + offsetForCountDownTimer);
    }

    private void StartTimer(final int timerDuration) {
        CountDownTimer timer = new CountDownTimer(timerDuration,1000){
            public void onTick(long millisecondsUntilDone){
                timerDisplay.setText(setTimerFormat(millisecondsUntilDone));
            }

            public void onFinish(){
                timerDisplay.setText(setTimerFormat(0));
                Toast.makeText(MainActivity.this, "TIMER END", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }
}
