package M;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import common.GlobalData;

public class ProductManager
{
	public static ArrayList<ProductDB> getAllProduct()
	{
		ArrayList<ProductDB> list = new ArrayList<ProductDB>();
		try
		{
			// create our mysql database connection
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_NAME;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "SELECT * FROM product";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while(rs.next())
			{
				int id = rs.getInt("product_id");
				String pName = rs.getString("product_name");
				double price = rs.getDouble("price_per_unit");
				String dresc = rs.getString("product_description");
				byte[] img_byte = rs.getBytes("product_image");
				ByteArrayInputStream bais = new ByteArrayInputStream(img_byte);
				BufferedImage bufferdimg = ImageIO.read(bais);
				bais.close();
				ProductDB a = new ProductDB(id, pName, price, dresc, bufferdimg);
				list.add(a);
				System.out.format("%d, %s, %f, %s\n", id, pName, price, dresc);
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

	public static void saveNewCustomer(ProductDB x)
	{
		try
		{
			// create our mysql database connection
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_NAME;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(x.product_image, "jpg", baos);
			byte[] byte_img = baos.toByteArray();
			String query = "INSERT INTO customer VALUE(0,'" + x.product_name + "','" + x.price_per_unit + "','"
					+ x.product_description + "','" +Arrays.toString(byte_img) +"')";

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
