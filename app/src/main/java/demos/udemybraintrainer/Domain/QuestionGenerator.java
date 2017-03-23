package demos.udemybraintrainer.Domain;

import android.util.Log;

public class QuestionGenerator {

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
}