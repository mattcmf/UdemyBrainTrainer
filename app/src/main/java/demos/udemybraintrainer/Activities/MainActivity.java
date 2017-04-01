package demos.udemybraintrainer.Activities;

import static demos.udemybraintrainer.R.id.btnStartGame;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
	Button startButton;

	long gameTimeRemaining;

	CountDownTimer timer;

	boolean gridStarted =false;


	final int offsetForCountDownTimer = 100;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		startUp();

		if (savedInstanceState != null) {
			String message = savedInstanceState.getString("message");
			Toast.makeText(this, message, Toast.LENGTH_LONG).show();
			startUp();
			//gameScore = savedInstanceState.getParcelable("gameScore");
			if (gridStarted){
				makeGridVisible();
				hidePlayButton();
			};
			answerGenerator =(savedInstanceState.getParcelable("answers"));
			questionGenerator=(savedInstanceState.getParcelable("question_generator"));
			questionDisplay.setText(formattedQuestion());
			gameScore = savedInstanceState.getParcelable("gameScore");
			gameScoreDisplay.setText(gameScore.getScore());
			gameTimeRemaining = savedInstanceState.getLong("gameTimeRemaining");
			StartTimer(gameTimeRemaining);

			try {
				returnSquareContent(answerGenerator .getAnswers());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		showGameStatsPanel();
	}

	public void showGameStatsPanel(){
		gameScoreDisplay.setVisibility(View.VISIBLE);
		questionDisplay.setVisibility(View.VISIBLE);
	}

	private String formattedQuestion(){
		int[] questionParts = questionGenerator.getCurrentQuestion();
		return questionParts[0] + " + " + questionParts[1];
	}


	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putString("message", "This is my message to be reloaded");
		outState.putParcelable("question_generator", questionGenerator);
		outState.putLong("gameTimeRemaining", gameTimeRemaining);
		outState.putParcelable("current_score", gameScore);
		outState.putParcelable("answers", answerGenerator);
		super.onSaveInstanceState(outState);
	}

	private void hidePlayButton() {
		startButton.setVisibility(View.INVISIBLE);
	}

	public void makeGridVisible(){
		topLeftTile.setVisibility(View.VISIBLE);
		topRightTile.setVisibility(View.VISIBLE);
		bottomLeft.setVisibility(View.VISIBLE);
		bottomRight.setVisibility(View.VISIBLE);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setContentView(R.layout.activity_main);
		tryAgain.setVisibility(View.INVISIBLE);
		gameMessage.setVisibility(View.INVISIBLE);
	}

	private void startUp() {
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
		startButton = (Button) findViewById(btnStartGame);

		tryAgain.setVisibility(View.INVISIBLE);
		gameMessage.setVisibility(View.INVISIBLE);
		topLeftTile.setVisibility(View.INVISIBLE);
		topRightTile.setVisibility(View.INVISIBLE);
		bottomLeft.setVisibility(View.INVISIBLE);
		bottomRight.setVisibility(View.INVISIBLE);
		gridStarted = true;
	}

	public void startGame(View view){
	    gameScore = new GameScore();
        questionGenerator = Graph.from(getApplication().getBaseContext()).questionGenerator();
        gameTimer = Graph.from(getApplication().getBaseContext()).gameTimer();
        answerGenerator = new AnswerGenerator();
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
        questionGenerator.generateQuestion();
	    answerGenerator.generateAnswersForQuestion(
			    questionGenerator.getCurrentQuestion());

	    questionDisplay.setText(formattedQuestion());
        answerGenerator.getCorrectAnswer();
	    try {
		    returnSquareContent(answerGenerator.getAnswers());
	    } catch (Exception e) {
		    e.printStackTrace();
	    }
    }

	public void restart(View view){
		GameScore.reset();
		gameScoreDisplay.setText(String.valueOf(GameScore.getScore()));
		StartTimer(gameTimer.getGameLength() + offsetForCountDownTimer);
		tryAgain.setVisibility(View.INVISIBLE);
		gameMessage.setVisibility(View.INVISIBLE);
		NewQuestion();
	}

    private void StartTimer(final long timerDuration) {
	    timer = new CountDownTimer(timerDuration,1000){
            public void onTick(long millisecondsUntilDone){
	            gameTimeRemaining = millisecondsUntilDone;
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
		makeGridVisible();

		topLeftTile.setText(String.valueOf(answers[0]));
		topRightTile.setText(String.valueOf(answers[1]));
		bottomLeft.setText(String.valueOf(answers[2]));
		bottomRight.setText(String.valueOf(answers[3]));

		return 0;
	}
}
