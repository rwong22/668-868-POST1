package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import payment.Cash;
import payment.Payment;
import store.Inventory;
import store.Item;

public class SalesLog {

	private BufferedWriter writer;

	private String storeName;
	private String customerName;
	private ItemTuple[] items;
	private Payment payment;
	private Date date;

	public static void main(String[] args) {
		Inventory inv = new Inventory("/home/arod/workspace_eclipse/668-POST1/products.txt");

		ItemTuple[] items = new ItemTuple[2];
		items[0] = new ItemTuple(0001, 10);
		items[1] = new ItemTuple(0002, 5);

		SalesLog log = new SalesLog("Store Name Goes Here", "Customer Name Goes Here", items, new Cash(new BigDecimal(100.00)));
	}

	public SalesLog(String storeName, String customerName, ItemTuple[] items, Payment payment) {
		this.storeName = storeName;
		this.customerName = customerName;
		this.items = items;
		this.payment = payment;
		this.date = new Date();
		try {
			this.writer = new BufferedWriter(new FileWriter("/home/arod/workspace_eclipse/668-POST1/transaction.txt", true));
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		writeLog();
	}

	private boolean writeLog() {
		String salesLog;
		BigDecimal total = new BigDecimal(0);

		salesLog = storeName + '\n';
		salesLog += '\n';
		salesLog += customerName + '\t' + date.toString() + '\n';
		for (int i = 0; i < items.length; i++) {
			Item currentItem = Inventory.items.get(items[i].getUPC());
			total.add(currentItem.getPrice().multiply(new BigDecimal(items[i].getQuantity())));
			salesLog += currentItem.getDescription() + '\t' + items[i].getQuantity() + " @ " + currentItem.getPrice() + '\t' + "$" + ( currentItem.getPrice().multiply(new BigDecimal(items[i].getQuantity())).toString() ) + '\n';
		}
		salesLog += "----------\n";
		salesLog += "Total: $" + total.toString() + '\n';
		// TODO: add payment info
		salesLog += "Amount Returned: $0.00";
		salesLog += '\n';

		try {
			writer.write(salesLog);
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		return true;
	}
}