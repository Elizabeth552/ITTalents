package main.cakes;

public class SpecialCake extends Cake{

    private String eventName;

    public SpecialCake(String name, String description, double price, int pieces, String type) {
        super(name, description, price, pieces, type);
    }

    @Override
    protected String getDefaultType() {
        return "Company";
    }

    @Override
    protected String[] getValidTypes() {
        String[] validTypes = {"Company", "Anivercery", "Commersial"};
        return validTypes;
    }

    @Override
    public int getTypeIndex() {
        return 0;
    }
}
