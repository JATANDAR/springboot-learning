package au.com.acttab.controller;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;

import au.com.acttab.dao.impl.ToDoDAOImpl;
import au.com.acttab.dao.impl.UserDAOImpl;
import au.com.acttab.dao.impl.VerificationTokenDAOImpl;
import au.com.acttab.event.RegisterationCompleteEventListener;
import au.com.acttab.service.UserService;

@SpringBootApplication
@EnableAsync
public class SpringThymeleaf6Application {

	/**
	 * https://stackoverflow.com/questions/9353167/auto-increment-id-in-h2-database
	 * https://ichihedge.wordpress.com/2016/05/22/spring-messaging-with-an-external-broker/
	 * https://github.com/apache/camel/tree/master/examples/camel-example-spring-boot-activemq
	 * https://www.devglan.com/spring-boot/spring-boot-jms-activemq-example
	 * **/

	@Bean(name="ds")
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder embeddedDBBbuilder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase embeddedDB = embeddedDBBbuilder
				.setType(EmbeddedDatabaseType.H2)
				.addScript("create-db.sql")
				.addScript("insert-data.sql")
				.build();
		return embeddedDB;
	}
	
	@Bean(name="activemq-connectionFactory")
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL("vm://localhost:61616?broker.persistent=false");
		 connectionFactory.setPassword("admin");
		    connectionFactory.setUserName("admin");
		System.out.println(":):):):):)activemq started");
		return connectionFactory;
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
		return jmsTemplate;
	}
	
	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
	    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	    factory.setConnectionFactory(connectionFactory());
	    factory.setConcurrency("1-1");
	    return factory;
	}
	
	@Bean
	public ActiveMQQueue queue() {
		ActiveMQQueue queue = new ActiveMQQueue("newUserRegisterationQueue");
		return queue;
	}

	@Bean(name="toDoDAOImpl")
	public ToDoDAOImpl toDoDAOImpl() {
		ToDoDAOImpl toDoDAOImpl = new ToDoDAOImpl(dataSource());
		return toDoDAOImpl;
	}

	@Bean(name="userDAOImpl")
	public UserDAOImpl userDAOImpl() {
		UserDAOImpl userDAOImpl = new UserDAOImpl(dataSource());
		return userDAOImpl;
	}

	@Bean(name="tokenVerification")
	public VerificationTokenDAOImpl verificationTokenDAOImpl() {
		VerificationTokenDAOImpl verificationTokenDAOImpl = new VerificationTokenDAOImpl(dataSource());
		return verificationTokenDAOImpl;
	}

	@Bean(name="userService")
	public UserService userService() {
		UserService userService = new UserService(userDAOImpl(), verificationTokenDAOImpl());
		return userService;
	}

//	@Bean
//	public ApplicationEventPublisher  applicationPublisher() {
//		ApplicationEventPublisher publisher = new RegisterationCompleteEventPublisher();
//		//publisher.publishEvent(new RegisterationCompleteEvent(new User()));;
//		return publisher;
//	}

	@Bean
	public RegisterationCompleteEventListener registerationCompleteListener() {
		RegisterationCompleteEventListener l = new RegisterationCompleteEventListener();
		return l;
	}

	@Bean(name="mailSender")
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
				mailSender.setHost("smtp.gmail.com");
			    mailSender.setPort(587);
			     
			    mailSender.setUsername("your.email@gmail.com");
			    mailSender.setPassword("yourpassword");
			    
			    Properties props = mailSender.getJavaMailProperties();
			    props.put("mail.transport.protocol", "smtp");
			    props.put("mail.smtp.auth", "true");
			    props.put("mail.smtp.starttls.enable", "true");
			    props.put("mail.debug", "true");

		return mailSender;

	}

	public static void main(String[] args) {
		try 
		{
//			Properties props = System.getProperties();
//			String host = "smtp.gmail.com";
//			props.put("mail.smtp.starttls.enable", "true");
//			props.put("mail.smtp.host", host);
//			props.put("mail.smtp.user", "youremail@gmail.com");
//			props.put("mail.smtp.password", "yourpassword");
//			props.put("mail.smtp.port", "587"); //465
//			props.put("mail.smtp.auth", "true");
			SpringApplication.run(SpringThymeleaf6Application.class, args);
		}
		catch(Throwable t) 
		{
			t.printStackTrace();
		}
	}
}
