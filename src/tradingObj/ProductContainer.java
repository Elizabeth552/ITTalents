package tradingObj;

import java.util.Random;

import merchants.Product;

public interface ProductContainer {

	default boolean sold(){
		return new Random().nextInt(4) == 1;//returns true 1 on every 4 occasions
	}
	
	void addProducts(Product[] products);
	public double getProfit();
}
