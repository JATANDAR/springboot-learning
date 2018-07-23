package au.com.acttab.event;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import au.com.acttab.model.User;
import au.com.acttab.service.UserService;

/**
 * https://github.com/altfatterz/application-events-with-spring/blob/master/src/main/java/com/zoltanaltfatter/events/async2/AsyncEventListenerExample.java
 * https://memorynotfound.com/spring-boot-embedded-activemq-configuration-example/
 * **/


@Component
public class RegisterationCompleteEventListener 
{
	@Autowired
	UserService userService;

	@Autowired
	JavaMailSender mailSender;

	
	@JmsListener(destination = "newUserRegisterationQueue")
	public void verifyEmail(RegisterationCompleteEvent event) {
		// TODO Auto-generated method stub
		
		System.out.println("Listener got message=" + event);
		User user = event.getUser();
		String uuid = UUID.randomUUID().toString();

		userService.createToken(user, uuid);
		System.out.println("I am in verifyEmail Application Listener");
		String confirmationUrl = event.getAppUrl() + "/user/registerationConfirm?token=" + uuid;
		sendVerifyEmailTokenMessage(uuid, user, confirmationUrl);

	}

	private void sendVerifyEmailTokenMessage(String uuid, User user, String confirmationUrl) 
	{
		SimpleMailMessage message = new SimpleMailMessage(); 
		message.setTo(user.getEmailAddress()); 
		message.setSubject("Please verify your email"); 
		String messageText = String.format(" Dear %s, \n Please verify your email using the link below\n\n %s \n\n\n Thanks,", user.getName(),confirmationUrl);;
		message.setText(messageText);
		mailSender.send(message);
		//		Session session = Session.getDefaultInstance(System.getProperties());
		//        MimeMessage message = new MimeMessage(session);
		//        try {
		//            message.setFrom(new InternetAddress(System.getProperty("mail.smtp.user")));
		//            InternetAddress[] toAddress = new InternetAddress[1];
		//
		//            // To get the array of addresses
		//            //for( int i = 0; i < to.length; i++ ) {
		//                toAddress[0] = new InternetAddress("jatandar.dhirwani@gmail.com");
		//           // }
		//
		//            for( int i = 0; i < toAddress.length; i++) {
		//                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
		//            }
		//
		//            message.setSubject("Please verify your email");
		//            message.setText("Please verify your email");
		//            Transport transport = session.getTransport("smtp");
		//            
		//            transport.connect(System.getProperty("mail.smtp.host"), 
		//            		System.getProperty("mail.smtp.user"), 
		//            		System.getProperty("mail.smtp.password"));
		//            transport.sendMessage(message, message.getAllRecipients());
		//            transport.close();
		//        }
		//        catch (AddressException ae) {
		//            ae.printStackTrace();
		//        }
		//        catch (MessagingException me) {
		//            me.printStackTrace();
		//        }
	}


}



