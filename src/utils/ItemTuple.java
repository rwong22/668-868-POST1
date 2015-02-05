package utils;

public class ItemTuple {

	private int UPC;
	private int quantity;

	public ItemTuple(int UPC, int quantity) {
		this.UPC = UPC;
		this.quantity = quantity;
	}

	public int getUPC() {
		return UPC;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setUPC(int uPC) {
		UPC = uPC;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
