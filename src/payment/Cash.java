package payment;

import java.math.BigDecimal;

/**
 * Cash payment
 *
 */
public class Cash extends Payment {

	public Cash(BigDecimal amount) {
		super(amount, PaymentType.CASH);
	}

}
