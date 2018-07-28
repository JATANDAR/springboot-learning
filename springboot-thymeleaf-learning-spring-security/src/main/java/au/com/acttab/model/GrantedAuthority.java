package au.com.acttab.model;

public class GrantedAuthority implements org.springframework.security.core.GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String grantedRole;
	
	public GrantedAuthority(String role) {
		this.grantedRole = role;
	}
	
	@Override
	public String getAuthority() {
		return grantedRole;
	}

	@Override
	public String toString() {
		return "GrantedAuthority [grantedRole=" + grantedRole + "]";
	}
}
