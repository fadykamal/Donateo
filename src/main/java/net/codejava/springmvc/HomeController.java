package net.codejava.springmvc;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import net.codejava.springmodels.Admins;
import net.codejava.springmodels.NGO;
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
@SessionAttributes({ "user", "registration_message", "login_message", "listMyFollowingProjects" })
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Home(HttpSession session, Locale locale, Model model) {
		logger.info("Wlcome home habebe", locale);

		Date date = new Date();
		DateFormat d = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);
		String formatteddate = d.format(date);
		// String greetings = "Greetings, Spring MVC!";
		model.addAttribute("serverTime", formatteddate);

		// User user = new User(1, "Sherine", "ddssd", "sdsdsds", "sddsdsds",
		// "dsdssdsd");
		// model.addAttribute("user", user);
		// session.setAttribute("user", user);
		// System.out.println(((User)session.getAttribute("user")).getId());

		return "Login";
	}

	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public String register(HttpSession session, Model model,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("phone") String phone,
			@RequestParam("phoneI") String phoneI,
			@RequestParam("phoneII") String phoneII,
			@RequestParam("address") String address) {

		String phone_number = phone + phoneI + phoneII;

		Hashtable<String, String> user_parameters = new Hashtable<String, String>();
		user_parameters.put("name", name);
		user_parameters.put("email", email);
		user_parameters.put("password", password);
		user_parameters.put("address", address);
		user_parameters.put("phone", phone_number);

		// User user = new User(1, name, email, password, phone_number,
		// address);
		// model.addAttribute("user", user);
		// return "User_Profile";

		RegisterCmd registerCmd = new RegisterCmd();
		System.out.println(name + "***************");
		Hashtable htblOutputParams = registerCmd.execute(user_parameters);
		if (htblOutputParams == null) {
			System.out.println("anaa hennaaaaa regi cont");
			return "home";
		}

		if (((String) htblOutputParams.get("success_message"))
				.equals("YOU HAVE SUCCESSFULLY REGISTERED")) {
			int id = ((Integer) htblOutputParams.get("user_id")).intValue();
			User user = new User(id, name, email, password, phone_number,
					address);
			// model.addAttribute("user", user);
			session.setAttribute("user", user);
			// call login to create the session
			Hashtable login_user = new Hashtable();
			login_user.put("email", email);
			login_user.put("password", password);
			LoginCmd loginCmd = new LoginCmd();
			loginCmd.execute(login_user);
			return "User_Profile";
		} else if (((String) htblOutputParams.get("success_message"))
				.equals("EMAIL OR PASSWORD ALREADY EXISTS")) {
			model.addAttribute("registration_message",
					"EMAIL OR PASSWORD ALREADY EXISTS");
			return "Register";

		} else if (((String) htblOutputParams.get("success_message"))
				.equals("USER NAME ALREADY EXISTS")) {
			model.addAttribute("registration_message",
					"USER NAME ALREADY EXISTS");
			return "Register";
		}
		// session.setAttribute("user", user);
		return "home";

	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String login(HttpSession session, Model model,
			@RequestParam("email") String email,
			@RequestParam("password") String password) {

		Hashtable<String, String> user_parameters = new Hashtable<String, String>();
		user_parameters.put("email", email);
		user_parameters.put("password", password);

		// System.out.println(email + "      " + password);

		LoginCmd loginCmd = new LoginCmd();
		Hashtable htblOutputParams = loginCmd.execute(user_parameters);
		
		if (htblOutputParams.get("success_message") != null
				&& ((String) htblOutputParams.get("success_message"))
						.equals("YOU HAVE NOT REGISTERED YET")) {
			model.addAttribute("login_message", "YOU HAVE NOT REGISTERED YET");
			return "Login";
		} else if (((String) htblOutputParams.get("login_message"))
				.equals("EMAIL OR PASSWORD IS WRONG")) {
			model.addAttribute("login_message", "EMAIL OR PASSWORD IS WRONG");
			return "Login";
		} else if (((String) htblOutputParams.get("login_message"))
				.equals("LOGGED IN")) {
			// account exists and user has successfully logged in

			// check on type to know which object to create
			if (((String) htblOutputParams.get("type")).equals("user")) {
				int user_id = ((Integer) htblOutputParams.get("id")).intValue();
				String user_name = (String) htblOutputParams.get("name");
				String user_email = (String) htblOutputParams.get("email");
				String user_password = (String) htblOutputParams
						.get("password");
				String user_phone = (String) htblOutputParams.get("phone");
				String user_address = (String) htblOutputParams.get("address");

				User user = new User(user_id, user_name, user_email,
						user_password, user_phone, user_address);
				session.setAttribute("user", user);
				//osama
				listFollowingProjects(session , user_id);
				// model.addAttribute("user", user);
				return "User_Profile";
			} else if (((String) htblOutputParams.get("type")).equals("ngo")) {
				int admin_id = ((Integer) htblOutputParams.get("admin_id"))
						.intValue();
				int ngo_id = ((Integer) htblOutputParams.get("ngo_id"))
						.intValue();
				String ngo_email = (String) htblOutputParams.get("ngo_email");
				String ngo_password = (String) htblOutputParams.get("password");
				String ngo_name = (String) htblOutputParams.get("ngo_name");
				String photo = (String) htblOutputParams.get("photo");
				String description = (String) htblOutputParams
						.get("description");
				String website_link = (String) htblOutputParams
						.get("websute_link");
				String phone = (String) htblOutputParams.get("phone");

				NGO ngo = new NGO(admin_id, ngo_id, ngo_email, ngo_password,
						ngo_name, photo, description, website_link, phone);
				session.setAttribute("user", ngo);
				// model.addAttribute("user", ngo);
				//return "NGO_Profile";
				return "newProject";
			} else {
				// the user is admin

				int admin_id = ((Integer) htblOutputParams.get("admin_id"))
						.intValue();
				String admin_email = (String) htblOutputParams
						.get("admin_email");
				String admin_password = (String) htblOutputParams
						.get("password");

				Admins admin = new Admins(admin_id, admin_email, admin_password);
				session.setAttribute("user", admin);
				// model.addAttribute("user", admin);
				return "Admin_Profile";
			}

		}

		return "";
	}

	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public String logout(HttpSession session, Model model) {
		// session.removeAttribute("user");
		// session.invalidate();
		Hashtable htblInputParams = new Hashtable();
		Object o = session.getAttribute("user");
		if (o != null) {
			if (o instanceof User) {
				User current_user = (User) o;
				htblInputParams.put("id", new Integer(current_user.getId()));
				htblInputParams.put("type", "user");
			} else if (o instanceof NGO) {
				NGO current_user = (NGO) o;
				htblInputParams
						.put("id", new Integer(current_user.getNgo_id()));
				htblInputParams.put("type", "ngo");
			} else if (o instanceof Admins) {
				Admins current_user = (Admins) o;
				htblInputParams.put("id",
						new Integer(current_user.getAdmin_id()));
				htblInputParams.put("type", "admin");
			}
		}
		session.setAttribute("user", null);

		LogoutCmd logoutCmd = new LogoutCmd();
		logoutCmd.execute(htblInputParams);
		// model.addAttribute("user", null);

		return "Login";
	}

	@RequestMapping(value = "/newProject", method = RequestMethod.POST)
	public String newProject(
			HttpSession session,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestParam("location") String location,
			@RequestParam("severity") String urgency_id,
			@RequestParam("type") String campaignOrProject,
			@RequestParam(value = "isDonation", required = false) String isDonation,
			@RequestParam(value = "isVolunteer", required = false) String isVolunteer,
			@RequestParam(value = "isObject", required = false) String isObject,
			@RequestParam(value = "health", required = false) String c_health,
			@RequestParam(value = "education", required = false) String c_education,
			@RequestParam(value = "orphans", required = false) String c_orphans,
			@RequestParam(value = "development", required = false) String c_development,
			@RequestParam(value = "slums", required = false) String c_slums,
			@RequestParam(value = "economy", required = false) String c_economy,
			@RequestParam(value = "deadline", required = false) String deadline,
			@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "to", required = false) String to,
			@RequestParam(value = "fund", required = false) String amount,
			@RequestParam(value = "object_name1", required = false) String name1,
			@RequestParam(value = "object_name2", required = false) String name2,
			@RequestParam(value = "object_name3", required = false) String name3,
			@RequestParam(value = "object_name4", required = false) String name4,
			@RequestParam(value = "object_name5", required = false) String name5,
			@RequestParam(value = "object_amount1", required = false) String value1,
			@RequestParam(value = "object_amount2", required = false) String value2,
			@RequestParam(value = "object_amount3", required = false) String value3,
			@RequestParam(value = "object_amount4", required = false) String value4,
			@RequestParam(value = "object_amount5", required = false) String value5) {

		Hashtable<String, Object> project_data = new Hashtable<String, Object>();
		ArrayList<String> categories = new ArrayList<String>();
		ArrayList<String> objects = new ArrayList<String>();
		Object o = session.getAttribute("user");
		if (o instanceof NGO) {
			NGO ngo = (NGO) o;

			project_data.put("ngo_id", ngo.getNgo_id() + "");
			project_data.put("urgency_id", urgency_id);
			project_data.put("name", name);
			project_data.put("description", description);
			project_data.put("location", location);
			// categories
			if (c_health != null)
				categories.add(c_health);
			if (c_education != null)
				categories.add(c_education);
			if (c_orphans != null)
				categories.add(c_orphans);
			if (c_development != null)
				categories.add(c_development);
			if (c_economy != null)
				categories.add(c_economy);
			project_data.put("categories", categories);
			// types
			if (isVolunteer != null)
				project_data.put("isVolunteer", isVolunteer);
			if (isDonation != null) {
				project_data.put("isMoney", isDonation);
				project_data.put("amount", amount);
			}
			if (isObject != null) {

				objects.add(name1);
				objects.add(value1);
				if (!name2.equals("") && !value2.equals("")) {
					objects.add(name2);
					objects.add(value2);
				}
				if (!name3.equals("") && !value3.equals("")) {
					objects.add(name3);
					objects.add(value3);
				}
				if (!name4.equals("") && !value4.equals("")) {
					objects.add(name4);
					objects.add(value4);
				}
				if (!name5.equals("") && !value5.equals("")) {
					objects.add(name5);
					objects.add(value5);
				}

				project_data.put("isObject", isObject);
				project_data.put("objects", objects);
				System.out.println("ARRAY OBJECTS IN CONTROLLER " + objects);
				System.out.println("size = " + objects.size());

			}

			if (campaignOrProject.equals("charity")) { // charity = case
				project_data.put("deadline", deadline);
				CreateCaseCmd createCaseCmd = new CreateCaseCmd();
				Hashtable errorOrSuccess = createCaseCmd.execute(project_data);

				Set set = errorOrSuccess.entrySet();
				Iterator it = set.iterator();
				Map.Entry entry = (Map.Entry) it.next();
				System.out.println(entry.getKey() + " : " + entry.getValue());
				if (entry.getKey().equals("success_message"))
					return "projectData";
				else
					return "newProject";
			} else if (campaignOrProject.equals("campaign")) {
				project_data.put("from", from);
				project_data.put("to", to);
				CreateCampaignCmd createCampaignCmd = new CreateCampaignCmd();
				createCampaignCmd.execute(project_data);
			}

		}
		return "projectData";
	}
	
	public void listFollowingProjects(HttpSession s , int user_id){
        Hashtable listMyFollowingProjects = new Hashtable();
        listMyFollowedProjectsCmd listmyprojects = new listMyFollowedProjectsCmd();
        Hashtable<String , Integer> in = new Hashtable<String , Integer>();
        in.put("user_id", user_id);
        listMyFollowingProjects = listmyprojects.execute(in);
        s.setAttribute("listMyFollowingProjects", listMyFollowingProjects);
        
    }

}
