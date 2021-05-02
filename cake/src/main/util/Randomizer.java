package main.util;

import java.util.Random;

public class Randomizer {

    public static int random(int min, int max){//both inclusive
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
}
