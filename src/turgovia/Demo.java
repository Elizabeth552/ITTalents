package turgovia;

import java.util.Random;

import merchants.ET;
import merchants.Merchant;
import merchants.NotEnoughMoneyException;
import merchants.Peddler;
import merchants.TradeLine;
import suppliers.BigSupplier;
import suppliers.SmallSupplier;
import suppliers.Supplier;
import tradingObj.IETShop;
import tradingObj.ITradeLineShop;
import tradingObj.MallShop;
import tradingObj.MarketShop;
import tradingObj.StreetShop;
import tradingObj.TradingObject;

public class Demo {

	public static void main(String[] args) {
		//for testing purposes
		for (int i = 0; i < 100000; i++) {
			doWork();
		}
	}
	
	private static void doWork(){

		//t.1
		Supplier[] suppliers = new Supplier[10];
		Random r = new Random();
		for (int i = 0; i < suppliers.length; i++) {
			if(r.nextBoolean()){
				suppliers[i] = new BigSupplier();
			}
			else{
				suppliers[i] = new SmallSupplier();
			}
		}
		//t.2
		TradingObject[] tradingObjects = new TradingObject[20];
		for (int i = 0; i < tradingObjects.length; i++) {
			int chance = r.nextInt(3);
			switch(chance){
				case 0: tradingObjects[i] = new MarketShop(5); break;
				case 1: tradingObjects[i] = new MallShop(50); break;
				case 2: tradingObjects[i] = new StreetShop(4); break;
			}
		}
		//t.3
		Peddler amb = new Peddler(100, "Pesho shisheto");
		ET et = new ET(500, "Dinko Treid");
		TradeLine tl = new TradeLine(3000, "Maria Ltd");
		//t.4
		int busyShop = 0;
		for (int i = 0; i < tradingObjects.length; i++) {
			if(tradingObjects[i] instanceof IETShop){
				et.setTradingObject((IETShop)tradingObjects[i]);
				busyShop = i;
			}
		}
		ITradeLineShop[] shopsForTradeLine = new ITradeLineShop[10];
		int index = 0;
		for (int i = 0; i < tradingObjects.length; i++) {
			if(tradingObjects[i] instanceof ITradeLineShop && i != busyShop){
				shopsForTradeLine[index++] = (ITradeLineShop)tradingObjects[i];
			}
			if(index >= shopsForTradeLine.length){
				break;
			}
		}
		tl.setShops(shopsForTradeLine);
		//t.5
		Merchant[] merchants = new Merchant[3];
		merchants[0] = et;
		merchants[1] = amb;
		merchants[2] = tl;
		try{
		startTrading(merchants);
		}
		catch(NotEnoughMoneyException e){
			System.out.println("kofti turgoviq vurtite neshto");
		}
		//t.6
		for (int i = 0; i < merchants.length; i++) {
			System.out.println(merchants[i].getCapital());
		}
		//t.7
		int maxSoldProducts = 0;
		Merchant winner = null;
		for (int i = 0; i < merchants.length; i++) {
			if(merchants[i].getSoldProducts() > maxSoldProducts){
				maxSoldProducts = merchants[i].getSoldProducts();
				winner = merchants[i];
			}
		}
		System.out.println("Winner on products = " + winner.getName());
		//t.8
		int maxTaxesPaid = 0;
		Merchant winnerOnTaxes = null;
		for (int i = 0; i < merchants.length; i++) {
			if(merchants[i].getTaxesPaid() > maxSoldProducts){
				maxTaxesPaid = merchants[i].getTaxesPaid();
				winnerOnTaxes = merchants[i];
			}
		}
		System.out.println("Winner on taxes = " + winnerOnTaxes.getName());
	}
	
	private static void startTrading(Merchant[] merchants) throws NotEnoughMoneyException{
		for (int i = 0; i < merchants.length; i++) {
			merchants[i].startTrading();
		}
	}
}
