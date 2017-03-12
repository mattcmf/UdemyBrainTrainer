package demos.udemybraintrainer;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

//@Mock MainActivity

public class QuestionGeneratorUnitTests {

    @Test
    public void whenIGenerateAQuestion_ThenTwoIntsAreGenerated() {
        int[] question;
        QuestionGenerator questionGenerator = new QuestionGenerator();

        questionGenerator.generateQuestion();
        question = questionGenerator.getCurrentQuestion();

        assertEquals(2, question.length);
    }

    @Test
    public void whenIGenerateAQuestion_ThenFirstNumberIsGreaterThan0() {
        int[] question;
        QuestionGenerator questionGenerator = new QuestionGenerator();

        questionGenerator.generateQuestion();
        question = questionGenerator.getCurrentQuestion();

        assertEquals(question[0] > 0, true);
    }

    @Test
    public void whenIGenerateAQuestion_ThenFirstNumberIsLessThan30(){
        int[] question;
        QuestionGenerator questionGenerator = new QuestionGenerator();

        questionGenerator.generateQuestion();
        question = questionGenerator.getCurrentQuestion();

        assertEquals(question[0] < 30, true);
    }

    @Test
    public void whenIGenerateAQuestion_ThenSecondNumberIsGreaterThan0() {
        int[] question;
        QuestionGenerator questionGenerator = new QuestionGenerator();

        questionGenerator.generateQuestion();
        question = questionGenerator.getCurrentQuestion();

        assertEquals(question[1] > 0, true);
    }

    @Test
    public void whenIGenerateAQuestion_ThenSecondNumberIsLessThan30(){
        int[] question;
        QuestionGenerator questionGenerator = new QuestionGenerator();

        questionGenerator.generateQuestion();
        question = questionGenerator.getCurrentQuestion();

        assertEquals(question[1] < 30, true);
    }
}