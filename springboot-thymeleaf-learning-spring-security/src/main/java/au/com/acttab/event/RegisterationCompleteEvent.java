package au.com.acttab.event;

import java.io.Serializable;

import au.com.acttab.model.User;

/**
 * 
 * http://zoltanaltfatter.com/2016/05/11/application-events-with-spring/
 * */


public class RegisterationCompleteEvent implements Serializable {
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
