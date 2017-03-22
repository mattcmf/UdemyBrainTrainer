package demos.udemybraintrainer;

import org.junit.Test;

import demos.udemybraintrainer.Domain.GameScore;

import static org.junit.Assert.assertEquals;

 //@Mock MainActivity

public class ScoringUnitTests {

    @Test
    public void whenIIncrementScore_ThenScoreIsUpdated()
    {
        GameScore.setScore(10);
        GameScore.reset();
        assertEquals(0, GameScore.getScore());
    }

    @Test
    public void whenIResetScore_ThenScoreIs0()
    {
        GameScore.setScore(0);
        GameScore.incrementScore();
        assertEquals(1, GameScore.getScore());
    }
}