package tradingObj;

public class StreetShop extends TradingObject implements IETShop, ITradeLineShop {

	public StreetShop(double area) {
		super((area >=4 && area <= 6) ? area : 5, 50);
	}
}
