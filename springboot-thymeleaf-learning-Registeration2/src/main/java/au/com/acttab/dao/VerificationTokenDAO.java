package au.com.acttab.dao;

import au.com.acttab.model.User;
import au.com.acttab.model.VerificationToken;

public interface VerificationTokenDAO 
{
	public void saveToken(User user, String token);
	public void deleteToken(String token);
	public VerificationToken searchToken(String token);
}
