package payment;

import java.math.BigDecimal;
import common.Check;
import common.PaymentType;

/**
 * Check payment
 *
 */
public class CheckImpl extends PaymentImpl implements Check {

	public CheckImpl(BigDecimal amount) {
		super(amount, PaymentType.CHECK);
	}
}
