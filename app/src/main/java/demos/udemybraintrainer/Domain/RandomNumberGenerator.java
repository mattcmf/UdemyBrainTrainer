package demos.udemybraintrainer.Domain;

import java.util.Random;

public class RandomNumberGenerator {

    public static int generate(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}