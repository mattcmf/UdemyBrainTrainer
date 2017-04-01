package demos.udemybraintrainer.Domain;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static demos.udemybraintrainer.Utilities.convertIntegers;
import static demos.udemybraintrainer.Utilities.shuffleArray;

public class AnswerGenerator implements Parcelable {

    private int[] answers;
    private int correctAnswer;
    private int maxAnswerRange;
    private int minAnswerRange;

    public void generateAnswersForQuestion(int[] question) {
        setMinAnswerRange(question[0], question[1]);
        setMaxAnswerRange(question[0], question[1]);
        setCorrectAnswer(question[0]+ question[1]);
        setAnswersOptions();
        Log.d("MATT-Answer Options", Arrays.toString(answers));
    }

    public int[] padWithInvalidAnswers(int correctAnswer) {
        List<Integer> paddedList = new ArrayList<Integer>();
        paddedList.add(correctAnswer);

        while (paddedList.size() < 4 ){
            int randomNumber = RandomNumberGenerator.generate(getMinAnswerRange(),getMaxAnswerRange());

            if (!paddedList.contains(randomNumber)) {
                paddedList.add(randomNumber);
            }
        }
        return convertIntegers(paddedList);
    }

    public void setAnswersOptions() {
        int[] shuffledAnswers = padWithInvalidAnswers(getCorrectAnswer());
        shuffleArray(shuffledAnswers);
        this.answers = shuffledAnswers;
    }

    public int getCorrectAnswer() {
        Log.d("MATT-Correct Answer", String.valueOf(correctAnswer));
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setMaxAnswerRange(int questionFirstPart, int questionSecondPart) {
        int buffer = 20;
        this.maxAnswerRange = (questionFirstPart + questionSecondPart) + buffer;
    }

    public int getMaxAnswerRange() {
        return maxAnswerRange;
    }

    public void setMinAnswerRange(int questionFirstPart, int questionSecondPart) {
        if (questionFirstPart < minAnswerRange){
            this.minAnswerRange = questionFirstPart;
        }else{
            this.minAnswerRange = questionSecondPart;
        }
    }

    public int getMinAnswerRange() {
        return minAnswerRange;
    }

    public int[] getAnswers() throws Exception {
	    if (answers == null)
	    {
		    throw new Exception("No answers have been generated!");
	    }
        return answers;
    }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

	}
}