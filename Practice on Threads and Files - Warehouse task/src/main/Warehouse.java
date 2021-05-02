package main;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    protected String name;

    enum ProductType {
        FRUITS, VEGETABLES, MEATS
    }

    enum ProductNames {
        BANANA, ORANGE, APPLE, POTATO, EGGPLANT, CUCUMBER, PORK, BEEF, CHICKEN
    }

    protected volatile HashMap<ProductType, HashMap<ProductNames, Integer>> products;


    public Warehouse(String name){
        this.name = name;
        this.products = new HashMap<>();
        products.put(ProductType.FRUITS, new HashMap<>());
        products.get(ProductType.FRUITS).put(ProductNames.BANANA, startingQuantity());
        products.get(ProductType.FRUITS).put(ProductNames.ORANGE, startingQuantity());
        products.get(ProductType.FRUITS).put(ProductNames.APPLE, startingQuantity());
        products.put(ProductType.VEGETABLES, new HashMap<>());
        products.get(ProductType.VEGETABLES).put(ProductNames.POTATO, startingQuantity());
        products.get(ProductType.VEGETABLES).put(ProductNames.EGGPLANT, startingQuantity());
        products.get(ProductType.VEGETABLES).put(ProductNames.CUCUMBER, startingQuantity());
        products.put(ProductType.MEATS, new HashMap<>());
        products.get(ProductType.MEATS).put(ProductNames.PORK, startingQuantity());
        products.get(ProductType.MEATS).put(ProductNames.BEEF, startingQuantity());
        products.get(ProductType.MEATS).put(ProductNames.CHICKEN, startingQuantity());
    }


    public synchronized void sipi(){
        while(!hasDeficitProducts()){
            System.out.println(Thread.currentThread().getName() + " - no deficit products, I wait");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " - I supply deficit products");
        supplyDeficitProducts();
        notifyAll();
    }

    public synchronized void zemi(ProductNames name){
        while(isDeficit(name)){
            System.out.println(Thread.currentThread().getName() + " - " + name + " is deficit, I wait");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int sold = deliver(name);
        System.out.println(Thread.currentThread().getName() + " - I get " + sold + " " +name);
        if(isDeficit(name)) {
            notifyAll();
        }
    }

    private int deliver(ProductNames name) {
        int sold = deliverQuantity();
        for(HashMap<ProductNames, Integer> p : products.values()){
            for(Map.Entry<ProductNames, Integer> e : p.entrySet()){
                if(e.getKey() == name){
                    p.put(e.getKey(), e.getValue() - sold);
                }
            }
        }
        return sold;
    }

    private boolean isDeficit(ProductNames name) {
        for(HashMap<ProductNames, Integer> p : products.values()){
            for(Map.Entry<ProductNames, Integer> e : p.entrySet()){
                if(e.getKey() == name && e.getValue() < minimumQuantity()){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasDeficitProducts(){
        for(HashMap<ProductNames, Integer> p : products.values()){
            for(Integer i : p.values()){
                if(i < minimumQuantity()){
                    return true;
                }
            }
        }
        return false;
    }

    protected void supplyDeficitProducts(){
        for(HashMap<ProductNames, Integer> p : products.values()){
            for(Map.Entry<ProductNames, Integer> e : p.entrySet()){
                if(e.getValue() < minimumQuantity()){
                    p.put(e.getKey(), e.getValue()+ supplyQuantity());
                }
            }
        }
    }

    protected int deliverQuantity(){
        return 15;
    }

    protected int supplyQuantity(){
        return 25;
    }

    protected int startingQuantity(){
        return 50;
    }

    protected int minimumQuantity() {
        return 15;
    }


    public void printAvailability() {
        System.out.println( "-------------------" + name + "-------------------");
        for(HashMap<ProductNames, Integer> p : products.values()){
            for(Map.Entry<ProductNames, Integer> e : p.entrySet()){
                System.out.println(e.getKey() + " - " + e.getValue());
            }
        }
        System.out.println("---------------------------------------------------------");
    }
}