package bookstore;

public class Demo {

    public static void main(String[] args) throws InterruptedException {

        BookStore bookStore = new BookStore();
        Client.bookStore = bookStore;
        Book.bookStore = bookStore;
        Client client1 = new Client("Klient 1");
        Client client2 = new Client("Klient 2");
        client1.start();
        Thread.sleep(10000);
        client2.start();

    }
}
