package demos.udemybraintrainer.Domain;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class QuestionGenerator implements Parcelable {

    private int[] currentQuestion;

    public void generateQuestion() {
        int[] question = new int[]{
                RandomNumberGenerator.generate(getMinQuestionRange(),getMaxQuestionRange()),
                RandomNumberGenerator.generate(getMinQuestionRange(),getMaxQuestionRange())};
        setCurrentQuestion(question);
    }

    public int[] getCurrentQuestion() {
        Log.d("MATT-Question", currentQuestion[0] + " + " + currentQuestion[1]);
        return currentQuestion;
    }

    public void setCurrentQuestion(int[] currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    private int getMinQuestionRange(){
        return 1;
    }

    private int getMaxQuestionRange(){
        return 30;
    }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

	}
}