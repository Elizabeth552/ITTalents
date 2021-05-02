package main.clients;

import main.util.Randomizer;

public class CorporateClient extends Client{

    private static final int DISCOUNT_PERCENT = 10;

    public CorporateClient(String name) {
        super(name);
    }

    @Override
    protected int getOrderNumber() {
        return Randomizer.random(3,5);
    }

    @Override
    public double getDiscount(double totalPrice) {
        return totalPrice * DISCOUNT_PERCENT / 100;
    }

    @Override
    public double getTipPercent() {
        return 5;
    }
}
