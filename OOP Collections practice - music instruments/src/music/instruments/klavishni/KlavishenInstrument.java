package music.instruments.klavishni;

import music.instruments.Instrument;

public abstract class KlavishenInstrument extends Instrument {
    public KlavishenInstrument(String name,double price) {
        super(name, "Клавишен",price);
    }
}
