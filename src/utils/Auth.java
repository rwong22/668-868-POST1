package utils;

import java.util.Random;

import payment.CreditCard;
import payment.Payment;

public class Auth {

	/**
	 * If payment is CreditCard, generate random number and reject ~10%
	 */
	public static boolean authenticate(Payment payment) {
		if (payment instanceof CreditCard) {
			Random random = new Random();
			int test = random.nextInt(100);
			return test > 10;
		}

		return true;
	}
}
