package payment;

import java.math.BigDecimal;
import common.Payment;
import common.PaymentType;

/**
 * Abstract class to standardize all payment types
 *
 */
public abstract class PaymentImpl implements Payment {

	private BigDecimal amount;
	private PaymentType type;

	public PaymentImpl(BigDecimal amount, PaymentType type) {
		this.amount = amount;
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}
}
