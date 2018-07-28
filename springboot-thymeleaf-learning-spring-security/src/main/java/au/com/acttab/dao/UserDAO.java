package au.com.acttab.dao;

import java.util.List;

import au.com.acttab.model.GrantedAuthority;
import au.com.acttab.model.User;

public interface UserDAO 
{
	public User findUser(String emailAddress);
	public void deleteCustomer(User user);
	public void saveUser(User user);
	public User updateUser(User user);
	public User findUserById(Integer id);
	public List<GrantedAuthority> getGrantedAuthorities(String emailAddress);

}
