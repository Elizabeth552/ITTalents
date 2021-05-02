package main.shop;

import main.cakes.Cake;
import main.clients.Client;

public class Order {

    private Client client;
    private double price;
    private String address;
    private Cake[] orderedCakes;
    private String date;

    public Order(Client client, String address, Cake[] orderedCakes, String date) {
        this.client = client;
        this.address = address;
        this.orderedCakes = orderedCakes;
        this.date = date;
        int price = 0;
        for (int i = 0; i < orderedCakes.length; i++) {
            price += orderedCakes[i].getPrice();
        }
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
