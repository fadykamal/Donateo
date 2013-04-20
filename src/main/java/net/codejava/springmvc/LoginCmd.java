package net.codejava.springmvc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;

public class LoginCmd implements Command {

	@Override
	public Hashtable execute(Hashtable htblInputParams) {

		String email = "";
		String password = "";

		Set s = htblInputParams.entrySet();
		Iterator i = s.iterator();
		while (i.hasNext()) {
			Map.Entry m = (Map.Entry) i.next();

			String key = (String) m.getKey();
			String value = (String) m.getValue();

			if (key.equalsIgnoreCase("email")) {
				email = (String)value;
			} else {
				password = (String)value;
			}
		}
		
		 try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("went heree**************");
			}


		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DonationCrowdfundingProject", "root", "");

			CallableStatement cStmt = conn
					.prepareCall("{call AccountExists(?, ?, ?)}");

			cStmt.setString("email", email);
			cStmt.setString("pass", password);
			cStmt.registerOutParameter("success_msg", Types.VARCHAR);
			cStmt.execute();
			
			String success_message = cStmt.getString("success_msg");
			
			Hashtable htblOutputParams = new Hashtable();
			//htblOutputParams.put("success_message", success_message);
			
			
			System.out.println("****1");
			if (success_message != null && success_message.equals("YOU HAVE NOT REGISTERED YET")) {
				//set the success message value to check in controller
				htblOutputParams.put("success_message", "YOU HAVE NOT REGISTERED YET");
				System.out.println("***2");
			}
			else {
				//that the person has an account
				htblOutputParams.put("success_message", "SUCCESS");
				
				//we need to get the type of that person
				CallableStatement cStmt2 = conn.prepareCall("{call getType(?, ?)}");
				cStmt2.setString("email", email);
				cStmt2.registerOutParameter("type", Types.VARCHAR);
				cStmt2.execute();
				System.out.println("*****3");
				String user_type = cStmt2.getString("type");
				htblOutputParams.put("type", user_type);
				System.out.println("******4");
				//check for the type
				if (user_type.equals("user")) {
					System.out.println("*******5");
					ResultSet rs = cStmt.getResultSet();
					rs.next();
					int user_id = rs.getInt("user_id");
					String user_name = rs.getString("user_name");
					String user_email = rs.getString("user_email");
					String user_password = rs.getString("password");
					String user_phone = rs.getString("phone");
					String user_address = rs.getString("address");

					htblOutputParams.put("id", new Integer(user_id));
					htblOutputParams.put("name", user_name);
					htblOutputParams.put("email", user_email);
					htblOutputParams.put("password", user_password);
					htblOutputParams.put("address", user_address);
					htblOutputParams.put("phone", user_phone);
				}
				else if (user_type.equals("ngo")) {
					System.out.println("*****6");
					ResultSet rs = cStmt.getResultSet();
					rs.next();
					int admin_id = rs.getInt("admin_id");
					int ngo_id = rs.getInt("ngo_id");
					String ngo_email = rs.getString("ngo_email");
					String ngo_password = rs.getString("password");
					String ngo_name = rs.getString("ngo_name");
					String photo = rs.getString("photo");
					String description = rs.getString("description");
					String website_link = rs.getString("website_link");
					String phone = rs.getString("phone");

					htblOutputParams.put("admin_id", admin_id);
					htblOutputParams.put("ngo_id", ngo_id);
					htblOutputParams.put("ngo_email", ngo_email);
					htblOutputParams.put("password", ngo_password);
					htblOutputParams.put("ngo_name", ngo_name);
					//htblOutputParams.put("photo", photo);
					htblOutputParams.put("description", description);
					//htblOutputParams.put("website_link", website_link);
					//htblOutputParams.put("phone", phone);
				}
				else {
					System.out.println("******7");
					//the user is admin
					ResultSet rs = cStmt.getResultSet();
					int admin_id = rs.getInt("admin_id");
					String admin_email = rs.getString("admin_email");
					String admin_password = rs.getString("passwod");
					
					htblOutputParams.put("admin_id", admin_id);
					htblOutputParams.put("admin_email", admin_email);
					htblOutputParams.put("password", admin_password);
  
				}
				cStmt2.close();
				System.out.println("*****8");
				//now the user has account so we can login
				CallableStatement cStmt3 = conn.prepareCall("{call logIn(?, ?, ?, ?)}");
				cStmt3.setString("email", email);
				cStmt3.setString("pass", password);
				cStmt3.registerOutParameter("type", Types.VARCHAR);
				cStmt3.registerOutParameter("success_msg", Types.LONGVARCHAR);
				System.out.println("*******9");
				
				try {
					String login_message = cStmt3.getString("success_msg");
					if(login_message.equals("EMAIL OR PASSWORD IS WRONG")) {
						htblOutputParams.put("login_message", "EMAIL OR PASSWORD IS WRONG");
						System.out.println("*******10");
					}
				
						
					
				} catch (Exception e) {
					System.out.println("successs zeft");
					htblOutputParams.put("login_message", "LOGGED IN");
					System.out.println("*****11");
				}
				
				cStmt3.close();
				
			}
					cStmt.close();
					
					conn.close();
					return htblOutputParams;


		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			System.out.println("iss hereee in login*********");
		}

		return null;
	}

}
