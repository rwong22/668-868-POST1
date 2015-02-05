package store;
import java.util.Scanner;

import post.Customer;
import post.Post;

//TODO: , , STORE, 
//DONE: ITEM User, CUSTOMER
public class Store {
	static Scanner scanner = new Scanner(System.in);
	static Customer customer;
	static Post post = new Post();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String login;
		do {
			System.out.println("POST STATUS: Not Open");
			System.out.print("hi, login: ");
			login = scanner.nextLine();
			System.out.println();
		} while (!login.equals("admin"));

		// admin stuff
		System.out.println("POST STATUS: OPEN");
		post.init();

		// POST mode:
		do {
			System.out.print("Login: ");
			login = scanner.nextLine();
			System.out.println();
			if (!login.equals("admin")) {
				customer = new Customer(login);
				goToCustomerMode();
			}

		} while (!login.equals("admin"));

		// close store
		post.cleanup();
		System.out.println("byebye");

	}

	private static void goToCustomerMode() {
		// buy stuff
		String scanned = "";
		do {
			System.out.print("hi, " + customer.getName()
					+ " Enter UPC#, 'p' for payment, or 'x' for exit: ");
			scanned = scanner.nextLine();
			if (!scanned.equals("x") || !scanned.equals("p")) {
				// check if upc is valid
				if (post.verifyUPC(scanned)) {
					// success
					customer.addItem(post.getItem(scanned));// this might be a
															// little odd, but
															// will rework later
															// on
					System.out.println("UPC FOUND! item added to Cart");
				} else {
					// fail
					System.out.println("sorry, UPC does not exsist in system.");
				}
			}
			// payment mode
			// if (scanned.equals("p")) {
			// // check if upc is valid
			// if (post.verifyUPC(scanned)) {
			// // success
			// customer.addItem(post.getItem(scanned));// this might be a
			// // little odd, but
			// // will rework later
			// // on
			// System.out.println("UPC FOUND! item added to Cart");
			// } else {
			// // fail
			// System.out.println("sorry, UPC does not exsist in system.");
			// }
			// }

		} while (!scanned.equals("x"));

		// exit
	}
}
