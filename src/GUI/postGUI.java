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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;


public class postGUI {

	private JFrame frmPost;
	private JTextField nameTextField;
	
	private String invoice;
	private JTextField amountTextField;
	private String dateTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					postGUI window = new postGUI();
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
	public postGUI() {
		initialize();
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
		
		JPanel productPanel = new JPanel();
		productPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		productPanel.setBounds(341, 11, 273, 100);
		frmPost.getContentPane().add(productPanel);
		productPanel.setLayout(null);
		
		JScrollPane invoicePane = new JScrollPane();
		invoicePane.setBounds(10, 122, 604, 267);
		frmPost.getContentPane().add(invoicePane);
		
		JLabel lblInvoice = new JLabel("Invoice");
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
		
		JComboBox upcComboBox = new JComboBox();																			// Get list of UPC for drop down
		upcComboBox.setBounds(54, 27, 73, 20);
		productPanel.add(upcComboBox);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQuantity.setBounds(137, 25, 76, 20);
		productPanel.add(lblQuantity);

		final JComboBox quantityComboBox = new JComboBox();
		quantityComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		quantityComboBox.setSelectedIndex(0);
		quantityComboBox.setBounds(223, 27, 40, 20);
		productPanel.add(quantityComboBox);
		
		JButton btnAdd = new JButton("Add");																				// What happens after the add button is pressed? get item? get unit price? get extened price?	calculate total? 
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invoice = txtInvoice.getText();
				invoice = invoice + "\n" + "ANOTHER TEST \t\t" + (Integer.parseInt((String) quantityComboBox.getSelectedItem()) );		// add to invoice text area
				txtInvoice.setText(invoice);
			}
		});
		btnAdd.setBounds(80, 66, 89, 23);
		productPanel.add(btnAdd);
		

		
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
		
		JPanel customerPanel = new JPanel();
		customerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		customerPanel.setBounds(10, 11, 295, 100);
		frmPost.getContentPane().add(customerPanel);
		customerPanel.setLayout(null);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(10, 21, 130, 20);
		customerPanel.add(lblCustomerName);
		lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		nameTextField = new JTextField();
		nameTextField.setText("Name");																						
		nameTextField.setBounds(154, 23, 131, 20);
		customerPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		JButton btnEnterName = new JButton("Enter");																		// if nameTextField is "admin" -> enable rest of the fields
		btnEnterName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateTime = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss").format(Calendar.getInstance().getTime());
				lblTimeStamp.setText(dateTime);																				// Update the date and time after a name is entered
			}
		});
		btnEnterName.setBounds(103, 66, 86, 23);
		customerPanel.add(btnEnterName);
		
		JPanel totalPanel = new JPanel();
		totalPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		totalPanel.setBounds(384, 400, 230, 45);
		frmPost.getContentPane().add(totalPanel);
		totalPanel.setLayout(null);
		
		JLabel lblTotal = new JLabel("Total");																				
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotal.setBounds(10, 11, 43, 23);
		totalPanel.add(lblTotal);
		
		JLabel lblTotalCost = new JLabel("$ 000000000.00");																	// where and how it total calculated?
		lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalCost.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalCost.setBounds(76, 11, 144, 23);
		totalPanel.add(lblTotalCost);
		

		
		JPanel paymentPanel = new JPanel();
		paymentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		paymentPanel.setBounds(117, 456, 497, 95);
		frmPost.getContentPane().add(paymentPanel);
		paymentPanel.setLayout(null);
		
		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setHorizontalAlignment(SwingConstants.CENTER);
		lblPayment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPayment.setBounds(0, 0, 66, 14);
		paymentPanel.add(lblPayment);
		
		JLabel paymentTypeLabel = new JLabel("Payment Type");
		paymentTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		paymentTypeLabel.setBounds(10, 22, 132, 20);
		paymentPanel.add(paymentTypeLabel);
		
		// Temporary array to hold payment types																			// pass in payment types for dropdown?
		String[] paymentTypeArray = {"Cash","Check","Credit"};
		
		JComboBox paymentTypeComboBox = new JComboBox();
		paymentTypeComboBox.setModel(new DefaultComboBoxModel(paymentTypeArray));
		//					paymentTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"CASH", "CHECK", "CREDIT"}));
		paymentTypeComboBox.setSelectedIndex(0);
		paymentTypeComboBox.setBounds(145, 24, 73, 20);
		paymentPanel.add(paymentTypeComboBox);
		
		JLabel amountLabel = new JLabel("Amount");
		amountLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		amountLabel.setBounds(243, 22, 73, 20);
		paymentPanel.add(amountLabel);
		
		amountTextField = new JTextField();																					// Check if valid amount entered
		amountTextField.setBounds(326, 24, 161, 20);
		paymentPanel.add(amountTextField);
		amountTextField.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit Order");																			// check everything before submission? what is submitted? a transaction object?
		btnSubmit.setBounds(373, 61, 114, 23);																						// clears everything at the end of transaction?
		paymentPanel.add(btnSubmit);

	}
}
