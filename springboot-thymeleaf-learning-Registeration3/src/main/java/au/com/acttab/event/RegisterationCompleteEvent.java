package au.com.acttab.event;

import au.com.acttab.model.User;

/**
 * 
 * http://zoltanaltfatter.com/2016/05/11/application-events-with-spring/
 * */


public class RegisterationCompleteEvent {
	final User user;
	final String appUrl;
	
	public RegisterationCompleteEvent(User user, String appUrl) {
		this.user = user;
		this.appUrl = appUrl;
	}

	public User getUser() {
		return user;
	}

	public String getAppUrl() {
		return appUrl;
	}

}
