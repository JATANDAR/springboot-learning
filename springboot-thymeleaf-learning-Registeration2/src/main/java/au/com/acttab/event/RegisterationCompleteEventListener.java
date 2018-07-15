package au.com.acttab.event;

import java.util.UUID;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import au.com.acttab.model.User;

@Component
public class RegisterationCompleteEventListener implements ApplicationListener<RegisterationCompleteEvent> {

	@Override
	public void onApplicationEvent(RegisterationCompleteEvent event)
	{
		verifyEmail(event);
	}

	private void verifyEmail(RegisterationCompleteEvent event) {
		// TODO Auto-generated method stub
		User user = event.getUser();
		String uuid = UUID.randomUUID().toString();
		
		
	}


}
