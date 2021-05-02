package main;

import main.clients.Client;
import main.clients.CorporateClient;
import main.clients.StandardClient;
import main.shop.PastryShop;

public class Demo {

    public static void main(String[] args) {

        System.out.println(" ===============  Creating shop ================");
        PastryShop shop = new PastryShop("Sladki talanti", "Man livadi", "0878554477", 5);

        System.out.println(" ===============  Creating clients ================");
        Client[] clients = new Client[10];
        int counter = 0;
        for (int i = 0; i < 5; i++) {
            Client client1 = new CorporateClient("Korpo Klient " + (i+1));
            client1.setShop(shop);
            Client client2 = new StandardClient("Smotan Klient " + (i+1));
            client2.setShop(shop);
            clients[counter++] = client1;
            clients[counter++] = client2;
        }
        System.out.println(" ===============  Print cakes before sell ================");
        shop.printAllCakes();
        System.out.println(" ===============  Order cakes ================");
        for (int i = 0; i < clients.length; i++) {
            clients[i].orderCakes();
        }
        System.out.println(" ===============  Print cakes after sell ================");
        shop.printSoldCakes();
        System.out.println(" ===============  Print profits ================");
        System.out.println(shop.getProfits());
        System.out.println(" ===============  Print delivery guy by wealth ================");
        shop.printDeliveryGuysByWealth();
        System.out.println(" ===============  Print most sold type of cake ================");
        shop.printMostSoldTypeOfCakes();
        System.out.println(" ===============  Print delivery guy with most orders ================");
        shop.printDeliveryGuyWithMostOrders();
        System.out.println(" ===============  Print most generous client ================");
        shop.printMostGenerousGuyEver();
    }
}
