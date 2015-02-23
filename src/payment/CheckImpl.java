package payment;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import common.Check;
import common.PaymentType;

/**
 * Check payment
 *
 */
public class CheckImpl extends PaymentImpl implements Check {

	public CheckImpl(BigDecimal amount) throws RemoteException {
		super(amount, PaymentType.CHECK);
	}
}
