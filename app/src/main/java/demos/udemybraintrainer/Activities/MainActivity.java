package demos.udemybraintrainer.Activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import demos.udemybraintrainer.Domain.AnswerGenerator;
import demos.udemybraintrainer.Domain.GameScore;
import demos.udemybraintrainer.Domain.GameTimer;
import demos.udemybraintrainer.Domain.QuestionGenerator;
import demos.udemybraintrainer.Domain.TimerActions;
import demos.udemybraintrainer.GraphSupportingFiles.Graph;
import demos.udemybraintrainer.R;

public class MainActivity extends AppCompatActivity {

    GameTimer gameTimer;
	GameScore gameScore;
    AnswerGenerator answerGenerator;
    QuestionGenerator questionGenerator;
    TimerActions timerActions;

	TextView timerDisplay;
	TextView gameScoreDisplay;
	TextView gameMessage;
	TextView questionDisplay;
	Button tryAgain;
	Button topLeftTile;
	Button topRightTile;
	Button bottomLeft;
	Button bottomRight;

	final int offsetForCountDownTimer = 100;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	    tryAgain = (Button) findViewById(R.id.playAgain);
        timerDisplay = (TextView) findViewById(R.id.txtTimer);
	    gameScoreDisplay = (TextView) findViewById(R.id.txtScore);
        questionDisplay = (TextView) findViewById(R.id.txtCurrentQuestion);
	    gameMessage = (TextView) findViewById(R.id.gameMessage);
        timerActions = new TimerActions();
	    topLeftTile = (Button) findViewById(R.id.topLeftTile);
	    topRightTile = (Button) findViewById(R.id.topRightTile);
	    bottomLeft = (Button) findViewById(R.id.bottomLeftTile);
	    bottomRight = (Button) findViewById(R.id.bottomRightTile);
    }

    public void startGame(View view){
	    gameScore = new GameScore();
        questionGenerator = Graph.from(getApplication().getBaseContext()).questionGenerator();
        gameTimer = Graph.from(getApplication().getBaseContext()).gameTimer();
        answerGenerator = new AnswerGenerator();
        Button startButton = (Button) findViewById(R.id.btnStartGame);
        startButton.setVisibility(View.INVISIBLE);
        StartTimer(gameTimer.getGameLength() + offsetForCountDownTimer);
        NewQuestion();
    }

	public void checkAnswer(View view){
		Button currentTile = (Button) view;
		int selectedAnswer = Integer.parseInt(currentTile.getText().toString());
		gameMessage.setVisibility(View.VISIBLE);
		if (selectedAnswer == answerGenerator.getCorrectAnswer()){
			GameScore.updateScoreWithCorrectAnswer();
			gameMessage.setText("Correct!");
		}else {
			GameScore.updateScoreWithIncorrectAnswer();
			gameMessage.setText("Wrong :(");
		}

		gameScoreDisplay.setText(String.valueOf(GameScore.getScore()));
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

	public void restart(View view){
		GameScore.reset();
		gameScoreDisplay.setText(String.valueOf(GameScore.getScore()));
		StartTimer(gameTimer.getGameLength() + offsetForCountDownTimer);
		tryAgain.setVisibility(View.INVISIBLE);
		gameMessage.setVisibility(View.INVISIBLE);
		NewQuestion();
	}

    private void StartTimer(final int timerDuration) {
        CountDownTimer timer = new CountDownTimer(timerDuration,1000){
            public void onTick(long millisecondsUntilDone){
                timerDisplay.setText(timerActions.setTimerFormat(millisecondsUntilDone));
            }

            public void onFinish(){
                timerDisplay.setText(timerActions.setTimerFormat(0));
	            tryAgain.setVisibility(View.VISIBLE);
	            gameMessage.setText("GAME OVER");
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
