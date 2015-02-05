package store;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import post.Customer;
import post.Post;
import utils.Transaction;

// TODO: , , STORE,
// DONE: ITEM User, CUSTOMER
public class Store {

	public static Scanner scanner = new Scanner(System.in);
	public static ArrayList<Customer> customerList;
	public static Post post = new Post();

	public static void main(String[] args) {
		Post post = new Post();
		post.init();
		customerList = Transaction.getTransactions();

		for (Iterator<Customer> iterator = customerList.iterator(); iterator.hasNext();) {
			Customer customer = (Customer) iterator.next();
			post.helpCustomer(customer);
		}

	}

}
