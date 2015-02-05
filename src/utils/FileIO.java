package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;

import store.Item;

public class FileIO {

	private String filePath;
	private FileIOReader reader;
	private HashMap<Integer, Item> items;

	public FileIO(String filePath) {
		this.filePath = filePath;
		this.items = new HashMap<Integer, Item>();

		reader = new FileIOReader();
		getProducts();
	}

	public HashMap<Integer, Item> getItems() {
		return items;
	}

	private void getProducts() {
		reader.readProducts();
	}

	private class FileIOReader {

		private char[] buffer = new char[10];
		private BufferedReader bufferedReader;

		public FileIOReader() {
			try {
				bufferedReader = new BufferedReader(new FileReader(filePath));
			} catch (FileNotFoundException exception) {
				exception.printStackTrace();
			}
		}

		public boolean readProducts() {
			String line = " ";
			while (line != null) {
				try {
					line = bufferedReader.readLine();

					if (line != null) {
						String[] tokens = new String[3];
						tokens[0] = line.substring(0, 4);
						tokens[1] = line.substring(9, 29);
						tokens[2] = line.substring(34, 41);

						items.put(Integer.parseInt(tokens[0]), new Item(tokens[0], tokens[1], null, new BigDecimal(tokens[2])));
					}

				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
			return true;
		}
	}
}
