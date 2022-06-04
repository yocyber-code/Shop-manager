package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import M.CustomerDB;
import M.CustomerManager;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerFrame extends JFrame
{
	private ArrayList<CustomerDB> list;
	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_surname;
	private JTextField textField_phone;
	private JTable table;

	public CustomerFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 684, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 445, 358);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(table.getSelectedColumnCount() < 1)
				{
					return;
				}
				int index = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(index, 0).toString());
				String name = table.getValueAt(index, 1).toString();
				String surname = table.getValueAt(index, 2).toString();
				String phone = table.getValueAt(index, 3).toString();

				textField_id.setText("" + id);
				textField_name.setText(name);
				textField_surname.setText(surname);
				textField_phone.setText(phone);
				
			}
		});
		table.setBackground(Color.white);
		scrollPane.setViewportView(table);

		JLabel lblId = new JLabel("id");
		lblId.setBounds(463, 21, 22, 16);
		contentPane.add(lblId);

		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setBackground(Color.GRAY);
		textField_id.setBounds(518, 16, 130, 26);
		contentPane.add(textField_id);
		textField_id.setColumns(10);

		JLabel lblName = new JLabel("name");
		lblName.setBounds(463, 54, 50, 16);
		contentPane.add(lblName);

		textField_name = new JTextField();
		textField_name.setColumns(10);
		textField_name.setBounds(518, 49, 130, 26);
		contentPane.add(textField_name);

		JLabel lblSurname = new JLabel("surname");
		lblSurname.setBounds(463, 87, 55, 16);
		contentPane.add(lblSurname);

		textField_surname = new JTextField();
		textField_surname.setColumns(10);
		textField_surname.setBounds(518, 82, 130, 26);
		contentPane.add(textField_surname);

		JLabel lblPhone = new JLabel("phone");
		lblPhone.setBounds(463, 120, 54, 16);
		contentPane.add(lblPhone);

		textField_phone = new JTextField();
		textField_phone.setColumns(10);
		textField_phone.setBounds(518, 115, 130, 26);
		contentPane.add(textField_phone);

		JButton btnSaveNew = new JButton("Save New");
		btnSaveNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				CustomerDB x = new CustomerDB(0, textField_name.getText().trim(), textField_surname.getText().trim(),
						textField_phone.getText().trim());
				CustomerManager.saveNewCustomer(x);
				load();
			}
		});
		btnSaveNew.setBounds(463, 148, 117, 29);
		contentPane.add(btnSaveNew);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				CustomerDB x = new CustomerDB(Integer.parseInt(textField_id.getText()), textField_name.getText().trim(),
						textField_surname.getText().trim(), textField_phone.getText().trim());
				CustomerManager.editCustomer(x);
				load();
				textField_id.setText("");
				textField_name.setText("");
				textField_surname.setText("");
				textField_phone.setText("");

				JOptionPane.showMessageDialog(CustomerFrame.this, "Finish");
			}
		});
		btnEdit.setBounds(463, 174, 117, 29);
		contentPane.add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(CustomerFrame.this, "Do you want to delete?",
						"Confirm?", JOptionPane.YES_NO_OPTION))
				{
					CustomerDB x = new CustomerDB(Integer.parseInt(textField_id.getText()), textField_name.getText().trim(),
							textField_surname.getText().trim(), textField_phone.getText().trim());
					CustomerManager.deleteCustomer(x);
					load();
					textField_id.setText("");
					textField_name.setText("");
					textField_surname.setText("");
					textField_phone.setText("");
					JOptionPane.showMessageDialog(CustomerFrame.this, "Delete Finish");
				}
				else
				{
					JOptionPane.showMessageDialog(CustomerFrame.this, "Delete Cancel");
				}
				
			}
		});
		btnDelete.setBounds(463, 201, 117, 29);
		contentPane.add(btnDelete);

		load();
	}

	public void load()
	{
		list = CustomerManager.getAllcustomer();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("name");
		model.addColumn("surname");
		model.addColumn("phone");
		for(CustomerDB c : list)
		{
			model.addRow(new Object[] {c.id, c.name, c.surname, c.phone});
		}

		table.setModel(model);
	}
}
