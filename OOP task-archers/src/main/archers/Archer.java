package main.archers;

import main.bows.IBow;
import main.util.Validator;

public abstract class Archer {
    public static final char FEMALE = 'F';
    public static final char MALE = 'M';

    private String name;
    private char gender;
    private int age;
    protected IBow bow;
    private int yearsOfTraining;
    private int pastTournaments;
    private Stats stats;

    public Archer(String name, char gender, int age, IBow bow, int yearsOfTraining) {
        if(Validator.isValidString(name)) {
            this.name = name;
        }
        if(gender == MALE || gender == FEMALE) {
            this.gender = gender;
        }
        else {
            this.gender = 'M';
        }
        if(age >= 12 && age <= 52) {
            this.age = age;
        }
        else{
            this.age = 33;
        }
        this.bow = bow;
        if(validYearsOfTraining(yearsOfTraining)) {
            this.yearsOfTraining = yearsOfTraining;
        }
        this.pastTournaments = 0;
    }

    public int getYearsOfTraining() {
        return yearsOfTraining;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    abstract boolean validYearsOfTraining(int yearsOfTraining);

    public void attend() {
        stats = new Stats(getArrowsNumber());
        pastTournaments++;
    }

    public void shootArrows(){
        int totalScore = 0;
        for (int i = 0; i < getArrowsNumber(); i++) {
            int score = getArrowScore();
            if(score == 0){
                stats.addMiss();
            }
            if(score == 10){
                stats.addTen();
            }
            totalScore += score;
        }
        stats.setTotalPoints(totalScore);
    }

    public abstract int getArrowsNumber();
    protected abstract int getArrowScore();
    protected abstract int getMissChance();
    public abstract int getType();

    public Stats getStats() {
        return stats;
    }

    public IBow getBow() {
        return bow;
    }

    @Override
    public String toString() {
        return name + " - " + stats.getTotalPoints();
    }
}
