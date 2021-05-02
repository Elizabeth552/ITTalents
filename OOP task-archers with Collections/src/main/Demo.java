package main;

import main.archers.*;
import main.bows.*;
import main.util.Randomizator;

import java.util.Random;

public class Demo {

    public static void main(String[] args) {

        Club club = new Club("IT Archer", "Sofia", "Krasi");
        for (int i = 0; i < 40; i++) {
            int age = Randomizator.random(12, 52);
            char gender = new Random().nextBoolean() ? Archer.MALE : Archer.FEMALE;
            int chance = Randomizator.random(1,3);
            int bowStrength = Randomizator.random(20,48);
            int bowWeight = Randomizator.random(1,3);
            String name = gender == Archer.MALE ? Randomizator.randomMaleName() : Randomizator.randomFemaleName();
            Archer a;
            switch (chance){
                case 1:
                    a = new JuniorArcher(
                            name,
                            gender,
                            age,
                            new WoodenBow(
                                    "Hoyt",
                                    bowWeight,
                                    bowStrength),
                            1);
                    club.addArcher(a);
                    break;
                case 2:
                    ISeniorBow bow = new Random().nextBoolean() ?
                            new AluminumBow("Hoyt", bowWeight, bowStrength) :
                            new CarbonBow("Hoyt", bowWeight, bowStrength);
                    a = new SeniorArcher(
                            name,
                            gender,
                            age,
                            bow,
                            Randomizator.random(3,8));
                    club.addArcher(a);
                    break;
                case 3:
                    a = new VeteranArcher(
                            name,
                            gender,
                            age,
                            new CarbonBow(
                                    "Hoyt",
                                    bowWeight,
                                    bowStrength),
                            Randomizator.random(10,20));
                    club.addArcher(a);
                    break;
            }
        }
        club.startTournament();
    }
}
