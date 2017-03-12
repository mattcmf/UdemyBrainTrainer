package demos.udemybraintrainer;

import android.support.v7.app.AppCompatActivity;

public class GameScore extends AppCompatActivity {

    private static int score;

    GameScore(){
        GameScore.score = 0;
    }

    public static void incrementScore() {
        GameScore.score++;
    }

    public static void reset() {
        GameScore.score=0;
    }

    public static int getScore() {
        return GameScore.score;
    }

    public static void setScore(int score) {
        GameScore.score = score;
    }
}