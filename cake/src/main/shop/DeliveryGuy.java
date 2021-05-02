package main.shop;

import main.Person;
import main.shop.Order;

import java.util.Arrays;

public class DeliveryGuy extends Person {

    private int orders;
    private double tips;

    public DeliveryGuy(String name) {
        super(name);
    }

    public void receiveTip(double tip){
        this.tips += tip;
    }

    public double getTips() {
        return tips;
    }

    public void increaseOrders(){
        orders++;
    }

    @Override
    public String toString() {
        return getName() + " - " + getTips();
    }

    public int getOrders() {
        return orders;
    }
}
