package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;

//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
//import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import common.Item;
import post.Post;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class PostGUI {

    private Post post;
	private JFrame frmPost;
	private JTextField nameTextField;
	
	private String invoice;
	private JTextField amountTextField;
	private String dateTime;
	
	private JFormattedTextField paymentFormattedTextField;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PostGUI window = new PostGUI(null);
					window.frmPost.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PostGUI(Post post) {
	    this.post = post;
		initialize();
	}
	
	public void open() {
	    frmPost.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPost = new JFrame();
		frmPost.setTitle("POST");
		frmPost.setBounds(100, 100, 640, 600);
		frmPost.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPost.getContentPane().setLayout(null);
		
		// -----------total panel------------
		JPanel totalPanel = new JPanel();
        totalPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        totalPanel.setBounds(384, 400, 230, 45);
        frmPost.getContentPane().add(totalPanel);
        totalPanel.setLayout(null);
        
        JLabel lblTotal = new JLabel("Total");                                                                              
        lblTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTotal.setBounds(10, 11, 43, 23);
        totalPanel.add(lblTotal);
        
        JLabel lblTotalCost = new JLabel("$ 0.00");                                                                 // where and how it total calculated?
        lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTotalCost.setHorizontalAlignment(SwingConstants.TRAILING);
        lblTotalCost.setBounds(76, 11, 144, 23);
        totalPanel.add(lblTotalCost);
        
		// ----------product panel------------
		JPanel productPanel = new JPanel();
		productPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		productPanel.setBounds(341, 11, 273, 100);
		frmPost.getContentPane().add(productPanel);
		productPanel.setLayout(null);
		
		JScrollPane invoicePane = new JScrollPane();
		invoicePane.setBounds(10, 122, 604, 267);
		frmPost.getContentPane().add(invoicePane);
		
		final JLabel lblInvoice = new JLabel("Invoice");
		invoicePane.setColumnHeaderView(lblInvoice);
		lblInvoice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		final JTextArea txtInvoice = new JTextArea();
		txtInvoice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		invoicePane.setViewportView(txtInvoice);
		txtInvoice.setDropMode(DropMode.INSERT);
		txtInvoice.setText("Item\t\t\tQuantity\tUnit Price\tExtended Price\r\n");
		txtInvoice.setEditable(false);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProduct.setBounds(0, 0, 62, 14);
		productPanel.add(lblProduct);
		
		JLabel lblUpc = new JLabel("UPC");
		lblUpc.setBounds(10, 25, 34, 20);
		productPanel.add(lblUpc);
		lblUpc.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// !!! UPC list - done
		JComboBox<String> upcComboBox = new JComboBox<String>();																			// Get list of UPC for drop down
		upcComboBox.setModel(new DefaultComboBoxModel(post.getUPCList().toArray()));
		upcComboBox.setSelectedIndex(0);
		upcComboBox.setBounds(54, 25, 73, 20);
		productPanel.add(upcComboBox);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQuantity.setBounds(137, 25, 76, 20);
		productPanel.add(lblQuantity);

		final JComboBox<String> quantityComboBox = new JComboBox<String>();
		quantityComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		quantityComboBox.setSelectedIndex(0);
		quantityComboBox.setBounds(223, 25, 40, 20);
		productPanel.add(quantityComboBox);
		
		// !!! Button - Add - done
		JButton btnAdd = new JButton("Add");																				// What happens after the add button is pressed? get item? get unit price? get extened price?	calculate total? 
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String UPC = (String) upcComboBox.getSelectedItem();
			    Item item = post.getItem(UPC);
			    int quantity = Integer.parseInt((String) quantityComboBox.getSelectedItem());
			    post.addItem(UPC, quantity);
			    
				invoice = txtInvoice.getText();
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
				txtInvoice.setText(invoice);
				
				lblTotalCost.setText(String.format("$%10.2f", post.getTotalDouble()));
			}
		});
		btnAdd.setBounds(174, 66, 89, 23);
		productPanel.add(btnAdd);
		

		// ------------time stamp panel-----------
		JPanel timeStampPanel = new JPanel();
		timeStampPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		timeStampPanel.setBounds(10, 400, 167, 45);
		frmPost.getContentPane().add(timeStampPanel);
		timeStampPanel.setLayout(null);
		
		dateTime = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss").format(Calendar.getInstance().getTime());					// time and date
		final JLabel lblTimeStamp = new JLabel(dateTime);
		lblTimeStamp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTimeStamp.setBounds(10, 11, 153, 23);
		timeStampPanel.add(lblTimeStamp);
		
		// ------------customer panel--------------
		JPanel customerPanel = new JPanel();
		customerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		customerPanel.setBounds(10, 11, 295, 100);
		frmPost.getContentPane().add(customerPanel);
		customerPanel.setLayout(null);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(10, 25, 130, 20);
		customerPanel.add(lblCustomerName);
		lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// !!! Button - Enter - done
		final JButton btnEnterName = new JButton("Enter");																		// if nameTextField is "admin" -> enable rest of the fields???
		btnEnterName.setEnabled(false);
		btnEnterName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    post.setCustomerName(nameTextField.getText().trim());
				dateTime = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss").format(Calendar.getInstance().getTime());
				lblTimeStamp.setText(dateTime);																				// Update the date and time after a name is entered
				lblInvoice.setText("Invoice   for   " + nameTextField.getText().trim());					// call a setCustomerName function of some sort?
				paymentFormattedTextField.setEnabled(true);													// enables payment text field after "Enter" name button is pressed
			}
		});
		btnEnterName.setBounds(199, 66, 86, 23);
		customerPanel.add(btnEnterName);
		
		nameTextField = new JTextField();
		nameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {		
				if(!nameTextField.getText().trim().equals("")) {		// if the name text field is not empty, enable "Enter" name button 
					btnEnterName.setEnabled(true);
				}
				else {													// else the name text field is empty, disable "Enter" name button 
					btnEnterName.setEnabled(false);		
				}
			}
		});
		nameTextField.setText("");				
		nameTextField.setBounds(154, 25, 131, 20);
		customerPanel.add(nameTextField);
		nameTextField.setColumns(10);
			
		// -------------payment panel------------
		JPanel paymentPanel = new JPanel();
		paymentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		paymentPanel.setBounds(71, 456, 543, 95);
		frmPost.getContentPane().add(paymentPanel);
		paymentPanel.setLayout(null);
		
		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setHorizontalAlignment(SwingConstants.CENTER);
		lblPayment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPayment.setBounds(0, 0, 66, 14);
		paymentPanel.add(lblPayment);
		
		JLabel paymentTypeLabel = new JLabel("Payment Type");
		paymentTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		paymentTypeLabel.setBounds(10, 22, 125, 20);
		paymentPanel.add(paymentTypeLabel);
		

		final JLabel amountCreditcardLabel = new JLabel("Cash Amount $");
		amountCreditcardLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		amountCreditcardLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		amountCreditcardLabel.setBounds(228, 22, 133, 20);
		paymentPanel.add(amountCreditcardLabel);
		
		
		// Temporary array to hold payment types																			// pass in payment types for drop down?
		String[] paymentTypeArray = {"Cash","Check","Credit"};
		
		final JComboBox paymentTypeComboBox = new JComboBox();
		paymentTypeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {								// Updates amountCreditcardLabel to which payment type was selected
				if(paymentTypeComboBox.getSelectedItem().equals("Cash")) {				// Cash was selected from the drop down
					amountCreditcardLabel.setText("Cash Amount $");						
				}
				else if(paymentTypeComboBox.getSelectedItem().equals("Check")) {		// Check was selected from the drop down
					amountCreditcardLabel.setText("Check Amount $");
				}
				else {																	// Credit was selected from the drop down
					amountCreditcardLabel.setText("Credit Card #");
				}
			}
		});
		paymentTypeComboBox.setModel(new DefaultComboBoxModel(paymentTypeArray));
		//					paymentTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"CASH", "CHECK", "CREDIT"}));
		paymentTypeComboBox.setSelectedIndex(0);
		paymentTypeComboBox.setBounds(140, 24, 73, 20);
		paymentPanel.add(paymentTypeComboBox);

		
		// !!! Button - Submit - Need modify
		final JButton btnSubmit = new JButton("Submit Order");																			// check everything before submission? what is submitted? a transaction object?
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateTime = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss").format(Calendar.getInstance().getTime());
				lblTimeStamp.setText(dateTime);											// Update the date and time after "Submit Order" is pressed
				post.setPurchaseTime(dateTime);
				
				if(paymentTypeComboBox.getSelectedItem().equals("Credit")) {              // Cash was selected from the drop down
                    String cardNumber = paymentFormattedTextField.getText().trim();
                    post.addCreditPayment(post.getTotalDouble(), cardNumber);                   
                } else {
                    // If input isn't a valid number, disable button, don't checkout.
                    try {
                        double amount = Double.parseDouble(paymentFormattedTextField.getText().trim());
                        if (paymentTypeComboBox.getSelectedItem().equals("Cash")) {
                            post.addCashPayment(amount);
                        } else {
                            post.addCheckPayment(amount);
                        }
                    } catch(NumberFormatException e1) {
                        btnSubmit.setEnabled(false);
                        return;
                    }
                }
				
				post.checkOut();
				
				// Reset GUI
				nameTextField.setText("");
				txtInvoice.setText("Item\t\t\tQuantity\tUnit Price\tExtended Price\r\n");
		        paymentFormattedTextField.setText("");
		        lblTotalCost.setText("$ 0.00");
		        lblInvoice.setText("Invoice");
                amountCreditcardLabel.setText("Cash Amount $");
		        upcComboBox.setSelectedIndex(0);
		        quantityComboBox.setSelectedIndex(0);
		        paymentTypeComboBox.setSelectedIndex(0);
		        btnEnterName.setEnabled(false);
		        btnSubmit.setEnabled(false);
		        paymentFormattedTextField.setEnabled(false);
			}
		});
		btnSubmit.setEnabled(false);
		btnSubmit.setBounds(419, 55, 114, 23);																						// clears everything at the end of transaction?
		paymentPanel.add(btnSubmit);
		
		paymentFormattedTextField = new JFormattedTextField();							// Still need to check for valid amount input or credit card number depending on which payment type was selected
		paymentFormattedTextField.setEnabled(false);
		paymentFormattedTextField.addKeyListener(new KeyAdapter() {
			@Override																	
			public void keyReleased(KeyEvent e) {										
				if(!paymentFormattedTextField.getText().trim().equals("")) {			// if the payment text field is not empty, enable submit button.  
					btnSubmit.setEnabled(true);
				}
				else {																	// else the payment text field is empty, disable submit button
					btnSubmit.setEnabled(false);
				}
			}
		});
		paymentFormattedTextField.setBounds(369, 24, 164, 20);
		paymentPanel.add(paymentFormattedTextField);

	}
	
}
