package main.cakes;

public abstract class Cake {

    private String name;
    private String description;
    private double price;
    private int pieces;
    private String type;

    public Cake(String name, String description, double price, int pieces, String type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.pieces = pieces;
        this.type = validateType(type);
    }

    public double getPrice() {
        return price;
    }

    protected String validateType(String type){
        String[] validTypes = getValidTypes();
        if(String.join("", validTypes).toUpperCase().contains(type.toUpperCase())){
            return type;
        }
        else{
            return getDefaultType();
        }
    }

    protected abstract String getDefaultType();
    protected abstract String[] getValidTypes();
    public abstract int getTypeIndex();

    @Override
    public String toString() {
        return name + " , " + price + " lv for " + pieces + " parcheta";
    }
}
