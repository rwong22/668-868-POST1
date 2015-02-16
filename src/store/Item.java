package store;

import java.math.BigDecimal;


/*
 * File:         [Item]
 * Created:      [2015/2/3]
 * Last Changed: [2015/2/3]
 * Author:       Anderson Tao
 * 
 * Changelog:
 * 2015/2/3 Creation of 'Item'
 * 2015/2/16 price type from BigDecimal to double
 */


public class Item {

	private String upc;
	private String description;
	private String gif;
	private BigDecimal price;

	public Item(String upc, String description, String gif, BigDecimal price) {
		this.setUpc(upc);
		this.setDescription(description);
		this.setGif(gif);
		this.setPrice(price);
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGif() {
		return gif;
	}

	public void setGif(String gif) {
		this.gif = gif;

	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price2) {
		this.price = price2;
	}
}
