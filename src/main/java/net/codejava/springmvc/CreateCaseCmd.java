package net.codejava.springmvc;

import java.text.DateFormat;
import java.io.ObjectInputStream.GetField;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.nt.NTEventLogAppender;

public class CreateCaseCmd // implements Command
{

	public static Hashtable execute(Hashtable<String, Object> table) {

		Hashtable<String, String> result = new Hashtable<String, String>();
		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = null;

			try {
				// Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager
						.getConnection(
								"jdbc:mysql://localhost:3306/DonationCrowdfundingProject",
								"root", "");

				String key;
				Set set = table.entrySet();
				Iterator it = set.iterator();

				String name = "", location = "", description = "";
				boolean isObject = false, isVolunteer = false, isMoney = false;
				int amount = 0, urgency_id = -1, ngo_id = -1, project_id = -1;
				int category_id = -1;
				ResultSet rs;
				String date = "";
				java.sql.Timestamp deadline;
				ArrayList<String> categories = new ArrayList<String>();
				ArrayList<String> objects = new ArrayList<String>();

				// loop over hashtable
				while (it.hasNext()) {
					Map.Entry entry = (Map.Entry) it.next();
					System.out.println(entry.getKey() + " : "
							+ entry.getValue());

					if (entry.getKey().equals("ngo_id")) {
						ngo_id = Integer.parseInt((String) entry.getValue());

					} else if (entry.getKey().equals("isObject")) {
						isObject = true;

					} else if (entry.getKey().equals("isVolunteer")) {
						isVolunteer = true;

					} else if (entry.getKey().equals("isMoney")) {
						isMoney = true;

					} else if (entry.getKey().equals("name")) {
						name = (String) entry.getValue();

					} else if (entry.getKey().equals("location")) {
						location = (String) entry.getValue();

					} else if (entry.getKey().equals("description")) {
						description = (String) entry.getValue();

					} else if (entry.getKey().equals("amount")) {
						amount = Integer.parseInt((String) entry.getValue());

					} else if (entry.getKey().equals("deadline"))
						date = (String) entry.getValue();

					else if (entry.getKey().equals("urgency_id")) {
						System.out.println("*" + entry.getValue() + "*");
						urgency_id = Integer
								.parseInt((String) entry.getValue());
					} else if (entry.getKey().equals("categories"))
						categories = (ArrayList<String>) entry.getValue();

					else if (entry.getKey().equals("objects")) {
						objects = (ArrayList<String>) entry.getValue();

					}

				}

				// call stored procedures
				// 1- Creating the Project
				CallableStatement createCase = conn
						.prepareCall("{call createCase(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

				// set IN, OUT & INOUT params
				createCase.setInt("NGO_id", 1);
				createCase.setInt("urg_id", urgency_id);
				createCase.setString("p_name", name);
				createCase.setString("p_description", description);
				createCase.setString("p_location", location);

				deadline = new Timestamp(2010, 10, 2, 0, 0, 0, 0);
				System.out.println("deadline ********" + deadline);

				createCase.setTimestamp("p_deadline", deadline);
				createCase.setBoolean("isVolunteer", isVolunteer);
				createCase.setBoolean("isMoney", isMoney);
				System.out.println("isMoney " + isMoney);
				createCase.setBoolean("isObject", isObject);
				createCase.setInt("p_amount", amount);

				createCase.registerOutParameter(11, Types.INTEGER);
				createCase.registerOutParameter(12, Types.VARCHAR);
				createCase.execute();

				// retrieve results
				// get either :project_id or message error
				if (createCase.getString("output_msg").equals("")
						|| createCase.getString("output_msg") == null) // success
				{
					project_id = createCase.getInt("p_id");
				
					// ADDING CATEGORIES!
					CallableStatement getCategoryID = conn
							.prepareCall("{call getCategoryID(?, ?)}");
					CallableStatement addCategory = conn
							.prepareCall("{call addCategoryToProject(?, ?)}");

					for (int i = 0; i < categories.size(); i++) {
						// 2- get the id of category

						getCategoryID.setString("categoryName",
								categories.get(i));
						getCategoryID.registerOutParameter(2, Types.VARCHAR);
						getCategoryID.execute();
						if (getCategoryID.getString("success_msg").equals(
								"This Category does not exist")) {
							result.put("error_message",
									"Something went wrong, please refresh and retry.");
							return result;
						} else { // returned cat_id in form of string
							category_id = Integer.parseInt(getCategoryID
									.getString("success_msg"));
						}
						// 3- add it to project (addcategorytoproject)
						System.out.println(project_id + " p ID **************");

						addCategory.setInt("p_id", project_id);
						addCategory.setInt("c_id", category_id);
						addCategory.execute();

					}

					// ADDING OBJECTS!
					CallableStatement addObjectToProject = conn
							.prepareCall("{call addObjectToProject(?, ?, ?, ?)}");
					for (int i = 0; i < objects.size(); i += 2) {

						addObjectToProject.setInt("pID", project_id);
						addObjectToProject.setString("objName", objects.get(i));
						addObjectToProject.setInt("objQuantity",
								Integer.parseInt(objects.get(i + 1)));
						addObjectToProject.registerOutParameter(4,
								Types.VARCHAR);
						addObjectToProject.execute();

						if (addObjectToProject.getString("success_msg").equals(
								"THIS PROJECT DOES NOT EXIST"))
							result.put("error_message",
									"Something went wrong, please refresh and retry.");

					}

					// put result in output hashtable
					result.put("success_message",
							"Project created successfully!");
					createCase.close();
					getCategoryID.close();
					addCategory.close();
					conn.close();

				} else if (createCase.getString("output_msg").equals(
						"Project name exist"))
					result.put("error_message",
							"Project name already exists! Please choose another name.");

				return result;

			} catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("ErrorCode: " + e.getErrorCode());
				e.printStackTrace();

			}
		} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block
			System.out.println("Class not found exception");
			e.printStackTrace();
		}
		return result;
	}
	
	

}
