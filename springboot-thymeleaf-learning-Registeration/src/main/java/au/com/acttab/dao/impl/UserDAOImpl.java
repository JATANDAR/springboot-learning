package au.com.acttab.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

import au.com.acttab.dao.UserDAO;
import au.com.acttab.model.User;

public class UserDAOImpl implements UserDAO {
	
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
		dbUser = (User) jdbcTemplate.queryForObject("select * from users where email_address='" + emailAddress + "'", new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setEmailAddress(rs.getString("email_address"));
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getString("phone"));
				return user;
			}});
		return dbUser;
	}

	@Override
	public void deleteCustomer(User user) {
		jdbcTemplate.execute("delete from users where id=" + user.getId());
	}

	@Override
	public void saveUser(User user) {
		String insertSql = "insert into users (email_address, name, password, phone) values (?,?,?,?)";
		jdbcTemplate.execute(insertSql, new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, user.getEmailAddress());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());
				ps.setString(4, user.getPhone());
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
				return user;
			}});
		return dbUser;
	}

}
