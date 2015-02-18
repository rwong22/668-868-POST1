package payment;

import java.math.BigDecimal;
import common.Cash;
import common.PaymentType;

/**
 * Cash payment
 *
 */
public class CashImpl extends PaymentImpl implements Cash {

	public CashImpl(BigDecimal amount) {
		super(amount, PaymentType.CASH);
	}

}
