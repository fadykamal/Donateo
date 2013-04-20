package net.codejava.springmvc;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class RegisterCmd implements Command {

	@Override
	public Hashtable execute(Hashtable htblInputParams) {
		String name = "";
		String email = "";
		String password = "";
		String phone = "";
		String address = "";

		Set s = htblInputParams.entrySet();
		Iterator i = s.iterator();
		while (i.hasNext()) {
			Map.Entry m = (Map.Entry) i.next();

			String key = (String) m.getKey();
			String value = (String) m.getValue();

			if (key.equalsIgnoreCase("name")) {
				name = (String)value;
			} else if (key.equalsIgnoreCase("email")) {
				email = (String)value;
			} else if (key.equalsIgnoreCase("password")) {
				password = (String)value;
			} else if (key.equalsIgnoreCase("phone")) {
				phone = (String)value;
			} else if (key.equalsIgnoreCase("address")) {
				address = (String)value;
			}

			//System.out.println("Key :" + key + "  value :" + value);
		}
		//making the connection
		
//	    try {
//	        Class.forName("com.mysql.jdbc.Driver");
//	    } catch (ClassNotFoundException e) {
//	        // TODO Auto-generated catch block
//	        e.printStackTrace();
//	        System.out.println("hennnaaaa");
//	    } 
		
		 try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("went heree**************");
			}

		Connection conn = null;

		try {
			
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DonationCrowdfundingProject?"
			//		+ "user=root&password=");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DonationCrowdfundingProject", "root", "");

//			String success_message = "";
//			CallableStatement cStmt = conn.prepareCall("{call user_register("
//					+ email + "," + password + "," + name + "," + address + ","
//					+ phone + "," + success_message + ")}");
			
			CallableStatement cStmt = conn.prepareCall("{call UserRegister(?, ?, ?, ?, ?, ?)}");
			
			cStmt.setString("email", email);
			cStmt.setString("pass", password);
			cStmt.setString("name", name);
			cStmt.setString("address", address);
			cStmt.setString("phone", phone);
			cStmt.registerOutParameter("success_msg", Types.LONGNVARCHAR);
			cStmt.execute();
			
			String success_message = cStmt.getString("success_msg");
			System.out.println("registration:" + success_message);
			//cStmt.close();
			
			Hashtable htblOutputParams = new Hashtable();
			htblOutputParams.put("success_message", success_message);
			
			if (success_message.equals("YOU HAVE SUCCESSFULLY REGISTERED")) {
				System.out.println("right");
				CallableStatement cStmt2 = conn.prepareCall("{call getUserId(?, ?, ?)}");
				cStmt2.setString("u_email", email);
				cStmt2.registerOutParameter("id", Types.INTEGER);
				cStmt2.registerOutParameter("output_msg", Types.LONGVARCHAR);
				cStmt2.execute();
				
				int user_id = cStmt2.getInt("id");
				System.out.println("user id:" + user_id);
				//cStmt2.close();
				
				htblOutputParams.put("user_id", new Integer(user_id));
			}
			conn.close();
			return htblOutputParams;


		} catch (SQLException ex) {
			System.out.println("fell in this shit");
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return null;

	}

	// public static void main (String [] args) {
	// //Hashtable<String, String> h = new Hashtable<String, String>();
	// Hashtable<String, String> user_parameters = new Hashtable<String,
	// String>();
	// user_parameters.put("name", "Sherine");
	// user_parameters.put("email", "s@s.com");
	// user_parameters.put("password", "123");
	// user_parameters.put("address", "sdasdsds");
	// user_parameters.put("phone", "012345785");
	//
	// RegisterCommand r = new RegisterCommand();
	// r.execute(user_parameters);
	//
	// }
}
