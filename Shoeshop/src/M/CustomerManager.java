package M;

import java.sql.*;
import java.util.ArrayList;

import common.GlobalData;

public class CustomerManager
{
	public static ArrayList<CustomerDB> getAllcustomer()
	{
		ArrayList<CustomerDB> list = new ArrayList<CustomerDB>();
		try
		{
			// create our mysql database connection
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_NAME;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "SELECT * FROM customer";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while(rs.next())
			{
				int id = rs.getInt("id");
				String firstName = rs.getString("name");
				String surname = rs.getString("surname");
				String phone = rs.getString("phone");
				CustomerDB a = new CustomerDB(id, firstName, surname, phone);
				list.add(a);
				System.out.format("%s, %s, %s, %s\n", "" + id, firstName, surname, phone);
			}
			st.close();
		}
		catch(Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	}

	public static void saveNewCustomer(CustomerDB x)
	{
		try
		{
			// create our mysql database connection
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_NAME;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "INSERT INTO customer VALUE(0,'" + x.name + "','" + x.surname + "','" + x.phone + "')";

			// create the java statement
			Statement st = conn.createStatement();

			st.executeUpdate(query);
			st.close();
		}
		catch(Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public static void editCustomer(CustomerDB x)
	{
		try
		{
			// create our mysql database connection
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_NAME;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "UPDATE customer SET name = '" + x.name + "', surname = '" + x.surname + "',phone = '" + x.phone
					+ "' WHERE id = " + x.id;
			System.out.println(query);

			// create the java statement
			Statement st = conn.createStatement();

			st.executeUpdate(query);
			st.close();
		}
		catch(Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static void deleteCustomer(CustomerDB x)
	{
		try
		{
			// create our mysql database connection
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_NAME;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "DELETE FROM customer WHERE id = " + x.id;
			System.out.println(query);

			// create the java statement
			Statement st = conn.createStatement();

			st.executeUpdate(query);
			st.close();
		}
		catch(Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
}
