package demos.udemybraintrainer;

import org.junit.Test;

import static org.junit.Assert.*;

 //@Mock MainActivity

public class DateBuilderUnitTests {

    @Test
    public void DateBuider_PrependSecondsWithZero()
    {
        assertEquals( "00:09s", MainActivity.timerDisplay(9000));
    }

    @Test
    public void DateBuider_PrependMinsWithZero()
    {
        assertEquals( "01:00s", MainActivity.timerDisplay(60000));
    }

    @Test
    public void DateBuider_CanSetMaxSeconds()
    {
        assertEquals( "00:59s", MainActivity.timerDisplay(59000));
    }

    @Test
    public void DateBuider_CanExceed1min()
    {
        assertEquals( "01:01s", MainActivity.timerDisplay(61000));
    }

    @Test
    public void DateBuider_CanExceed2mins()
    {
        assertEquals( "02:01s", MainActivity.timerDisplay(121000));
    }
}