package payment;

import java.math.BigDecimal;

public class CreditCard extends Payment {

	private String cardNumber;

	public CreditCard(BigDecimal amount, String cardNumber) {
		super(amount, PaymentType.CREDITCARD);
		this.cardNumber = cardNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

}
