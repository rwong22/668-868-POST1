package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import payment.Payment;
import store.Inventory;
import store.Item;

public class SalesLog {

	private BufferedWriter writer;

	private String storeName;
	private String customerName;
	private ArrayList<ItemTuple> items;
	private Payment payment;
	private Date date;

	public SalesLog(String storeName, String customerName, ArrayList<ItemTuple> items, Payment payment) {
		this.storeName = storeName;
		this.customerName = customerName;
		this.items = items;
		this.payment = payment;
		this.date = new Date();
	}

	public boolean writeLog() {
		try {
			this.writer = new BufferedWriter(new FileWriter("/home/arod/workspace_eclipse/668-POST1/sales.log", true));
		} catch (IOException exception) {
			exception.printStackTrace();
			return false;
		}
		String salesLog;
		BigDecimal total = new BigDecimal(0);

		salesLog = storeName + '\n';
		salesLog += '\n';
		salesLog += customerName + '\t' + date.toString() + '\n';
		for (int i = 0; i < items.size(); i++) {
			Item currentItem = (Item) Inventory.getItems().get(items.get(i).getUPC());
			total = total.add(currentItem.getPrice().multiply(new BigDecimal(items.get(i).getQuantity())));
			salesLog += currentItem.getDescription() + '\t' + items.get(i).getQuantity() + " @ " + currentItem.getPrice() + '\t' + "$" + ( currentItem.getPrice().multiply(new BigDecimal(items.get(i).getQuantity())).toString() ) + '\n';
		}
		salesLog += "----------\n";
		salesLog += "Total: $" + total.toString() + '\n';
		// TODO: add payment info
		salesLog += "Amount Returned: $0.00";
		salesLog += '\n';
		salesLog += '\n';

		System.out.println(salesLog);

		try {
			writer.write(salesLog);
			writer.flush();
			writer.close();
		} catch (IOException exception) {
			exception.printStackTrace();
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		Inventory inv = new Inventory("/home/arod/workspace_eclipse/668-POST1/products.txt");

		// SalesLog log = new SalesLog("Store Name", "Customer Name", items, new
		// Cash(new BigDecimal(100.00)));
		// log.writeLog();
	}
}
