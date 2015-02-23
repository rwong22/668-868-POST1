package GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class TotalPanel extends JPanel {
	
    private PostGUI frame;
	private JLabel lblTotal;
	private JLabel lblTotalCost;

	/**
	 * Create the panel.
	 */
	public TotalPanel(PostGUI frame) {
	    this.frame = frame;
	    
        this.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.setBounds(384, 400, 230, 45);
        
        this.setLayout(null);
        
        this.setVisible(false);
        
        lblTotal = new JLabel("Total");                                                                              
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
        lblTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTotal.setBounds(10, 11, 56, 23);
        this.add(lblTotal);
        
        lblTotalCost = new JLabel("$ 0.00");                                                                 
        lblTotalCost.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTotalCost.setHorizontalAlignment(SwingConstants.TRAILING);
        lblTotalCost.setBounds(76, 11, 144, 23);
        this.add(lblTotalCost);
	}
	
	/**
	 * sets the label for total cost
	 */
	public void setTotalCostLabel(String totalCost) {
		lblTotalCost.setText(totalCost);
	}

}
