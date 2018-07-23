package au.com.acttab.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import au.com.acttab.dao.VerificationTokenDAO;
import au.com.acttab.model.User;
import au.com.acttab.model.VerificationToken;

/**
 * http://www.javarticles.com/2015/02/example-of-spring-callbacks-used-in-jdbctemplate.html
 * https://github.com/Baeldung/spring-security-registration/blob/master/src/main/java/org/baeldung/web/util/GenericResponse.java
 * */


public class VerificationTokenDAOImpl implements VerificationTokenDAO {

	private JdbcTemplate jdbcTemplate;
	private DataSource ds;

	public VerificationTokenDAOImpl(DataSource ds) {
		super();
		this.ds = ds;
		this.jdbcTemplate = new JdbcTemplate(this.ds);
	}

	@Override
	public void saveToken(User user, String token) {
		String insertSQL = "insert into verificationtoken (token, email_address) values (?,?)";
		jdbcTemplate.execute(insertSQL, new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, token);
				ps.setString(2, user.getEmailAddress());
				return ps.execute();
			}});

	}

	public void getToken(String token) {
		String getTokenSQL = "select * from verificationtoken where token=?";
		jdbcTemplate.query(getTokenSQL,new Object[] {token}, new RowMapper<VerificationToken>() {

			@Override
			public VerificationToken mapRow(ResultSet rs, int rowNum) throws SQLException {
				VerificationToken token = new VerificationToken();
				token.setEmailAddress(rs.getString("email_address"));
				token.setId(rs.getLong("id"));
				token.setTokenUUID(rs.getString("token"));
				return null;
			}});
		//		jdbcTemplate.execute(getTokenSQL, new PreparedStatementCallback<VerificationToken>(){
		//
		//			@Override
		//			public VerificationToken doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
		//				return null;
		//			}})
	}

	@Override
	public void deleteToken(String token){
		// TODO Auto-generated method stub
		String deleteTokenSQL = "delete from verificationtoken where token='" + token + "'";

		jdbcTemplate.update(deleteTokenSQL);

	}

	@Override
	public VerificationToken searchToken(String token) {
		VerificationToken verifyToken = null;
		
		String verifyTokenSQL = "select * from verificationtoken where token='" + token + "'" ;
		try 
		{
			verifyToken =  jdbcTemplate.queryForObject(verifyTokenSQL, new RowMapper<VerificationToken>() {

				@Override
				public VerificationToken mapRow(ResultSet rs, int rowNum) throws SQLException {
					VerificationToken token = new VerificationToken();
					token.setEmailAddress(rs.getString("email_address"));
					token.setId(rs.getLong("id"));
					token.setTokenUUID(rs.getString("token"));
					return token;
				}});
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

		return verifyToken;

		/*jdbcTemplate.query(getTokenSQL,new Object[] {token}, new RowMapper<VerificationToken>() {

			@Override
			public VerificationToken mapRow(ResultSet rs, int rowNum) throws SQLException {
				VerificationToken token = new VerificationToken();
				token.setEmailAddress(rs.getString("email_address"));
				token.setId(rs.getLong("id"));
				token.setTokenUUID(rs.getString("token"));
				return null;
			}});*/
	}

}
