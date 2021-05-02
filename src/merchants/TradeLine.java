package merchants;

import java.util.Random;

import suppliers.BigSupplier;
import suppliers.SmallSupplier;
import suppliers.Supplier;
import tradingObj.ITradeLineShop;
import tradingObj.TradingObject;

public class TradeLine extends Merchant {

    private Supplier[] suppliers;
    private ITradeLineShop[] shops;

    public TradeLine(double capital, String name) {
        super(capital, name);
        suppliers = new Supplier[15];
        shops = new ITradeLineShop[10];

        for (int i = 0; i < suppliers.length; i++) {
            suppliers[i] = new Random().nextBoolean() ? new BigSupplier() : new SmallSupplier();
        }
    }

    public void setShops(ITradeLineShop[] shopsForTradeLine) {
        this.shops = shopsForTradeLine;
    }

    @Override
    public void startTrading() throws NotEnoughMoneyException {
        for (int i = 0; i < shops.length; i++) {
            if (shops[i] != null) {
                TradingObject shop = (TradingObject) shops[i];
                //������� �� ����� �� ��������� �� ����� ��������� �����
                makeOrder(capital / 3, suppliers[new Random().nextInt(suppliers.length)], shop);
                //��������� �� ������ �� ����� ��������� �����
                cacheOut(shop);
                //������� �� ������� ����� ��� ��������� �� ����� ��������� �����.
                payTaxes(shop);
            }
        }
    }

    @Override
    public int getSoldProducts() {
        int allProductsSold = 0;
        for (int i = 0; i < shops.length; i++) {
            if (shops[i] != null) {
                TradingObject shop = (TradingObject) shops[i];
                allProductsSold += shop.getSoldProducts();
            }
        }
        return allProductsSold;
    }

    @Override
    public int getTaxesPaid() {
        int allTaxesPaid = 0;
        for (int i = 0; i < shops.length; i++) {

            if (shops[i] != null) {
                TradingObject shop = (TradingObject) shops[i];
                allTaxesPaid += shop.getTax();
            }
        }
        return allTaxesPaid;
    }
}
