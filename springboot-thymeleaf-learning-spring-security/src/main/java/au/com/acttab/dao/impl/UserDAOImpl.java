package au.com.acttab.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import au.com.acttab.dao.UserDAO;
import au.com.acttab.model.GrantedAuthority;
import au.com.acttab.model.User;

public class UserDAOImpl implements UserDAO, UserDetailsService{
	
	private final JdbcTemplate jdbcTemplate;
	private final DataSource ds;

	public UserDAOImpl(DataSource ds) {
		this.ds = ds;
		this.jdbcTemplate = new JdbcTemplate(this.ds);
	}

	@Override
	public User findUser(String emailAddress) {
		User dbUser = null;
		//jdbcTemplate.q
		dbUser = (User) jdbcTemplate.queryForObject("select * from users where email_address='" + emailAddress + "'", new RowMapper<User>()
		{
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setEmailAddress(rs.getString("email_address"));
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getString("phone"));
				user.setEnabled(rs.getBoolean("enabled"));
				return user;
			}});
		
		List<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(emailAddress);
		dbUser.setGrantedAuths(grantedAuthorities);
		
		return dbUser;
	}
	
	@Override
	public List<GrantedAuthority> getGrantedAuthorities(String emailAddress) 
	{
		List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
		String sql = "select * from authorities where email_address='" + emailAddress + "'";
		
		List<Map<String,Object>> queryForList = jdbcTemplate.queryForList(sql);
		
		for(Map row : queryForList) {
			GrantedAuthority auth = new GrantedAuthority( (String)row.get("authority"));
			System.out.println(auth);
			authorityList.add(auth);
		}
//		jdbcTemplate.queryForList(sql, new String[] {emailAddress});
//		jdbcTemplate.query(sql, new String[] {emailAddress}, new RowMapper<GrantedAuthority>() {
//
//			@Override
//			public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
//				// TODO Auto-generated method stub
//				return null;
//			}})
		return authorityList;
	}

	@Override
	public void deleteCustomer(User user) {
		jdbcTemplate.execute("delete from users where id=" + user.getId());
	}

	@Override
	public void saveUser(User user) {
		String insertSql = "insert into users (email_address, name, password, phone, enabled) values (?,?,?,?,?)";
		jdbcTemplate.execute(insertSql, new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, user.getEmailAddress());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());
				ps.setString(4, user.getPhone());
				ps.setBoolean(5, false);
				return ps.execute();
			}});
	}

	@Override
	public User updateUser(User user) {
		return null;
	}

	@Override
	public User findUserById(Integer id) {
		User dbUser = null;
		dbUser = jdbcTemplate.queryForObject("select * from users where id=" + id, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setEmailAddress(rs.getString("email_address"));
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getString("phone"));
				user.setEnabled(rs.getBoolean("enabled"));
				return user;
			}});
		return dbUser;
	}

	@Override
	public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return findUser(emailAddress);
	}
}
