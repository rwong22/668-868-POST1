package payment;
import java.math.BigDecimal;

public class Check extends Payment {
	private int checkNumber;

	public Check(BigDecimal amount, int checkNumber) {
		this.amount = amount;
		this.checkNumber = checkNumber;
	}
}
