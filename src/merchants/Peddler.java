package merchants;

import suppliers.SmallSupplier;
import tradingObj.ProductContainer;

public class Peddler extends Merchant implements ProductContainer {

	private SmallSupplier supplier;
	private Product[] products;
	
	private int soldProducts = 0;
	
	public Peddler(double capital, String name){
		super(capital, name);
		supplier = new SmallSupplier();
		products = new Product[50];
	}

	public void addProducts(Product[] products) {
		int index =0;
		for(int i = 0; i < this.products.length; i++){
			if(this.products[i] == null){
				this.products[i] = products[index++];
			}
			if(index >= products.length || products[index] == null){
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

	@Override
	public void startTrading() throws NotEnoughMoneyException {
		//поръчка на стоки от доставчик за всеки търговски обект 
		makeOrder(capital/3, supplier, this);
		//прибиране на оборот от всеки търговски обект
		capital+= getProfit();
		//плащане на месечен данък към държавата за всеки търговски обект.
		//fuck you!
	}
	
	@Override
	public int getSoldProducts() {
		return soldProducts;
	}
	
	@Override
	public int getTaxesPaid() {
		return 0;
	}
}
