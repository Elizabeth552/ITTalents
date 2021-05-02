package main.archers;

import main.archers.Archer;
import main.bows.Bow;
import main.util.Validator;

import java.util.*;

public class Club {

    private String name;
    private String address;
    private String trainer;
    private HashSet<Archer> archers;
    private int freePlaces;
    private HashMap<Integer, HashSet<Archer>> contestants;
    public static final int JUNIORS = 0;
    public static final int SENIORS = 1;
    public static final int VETERANS = 2;


    public Club(String name, String address, String trainer) {
        contestants = new HashMap<>();
        if (Validator.isValidString(name)) {
            this.name = name;
        }
        if (Validator.isValidString(address)) {
            this.address = address;
        }
        if (Validator.isValidString(trainer)) {
            this.trainer = trainer;
        }
        this.archers = new HashSet<>();

    }


    public void addArcher(Archer archer) {
        archers.add(archer);
    }

    public void startTournament() {

        showSortedContestantsByName();

        for (Archer a : archers) {
            if (!contestants.containsKey(a.getType())) {
                contestants.put(a.getType(), new HashSet<>());
            }
            contestants.get(a.getType()).add(a);
            a.attend();
            a.shootArrows();
        }

        printWinner();
        printAvgScore();
        printSharpShooter();
        printMoron();
        printWomenByAccuracy();
        printMenWithCarbonByExp();
    }

    private void printMenWithCarbonByExp() {
        System.out.println("============ MEN BY EXPERIENCE ==============");
        archers.stream()
                .filter(a -> a.getGender() == Archer.MALE && a.getBow().getType().equals(Bow.BOW_TYPE_CARBON))
                .sorted((a1, a2) -> Integer.compare(a1.getYearsOfTraining(), a2.getYearsOfTraining()))
                .forEach(archer -> {
                    System.out.println(archer.getName() + " -> " + archer.getYearsOfTraining());

                });




    /*    ArrayList<Archer> sortedByYears = new ArrayList<>(); //правим си локален арей лист
        for (HashSet<Archer> a : contestants.values()) {  //и за всеки хаш сет обхождаме стойностите
            for (Archer ar : a) {
                if (ar.getGender() == Archer.MALE && ar.getBow().getType().equals(Bow.BOW_TYPE_CARBON)) {
                    sortedByYears.add(ar);
                }
            }
        }
        sortedByYears.sort((a1, a2) -> Integer.compare(a1.getYearsOfTraining(), a2.getYearsOfTraining()));
        System.out.println("============ MEN BY EXPERIENCE ==============");
        for (Archer a : sortedByYears) {
            System.out.println(a.getName() + " -> " + a.getYearsOfTraining());
        }*/
    }

    private void printWomenByAccuracy() {
        System.out.println("============ WOMEN BY ACCURACY ==============");
        archers.stream()
                .filter(a -> a.getGender() == Archer.FEMALE)
                .sorted((a1, a2) -> Double.compare(a2.getStats().getAccuracy(), a1.getStats().getAccuracy()))
                .forEach(a -> {
                    System.out.println(a.getName() + " -> " + a.getStats().getAccuracy() + "%");
                });
    }

    private void printMoron() {
        System.out.println("Moron of the tournament:");
        System.out.println(archers.stream()
                .max(Comparator.comparingInt(a -> a.getStats().getMisses()))
                .get());

    }

    private void printSharpShooter() {
        System.out.println("Sharpshooter of the tournament:");
        System.out.println(archers.stream()
                .max(Comparator.comparingDouble(a -> a.getStats().getTensPercent()))
                .get());
    }

    private void printAvgScore() {
        System.out.println("Juniors avg:");
        System.out.println(getAvg(JUNIORS));
        System.out.println("Seniors avg:");
        System.out.println(getAvg(SENIORS));
        System.out.println("Veterans avg:");
        System.out.println(getAvg(VETERANS));
    }

    private double getAvg(int category) {
        return contestants.get(category).stream()
                .mapToInt(value -> value.getStats().getTotalPoints())
                .summaryStatistics()
                .getAverage();
     /*   int total = contestants.get(category).size();
        double sum = 0;
        for (Archer a : contestants.get(category)){
            sum += a.getStats().getTotalPoints();
        }
        return Math.round((sum / total) * 100.0) / 100.0;*/
    }

    private void printWinner() {
        System.out.println("Juniors winner:");
        System.out.println(getWinner(JUNIORS));
        System.out.println("Seniors winner:");
        System.out.println(getWinner(SENIORS));
        System.out.println("Veterans winner:");
        System.out.println(getWinner(VETERANS));
    }

    private Archer getWinner(int category) {
        Archer winner = contestants.get(category).stream()
                .max((a1, a2) -> {
                   if (a1.getStats().getTotalPoints() == a2.getStats().getTotalPoints()){
                       return a1.getStats().getTens() - a2.getStats().getTens();
                   }
                   return a1.getStats().getTotalPoints() - a2.getStats().getTotalPoints();
                }).get();

        return winner;
    }

    private void showSortedContestantsByName() {
        ArrayList<Archer> sorted = new ArrayList<>();
        sorted.addAll(archers);
        sorted.sort((a1, a2) -> a1.getName().compareTo(a2.getName()));
        int pos = 1;
        for (Archer a : sorted) {
            System.out.println((pos++) + ":" + a.getName());
        }
    }
}
