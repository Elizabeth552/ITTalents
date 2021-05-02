package main;

import java.util.*;

public class Shop extends Warehouse implements Runnable {

    public static Warehouse warehouse;

    public Shop(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true){
            this.sipi();
        }
    }

    @Override
    protected void supplyDeficitProducts() {
        List<ProductNames> deficits = getDeficitProducts();
        System.out.println("Deficit products in " + name + " are " + deficits);
        for(ProductNames name : deficits){
            warehouse.zemi(name);
        }
        super.supplyDeficitProducts();
    }

    private List<ProductNames> getDeficitProducts() {
        List<ProductNames> deficits = new ArrayList<>();
        for(HashMap<ProductNames, Integer> p : products.values()){
            for(Map.Entry<ProductNames, Integer> e : p.entrySet()){
                if(e.getValue() < minimumQuantity()){
                    deficits.add(e.getKey());
                }
            }
        }
        return deficits;
    }

    @Override
    protected int deliverQuantity() {
        return new Random().nextInt(4)+1;
    }

    @Override
    protected int supplyQuantity() {
        return 15;
    }

    @Override
    protected int startingQuantity() {
        return 0;
    }

    @Override
    protected int minimumQuantity() {
        return 5;
    }
}
