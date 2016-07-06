package cybage.spring.security;

import java.io.Serializable;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import cybage.dao.UserDao;
import cybage.spring.model.UserPermission;

public class CustomPermissionEvaluator implements PermissionEvaluator{

	protected static Logger logger = Logger.getLogger("CustomPermissionEvaluator");
	
	// get user from the database, via Hibernate
	@Autowired
	private UserDao userDao;
			
	@Override
	public boolean hasPermission(Authentication authentication, Object arg1, Object permission) {
		logger.info("Evaluating expression using hasPermission signature #1");
		  boolean hasPermission = false;
		  
		  
		  if ( authentication != null &&  permission instanceof String){

			  String userName = authentication.getName();
			  
			  cybage.spring.model.User user = userDao.findByUserName(userName);
			  Set<UserPermission> permissions= user.getUserPermission();
			  
			  Set<UserPermission> evelutedPer= permissions.stream().filter( per -> per.getPermission().equals(permission) ).collect(Collectors.toSet());
			  if(!evelutedPer.isEmpty()){
				  logger.info("User : " + userName + " has permission: " +  permission);
				  hasPermission= true;
			  }
			  
		  }
		return hasPermission;
	}

	@Override
	public boolean hasPermission(Authentication arg0, Serializable arg1, String arg2, Object arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
