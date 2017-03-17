package demos.udemybraintrainer;

public class QuestionGenerator {

    private int[] currentQuestion;

    public void generateQuestion() {
        int[] question = new int[]{
                RandomNumberGenerator.generate(getMinQuestionRange(),getMaxQuestionRange()),
                RandomNumberGenerator.generate(getMinQuestionRange(),getMaxQuestionRange())};
        setCurrentQuestion(question);
    }

    public int[] getCurrentQuestion() {
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

    public String mockQuestion() {
        return "Sponge Bob";
    }

}