package cybage.spring.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(method = RequestMethod.GET)
	
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
			logger.info("Trying to authenticate -- LoginController");
			ModelAndView model = new ModelAndView("loginPage");
			System.out.println("In the loging controller");
			if (error != null) {
				model.addObject("error", "Invalid username and password!");
			}else{
				logger.info("Error Not found " );
			}

			if (logout != null) {
				model.addObject("msg", "You've been logged out successfully.");
			}
			
			logger.info("Will display loginPage" );
			return model;

		}
}
