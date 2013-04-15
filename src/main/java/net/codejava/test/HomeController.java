package net.codejava.test;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Home(Locale locale ,Model model) {
		logger.info("Wlcome home habebe",locale);
		
		Date date = new Date();
		DateFormat d = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG,locale);
		String formatteddate = d.format(date);
	   // String greetings = "Greetings, Spring MVC!";
	    model.addAttribute("serverTime", formatteddate);
	 
	    return "home";
	}
	
	@RequestMapping(value = "/User_Profile", method = RequestMethod.GET)
	public String User_Profile(Locale locale ,Model model) {
		logger.info("",locale);
		String greetings = "Mai Zuhair Elkomy ";
        model.addAttribute("message", greetings);
	    return "User_Profile";
	}
	
	@RequestMapping(value = "/log_in", method = RequestMethod.GET)
	public String Login(Locale locale ,Model model) {
		logger.info("",locale);
		String greetings = "logging in  ";
	    model.addAttribute("message", greetings);
	    
	 
	    return "Login";
	}
	
	@RequestMapping(value = "/Register", method = RequestMethod.GET)
	public String Register(Locale locale ,Model model) {
		logger.info("",locale);
		String greetings = "Register  ";
	    model.addAttribute("message", greetings);
	    
	 
	    return "Register";
	}
	
	
	
}
