package merchants;

import suppliers.Supplier;
import tradingObj.ProductContainer;
import tradingObj.TradingObject;

public interface IMerchant {

	void makeOrder(double amount, Supplier s, ProductContainer to) throws NotEnoughMoneyException;
	void cacheOut(TradingObject to);
	void payTaxes(TradingObject to);
}
