package au.com.acttab.event;

import au.com.acttab.model.User;

/**
 * 
 * http://zoltanaltfatter.com/2016/05/11/application-events-with-spring/
 * */


public class RegisterationCompleteEvent {
	final User user;
	
	public RegisterationCompleteEvent(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
	

}
