package demos.udemybraintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import static demos.udemybraintrainer.TimerActions.setTimerFormat;

public class MainActivity extends AppCompatActivity {

    TextView timerDisplay;
    TextView questionDisplay;
    final int GameLength = 5000;
    AnswerGenerator answerGenerator;
    QuestionGenerator questionGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerDisplay = (TextView) findViewById(R.id.txtTimer);
        questionDisplay = (TextView) findViewById(R.id.txtCurrentQuestion);
    }

    public void startGame(View view){
        int offsetForCountDownTimer = 100;
        answerGenerator = new AnswerGenerator();
        questionGenerator = new QuestionGenerator();

        Button startButton = (Button) findViewById(R.id.btnStartGame);
        //startButton.setVisibility(View.INVISIBLE);
        //StartTimer(GameLength + offsetForCountDownTimer);
        NewQuestion();
    }

    public void NewQuestion(){
        int[] questionParts;
        questionGenerator.generateQuestion();
        questionParts = questionGenerator.getCurrentQuestion();
        answerGenerator.generateAnswersForQuestion(questionParts[0], questionParts[1]);
        //questionDisplay.setText(questionParts[0] + " + " + questionParts[1]);
        questionDisplay.setText(questionGenerator.MockQuestion());

        Log.d("MATT-Question", questionParts[0] + " + " + questionParts[1]);
        Log.d("MATT-Answer Options", Arrays.toString(answerGenerator.getAnswers()));
        Log.d("MATT-Correct Answer", String.valueOf(answerGenerator.getCorrectAnswer()));
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
