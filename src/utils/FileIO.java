package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;

import store.Item;

public class FileIO {

	private String filePath;
	private FileIOType ioType;
	private FileIOReader reader;
	private FileIOWriter writer;
	private HashMap<Integer, Item> items;

	public static void main(String[] args) {
		FileIO io = new FileIO("/home/arod/workspace_eclipse/668-POST1/products.txt", FileIOType.READER);
	}

	public FileIO(String filePath, FileIOType ioType) {
		this.filePath = filePath;
		this.ioType = ioType;
		this.items = new HashMap<Integer, Item>();

		if (ioType == FileIOType.READER) {
			reader = new FileIOReader();
			getProducts();
		} else {
			writer = new FileIOWriter();
		}
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
			int i = 1;
			String line = " ";
			String delims = "[ ]+";
			while (line != null) {
				try {
					line = bufferedReader.readLine();

					if (line != null) {
						String[] tokens = line.split(delims);

						items.put(Integer.parseInt(tokens[0]), new Item(tokens[0], tokens[1], null, new BigDecimal(tokens[2])));
					}

				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
			return true;
		}
	}

	private class FileIOWriter {

		public boolean write() {
			return false;
		}
	}

}
