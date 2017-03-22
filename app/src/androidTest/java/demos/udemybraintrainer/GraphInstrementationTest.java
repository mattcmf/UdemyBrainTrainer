package demos.udemybraintrainer;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.when;


@RunWith(AndroidJUnit4.class)
public class GraphInstrementationTest {

    @Mock private QuestionGenerator questionGenerator;
    @Mock private Graph graph;

    @Rule public ActivityTestRule<MainActivity> testRule = new ActivityTestRule<>(MainActivity.class, false, false);
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void MockTest() {
        int[] TestValues = {1,2};
        initGraphWithQuestionGenerator();
        Mockito.when(questionGenerator.getCurrentQuestion()).thenReturn(TestValues);
        testRule.launchActivity(null);
        onView((withId(R.id.btnStartGame))).perform(click());
        onView((withId(R.id.btnStartGame))).perform(click());

    }

    private void initGraphWithQuestionGenerator() {
        when(graph.questionGenerator()).thenReturn(questionGenerator);
        TestApplication.setGraph(graph);
    }
}
