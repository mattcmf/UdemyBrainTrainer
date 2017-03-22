package demos.udemybraintrainer.Activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import demos.udemybraintrainer.Domain.AnswerGenerator;
import demos.udemybraintrainer.Domain.QuestionGenerator;
import demos.udemybraintrainer.Domain.TimerActions;
import demos.udemybraintrainer.GraphSupportingFiles.Graph;
import demos.udemybraintrainer.R;

public class MainActivity extends AppCompatActivity {

    TextView timerDisplay;
    TextView questionDisplay;
    final int GameLength = 5000;
    AnswerGenerator answerGenerator;
    QuestionGenerator questionGenerator;
    TimerActions timerActions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerDisplay = (TextView) findViewById(R.id.txtTimer);
        questionDisplay = (TextView) findViewById(R.id.txtCurrentQuestion);
        timerActions = new TimerActions();
    }

    public void startGame(View view){
        int offsetForCountDownTimer = 100;
        questionGenerator = Graph.from(getApplication().getBaseContext()).questionGenerator();
        answerGenerator = new AnswerGenerator();
        Button startButton = (Button) findViewById(R.id.btnStartGame);
        startButton.setVisibility(View.INVISIBLE);
        StartTimer(GameLength + offsetForCountDownTimer);
        NewQuestion();
    }

    public void NewQuestion(){
        int[] questionParts;
        questionGenerator.generateQuestion();
        questionParts = questionGenerator.getCurrentQuestion();
        answerGenerator.generateAnswersForQuestion(questionParts[0], questionParts[1]);
        questionDisplay.setText(questionParts[0] + " + " + questionParts[1]);
        questionDisplay.setText(questionGenerator.mockQuestion());
        answerGenerator.getCorrectAnswer();
    }

    private void StartTimer(final int timerDuration) {
        CountDownTimer timer = new CountDownTimer(timerDuration,1000){
            public void onTick(long millisecondsUntilDone){
                timerDisplay.setText(timerActions.setTimerFormat(millisecondsUntilDone));
            }

            public void onFinish(){
                timerDisplay.setText(timerActions.setTimerFormat(0));
                Toast.makeText(MainActivity.this, "TIMER END", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }
}
