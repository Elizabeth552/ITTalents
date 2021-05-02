package music;

import music.instruments.Instrument;
import music.instruments.duhovi.Fleita;
import music.instruments.duhovi.Gaida;
import music.instruments.duhovi.Klarinet;
import music.instruments.klavishni.Piano;
import music.instruments.strunni.Kontrabas;
import music.instruments.udarni.Tupan;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        Shop shop = new Shop("notibg");

        List<Instrument> naSpeedyBusa = new ArrayList<>();
        naSpeedyBusa.add(new Gaida(200));      //tovarim na Speedy busa ot nqkude si
        naSpeedyBusa.add(new Gaida(200));
        naSpeedyBusa.add(new Fleita(250));
        naSpeedyBusa.add(new Kontrabas(300));
        naSpeedyBusa.add(new Piano(700));
        naSpeedyBusa.add(new Tupan(550));
        naSpeedyBusa.add(new Piano(400));
        naSpeedyBusa.add(new Tupan(800));
        naSpeedyBusa.add(new Klarinet(200));

        shop.receive(naSpeedyBusa);        //idva na Speedy busa v magazina i raztovarva (v shop - metod receive)
        shop.sell("Гайда",2);
        shop.sell("Флейта",1);
        shop.sell("Кларинет",1);

        shop.printSold();
        shop.printAvailable(); //podredeni po vid
        shop.printByName();
        shop.printBalance();
        shop.printByPrice(SortingOrder.ASC);
        shop.printBySales();

    }
}
