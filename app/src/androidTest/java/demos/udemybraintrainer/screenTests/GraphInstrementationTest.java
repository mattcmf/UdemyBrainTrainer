package demos.udemybraintrainer.screenTests;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import demos.udemybraintrainer.Activities.MainActivity;
import demos.udemybraintrainer.Domain.QuestionGenerator;
import demos.udemybraintrainer.GraphSupportingFiles.Graph;
import demos.udemybraintrainer.R;
import demos.udemybraintrainer.TestApplication;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.when;


@RunWith(AndroidJUnit4.class)
public class GraphInstrementationTest {

    @Mock private QuestionGenerator questionGenerator;
    @Mock private Graph graph;

    @Rule  public ActivityTestRule<MainActivity> testRule = new ActivityTestRule<>(MainActivity.class, false, false);
    @Rule  public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setup(){
        initGraphWithQuestionGenerator();
        Mockito.when(questionGenerator.getCurrentQuestion()).thenReturn(new int[]{1, 2});
        testRule.launchActivity(null);}

    @Test
    public void MockTest() {
        onView((ViewMatchers.withId(R.id.btnStartGame))).perform(click());
        onView((withId(R.id.btnStartGame))).perform(click());
    }

    @Test
    public void LoadSplashScreen() {
        Spoon.screenshot(testRule.getActivity(), "On-first-load");
        onView((withId(R.id.btnStartGame))).perform(click());
        Spoon.screenshot(testRule.getActivity(), "After-click-button-hidden");
        onView(withId(R.id.btnStartGame)).check((matches(not(isDisplayed()))));
    }

    private void initGraphWithQuestionGenerator() {
        when(graph.questionGenerator()).thenReturn(questionGenerator);
        TestApplication.setGraph(graph);
    }
}
