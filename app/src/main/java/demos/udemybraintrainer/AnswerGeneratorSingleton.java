package demos.udemybraintrainer;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static demos.udemybraintrainer.Utilities.convertIntegers;
import static demos.udemybraintrainer.Utilities.shuffleArray;

public class AnswerGeneratorSingleton extends AppCompatActivity {

    private int[] answers;
    private int correctAnswer;
    private int maxAnswerRange;
    private int minAnswerRange;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void generateAnswersForQuestion(int questionFirstPart, int questionSecondPart) {
        setMinAnswerRange(questionFirstPart, questionSecondPart);
        setMaxAnswerRange(questionFirstPart, questionSecondPart);
        setCorrectAnswer(questionFirstPart + questionSecondPart);
        setAnswersOptions();
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setAnswersOptions() {
        int[] shuffledAnswers = padWithInvalidAnswers(getCorrectAnswer());
        shuffleArray(shuffledAnswers);
        this.answers = shuffledAnswers;
    }

    public int getCorrectAnswer() {
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

    public int[] getAnswers() {
        return answers;
    }
}