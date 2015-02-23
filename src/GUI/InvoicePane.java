package GUI;

import java.awt.Font;
import javax.swing.DropMode;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InvoicePane  extends JScrollPane {
	
    private PostGUI frame;
	JLabel lblInvoice;
	JTextArea txtInvoice;

	/**
	 * Pane that displays the invoice
	 */
	public InvoicePane(PostGUI frame) {
	    this.frame = frame;
		
		this.setBounds(10, 122, 604, 267);
			
		this.setVisible(false);
		
		lblInvoice = new JLabel("Invoice");
		this.setColumnHeaderView(lblInvoice);
		lblInvoice.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtInvoice = new JTextArea();
		txtInvoice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.setViewportView(txtInvoice);
		txtInvoice.setDropMode(DropMode.INSERT);
		txtInvoice.setText("Item\t\t\tQuantity\tUnit Price\tExtended Price\r\n");
		txtInvoice.setEditable(false);
	}
	
	/**
	 * 
	 * @return the text in the jtextarea
	 */
	public String getText() {
		return txtInvoice.getText();
	}

	/**
	 * updates the text in the jtextarea
	 * @param invoice
	 */
	public void setText(String invoice) {
		txtInvoice.setText(invoice);
		
	}
	
	/**
	 * Changes the invoicec label
	 * @param name
	 */
	public void setInvoiceLabel(String name) {
		lblInvoice.setText(name);
	}
	
}
