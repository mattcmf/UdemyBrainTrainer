package demos.udemybraintrainer;

//@Mock MainActivity

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AnswerGeneratorUnitTests {

    @Test
    public void whenIGenerateAQuestionWithInputs5and5_ThenAnswerIs10() {
        int correctAnswer;
        AnswerGenerator answerGenerator = new AnswerGenerator();

        answerGenerator.generateAnswersForQuestion(5, 7);
        correctAnswer =  answerGenerator.getCorrectAnswer();

        assertEquals(12, correctAnswer);
    }

    @Test
    public void whenIGenerateAQuestion_Then4AnswersAreGenerated() {
        int[] answers;
        AnswerGenerator answerGenerator = new AnswerGenerator();

        answerGenerator.generateAnswersForQuestion(2, 12);
        answers =  answerGenerator.getAnswers();

        assertEquals(4, answers.length);
    }

    @Test
    public void whenIGenerateAQuestion_Then4UniqueAnswersAreGenerated() {
        int[] answers;
        AnswerGenerator answerGenerator = new AnswerGenerator();

        answerGenerator.generateAnswersForQuestion(2, 4);
        answers = answerGenerator.getAnswers();

        boolean duplicates = false;
        int j, k;
        for (j = 0; j < answers.length; j++)
            for (k = j + 1; k < answers.length; k++)
                if (k != j && answers[k] == answers[j])
                    duplicates = true;

        assertEquals(false, duplicates);
    }
}