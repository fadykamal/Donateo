package net.codejava.springmvc;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import net.codejava.springmodels.Ngo;
import net.codejava.springmodels.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;



/**
 * Handles requests for the application home page.
 */
@SessionAttributes({"user"})
@Controller

public class HomeController {
	RegisterCommand registerCmd;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public String register(Model model, @RequestParam("Username") String name, @RequestParam("Email") String email, @RequestParam("password") String password, @RequestParam("Phone") String phone, @RequestParam("Address") String address) {

		
		Hashtable<String, String> user_parameters = new Hashtable<String, String>();
		user_parameters.put("name", name);
		user_parameters.put("email", email);
		user_parameters.put("password", password);
		user_parameters.put("address", address);
		user_parameters.put("phone", phone);
		
		registerCmd = new RegisterCommand();
		Hashtable result = registerCmd.execute(user_parameters);
		
		if(result.get("success") != null) {
			int success = ((Integer)result.get("success")).intValue();
			if(success == 1) {
				//registration was successfull
				
				Connection conn = null;
				int id = -1;

				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost/test?"
							+ "user=monty&password=greatsqldb");
					
					CallableStatement cStmt = conn.prepareCall("{call getUserId(?, ?)}");
					
					cStmt.setString(1, email);
					cStmt.registerOutParameter(2, Types.INTEGER);

					cStmt.execute();
					id = cStmt.getInt("user_id");
					
					cStmt.close();
					conn.close();


				} catch (SQLException ex) {
					// handle any errors
					System.out.println("SQLException: " + ex.getMessage());
					System.out.println("SQLState: " + ex.getSQLState());
					System.out.println("VendorError: " + ex.getErrorCode());
				}
				
				if (id != -1) {
					User user = new User(id, name, email, password, phone, address);
					model.addAttribute("user", user);
					return "User_Profile";
				}
			}
			else {
				//registration was not successfull
				return "Register";
			}
			
		}
		
			//success is null
			return "Register";
		//session.setAttribute("user", user);
		
	}
	
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String login (Model model, @RequestParam("Email") String username, @RequestParam("password") String password) {
		
		Hashtable<String, String> user_parameters = new Hashtable<String, String>();
		user_parameters.put("email", username);
		user_parameters.put("password", password);
		
		LoginCommand loginCmd = new LoginCommand();
		Hashtable htblOutputParams = loginCmd.execute(user_parameters);
		
		if(htblOutputParams == null) {
			return "Login";
		}
		else {
			if ((Boolean)htblOutputParams.get("is_user")) {
				int user_id = ((Integer)htblOutputParams.get("id")).intValue();
				String user_name = (String)htblOutputParams.get("name");
				String user_email = (String)htblOutputParams.get("email");
				String user_password = (String)htblOutputParams.get("password");
				String user_phone = (String)htblOutputParams.get("phone");
				String user_address = (String)htblOutputParams.get("address");
				
				User user = new User(user_id, user_name, user_email, user_password, user_phone, user_address);
				model.addAttribute("user", user);
				return "User_Profile";
			}
			else {
				//is ngo
				int admin_id = ((Integer)htblOutputParams.get("admin_id")).intValue();
				int ngo_id = ((Integer)htblOutputParams.get("ngo_id")).intValue();
				String ngo_email = (String)htblOutputParams.get("ngo_email");
				String ngo_password = (String)htblOutputParams.get("password");
				String ngo_name = (String)htblOutputParams.get("ngo_name");
				String photo = (String)htblOutputParams.get("photo");
				String description = (String)htblOutputParams.get("description");
				String website_link = (String)htblOutputParams.get("websute_link");
				String phone = (String)htblOutputParams.get("phone");
				
				Ngo ngo = new Ngo(admin_id, ngo_id, ngo_email, ngo_password, ngo_name, photo, description, website_link, phone);
				model.addAttribute("user", ngo);
				return "NGO_Profile";
			}

		}
	}
	
	@RequestMapping(value = "/Logout", method = RequestMethod.POST)
	public String logout (HttpSession session, Model model) {
		//session.removeAttribute("user");
		//session.invalidate();
		model.addAttribute("user", null);
		
		return "home";
	}
	


}
