package merchants;

import java.util.Random;

public class Product {

	private static final String[] NAMES = {"Orange", "Banana", "Pen", "Cola", "Paper", "Cheese", "Coffee"};
	
	private String name;
	private double price;
	
	Product(){
		Random r = new Random();
		this.price = r.nextInt(11)+5;
		this.name = NAMES[r.nextInt(NAMES.length)];
	}

	public double getPrice() {
		return price;
	}
}
