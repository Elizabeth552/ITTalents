package main.util;

import java.util.Random;

public class Randomizator {

    private static final String[] MALE_NAMES = {"Boris", "Stoyan",  "Krasi", "Tolga", "Vlado", "Hristo", "Pavel"};
    private static final String[] FEMALE_NAMES = {"Monika", "Maria", "Krisi", "Sisi", "Reni"};

    public static int random(int min, int max){
        return new Random().nextInt(max - min + 1) + min;
    }

    public static String randomMaleName(){
        return MALE_NAMES[random(0, MALE_NAMES.length-1)];
    }

    public static String randomFemaleName(){
        return FEMALE_NAMES[random(0, FEMALE_NAMES.length-1)];
    }
}
