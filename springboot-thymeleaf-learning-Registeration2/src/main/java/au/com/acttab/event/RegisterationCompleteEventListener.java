package au.com.acttab.event;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import au.com.acttab.model.User;
import au.com.acttab.service.UserService;

/**
 * https://github.com/altfatterz/application-events-with-spring/blob/master/src/main/java/com/zoltanaltfatter/events/async2/AsyncEventListenerExample.java
 * 
 * **/


@Component
public class RegisterationCompleteEventListener 
{
	@Autowired
	UserService userService;
	
	@Autowired
	JavaMailSender mailSender;
	
	@Async
	@EventListener
	public void verifyEmail(RegisterationCompleteEvent event) {
		// TODO Auto-generated method stub
		User user = event.getUser();
		String uuid = UUID.randomUUID().toString();
		
		userService.createToken(user, uuid);
		System.out.println("I am in verifyEmail Application Listener");
		sendVerifyEmailTokenMessage();
		
	}

	private void sendVerifyEmailTokenMessage() 
	{
		 SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setTo("jatandar.dhirwani@gmail.com"); 
	        message.setSubject("Please verify your email"); 
	        message.setText("Please verify your email");
	        mailSender.send(message);
	}


}
