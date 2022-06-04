package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				CustomerFrame f = new CustomerFrame();
				f.setVisible(true);
			}
		});
		btnCustomer.setBounds(21, 21, 117, 29);
		contentPane.add(btnCustomer);

		JButton btnProduct = new JButton("Product");
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductFrame f = new ProductFrame();
				f.setVisible(true);
			}
		});
		btnProduct.setBounds(21, 53, 117, 29);
		contentPane.add(btnProduct);

		JButton btnUser = new JButton("User");
		btnUser.setEnabled(false);
		btnUser.setBounds(21, 83, 117, 29);
		contentPane.add(btnUser);

		JButton btnInvoice = new JButton("Invoice");
		btnInvoice.setEnabled(false);
		btnInvoice.setBounds(21, 113, 117, 29);
		contentPane.add(btnInvoice);
	}
}
