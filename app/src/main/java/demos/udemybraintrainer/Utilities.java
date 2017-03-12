package demos.udemybraintrainer;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;
import java.util.Random;

/**
 * Created by matthewframpton on 12/03/2017.
 */

public class Utilities {

    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++) {
            ret[i] = integers.get(i);
        }
        return ret;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    static void shuffleArray(int[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

}
