package au.com.acttab.dao;

import au.com.acttab.model.User;

public interface UserDAO 
{
	
	public User findUser(String emailAddress);
	public void deleteCustomer(User user);
	public void saveUser(User user);
	public User updateUser(User user);
	public User findUserById(Integer id);

}
