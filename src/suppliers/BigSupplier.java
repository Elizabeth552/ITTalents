package suppliers;

public class BigSupplier extends Supplier {

	private static final int DISCOUNT = 15;//%
	
	@Override
	public int getDiscount() {
		return DISCOUNT;
	}

}
