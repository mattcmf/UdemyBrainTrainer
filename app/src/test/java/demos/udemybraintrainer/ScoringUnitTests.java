package demos.udemybraintrainer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import demos.udemybraintrainer.Domain.GameScore;

public class ScoringUnitTests {

    @Test
    public void whenIIncrementScore_ThenScoreIsUpdated()
    {
        GameScore.overrideScore(10);
        GameScore.reset();
        assertEquals(0, GameScore.getScore());
    }

    @Test
    public void whenIResetScore_ThenScoreIs0()
    {
        GameScore.overrideScore(0);
        //GameScore.updateScore();
        assertEquals(1, GameScore.getScore());
    }
}