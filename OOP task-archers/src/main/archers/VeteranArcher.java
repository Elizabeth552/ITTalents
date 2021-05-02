package main.archers;

import main.Club;
import main.bows.CarbonBow;
import main.bows.ISeniorBow;

public class VeteranArcher extends SeniorArcher {

    public VeteranArcher(String name, char gender, int age, CarbonBow bow, int yearsOfTraining) {
        super(name, gender, age, bow, yearsOfTraining);
    }

    @Override
    boolean validYearsOfTraining(int yearsOfTraining) {
        return yearsOfTraining >= 10;
    }

    @Override
    protected int getMissChance() {
        return 0;
    }

    @Override
    public int getType() {
        return Club.VETERANS;
    }
}
