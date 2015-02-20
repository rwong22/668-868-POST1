package services;


//import java.util.Random;

import common.CreditCard;
import common.Payment;

public class AuthenticateService {

	/**
	 * If payment is CreditCard, generate random number and reject ~10%
	 */
	public static boolean authenticate(Payment payment) {
		if (payment instanceof CreditCard) {
		    return Math.random() > 0.1;
//			Random random = new Random();
//			int test = random.nextInt(100);
//			return test > 10;
		}

		return true;
	}
}
