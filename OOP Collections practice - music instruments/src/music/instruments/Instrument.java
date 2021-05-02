package music.instruments;

import java.util.Objects;

public abstract class Instrument { //clasa e abstrakten, zashtoto ne mi trqbvat obekti ot nego

    private String name;
    protected String type;
    private double price;
    private int id; //pravim si edin serien nomer, za da ne moje edna kitara da vleze 2 puti
    private int uniqueId = 1;

    public Instrument(String name, String type, double price) { // podavat se name,type
        this.name = name;     // baraban
        this.type = type;     // udaren
        this.price = price;
        this.id = uniqueId++; // nqkakvo ID
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument that = (Instrument) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}

