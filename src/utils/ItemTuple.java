package utils;

public class ItemTuple {

	private String UPC;
	private int quantity;

	public ItemTuple(String UPC, int quantity) {
		this.UPC = UPC;
		this.quantity = quantity;
	}

	public String getUPC() {
		return UPC;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setUPC(String uPC) {
		UPC = uPC;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
