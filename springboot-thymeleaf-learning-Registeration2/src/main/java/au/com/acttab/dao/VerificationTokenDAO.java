package au.com.acttab.dao;

import java.util.Date;

import au.com.acttab.model.User;

public interface VerificationTokenDAO 
{
	public void saveToken(User user, String token);
	public void deleteToken(long id);
}
