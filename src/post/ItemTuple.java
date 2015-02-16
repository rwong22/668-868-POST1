package post;

/**
 * Holds an Item UPC and quantity for that Item.
 * b/c we are using a singleton in the Inventory class,
 * we only need to really pass around ItemTuple arrays
 * to have all Item information, and customer cart info.
 */
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
