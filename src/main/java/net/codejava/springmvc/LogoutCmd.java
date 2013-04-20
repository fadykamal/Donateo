package net.codejava.springmvc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Hashtable;

public class LogoutCmd implements Command {

	@Override
	public Hashtable execute(Hashtable htblInputParams) {
		
		int id = ((Integer)htblInputParams.get("id")).intValue();
		String type = (String)htblInputParams.get("type");
		
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

			CallableStatement cStmt = conn.prepareCall("{call logOut(?, ?)}");
			cStmt.setInt("id",id);
			cStmt.setString("type", type);
			
			cStmt.execute();
			
			cStmt.close();
			conn.close();
			

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		return null;
	}

}
