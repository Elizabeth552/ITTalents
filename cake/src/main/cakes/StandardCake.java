package main.cakes;

public class StandardCake extends Cake{

    private boolean hasSirop;

    public StandardCake(String name, String description, double price, int pieces, String type) {
        super(name, description, price, pieces, type);
    }

    @Override
    protected String getDefaultType() {
        return "Biscuit";
    }

    @Override
    protected String[] getValidTypes() {
        String[] validTypes = {"Biscuit", "Ecler", "Fruit", "Choko"};
        return validTypes;
    }

    @Override
    public int getTypeIndex() {
        return 3;
    }


}
