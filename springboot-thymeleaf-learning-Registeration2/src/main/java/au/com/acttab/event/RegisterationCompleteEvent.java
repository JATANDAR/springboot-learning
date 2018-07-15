package au.com.acttab.event;

import org.springframework.context.ApplicationEvent;

import au.com.acttab.model.User;

public class RegisterationCompleteEvent extends ApplicationEvent {
	final User user;
	
	public RegisterationCompleteEvent(User user) {
		super(user);
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
	

}
