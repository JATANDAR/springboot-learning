package au.com.acttab.controller;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import au.com.acttab.dao.impl.ToDoDAOImpl;

@SpringBootApplication
public class SpringThymeleaf3Application {
	
	/**
	 * https://stackoverflow.com/questions/9353167/auto-increment-id-in-h2-database
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
	
	@Bean(name="toDoDAOImpl")
	public ToDoDAOImpl toDoDAOImpl() {
		ToDoDAOImpl toDoDAOImpl = new ToDoDAOImpl(dataSource());
		return toDoDAOImpl;
	}

	public static void main(String[] args) {
		try 
		{
		SpringApplication.run(SpringThymeleaf3Application.class, args);
		}
		catch(Throwable t) 
		{
			t.printStackTrace();
		}
	}
}
