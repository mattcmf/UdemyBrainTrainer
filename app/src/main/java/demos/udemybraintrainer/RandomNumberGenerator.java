package demos.udemybraintrainer;

import android.support.v7.app.AppCompatActivity;

import java.util.Random;

public class RandomNumberGenerator extends AppCompatActivity {

    public static int generate(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}