package demos.udemybraintrainer;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.format.DateUtils;

import com.squareup.spoon.Spoon;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Mock
    QuestionGenerator mockQuestionGenerator;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    private MainActivity mActivity;

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        mockQuestionGenerator = Mockito.mock(QuestionGenerator.class);
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("demos.udemybraintrainer", appContext.getPackageName());

    }

    /*
        ** TIPS **

        ** Tip: use tags to find out which button has been pressed
        ** Think about using a way to re-use formatting
        *
        * ** use tools in xml instead of android for properties that should only display at design time.
    */

    @Test
    public void LoadSplashScreen() {

        Spoon.screenshot(mActivity, "On-first-load");

        onView(withId(R.id.btnStartGame)).perform(click());

        //GoButtonIsGreen

        //OnClickDisappears
        Spoon.screenshot(mActivity, "After-click-button-hidden");

        onView(withId(R.id.btnStartGame)).check((matches(not(isDisplayed()))));


        //OnClickShowMainMenu
    }

    @Test
    public void Timer(){

        long waitingTime = DateUtils.SECOND_IN_MILLIS * 5;

        IdlingPolicies.setIdlingResourceTimeout(
                waitingTime, TimeUnit.MILLISECONDS);

        Spoon.screenshot(mActivity, "On-first-load");

        onView(withId(R.id.txtTimer)).check((matches(withText("--:--"))));

        onView((withId(R.id.btnStartGame))).perform(click());

        // Now we wait
        IdlingResource idlingResource = new ElapsedTimeIdlingResource(waitingTime);
        Espresso.registerIdlingResources(idlingResource);

        onView(withId(R.id.txtTimer)).check((matches(withText("00:00s"))));

        // Clean up
        Espresso.unregisterIdlingResources(idlingResource);
    }

    @Test
    public void LoadGameScreen(){



        //Display a 4 grid in the middle of the screen

        //TopLeft is pink
        //TopRight is blue
        //BottomLeft is red
        //BottomRight is green

        //All Squares have no values
    }

    @Test
    public void QuestionBox(){
        //Given Go has been clicked
        // Two numbers between 1 and 40 displayed
        // Format is "X + Y"
        //Background is transparent
        //Alignment is top center
    }

    @Test
    public void ScoreDisplay() {

        //Box colour?
        //Alignment is top Right
        onView(withId(R.id.txtScore)).check((matches(withText("0/0"))));

        //Mockito.when(ag.getMinAnswerRange()).thenReturn(1);
        //Mockito.when(ag.getMaxAnswerRange()).thenReturn(1);

        Mockito.when(mockQuestionGenerator.mockQuestion()).thenReturn("1 + 1");

        onView((withId(R.id.btnStartGame))).perform(click());

        onView(withId(R.id.txtScore)).check((matches(withText("1 + 1"))));
    }

    @Test
    public void ScoreCorrect(){
        //Given a fresh load Display 0/0
        //When I answer a question correct Then
        //Then Increase both score and question count
        //And display new question
        //And display new answers
    }

    @Test
    public void ScoreIncorrect(){
        //Given a fresh load Display 0/0
        //When I answer a question correct Then
        //Then Increase ONLY question count
        //And display new question
        //And display new answers
    }

    @Test
    public void GameMessageCorrect(){
        //Given I have answered a question right
        //Then display "Correct"
        //And layout is bottom middle
    }


    @Test
    public void GameMessageTimerEnd(){
        //Given the timer is 0
        //And alignment is bottom middle
        //Then "Your score: 5/16"
    }

    @Test
    public void PlayAgainButton(){
        //Given the timer is at 0
        // When the play again button has been clicked
        //Then reset score
        //And reset question
        //And reset GameMessage output
        //And alignment is bottom middle below game message
    }
}
