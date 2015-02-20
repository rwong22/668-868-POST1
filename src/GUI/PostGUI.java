package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import post.Post;


public class PostGUI {

    Post post;
	private JFrame frmPost;
	
	///////////
	TotalPanel totalPanel;
	InvoicePane invoicePane;
	ProductPanel productPanel;
	TimeStampPanel timeStampPanel;
	CustomerPanel customerPanel;
	PaymentPanel paymentPanel;
	///////////
	
	
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
		totalPanel = new TotalPanel(this);
		frmPost.getContentPane().add(totalPanel);
        
        // ----------invoice pane-------------
        invoicePane = new InvoicePane(this);
        frmPost.getContentPane().add(invoicePane);

		// ------------time stamp panel-----------
		timeStampPanel = new TimeStampPanel(this);
		frmPost.getContentPane().add(timeStampPanel);
		
		// ----------product panel------------
		productPanel = new ProductPanel(this);
		frmPost.getContentPane().add(productPanel);
		
		// -------------payment panel------------
		paymentPanel = new PaymentPanel(this);
		frmPost.getContentPane().add(paymentPanel);
		
		// ------------customer panel--------------
		customerPanel = new CustomerPanel(this);
		frmPost.getContentPane().add(customerPanel);
			
		// Add Listeners for the components in all Panels
		productPanel.addListener();
		customerPanel.addListener();
		paymentPanel.addListener();

	}
	
}
