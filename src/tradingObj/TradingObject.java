package tradingObj;

import java.util.Random;

import merchants.Product;

public abstract class TradingObject implements ProductContainer {

	private String address;
	private String workingTime;
	private double area;
	private int tax;
	private Product[] products;
	private int soldProducts = 0;
	
	public TradingObject(double area, int tax){
		this.area = area;
		this.tax = tax;
		this.products = new Product[50];
	}

	public void addProducts(Product[] products) {
		int index =0;
		for(int i = 0; i < this.products.length; i++){
			if(this.products[i] == null){
				this.products[i] = products[index++];
			}
			if(products[index] == null || index > products.length){
				break;
			}
		}
	}

	public double getProfit() {
		double profit = 0;
		for(int i = 0; i < this.products.length; i++){
			if(this.products[i] != null){
				if(sold()){
					profit += this.products[i].getPrice()*1.3;
					soldProducts++;
					this.products[i] = null;
				}
			}
		}
		return profit;
	}

	public int getTax() {
		return this.tax;
	}

	public int getSoldProducts() {
		return this.soldProducts;
	}
}
