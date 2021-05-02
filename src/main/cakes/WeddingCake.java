package main.cakes;

public class WeddingCake extends Cake{

    private int levels;

    public WeddingCake(String name, String description, double price, int pieces, String type) {
        super(name, description, price, pieces, type);
    }

    @Override
    protected String getDefaultType() {
        return "Big";
    }

    @Override
    protected String[] getValidTypes() {
        String[] validTypes = {"Big", "Small", "Middle"};
        return validTypes;
    }

    @Override
    public int getTypeIndex() {
        return 1;
    }
}
