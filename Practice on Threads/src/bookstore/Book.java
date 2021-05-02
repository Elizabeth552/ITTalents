package bookstore;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.TreeMap;

public class Book {

    private String name;
    private Thread checker;
    TreeMap<LocalDateTime, LocalDateTime> chronology;
    static BookStore bookStore;

    public Book(String name) {
        this.name = name;
        this.chronology = new TreeMap<>();
    }

    public Duration getRentDuration() {
        return Duration.ofSeconds(2);
    }

    public String getName() {
        return name;
    }

    public Double getTax() {
        return 2.0;
    }

    public void markTaken() {
        chronology.put(LocalDateTime.now(), null);
        checker = new Thread(() -> {
            try {
                Thread.sleep(getRentDuration().getSeconds()*1000);
                System.out.println("Книгата" + getName() + " трябваше вече да е върната. Засрамете се!");
                while(true){
                    if(Thread.currentThread().isInterrupted()) {
                        return;
                    }
                    Thread.sleep(1000);
                    System.out.println("Начисляваме 1% и си искам книгата, моля ..");
                    bookStore.increaseTax(this);
                }
            } catch (InterruptedException e) {
                System.out.println("Checker for " + getName() + " stopped");
                return;
            }
        });
        checker.start();
    }

    public void markReturned() {
        chronology.put(chronology.lastKey(), LocalDateTime.now());
        checker.interrupt();
    }
}
