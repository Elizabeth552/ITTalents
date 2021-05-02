package bookstore;

public class Client extends Thread{

    public static BookStore bookStore;
    private double money = 50;

    Client(String name){
        super(name);
    }

    @Override
    public void run() {
        try {
            Book book = bookStore.rentABook("Идиот");
            Thread.sleep(5000);
            bookStore.returnBook(book, this);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void spendMoney(double tax) {
        this.money -= tax;
    }
}
