package music;


import music.instruments.Instrument;

import java.util.*;
import java.util.stream.Collectors;

public class Shop {
    private String name;
    private double money;
    //type ->        [ name -> list of Instruments ]
    private HashMap<String, HashMap<String, HashSet<Instrument>>> sklad;
    //type ->        [ name -> number of sold instruments ]    // trqbvat mi i dvete, zashtoto v uslovieto ima kolko sum prodal i po vid
    private HashMap<String, HashMap<String, Integer>> soldInstruments;

    public Shop(String name) {
        this.name = name;
        this.money = 0;
        this.sklad = new HashMap<>();
        this.soldInstruments = new HashMap<>();
    }

    //2. reshavame problema kak da prodavame - kak da vzema 2 kitari ot kataloga i da se proveri ima li tolkova kitari
    public void sell(String instrumentName, int quantity) { // trqbva da  podam vida instrument koito e kitara - vseki instrument trqbva da ima tip strunen i vid kitara (toest po imeto - name)
        //daite mi 2 kitari --> kitarite sa strunni --> znachi otivame na stelaja sus strunni instr --> ako nqma znachi nqma i nalichnost
        //ako ima otivam i tursq sekciq kittari --> ako nqmam takava sekciq nqmam nalichnost
        // ako imam, stigam do sekciqta i otvarqm masiva/kashona tam da vidq kolko kitari ima tam --> ako nqma nqma i nalichnost

        //kak da obhodim samo po ime -  obhojdame samo sekciite bez stelajite -> toest samo values na sklada bez keys, toest samo vutreshniq hasmap
        boolean available = false;
        for (HashMap<String, HashSet<Instrument>> stelaj : sklad.values()) {
            for (String name : stelaj.keySet()) { //obhojdam vsqko ime ot stelaja
                if (name.equals(instrumentName)) { // i ako namerq ime ravno na tova, oeto choveka tursi da mu prodam
                    HashSet<Instrument> kashon = stelaj.get(instrumentName); // vzimam kashona ot stelaj,getname/ ot stelaja vzimam kashona, koito stoi zad tova ime
                    if (kashon.size() >= quantity) { // v kashona proverqvame dali se sudurja dostatuchno kolichestvo ot instrumentite, koito az iziskvam
                        available = true;
                        //remove from shop - kato gi prodavame gi mahame i ot magazina
                        int counter = 0;
                        for (Iterator<Instrument> it = kashon.iterator(); it.hasNext(); ) {
                            counter++;
                            Instrument instrument = it.next();// purvo kazvame .next, zashtoto enxt shte otide do purviq element
                            // shte mu vzemem cenata = nie pechelim tolkova kolkoto struva instrumenta --> //receive money based on the price
                            this.money = instrument.getPrice();
                            addToSold(instrument); //add to sold instruments -> za da si napravim spravkata --> pravim si Map
                            it.remove();// zashtoto e Hashset - nai-dobriq nachin za premahvane na element e chrez Iterator
                            if (counter == quantity) {
                                break;
                            }
                        }
                        break;
                    }

                }
            }
        }
        if (!available) {
            System.out.println("Either missing or insufficiene quantity"); // moje da nqmam duhovi ili da nqmam razddel gaidi ili da nqmam broiki
        }
    }

    //3.addtosold
    private void addToSold(Instrument i) { //pravim sushtoto kato za receive, no za soldInstruments
        if (!soldInstruments.containsKey(i.getType())) { //ako mu nqma tipa
            soldInstruments.put(i.getType(), new HashMap<>()); // go dobavqme
        }
        if (!soldInstruments.get(i.getType()).containsKey(i.getName())) { //imame li mu imeto
            soldInstruments.get(i.getType()).put(i.getName(), 0); // ako go nqma go dobavqme
        }
        HashMap<String, Integer> inner = soldInstruments.get(i.getType());
        inner.put(i.getName(), inner.get(i.getName()) + 1);
        //incr na stoinost v Map -> za sushtiq kluch put starata stoinost + 1
    }


    //1.reshavama problema kak da poluchavame
    public void receive(List<Instrument> naSpeedyBusa) {  //shte napravq list ot instrumenti, za da moje da priemat 2 kitari, 3 barabana i t.n
        //v tozi metod raztovarvame na sppedy busa sled kato e natovaren v demoto i instrumentite otivat v sklada
        // trqbva da proverim ima li shkafche/stelaj/svobodni mesta za muzikalnite instrumenti
        for (Instrument i : naSpeedyBusa) {
            if (!sklad.containsKey(i.getType())) {           // proverqvame dali v sklada imame tipa na muzikalniq instrument
                sklad.put(i.getType(), new HashMap<>());    // ako nqmame -> getType, slagame nov stelaj
            }
            if (!sklad.get(i.getType()).containsKey(i.getName())) {  // trqbva da vlezem v noviq HashMap i da proverim ima li sekciq kitari na toq stelaj
                sklad.get(i.getType()).put(i.getName(), new HashSet<>());
            }
            sklad.get(i.getType()).get(i.getName()).add(i);   //dobavq instrumenta/krugcheto

        }
    }

    public void printSold() {
        System.out.println("Складова наличност");
        for (Map.Entry<String, HashMap<String, HashSet<Instrument>>> e : sklad.entrySet()) { //strunni,udarni,duhovi
            System.out.println("==============" + e.getKey() + "===============");
            for (Map.Entry<String, HashSet<Instrument>> ee : e.getValue().entrySet()) { //obhojdam entryset na value na golqmoto entry, koeto e malkoto entry
                System.out.println("----" + ee.getKey() + "----" + ee.getValue().size());  // shte vurne kitara, gaida + kolko sa nalichnite
            }
        }
    }

    public void printAvailable() {
        System.out.println("Продадени стоки");
        for (Map.Entry<String, HashMap<String, Integer>> e : soldInstruments.entrySet()) { //strunni,udarni,duhovi
            System.out.println("==============" + e.getKey() + "===============");
            for (Map.Entry<String, Integer> ee : e.getValue().entrySet()) { //obhojdam entryset na value na golqmoto entry, koeto e malkoto entry
                System.out.println("----" + e.getKey() + "----" + ee.getValue());  // shte vurne kitara, gaida + kolko sa nalichnite
            }
        }
    }

    public void printBalance() {
        System.out.println("Пари в джоба " + money);
    }

    public void printByName() {
        System.out.println("=========== Сортирани по име ==========");
        ArrayList<Instrument> instruments = new ArrayList<>();
        for (HashMap<String, HashSet<Instrument>> stelaj : sklad.values()) {
            for (HashSet<Instrument> kashnon : stelaj.values()) {
                instruments.addAll(kashnon);
            }
        }
        //sega trqbva da preleem vsichki instrumenti ot sklada v tozi list
        instruments.sort((i1, i2) -> i1.getName().compareTo(i2.getName()));

        for (Instrument i : instruments) {
            System.out.println(i.getName());
        }
    }

    public void printByPrice(SortingOrder order) {
        System.out.println("=========== Сортирани по цена във възходящ ред ==========");
        ArrayList<Instrument> instruments = new ArrayList<>();  // prelivame ot sklad kolekciqta
        for (HashMap<String, HashSet<Instrument>> stelaj : sklad.values()) {
            for (HashSet<Instrument> kashnon : stelaj.values()) {
                instruments.addAll(kashnon);
            }
        }
        if (order == SortingOrder.ASC) {
            instruments.sort((i1, i2) -> Double.compare(i1.getPrice(), i2.getPrice()));
        }
        else {
            instruments.sort((i1, i2) -> Double.compare(i2.getPrice(), i1.getPrice()));
        }
        for (Instrument i : instruments) {
            System.out.println(i.getName() + " - " + i.getPrice());
        }
    }

    public void printBySales() {
        System.out.println("=========== Сортирани по продажби ==========");
        HashMap<String, Integer> instruments = new HashMap<>();
            for (HashMap<String, Integer> stelaj : soldInstruments.values()){
                instruments.putAll(stelaj);    // previmae tozi put ot sold kolekciqta i na tozi red imame vsichki prodadeni
        }
            //tozi instruments trqbva da go sortirame po values i zatova si suzdavame edin list ot entryta
            // taka v lista shte se sortirat po values, a ne po key
            List<Map.Entry<String,Integer>> result = instruments.entrySet()
                    .stream()
                    .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                    .collect(Collectors.toList());
            for (Map.Entry<String,Integer> e : result){
                System.out.println(e.getKey() + " - " + e.getValue()); // key e imeto, a values sa prodajbite
            }
    }
}
