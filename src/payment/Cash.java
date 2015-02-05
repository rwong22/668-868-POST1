package payment;

import java.math.BigDecimal;

public class Cash extends Payment {

	public Cash(BigDecimal amount) {
		super(amount, PaymentType.CASH);
	}

}
