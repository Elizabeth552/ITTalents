package main;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Demo {

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse("Warehouse");
        Shop.warehouse = warehouse;

        Supplier supplier = new Supplier(warehouse);
        supplier.start();

        Shop shop1 = new Shop("Shop 1");
        Shop shop2 = new Shop("Shop 2");
        Shop shop3 = new Shop("Shop 3");
        new Thread(shop1, "Shop 1").start();
        new Thread(shop2, "Shop 2").start();
        new Thread(shop3, "Shop 3").start();

        Client c1 = new Client(shop1, "Client 1");
        Client c2 = new Client(shop1, "Client 2");
        Client c3 = new Client(shop1, "Client 3");
        Client c4 = new Client(shop2, "Client 4");
        Client c5 = new Client(shop2, "Client 5");
        Client c6 = new Client(shop2, "Client 6");
        Client c7 = new Client(shop3, "Client 7");
        Client c8 = new Client(shop3, "Client 8");
        Client c9 = new Client(shop3, "Client 9");

        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();
        c7.start();
        c8.start();
        c9.start();

        Thread archiver = new Thread(() -> {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            Stats stats = warehouse.getStats();
//            File f = new File("stats.txt");
//            try(PrintWriter pr = new PrintWriter(f);) {
//                pr.println(stats);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
        });
        archiver.setDaemon(true);
        archiver.start();

        Thread statistics = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    warehouse.printAvailability();
                    shop1.printAvailability();
                    shop2.printAvailability();
                    shop3.printAvailability();
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        statistics.setDaemon(true);
        statistics.start();

    }
}
