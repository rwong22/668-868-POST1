package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import post.Post;
import javax.swing.SwingConstants;

public class CustomerPanel extends JPanel {

    private PostGUI frame;
    private JLabel lblEnterName;
	private JLabel lblCustomerName;
	private JTextField nameTextField;
	private String dateTime;
	private JButton btnEnterName;
	private Boolean managerIsLoggedIn;	// keeps track whether manager is logged in or not
	
	/**
	 * Create the Customer Panel
	 */
	public CustomerPanel(PostGUI frame) {
	    this.frame = frame;
	    
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setBounds(10, 11, 322, 100);
		
		this.setLayout(null);
		
		// default: manager is not logged in
		managerIsLoggedIn = false;
		
		lblEnterName = new JLabel("Step 1: Enter Name");
		lblEnterName.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterName.setBounds(10, 0, 302, 14);
		lblEnterName.setVisible(false);
		add(lblEnterName);
		
		lblCustomerName = new JLabel("Manager Login");
		lblCustomerName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerName.setBounds(10, 25, 161, 20);
		this.add(lblCustomerName);
		lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnEnterName = new JButton("Enter");																		
		btnEnterName.setEnabled(false);
		
		btnEnterName.setBounds(226, 66, 86, 23);
		this.add(btnEnterName);
		
		nameTextField = new JTextField();
		nameTextField.setText("");				
		nameTextField.setBounds(181, 25, 131, 20);
		this.add(nameTextField);
		nameTextField.setColumns(10);
		
	}
	
	void addListener() {
	    btnEnterName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.post.setCustomerName(nameTextField.getText().trim());
                
                // if "manager" is entered into the name text field
                if(nameTextField.getText().trim().equals("manager")) {
                	
                	// toggles whether or not manager is logged in
                	managerIsLoggedIn = !managerIsLoggedIn;				
                	
                	// manager is logged in
                    if(managerIsLoggedIn == true) {
                    	lblCustomerName.setText("Customer Name");
                    	lblEnterName.setVisible(true);
                    	
                    	frame.productPanel.setVisible(true);
                    	frame.invoicePane.setVisible(true);
                    	frame.totalPanel.setVisible(true);
                    	frame.timeStampPanel.setVisible(true);
                    	frame.paymentPanel.setVisible(true);
                    	
                    	frame.paymentPanel.resetGUI();
                    }
                    
                    // manager is not logged in
                    else {	// managerIsLoggedIn == false
                    	lblCustomerName.setText("Manager Login");
                    	lblEnterName.setVisible(false);
                    	
                    	frame.productPanel.setVisible(false);
                    	frame.invoicePane.setVisible(false);
                    	frame.totalPanel.setVisible(false);
                    	frame.timeStampPanel.setVisible(false);
                    	frame.paymentPanel.setVisible(false);
                    	
                    	frame.paymentPanel.resetGUI();
                    }
                }
   
                dateTime = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss").format(Calendar.getInstance().getTime());
                frame.timeStampPanel.setLabel();                                                                            // Update the date and time after a name is entered
                frame.invoicePane.setInvoiceLabel("Invoice   for   " + nameTextField.getText().trim());                 // call a setCustomerName function of some sort?
                frame.paymentPanel.setTextFieldEnabled(true);                                                   // enables payment text field after "Enter" name button is pressed
            }
        });
	    
	    nameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {       
                if(!nameTextField.getText().trim().equals("")) {        // if the name text field is not empty, enable "Enter" name button 
                    btnEnterName.setEnabled(true);
                }
                else {                                                  // else the name text field is empty, disable "Enter" name button 
                    btnEnterName.setEnabled(false);     
                }
            }
        });
	}
	
	/**
	 * Sets the name in the text field
	 * @param name
	 */
	public void setText(String name) {
		nameTextField.setText(name);
	}
	
	/**
	 * Sets the button to enabled or disabled
	 */
	public void setEnabled(boolean bool) {
		btnEnterName.setEnabled(bool);
	}
}
