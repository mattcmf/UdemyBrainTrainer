package demos.udemybraintrainer;

public class TimerActions {

    public static String setTimerFormat(long milliseconds) {

        int seconds = (int) (milliseconds / 1000);
        int secondsWithMins = seconds % 60;
        int mins = seconds / 60;

        String outputSeconds;
        String outputMins;

        if (mins < 10) {
            outputMins = "0" + mins;
        } else {
            outputMins = String.valueOf(mins);
        }

        if (secondsWithMins < 10) {
            outputSeconds = "0" + secondsWithMins;
        } else {
            outputSeconds = String.valueOf(secondsWithMins);
        }

        return outputMins + ":" + outputSeconds + "s";
    }
}