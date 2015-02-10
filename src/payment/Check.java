package payment;

import java.math.BigDecimal;

/**
 * Check payment
 *
 */
public class Check extends Payment {

	public Check(BigDecimal amount) {
		super(amount, PaymentType.CHECK);
	}
}
