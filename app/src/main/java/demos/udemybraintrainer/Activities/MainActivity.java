package demos.udemybraintrainer.Activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import demos.udemybraintrainer.Domain.AnswerGenerator;
import demos.udemybraintrainer.Domain.GameTimer;
import demos.udemybraintrainer.Domain.QuestionGenerator;
import demos.udemybraintrainer.Domain.TimerActions;
import demos.udemybraintrainer.GraphSupportingFiles.Graph;
import demos.udemybraintrainer.R;

public class MainActivity extends AppCompatActivity {

    TextView timerDisplay;
    TextView questionDisplay;
    GameTimer gameTimer;
    AnswerGenerator answerGenerator;
    QuestionGenerator questionGenerator;
    TimerActions timerActions;
	Button topLeftTile;
	Button topRightTile;
	Button bottomLeft;
	Button bottomRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerDisplay = (TextView) findViewById(R.id.txtTimer);
        questionDisplay = (TextView) findViewById(R.id.txtCurrentQuestion);
        timerActions = new TimerActions();
	    topLeftTile = (Button) findViewById(R.id.topLeftTile);
	    topRightTile = (Button) findViewById(R.id.topRightTile);
	    bottomLeft = (Button) findViewById(R.id.bottomLeftTile);
	    bottomRight = (Button) findViewById(R.id.bottomRightTile);
    }

    public void startGame(View view){
        int offsetForCountDownTimer = 100;
        questionGenerator = Graph.from(getApplication().getBaseContext()).questionGenerator();
        gameTimer = Graph.from(getApplication().getBaseContext()).gameTimer();
        answerGenerator = new AnswerGenerator();
        Button startButton = (Button) findViewById(R.id.btnStartGame);
        startButton.setVisibility(View.INVISIBLE);
        StartTimer(gameTimer.getGameLength() + offsetForCountDownTimer);
        NewQuestion();
    }

    public void NewQuestion(){
        int[] questionParts;
        questionGenerator.generateQuestion();
        questionParts = questionGenerator.getCurrentQuestion();
        answerGenerator.generateAnswersForQuestion(questionParts[0], questionParts[1]);
        questionDisplay.setText(questionParts[0] + " + " + questionParts[1]);
        answerGenerator.getCorrectAnswer();
	    returnSquareContent(answerGenerator.getAnswers());
    }

    private void StartTimer(final int timerDuration) {
        CountDownTimer timer = new CountDownTimer(timerDuration,1000){
            public void onTick(long millisecondsUntilDone){
                timerDisplay.setText(timerActions.setTimerFormat(millisecondsUntilDone));
            }

            public void onFinish(){
                timerDisplay.setText(timerActions.setTimerFormat(0));
	            topLeftTile.setVisibility(View.INVISIBLE);
	            topRightTile.setVisibility(View.INVISIBLE);
	            bottomLeft.setVisibility(View.INVISIBLE);
	            bottomRight.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

	private int returnSquareContent(int[] answers){
		topLeftTile.setVisibility(View.VISIBLE);
		topRightTile.setVisibility(View.VISIBLE);
		bottomLeft.setVisibility(View.VISIBLE);
		bottomRight.setVisibility(View.VISIBLE);

		topLeftTile.setText(String.valueOf(answers[0]));
		topRightTile.setText(String.valueOf(answers[1]));
		bottomLeft.setText(String.valueOf(answers[2]));
		bottomRight.setText(String.valueOf(answers[3]));

		return 0;
	}
}
