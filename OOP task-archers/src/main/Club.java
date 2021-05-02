package main;

import main.archers.Archer;
import main.bows.Bow;
import main.util.Validator;

public class Club {

    private String name;
    private String address;
    private String trainer;
    private Archer[] archers;
    private int freePlaces;
    private Archer[][] contestants;
    public static final int JUNIORS = 0;
    public static final int SENIORS = 1;
    public static final int VETERANS = 2;


    public Club(String name, String address, String trainer, int capacity) {
        contestants = new Archer[3][40];
        if(Validator.isValidString(name)) {
            this.name = name;
        }
        if(Validator.isValidString(address)) {
            this.address = address;
        }
        if(Validator.isValidString(trainer)) {
            this.trainer = trainer;
        }
        if(capacity >= 40) {
            this.archers = new Archer[capacity];
        }
        this.freePlaces = capacity;
    }


    public void addArcher(Archer archer){
        if(freePlaces > 0){
            archers[archers.length - freePlaces--] = archer;
        }
    }

    public void startTournament(){

        showSortedContestantsByName();

        for (int i = 0; i < archers.length; i++) {
            Archer a = archers[i];
            for (int j = 0; j < contestants[a.getType()].length; j++) {
                if(contestants[a.getType()][j] == null){
                    contestants[a.getType()][j] = a;
                    break;
                }
            }
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

    private void printMenWithCarbonByExp(){
        Archer[] carbonMen = new Archer[40];
        int freePlaces = 40;
        for (int i = 0; i < contestants.length; i++) {
            for (int j = 0; j < contestants[i].length; j++) {
                if(contestants[i][j] != null) {
                    Archer a = contestants[i][j];
                    if(a.getGender() == Archer.MALE && a.getBow().getType().equals(Bow.BOW_TYPE_CARBON)){
                        carbonMen[carbonMen.length - freePlaces--] = a;
                    }
                }
            }
        }

        //sort by yesrs but ignore null cells
        for (int i = 0; i < carbonMen.length - freePlaces; i++) {
            for (int j = 0; j < carbonMen.length - freePlaces - i - 1; j++) {
                if(carbonMen[j].getYearsOfTraining() < carbonMen[j+1].getYearsOfTraining()){
                    Archer temp = carbonMen[j];
                    carbonMen[j] = carbonMen[j+1];
                    carbonMen[j+1] = temp;
                }
            }
        }
        System.out.println("============ MEN BY EXPERIENCE ==============");
        for (int i = 0; i < carbonMen.length - freePlaces; i++) {
            System.out.println(carbonMen[i].getName() + " -> " + carbonMen[i].getYearsOfTraining());
        }

    }

    private void printWomenByAccuracy(){
        Archer[] women = new Archer[40];
        int freePlaces = 40;
        for (int i = 0; i < contestants.length; i++) {
            for (int j = 0; j < contestants[i].length; j++) {
                if(contestants[i][j] != null) {
                    Archer a = contestants[i][j];
                    if(a.getGender() == Archer.FEMALE){
                        women[women.length - freePlaces--] = a;
                    }
                }
            }
        }
        //sort by accuracy but ignore null cells
        for (int i = 0; i < women.length - freePlaces; i++) {
            for (int j = 0; j < women.length - freePlaces - i - 1; j++) {
                if(women[j].getStats().getAccuracy() < women[j+1].getStats().getAccuracy()){
                    Archer temp = women[j];
                    women[j] = women[j+1];
                    women[j+1] = temp;
                }
            }
        }
        System.out.println("============ WOMEN BY ACCURACY ==============");
        for (int i = 0; i < women.length - freePlaces; i++) {
            System.out.println(women[i].getName() + " -> " + women[i].getStats().getAccuracy() + "%");
        }
    }

    private void printMoron() {
        Archer moron = null;
        for (int i = 0; i < contestants.length; i++) {
            for (int j = 0; j < contestants[i].length; j++) {
                if(contestants[i][j] != null){
                    Archer a = contestants[i][j];
                    if(moron == null){
                        moron = a;
                    }
                    else{
                        if(a.getStats().getMisses() > moron.getStats().getMisses()){
                            moron = a;
                        }
                    }
                }
            }
        }
        System.out.println("Moron of the tournament:");
        System.out.println(moron);
    }

    private void printSharpShooter() {
        Archer sharpShooter = null;
        for (int i = 0; i < contestants.length; i++) {
            for (int j = 0; j < contestants[i].length; j++) {
                if(contestants[i][j] != null){
                    Archer a = contestants[i][j];
                    if(sharpShooter == null){
                        sharpShooter = a;
                    }
                    else{
                        if(a.getStats().getTensPercent() > sharpShooter.getStats().getTensPercent()){
                            sharpShooter = a;
                        }
                    }
                }
            }
        }
        System.out.println("Sharpshooter of the tournament:");
        System.out.println(sharpShooter);
    }

    private void printAvgScore() {
        System.out.println("Juniors avg:");
        System.out.println(getAvg(JUNIORS));
        System.out.println("Seniors avg:");
        System.out.println(getAvg(SENIORS));
        System.out.println("Veterans avg:");
        System.out.println(getAvg(VETERANS));
    }

    private double getAvg(int category){
        int total = 0;
        double sum = 0;
        for (int i = 0; i < contestants[category].length; i++) {
            Archer a = contestants[category][i];
            if(a != null){
                total++;
                sum += a.getStats().getTotalPoints();
            }
        }
        return Math.round((sum / total) * 100.0) / 100.0;
    }

    private void printWinner() {
        System.out.println("Juniors winner:");
        System.out.println(getWinner(JUNIORS));
        System.out.println("Seniors winner:");
        System.out.println(getWinner(SENIORS));
        System.out.println("Veterans winner:");
        System.out.println(getWinner(VETERANS));
    }

    private Archer getWinner(int category){
        Archer winner = null;
        for (int i = 0; i < contestants[category].length; i++) {
            Archer a = contestants[category][i];
            if(a != null){
                if(winner == null){
                    winner = a;
                }
                else{
                    if(a.getStats().getTotalPoints() > winner.getStats().getTotalPoints()){
                        winner = a;
                    }
                    else{
                        if(a.getStats().getTotalPoints() == winner.getStats().getTotalPoints() &&
                                a.getStats().getTens() > winner.getStats().getTens()){
                            winner = a;
                        }
                    }
                }
            }
        }
        return winner;
    }

    private void showSortedContestantsByName(){
        for (int i = 0; i < archers.length; i++) {
            for (int j = 0; j < archers.length - i - 1; j++) {
                if(archers[j].getName().compareTo(archers[j+1].getName()) > 0){
                    Archer temp = archers[j];
                    archers[j] = archers[j+1];
                    archers[j+1] = temp;
                }
            }
        }
        for (int i = 0; i < archers.length; i++) {
            System.out.println( (i+1) + ":" + archers[i].getName());
        }
    }
}
