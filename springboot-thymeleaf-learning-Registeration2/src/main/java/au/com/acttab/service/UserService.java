package au.com.acttab.service;

import au.com.acttab.dao.UserDAO;
import au.com.acttab.dao.VerificationTokenDAO;
import au.com.acttab.model.User;

public class UserService {

	private UserDAO userDAOImpl;
	
	private VerificationTokenDAO verificationTokenDAOImpl;

	public UserService(UserDAO userDAOImpl, VerificationTokenDAO verificationTokenDAOImpl) {
		this.userDAOImpl = userDAOImpl;
		this.verificationTokenDAOImpl = verificationTokenDAOImpl;
	}

	public User loginUser(User user) {
		User foundUser = null;

		try 
		{
			foundUser =  userDAOImpl.findUser(user.getEmailAddress());
			if(foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
				return foundUser;
			}
		}
		catch(Exception ex) {}

		return null;
	}

	public User saveUser(User user) {
		User foundUser = null;
		
		try 
		{
			userDAOImpl.saveUser(user);
			foundUser = userDAOImpl.findUser(user.getEmailAddress());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return foundUser;
	}
	
	public void createToken(User user, String token) {
		verificationTokenDAOImpl.saveToken(user, token);
	}


}
