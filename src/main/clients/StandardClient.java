package main.clients;

import main.util.Randomizer;

import java.util.Random;

public class StandardClient extends Client{

    private int[] vauchers;

    public StandardClient(String name){
        super(name);
        vauchers = new int[Randomizer.random(1,4)];
        for (int i = 0; i < vauchers.length; i++) {
            vauchers[i] = Randomizer.random(10,30);
        }
    }

    @Override
    protected int getOrderNumber() {
        return Randomizer.random(1,3);
    }

    @Override
    public double getDiscount(double totalPrice) {
        double discount = 0;
        for (int i = 0; i < vauchers.length; i++) {
            discount += vauchers[i];
            vauchers[i] = 0;
            if(discount > totalPrice){
                discount = totalPrice;
                break;
            }
        }
        return discount;
    }

    @Override
    public double getTipPercent() {
        return 2;
    }
}
