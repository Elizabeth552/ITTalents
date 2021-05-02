package merchants;

public class NotEnoughMoneyException extends Exception {

	@Override
	public String getMessage() {
		return "Not enought money, bitch!";
	}
}
