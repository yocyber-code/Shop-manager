package V;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import M.CustomerDB;
import M.CustomerManager;
import M.ProductDB;
import M.ProductManager;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductFrame extends JFrame
{
	private ArrayList<ProductDB> list;
	private JPanel contentPane;
	private JTextField textField_product_id;
	private JTextField textField_product_name;
	private JTextField textField_price_per_unit;
	private JTextField textField_description;
	private ImagePanel imagePanel;
	private JTable table;

	public ProductFrame() {
		
		try
		{
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(UnsupportedLookAndFeelException e)
		{
			// handle exception
		}
		catch(ClassNotFoundException e)
		{
			// handle exception
		}
		catch(InstantiationException e)
		{
			// handle exception
		}
		catch(IllegalAccessException e)
		{
			// handle exception
		}
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 777, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 771, 378);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 445, 358);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(table.getSelectedColumnCount() < 1 || table.getSelectedColumnCount() > 1)
				{
					return;
				}
				int index = table.getSelectedRow();
				int pid = Integer.parseInt(table.getValueAt(index, 0).toString());
				String pname = table.getValueAt(index, 1).toString();
				double price = Double.parseDouble(table.getValueAt(index, 2).toString());
				String drescription = table.getValueAt(index, 3).toString();

				textField_product_id.setText("" + pid);
				textField_product_name.setText(pname);
				textField_price_per_unit.setText("" + price);
				textField_description.setText(drescription);
				imagePanel.setImage(list.get(index).product_image);
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblProductId = new JLabel("product id");
		lblProductId.setBounds(463, 21, 72, 16);
		panel.add(lblProductId);
		
		textField_product_id = new JTextField();
		textField_product_id.setEditable(false);
		textField_product_id.setColumns(10);
		textField_product_id.setBackground(Color.GRAY);
		textField_product_id.setBounds(552, 16, 212, 26);
		panel.add(textField_product_id);
		
		JLabel lblProductName = new JLabel("product name");
		lblProductName.setBounds(463, 54, 87, 16);
		panel.add(lblProductName);
		
		textField_product_name = new JTextField();
		textField_product_name.setColumns(10);
		textField_product_name.setBounds(552, 49, 212, 26);
		panel.add(textField_product_name);
		
		JLabel lblPricePerUnit = new JLabel("price per unit");
		lblPricePerUnit.setBounds(463, 87, 87, 16);
		panel.add(lblPricePerUnit);
		
		textField_price_per_unit = new JTextField();
		textField_price_per_unit.setColumns(10);
		textField_price_per_unit.setBounds(552, 82, 212, 26);
		panel.add(textField_price_per_unit);
		
		JLabel lblProductDesc = new JLabel("product desc.");
		lblProductDesc.setBounds(463, 120, 87, 16);
		panel.add(lblProductDesc);
		
		textField_description = new JTextField();
		textField_description.setColumns(10);
		textField_description.setBounds(552, 115, 212, 42);
		panel.add(textField_description);
		
		JButton button_save = new JButton("Save New");
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductDB x = new ProductDB(0, textField_product_name.getText().trim(), Double.parseDouble(textField_price_per_unit.getText().trim()),
						textField_description.getText().trim(),(BufferedImage)imagePanel.getImage());
				ProductManager.saveNewCustomer(x);
				load();
			}
		});
		button_save.setBounds(463, 310, 95, 29);
		panel.add(button_save);
		
		JButton button_edit = new JButton("Edit");
		button_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button_edit.setBounds(552, 310, 95, 29);
		panel.add(button_edit);
		
		JButton button_delete = new JButton("Delete");
		button_delete.setBounds(463, 335, 95, 29);
		panel.add(button_delete);
		
		imagePanel = new ImagePanel((Image) null);
		imagePanel.setBounds(552, 163, 212, 135);
		panel.add(imagePanel);
		
		JButton btnBrowser = new JButton("Browser");
		btnBrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new OpenFileFilter("jpeg","Photo in JPEG format") );
				fc.addChoosableFileFilter(new OpenFileFilter("jpg","Photo in JPG format") );
				fc.addChoosableFileFilter(new OpenFileFilter("png","PNG image") );
				fc.addChoosableFileFilter(new OpenFileFilter("svg","Scalable Vector Graphic") );
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(ProductFrame.this);
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					File f = fc.getSelectedFile();
					try
					{
						BufferedImage bimg =  ImageIO.read(f);
						imagePanel.setImage(bimg);
					}
					catch(IOException e1)
					{
						e1.printStackTrace();
					}
				}
			}
		});
		btnBrowser.setBounds(452, 171, 98, 29);
		panel.add(btnBrowser);
		
		load();
	}
	
	public void load()
	{
		list = ProductManager.getAllProduct();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("name");
		model.addColumn("price/unit");
		model.addColumn("drescription");
		for(ProductDB c : list)
		{
			model.addRow(new Object[] {c.product_id, c.product_name, c.price_per_unit, c.product_description});
		}

		table.setModel(model);
	}
}

class OpenFileFilter extends FileFilter {

    String description = "";
    String fileExt = "";

    public OpenFileFilter(String extension) {
        fileExt = extension;
    }

    public OpenFileFilter(String extension, String typeDescription) {
        fileExt = extension;
        this.description = typeDescription;
    }

    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        return (f.getName().toLowerCase().endsWith(fileExt));
    }

    @Override
    public String getDescription() {
        return description;
    }
}
