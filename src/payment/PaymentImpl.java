package payment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import common.Payment;
import common.PaymentType;

/**
 * Abstract class to standardize all payment types
 *
 */
public abstract class PaymentImpl extends UnicastRemoteObject implements Payment, Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private BigDecimal amount;
	private PaymentType type;

	public PaymentImpl(BigDecimal amount, PaymentType type) throws RemoteException {
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
