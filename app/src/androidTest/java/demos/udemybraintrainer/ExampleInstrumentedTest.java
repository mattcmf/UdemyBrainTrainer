package demos.udemybraintrainer;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);


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
    */

    @Test
    public void LoadSplashScreen() {

        onView(withId(R.id.btnStartGame)).perform(click());

        //GoButtonIsGreen

        //OnClickDisappears
        onView(withId(R.id.btnStartGame)).check(doesNotExist());

        //OnClickShowMainMenu
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
    public void Timer(){
        //Timer is displayed on the top left
        //Given Go has been clicked
        //      Then set Timer to 30
        //Timer decrements at 1 second
        //Timer can display 0
        //Timer colour is yellow
        //Timer is prefixed by s
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
        //Then Reset score
        //And Reset question
        //And reset GameMessage output
        //And alignment is bottom middle below game message
    }
}
