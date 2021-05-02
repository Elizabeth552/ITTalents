package merchants;

import java.util.Random;

import suppliers.Supplier;
import tradingObj.ProductContainer;
import tradingObj.TradingObject;

public abstract class Merchant implements IMerchant{

	private String name;
	private String address;
	protected double capital;

	public Merchant(double capital, String name) {
		this.capital = capital;
		this.name = name;
	}

	@Override
	public void cacheOut(TradingObject to) {
		capital += to.getProfit();
	}

	@Override
	public void payTaxes(TradingObject to) {
		capital -= to.getTax()/12;
	}

	@Override
	public void makeOrder(double amount, Supplier s, ProductContainer to) throws NotEnoughMoneyException {
		if(amount > (this.capital/2)){
			throw new NotEnoughMoneyException();
		}
		
		Product[] products = new Product[(int)amount/5];
		int i = 0;//index in the array
		double totalPrice = 0;
		while(totalPrice <= amount){
			Product p = new Product();
			totalPrice+=p.getPrice();
			if(totalPrice > amount){
				break;
			}
			products[i++] = p;
		}
		double price = totalPrice*((100-s.getDiscount())/100);
		capital -= price;
		to.addProducts(products);
	}

	public abstract void startTrading()throws NotEnoughMoneyException;

	public double getCapital() {
		return capital;
	}
	
	public abstract int getSoldProducts();
	
	public abstract int getTaxesPaid();

	public String getName() {
		return name;
	}
	

}
