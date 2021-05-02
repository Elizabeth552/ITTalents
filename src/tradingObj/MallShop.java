package tradingObj;

public class MallShop extends TradingObject implements ITradeLineShop {

	public MallShop(double area) {
		super((area >=10 && area <= 100) ? area : 15, 150);
	}
}
