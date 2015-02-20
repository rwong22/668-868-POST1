package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import post.Post;

public class PaymentPanel extends JPanel {

    private PostGUI frame;
	private String dateTime;
	private JFormattedTextField paymentFormattedTextField;
	private JButton btnSubmit;
	private JLabel lblPayment;
	private JLabel paymentTypeLabel;
	private JLabel amountCreditcardLabel;
	private JComboBox paymentTypeComboBox;
	
	/**
	 * Create the panel.
	 */
	public PaymentPanel(PostGUI frame) {
	    this.frame = frame;
	    
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setBounds(71, 456, 543, 95);
		this.setLayout(null);
		
		lblPayment = new JLabel("Payment");
		lblPayment.setHorizontalAlignment(SwingConstants.CENTER);
		lblPayment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPayment.setBounds(0, 0, 66, 14);
		this.add(lblPayment);
		
		paymentTypeLabel = new JLabel("Payment Type");
		paymentTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		paymentTypeLabel.setBounds(10, 22, 125, 20);
		this.add(paymentTypeLabel);
		

		amountCreditcardLabel = new JLabel("Cash Amount $");
		amountCreditcardLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		amountCreditcardLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		amountCreditcardLabel.setBounds(228, 22, 133, 20);
		this.add(amountCreditcardLabel);
		
		
		// Temporary array to hold payment types
		// pass in payment types for drop down?
		String[] paymentTypeArray = {"Cash","Check","Credit"};
		
		paymentTypeComboBox = new JComboBox();
		paymentTypeComboBox.setModel(new DefaultComboBoxModel(paymentTypeArray));
		paymentTypeComboBox.setSelectedIndex(0);
		paymentTypeComboBox.setBounds(140, 24, 73, 20);
		this.add(paymentTypeComboBox);
		
		
		// Formatted Text Field
		paymentFormattedTextField = new JFormattedTextField();
		paymentFormattedTextField.setEnabled(false);
		paymentFormattedTextField.setBounds(369, 24, 164, 20);
		this.add(paymentFormattedTextField);

		btnSubmit = new JButton("Submit Order");
		btnSubmit.setEnabled(false);
		btnSubmit.setBounds(419, 55, 114, 23);
		this.add(btnSubmit);

	}
	
	void addListener() {
	    paymentTypeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             // Updates amountCreditcardLabel to which payment type was selected
                if(paymentTypeComboBox.getSelectedItem().equals("Cash")) {
                 // Cash was selected from the drop down
                    amountCreditcardLabel.setText("Cash Amount $");                     
                }
                else if(paymentTypeComboBox.getSelectedItem().equals("Check")) {
                 // Check was selected from the drop down
                    amountCreditcardLabel.setText("Check Amount $");
                }
                else {
                 // Credit was selected from the drop down
                    amountCreditcardLabel.setText("Credit Card #");
                }
            }
        });
	    
	    paymentFormattedTextField.addKeyListener(new KeyAdapter() {
            @Override                                                                   
            public void keyReleased(KeyEvent e) {
             // if the payment text field is not empty, enable submit button.
                if(!paymentFormattedTextField.getText().trim().equals("")) {
                    btnSubmit.setEnabled(true);
                }
                else {
                 // else the payment text field is empty, disable submit button
                    btnSubmit.setEnabled(false);
                }
            }
        });
	    
	    btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dateTime = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss")
                                .format(Calendar.getInstance().getTime());
             // Update the date and time after "Submit Order" is pressed
                frame.timeStampPanel.setLabel();
                frame.post.setPurchaseTime(dateTime);
                
             // Cash was selected from the drop down
                if (paymentTypeComboBox.getSelectedItem().equals("Credit")) {
                    String cardNumber = paymentFormattedTextField.getText().trim();
                    frame.post.addCreditPayment(frame.post.getTotalDouble(), cardNumber);                   
                } else {
                    // If input isn't a valid number, disable button, don't checkout.
                    try {
                        double amount = Double.parseDouble(paymentFormattedTextField.getText().trim());
                        if (paymentTypeComboBox.getSelectedItem().equals("Cash")) {
                            frame.post.addCashPayment(amount);
                        } else {
                            frame.post.addCheckPayment(amount);
                        }
                    } catch(NumberFormatException e1) {
                        btnSubmit.setEnabled(false);
                        return;
                    }
                }
                
                // Authenticate the payment
                if (!frame.post.authenticate()) {
                    System.out.println("Authentication Fail!");
                    return;
                }
                
                frame.post.checkOut();
                resetGUI();
                
            }
        });
	}
	
	private void resetGUI() {
	    frame.customerPanel.setText("");
        frame.invoicePane.setText("Item\t\t\tQuantity\tUnit Price\tExtended Price\r\n");
        paymentFormattedTextField.setText("");
        frame.totalPanel.setTotalCostLabel("$ 0.00");
        frame.invoicePane.setInvoiceLabel("Invoice");
        amountCreditcardLabel.setText("Cash Amount $");
        frame.productPanel.setUpcComboBoxIndex(0);
        frame.productPanel.setQuantityComboBoxIndex(0);
        paymentTypeComboBox.setSelectedIndex(0);
        frame.customerPanel.setEnabled(false);
        btnSubmit.setEnabled(false);
        paymentFormattedTextField.setEnabled(false);
	}
	
	/**
	 * Sets the formatted text field to enabled or disabled
	 */
	public void setTextFieldEnabled(boolean bool) {
		paymentFormattedTextField.setEnabled(bool);
	}

}
