package payment;

import java.math.BigDecimal;
import common.CreditCard;
import common.PaymentType;

/**
 * CreditCard payment
 *
 */
public class CreditCardImpl extends PaymentImpl implements CreditCard {

	private String cardNumber;

	public CreditCardImpl(BigDecimal amount, String cardNumber) {
		super(amount, PaymentType.CREDIT);
		this.cardNumber = cardNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

}
