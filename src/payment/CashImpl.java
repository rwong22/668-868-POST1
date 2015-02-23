package payment;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import common.Cash;
import common.PaymentType;

/**
 * Cash payment
 *
 */
public class CashImpl extends PaymentImpl implements Cash {

	public CashImpl(BigDecimal amount) throws RemoteException {
		super(amount, PaymentType.CASH);
	}

}
