package net.codejava.springmvc;

import java.lang.reflect.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class listMyFollowedProjectsCmd {
	public static Hashtable execute(Hashtable table) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DonationCrowdfundingProject",
						"root", "");
			
			Set set = table.entrySet();
			Iterator it = set.iterator();
			int user_id;
			Map.Entry entry = (Map.Entry) it.next();
			user_id =  (Integer)entry.getValue();
			CallableStatement listfollowedprojects = conn.prepareCall("{call listFollowedProjects(? , ?)}");
			listfollowedprojects.registerOutParameter(2, Types.VARCHAR);
			listfollowedprojects.setInt("user_id", user_id);
			boolean hadResults = listfollowedprojects.execute();
			Hashtable<Integer , String[]> out = new Hashtable<Integer , String[]>();
			ResultSet rs = null;
			//System.out.println("1");
			String output = "";
			output = listfollowedprojects.getString(2);
			
			Hashtable<String , String> error = new Hashtable<String , String>();
			if(output != null){	
				
					error.put("error", output);
					System.out.println(output);
					return error;
			}
			while(hadResults){
				  rs = listfollowedprojects.getResultSet();
				  if(rs.next()){
				  int x = rs.getInt(1);
					String y = rs.getString(3);
					String z = rs.getString(4);
					String [] p = new String[2];
					p[0] = y;
					p[1] = z;
					System.out.println(x);
					System.out.println(y);
					System.out.println(z);
					out.put(x, p);
				  }
				  hadResults = listfollowedprojects.getMoreResults();
			}
			return out;
			//System.out.println("2");
			
			/*else{
				while(rs.next()){
					int x = rs.getInt(0);
					String y = rs.getString(2);
					System.out.println(x);
					System.out.println(y);
					out.put(x, y);
					
				}
			}*/
			//System.out.println("3");
			
	}
	
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("went heree**************");
			}

					return table;
}
}
