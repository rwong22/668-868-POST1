package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import post.Post;
import common.Item;

public class ProductPanel extends JPanel {

    private PostGUI frame;
    
	private String invoice;
	private JComboBox<String> upcComboBox;
	private JComboBox<String> quantityComboBox;
	private JLabel lblProduct;
	private JLabel lblUpc;
	private JLabel lblQuantity;
	private JButton btnAdd;
	
	/**
	 * Create the panel.
	 */
	public ProductPanel(PostGUI frame) {
	    this.frame = frame;
	    
	    this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setBounds(341, 11, 273, 100);
		
		this.setLayout(null);
		
		this.setVisible(false);
		
		lblProduct = new JLabel("Step 2: Add Product(s)");
		lblProduct.setHorizontalAlignment(SwingConstants.LEFT);
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProduct.setBounds(10, 0, 253, 14);
		this.add(lblProduct);
		
		lblUpc = new JLabel("UPC");
		lblUpc.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpc.setBounds(10, 25, 46, 20);
		this.add(lblUpc);
		lblUpc.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		// !!! UPC list - done
		upcComboBox = new JComboBox<String>();																			// Get list of UPC for drop down
		upcComboBox.setModel(new DefaultComboBoxModel(frame.post.getUPCList().toArray()));
		upcComboBox.setSelectedIndex(0);
		upcComboBox.setBounds(57, 25, 73, 20);
		this.add(upcComboBox);
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuantity.setBounds(140, 25, 82, 20);
		this.add(lblQuantity);

		quantityComboBox = new JComboBox<String>();
		quantityComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		quantityComboBox.setSelectedIndex(0);
		quantityComboBox.setBounds(223, 25, 40, 20);
		this.add(quantityComboBox);
		
		btnAdd = new JButton("Add");																				// What happens after the add button is pressed? get item? get unit price? get extened price?	calculate total? 
		btnAdd.setBounds(174, 66, 89, 23);
		this.add(btnAdd);
	}
	
	void addListener() {
	    btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String UPC = (String) upcComboBox.getSelectedItem();
                Item item = frame.post.getItem(UPC);
                int quantity = Integer.parseInt((String) quantityComboBox.getSelectedItem());
                frame.post.addItem(UPC, quantity);
                
                invoice = frame.invoicePane.getText();
                String itemInfo = "Can not read item information!";
                try {
                    double price = item.getPrice().doubleValue();
                    itemInfo = String.format("\n%20s\t\t%2s\t%6.2f\t%6.2f", item.getDescription(),
                                  (String) quantityComboBox.getSelectedItem(),
                                  price, price * quantity);       // add to invoice text area
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                invoice = invoice + itemInfo;
                frame.invoicePane.setText(invoice);
                
                frame.totalPanel.setTotalCostLabel(String.format("$%10.2f", frame.post.getTotalDouble()));
            }
        });
	}
	
	/**
	 * Sets the upc combo box selected index
	 * @param index
	 */
	public void setUpcComboBoxIndex(int index) {
		upcComboBox.setSelectedIndex(index);
	}
	
	/**
	 * Sets the quantity combobox selected index
	 * @param index
	 */
	public void setQuantityComboBoxIndex(int index) {
		quantityComboBox.setSelectedIndex(index);
	}

}
