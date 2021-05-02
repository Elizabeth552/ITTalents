package music.instruments.udarni;

import music.instruments.Instrument;

public abstract class UdarenInstrument extends Instrument {

    public UdarenInstrument(String name,double price) { // naslednicite shte trqbva da overrid-nat constr samo s ime / supera s ime baraban vika:
        super(name, "Ударен",price); // supera na baraban, udaren
    }
}
