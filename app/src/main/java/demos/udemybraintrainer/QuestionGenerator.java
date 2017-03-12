package demos.udemybraintrainer;

import android.support.v7.app.AppCompatActivity;

public class QuestionGenerator extends AppCompatActivity {

    private int[] currentQuestion;
    private final int minQuestionRange = 1;
    private final int maxQuestionRange = 1;

    public void generateQuestion() {
        int[] question = new int[]{
                RandomNumberGenerator.generate(minQuestionRange,maxQuestionRange),
                RandomNumberGenerator.generate(minQuestionRange,maxQuestionRange)};
        setCurrentQuestion(question);
    }

    public int[] getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int[] currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
}