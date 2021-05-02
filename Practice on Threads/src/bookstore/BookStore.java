package bookstore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class BookStore {

    private HashMap<String, Book> books;//name -> book
    private ConcurrentHashMap<Book, TreeMap<LocalDateTime, LocalDateTime>> chronology;
    private ConcurrentHashMap<Book, Double> taxes;
    private double money;
    private Object lock = new Object();

    BookStore(){
        books = new HashMap<>();
        chronology = new ConcurrentHashMap<>();
        taxes = new ConcurrentHashMap<>();
        money = 34;
        books.put("Война и мир", new Book("Война и мир"));
        books.put("Дядо Праз", new Book("Дядо Праз"));
        books.put("Вещерът", new Book("Вещерът"));
        books.put("Идиот", new Book("Идиот"));
    }

    public Book rentABook(String name) throws BookNotFoundException {
        Book book;
        synchronized (lock){
            book = books.get(name);
            if (book == null) {
                throw new BookNotFoundException();
            }
            books.remove(book.getName());
        }
        if(!chronology.containsKey(book)){
            chronology.put(book, new TreeMap<>());
        }
        chronology.get(book).put(LocalDateTime.now(), LocalDateTime.now().plus(book.getRentDuration()));
        taxes.put(book, book.getTax());
        book.markTaken();
        System.out.println("Vzehte " + name);
        return book;
    }


    public void returnBook(Book book, Client client) {
        synchronized (lock){
            books.put(book.getName(), book);
        }
        double tax = taxes.get(book);
        System.out.println("Vurnahte " + book.getName() + ", duljite " + tax);
        receiveTax(tax);
        client.spendMoney(tax);
        book.markReturned();
    }

    private void receiveTax(double tax) {
        this.money += tax;
    }

    protected void increaseTax(Book book){
        taxes.put(book, taxes.get(book)*1.01);
    }
}
