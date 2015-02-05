package payment;

import java.math.BigDecimal;

public class CreditCard extends Payment {

	private String cardNumber;

	public CreditCard(String cardNumber) {
		super(new BigDecimal(0), PaymentType.CREDIT);
		this.cardNumber = cardNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

}
