package demos.udemybraintrainer;

//@Mock MainActivity

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class AnswerGeneratorUnitTests {

    @Test
    public void whenIGenerateAQuestionWithInputs5and5_ThenAnswerIs10() {
        int correctAnswer;
        AnswerGeneratorSingleton answerGeneratorSingleton = new AnswerGeneratorSingleton();

        answerGeneratorSingleton.generateAnswersForQuestion(5, 7);
        correctAnswer =  answerGeneratorSingleton.getCorrectAnswer();

        assertEquals(12, correctAnswer);
    }

    @Test
    public void whenIGenerateTwoQuestion_ThenTheAppDoesNotCarryOverData() {
        int correctAnswer;
        AnswerGeneratorSingleton answerGeneratorSingleton = new AnswerGeneratorSingleton();

        answerGeneratorSingleton.generateAnswersForQuestion(5, 7);
        correctAnswer =  answerGeneratorSingleton.getCorrectAnswer();

        assertEquals(12, correctAnswer);

        answerGeneratorSingleton.generateAnswersForQuestion(10, 5);
        correctAnswer =  answerGeneratorSingleton.getCorrectAnswer();

        assertEquals(15, correctAnswer);
    }

    @Test
    public void whenIGenerateAQuestion_Then4AnswersAreGenerated() {
        int[] answers;
        AnswerGeneratorSingleton answerGeneratorSingleton = new AnswerGeneratorSingleton();

        answerGeneratorSingleton.generateAnswersForQuestion(2, 12);
        answers =  answerGeneratorSingleton.getAnswers();

        assertEquals(4, answers.length);
    }

    @Test
    public void whenIGenerateAQuestion_Then4UniqueAnswersAreGenerated() {
        int[] answers;
        AnswerGeneratorSingleton answerGeneratorSingleton = new AnswerGeneratorSingleton();

        answerGeneratorSingleton.generateAnswersForQuestion(2, 4);
        answers = answerGeneratorSingleton.getAnswers();

        boolean duplicates = false;
        int j, k;
        for (j = 0; j < answers.length; j++)
            for (k = j + 1; k < answers.length; k++)
                if (k != j && answers[k] == answers[j])
                    duplicates = true;

        assertEquals(false, duplicates);
    }
}