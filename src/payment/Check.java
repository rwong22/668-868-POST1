package payment;

import java.math.BigDecimal;

public class Check extends Payment {

	public Check(BigDecimal amount) {
		super(amount, PaymentType.CHECK);
	}
}
