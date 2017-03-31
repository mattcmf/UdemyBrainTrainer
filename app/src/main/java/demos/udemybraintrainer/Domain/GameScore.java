package demos.udemybraintrainer.Domain;

import android.os.Parcel;
import android.os.Parcelable;

public class GameScore implements Parcelable {

    private static int score;
	private static int checkedAnswers;

	public GameScore(){
        GameScore.score = 0;
    }

    public static void updateScoreWithCorrectAnswer() {
	    GameScore.checkedAnswers++;
        GameScore.score++;
    }

	public static void updateScoreWithIncorrectAnswer() {
		GameScore.checkedAnswers++;
	}

    public static void reset() {
        GameScore.score=0;
	    GameScore.checkedAnswers=0;
    }

    public static String getScore() {
        return formatScore();
    }

	private static String formatScore() {
		return score + "/" + checkedAnswers;
	}

	public static void overrideScore(int score) {
        GameScore.score = score;
    }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

	}
}