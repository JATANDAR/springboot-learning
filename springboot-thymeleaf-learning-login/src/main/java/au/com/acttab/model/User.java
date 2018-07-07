package au.com.acttab.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class User {
	
	private Long id;
	
	@NotNull
	@Size(max = 100, min = 5)
	private String name;
	
	@NotNull
	@Email
	private String emailAddress;
	
	@NotNull
	private String password;
	
	@Size(max = 20)
	private String phone;
	
	public User() {
		super();
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", emailAddress=" + emailAddress + ", password=" + password
				+ ", phone=" + phone + "]";
	}
}
