package main.cakes;

public class KidsCake extends Cake{

    private String kidName;

    public KidsCake(String name, String description, double price, int pieces, String type) {
        super(name, description, price, pieces, type);
    }

    @Override
    protected String getDefaultType() {
        return "BirthDay";
    }

    @Override
    protected String[] getValidTypes() {
        String[] validTypes = {"BirthDay", "BaptiParty", "ProshtaParti"};
        return validTypes;
    }

    @Override
    public int getTypeIndex() {
        return 2;
    }
}
