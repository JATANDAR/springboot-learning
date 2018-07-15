package au.com.acttab.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class RegisterationCompleteEventPublisher implements ApplicationEventPublisher {

	@Override
	public void publishEvent(ApplicationEvent event) {
		System.out.println("event1=" +event);
	}

	@Override
	public void publishEvent(Object event) {
		System.out.println("event2=" +event);
		// TODO Auto-generated method stub
		//System.out.println("I am in object=" + event);
		if(event instanceof RegisterationCompleteEvent) {
			System.out.println("Got send email event=" + event);
			//RegisterationCompleteEvent e = (RegisterationCompleteEvent)event;
			//this.publishEvent(e);
		}
		
	}

}
