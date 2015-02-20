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

public class CustomerPanel extends JPanel {

    private PostGUI frame;
	private JLabel lblCustomerName;
	private JTextField nameTextField;
	private String dateTime;
	private JButton btnEnterName;
	
	/**
	 * Create the Customer Panel
	 */
	public CustomerPanel(PostGUI frame) {
	    this.frame = frame;
	    
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setBounds(10, 11, 295, 100);
		
		this.setLayout(null);
		
		lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(10, 25, 130, 20);
		this.add(lblCustomerName);
		lblCustomerName.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnEnterName = new JButton("Enter");																		
		btnEnterName.setEnabled(false);
		
		btnEnterName.setBounds(199, 66, 86, 23);
		this.add(btnEnterName);
		
		nameTextField = new JTextField();
		nameTextField.setText("");				
		nameTextField.setBounds(154, 25, 131, 20);
		this.add(nameTextField);
		nameTextField.setColumns(10);
	}
	
	void addListener() {
	    btnEnterName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.post.setCustomerName(nameTextField.getText().trim());
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
