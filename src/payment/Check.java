package payment;

import java.math.BigDecimal;

public class Check extends Payment {

	private int checkNumber;

	public Check(BigDecimal amount, int checkNumber) {
		super(amount, PaymentType.CHECK);
		this.checkNumber = checkNumber;
	}

	public int getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(int checkNumber) {
		this.checkNumber = checkNumber;
	}
}
