package demos.udemybraintrainer.screenTests;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.when;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.format.DateUtils;

import com.squareup.spoon.Spoon;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.concurrent.TimeUnit;

import demos.udemybraintrainer.Activities.MainActivity;
import demos.udemybraintrainer.Domain.GameTimer;
import demos.udemybraintrainer.Domain.QuestionGenerator;
import demos.udemybraintrainer.ElapsedTimeIdlingResource;
import demos.udemybraintrainer.GraphSupportingFiles.Graph;
import demos.udemybraintrainer.R;
import demos.udemybraintrainer.TestApplication;


@RunWith(AndroidJUnit4.class)
public class GraphInstrementationTest {

    @Mock private QuestionGenerator questionGenerator;
    @Mock private Graph graph;
	@Mock private GameTimer gameTimer;

    @Rule  public ActivityTestRule<MainActivity> testRule = new ActivityTestRule<>(MainActivity.class, false, false);
    @Rule  public MockitoRule mockitoRule = MockitoJUnit.rule();

    private final long waitingTime = DateUtils.SECOND_IN_MILLIS * 5;
    ElapsedTimeIdlingResource idlingResource;

    @Before
    public void setup(){
        initGraphWithQuestionGenerator();


        //Idling resources
        IdlingPolicies.setIdlingResourceTimeout(waitingTime, TimeUnit.MILLISECONDS);
        idlingResource = new ElapsedTimeIdlingResource(waitingTime);
    }

    @Test
    public void whenStartGameClicked_ThenHide() {
        Mockito.when(questionGenerator.getCurrentQuestion()).thenReturn(new int[]{1, 2});
        testRule.launchActivity(null);

        Spoon.screenshot(testRule.getActivity(), "On-first-load");
        onView((withId(R.id.btnStartGame))).perform(click());
        Spoon.screenshot(testRule.getActivity(), "After-click-button-hidden");
        onView(withId(R.id.btnStartGame)).check((matches(not(isDisplayed()))));
    }

    @Test
    public void whenStartGame_ThenCountdownToZero() {
        Mockito.when(questionGenerator.getCurrentQuestion()).thenReturn(new int[]{1, 2});
	    Mockito.when(gameTimer.getGameLength()).thenReturn(5000);
        testRule.launchActivity(null);

        Spoon.screenshot(testRule.getActivity(), "On-first-load");
        onView(withId(R.id.txtTimer)).check((matches(withText("--:--"))));
        onView((withId(R.id.btnStartGame))).perform(click());
        Espresso.registerIdlingResources(idlingResource);
        onView(withId(R.id.txtTimer)).check((matches(withText("00:00s"))));
    }

    private void initGraphWithQuestionGenerator() {
        when(graph.questionGenerator()).thenReturn(questionGenerator);
	    when(graph.gameTimer()).thenReturn(gameTimer);
        TestApplication.setGraph(graph);
    }

    @After
    public void tearDown(){
        Espresso.unregisterIdlingResources(idlingResource);
    }
}
