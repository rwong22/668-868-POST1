package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;

import payment.Cash;
import payment.Check;
import payment.CreditCard;
import post.Customer;

public class Transaction {

	private static ArrayList<Customer> customerList;
	private static String filePath = "transactions.txt";
	private static BufferedReader bufferedReader;

	public static ArrayList<Customer> getTransactions() {
		if (customerList == null) {
			readTransactions();
		}

		return customerList;
	}

	private static boolean readTransactions() {
		try {
			bufferedReader = new BufferedReader(new FileReader(filePath));
			customerList = new ArrayList<Customer>();
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
			return false;
		}

		Customer newCustomer;
		String line = " ";
		while (line != null) {
			try {
				line = bufferedReader.readLine();
				String name = line;

				newCustomer = new Customer(name);

				line = bufferedReader.readLine();
				while (!line.contains("CREDIT") && !line.contains("CHECK") && !line.contains("CASH") && !line.equals("")) {
					String[] tokens = line.split("[ ]+");
					ItemTuple item = new ItemTuple(tokens[0], Integer.parseInt(tokens[1]));
					newCustomer.addItem(item);

					line = bufferedReader.readLine();
				}

				String[] tokens = line.split("[ ]+");
				if (tokens[0].equals("CREDIT")) {
					newCustomer.addPayment(new CreditCard(tokens[1]));
				} else if (tokens[0].equals("CHECK")) {
					newCustomer.addPayment(new Check(new BigDecimal(tokens[1])));
				} else if (tokens[0].equals("CASH")) {
					newCustomer.addPayment(new Cash(new BigDecimal(tokens[1])));
				}

				customerList.add(newCustomer);

				line = bufferedReader.readLine();

			} catch (Exception exception) {
				exception.printStackTrace();
				break;
			}
		}

		return true;
	}
}
