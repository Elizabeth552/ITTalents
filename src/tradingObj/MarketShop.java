package tradingObj;

public class MarketShop extends TradingObject implements IETShop {

	public MarketShop(double area) {
		super((area >=2 && area <= 10) ? area : 5, 50);
	}

}
