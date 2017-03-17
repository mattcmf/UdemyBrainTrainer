package demos.udemybraintrainer;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;

public class DateBuilderUnitTestsMockTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    TimerActionsInterface timerActionsMock;

//    @Before
//    public void setuo(){
//        timerActionsMock = Mockito.mock(TimerActionsInterface.class);
//    }

    @Test
    public void DateBuider_PrependSecondsWithZero() {
        Mockito.when(timerActionsMock.setTimerFormat(9000)).thenReturn("00:09s");

        assertEquals( "00:09s", timerActionsMock.setTimerFormat(9000));
    }
}