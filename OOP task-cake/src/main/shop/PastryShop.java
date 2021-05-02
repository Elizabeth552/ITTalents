package main.shop;

import main.cakes.*;
import main.clients.Client;
import main.util.Randomizer;
import org.w3c.dom.ls.LSOutput;

public class PastryShop {

    private String name;
    private String address;
    private String phone;
    private DeliveryGuy[] deliveryGuys;
    private Cake[] catalogue;
    private double profits;
    private Cake[] soldCakes;
    private int sold = 0;
    int[] soldByType = new int[4];//0 -> Special, 1 -> Wedding, 2 -> Kids, 3 -> Standard
    private double maxProfit = 0;
    private Client mostGenerousGuyEver = null;

    public PastryShop(String name, String address, String phone, int deliveryGuys) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.deliveryGuys = new DeliveryGuy[deliveryGuys];
        for (int i = 0; i < this.deliveryGuys.length; i++) {
            this.deliveryGuys[i] = new DeliveryGuy("Delivery Guy " + (i+1));
        }
        this.catalogue = new Cake[30];
        this.soldCakes = new Cake[5000];
        for (int i = 0; i < catalogue.length; i++) {
            catalogue[i] = generateRandomCake();
        }
    }

    private Cake generateRandomCake() {
        int type = Randomizer.random(1,4);
        switch (type){
            case 1: return new SpecialCake("Specialna", "mnogo qka", 10.99, 10, "");
            case 2: return new WeddingCake("Svatbena", "mnogo qka", 349.99, 200, "");
            case 3: return new KidsCake("Detska", "mnogo qka", 37.99, 8, "");
            default: return new StandardCake("Standartna", "mnogo qka", 8.99, 6, "");
        }
    }

    public void printMostGenerousGuyEver() {
        System.out.println(mostGenerousGuyEver.getName() + " - " + maxProfit);
    }

    public void receiveProfits(double profit, Client client) {
        if(profit > maxProfit){
            maxProfit = profit;
            this.mostGenerousGuyEver = client;
        }
        this.profits += profit;
    }

    public DeliveryGuy getRandomDeliveryGuy() {
        return deliveryGuys[Randomizer.random(0, deliveryGuys.length-1)];
    }

    public Cake getCake() {
        Cake cake = catalogue[Randomizer.random(0, catalogue.length-1)];
        soldCakes[sold++] = cake;
        soldByType[cake.getTypeIndex()]++;
        return cake;
    }

    public void printAllCakes() {
        for (int i = 0; i < catalogue.length; i++) {
            if(catalogue[i] != null) {
                System.out.println(catalogue[i]);
            }
            else{
                break;
            }
        }
    }

    public void printSoldCakes() {
        for (int i = 0; i < soldCakes.length; i++) {
            if(soldCakes[i] != null) {
                System.out.println(soldCakes[i]);
            }
            else{
                break;
            }
        }
    }

    public double getProfits() {
        return profits;
    }

    public void printDeliveryGuysByWealth(){
        for (int i = 0; i < deliveryGuys.length; i++) {
            for (int j = 0; j < deliveryGuys.length - i-1; j++) {
                if(deliveryGuys[j].getTips() < deliveryGuys[j+1].getTips()){
                    DeliveryGuy temp = deliveryGuys[j];
                    deliveryGuys[j] = deliveryGuys[j+1];
                    deliveryGuys[j+1] = temp;
                }
            }
        }
        for (int i = 0; i < deliveryGuys.length; i++) {
            System.out.println(deliveryGuys[i]);
        }
    }

    public void printMostSoldTypeOfCakes() {
        int maxIdx = 0;
        int max = 0;
        for (int i = 0; i < soldByType.length; i++) {
            if(soldByType[i] > max){
                max = soldByType[i];
                maxIdx = i;
            }
        }
        switch (maxIdx){
            case 0 :
                System.out.println("Most sold is Special cake"); break;
            case 1 :
                System.out.println("Most sold is Wedding cake"); break;
            case 2 :
                System.out.println("Most sold is Kids cake"); break;
            case 3 :
                System.out.println("Most sold is Standard cake"); break;
        }
    }

    public void printDeliveryGuyWithMostOrders(){
        DeliveryGuy winner = deliveryGuys[0];
        for (int i = 1; i < deliveryGuys.length; i++) {
            if(deliveryGuys[i].getOrders() > winner.getOrders()){
                winner = deliveryGuys[i];
            }
        }
        System.out.println("Guy with most orders: ");
        System.out.println(winner + "with " + winner.getOrders() + " orders");
    }
}
