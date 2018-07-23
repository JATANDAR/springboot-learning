package au.com.acttab.model;

import java.util.Date;

public class VerificationToken 
{
	private long id;
	private String tokenUUID;
	private Date expiryDate;
	private String emailAddress;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTokenUUID() {
		return tokenUUID;
	}
	
	public void setTokenUUID(String tokenUUID) {
		this.tokenUUID = tokenUUID;
	}
	
	public Date getExpiryDate() {
		return expiryDate;
	}
	
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String toString() {
		return "VerificationToken [id=" + id + ", tokenUUID=" + tokenUUID + ", expiryDate=" + expiryDate
				+ ", emailAddress=" + emailAddress + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((tokenUUID == null) ? 0 : tokenUUID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VerificationToken other = (VerificationToken) obj;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
			return false;
		if (id != other.id)
			return false;
		if (tokenUUID == null) {
			if (other.tokenUUID != null)
				return false;
		} else if (!tokenUUID.equals(other.tokenUUID))
			return false;
		return true;
	}

}
