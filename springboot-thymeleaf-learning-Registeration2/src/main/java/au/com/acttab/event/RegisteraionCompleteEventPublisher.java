package au.com.acttab.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class RegisteraionCompleteEventPublisher implements ApplicationEventPublisher {

	@Override
	public void publishEvent(ApplicationEvent event) {
		if(event instanceof RegisterationCompleteEvent) {
			System.out.println("Got send email event=" + event);
		}
	}

	@Override
	public void publishEvent(Object event) 
	{
	}

	


}
