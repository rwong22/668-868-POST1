package utils;

import java.util.ArrayList;

import payment.Payment;

public class Transaction {

	private String customerName;
	private ArrayList<ItemTuple> items;
	private Payment payment;

	public Transaction(String customerName) {
		this.customerName = customerName;
		this.items = new ArrayList<ItemTuple>();
	}

	public boolean addItem(String UPC, int quantity) {
		items.add(new ItemTuple(UPC, quantity));

		return true;
	}

	public boolean addPayment(Payment payment) {
		this.payment = payment;

		return true;
	}

	public String getCustomerName() {
		return customerName;
	}

	public ArrayList<ItemTuple> getItems() {
		return items;
	}

	public Payment getPayment() {
		return payment;
	}
}
