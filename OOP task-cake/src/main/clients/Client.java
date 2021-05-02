package main.clients;

import main.Person;
import main.cakes.Cake;
import main.shop.DeliveryGuy;
import main.shop.Order;
import main.shop.PastryShop;

public abstract class Client extends Person {

    private PastryShop shop;

    Client(String name){
        super(name);
    }

    public void setShop(PastryShop shop) {
        this.shop = shop;
    }

    public void orderCakes(){
        DeliveryGuy deliveryGuy = shop.getRandomDeliveryGuy();
        Cake[] cakes = new Cake[getOrderNumber()];
        System.out.println("%%%%%%%%%%%%%%" + getName() + " Orders: ");
        for (int i = 0; i < cakes.length; i++) {
            cakes[i] = shop.getCake();
            System.out.println("    ---   " + cakes[i]);
        }
        Order order = new Order(this, "Sofia, Mladost", cakes, "today");
        double price = order.getPrice();
        price = price - getDiscount(price);
        deliveryGuy.increaseOrders();
        deliveryGuy.receiveTip(price * getTipPercent() / 100);
        shop.receiveProfits(price, this);
    }

    protected abstract int getOrderNumber();

    @Override
    public String getName() {
        return super.getName();
    }

    public abstract double getDiscount(double totalPrice);
    public abstract double getTipPercent();
}
