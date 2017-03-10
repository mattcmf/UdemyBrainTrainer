package demos.udemybraintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView timerDisplay;

    public static String TimerDisplay(long milliSeconds){
        String minsFormatted;
        String secondsFormatted;

        long mins =  milliSeconds / 60;
        if (mins < 10){
            minsFormatted = "0" + mins;
        }
        else
        {
            minsFormatted = String.valueOf(mins);
        }

        long seconds =  mins / 60;
        if (seconds < 10){
            secondsFormatted = "0" + seconds;
        }
        else
        {
            secondsFormatted = String.valueOf(seconds);
        }

        return minsFormatted + ":" + secondsFormatted;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerDisplay = (TextView) findViewById(R.id.txtTimer);
    }

    public void startGame(View view){
        Button startButton = (Button) findViewById(R.id.btnStartGame);
        startButton.setVisibility(View.INVISIBLE);
        StartTimer(5000 + 100);
    }

    private void StartTimer(final int timerDuration) {
        CountDownTimer timer = new CountDownTimer(timerDuration,1000){
            public void onTick(long millisecondsUntilDone){
                timerDisplay.setText(String.valueOf(millisecondsUntilDone/1000));
                Log.i("Seconds left:  ", String.valueOf(millisecondsUntilDone));
            }

            public void onFinish(){
                timerDisplay.setText("00:00s");
                Toast.makeText(MainActivity.this, "TIMER END", Toast.LENGTH_SHORT).show();
                Log.i("TIMER", "DONE!");
            }
        }.start();
    }
}
