package GUI;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class TimeStampPanel extends JPanel {
	
    private PostGUI frame;
	JLabel lblTimeStamp;
	String dateTime;
	
	/**
	 * Create the Timestamp Panel
	 */
	public TimeStampPanel(PostGUI frame) {
	    this.frame = frame;
	    
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setBounds(10, 400, 167, 45);
		this.setLayout(null);
		
		this.setVisible(false);
		
		dateTime = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss").format(Calendar.getInstance().getTime());					// time and date
		lblTimeStamp = new JLabel(dateTime);
		lblTimeStamp.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeStamp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTimeStamp.setBounds(0, 11, 167, 23);
		this.add(lblTimeStamp);
	
	}
	
	/**
	 * Updates the date/time stamp label
	 */
	public void setLabel() {
		dateTime = new SimpleDateFormat("yyyy/MM/dd   HH:mm:ss").format(Calendar.getInstance().getTime());
		lblTimeStamp.setText(dateTime);
	}
	
}
