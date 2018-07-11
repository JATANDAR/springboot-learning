package au.com.acttab.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

import au.com.acttab.dao.ToDoDAO;
import au.com.acttab.model.ToDoDatumModel;


/***
 * 
 * https://www.javatpoint.com/example-of-PreparedStatement-in-Spring-JdbcTemplate
 * http://www.baeldung.com/spring-jdbc-jdbctemplate
 * https://www.javatpoint.com/java-sql-date
 * https://springframework.guru/spring-boot-web-application-part-4-spring-mvc/
 * **/


public class ToDoDAOImpl implements ToDoDAO {

	private JdbcTemplate jdbcTemplate;
	private DataSource ds;
	List<ToDoDatumModel> toDoList = new ArrayList<ToDoDatumModel>();
	final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");

	public ToDoDAOImpl(DataSource ds) {
		this.ds = ds;
		this.jdbcTemplate = new JdbcTemplate(this.ds);
		getAllToDos();
	}


	@Override
	public List<ToDoDatumModel> getAllToDos() {
		if(this.toDoList.isEmpty()) {
			synchronized(this.toDoList) 
			{
				this.toDoList = jdbcTemplate.query("select * from todo", new RowMapper<ToDoDatumModel>() 
				{

					@Override
					public ToDoDatumModel mapRow(ResultSet rs, int rowNum) throws SQLException {

						ToDoDatumModel todo= new ToDoDatumModel(rs.getString("description"),
								simpleDateFormat.format(rs.getDate("target_date")),
								rs.getBoolean("isItDone"), 
								rs.getInt("id"));
						System.out.println("getAllToDos() todo=" + todo);
						return todo;
					}});

			}
		}

		return this.toDoList;
	}

	@Override
	public boolean delete() {
		return false;
	}

	@Override
	public void save(final ToDoDatumModel toDo) {
		//Integer position = toDo.getPosition();
		//synchronized(this.toDoList) {

		System.out.println("save=" + toDo);
		

//		java.util.Date date = null;
//		try {
//			date = new SimpleDateFormat("dd-MM-YYYY").parse(toDo.getTargetDate());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		//		System.out.println("date=" + date);
		//		calendarInstance.setTime(date);
		//		jdbcTemplate.update("UPDATE todo SET description='" + toDo.getDescription() 
		//		+ "', target_date=" + new java.sql.Date(calendarInstance.getTimeInMillis())
		//		+ ", isItDone='" + 	toDo.getIsItDone()
		//		+ "' where id=" + toDo.getPosition()+ "");
		String sqlUpdate = "UPDATE todo SET description=?, target_date=? , isItDone=? where id=?";
		PreparedStatement  psc;
		jdbcTemplate.execute(sqlUpdate, new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, toDo.getDescription());
				try 
				{
					Calendar calendarInstance = java.util.Calendar.getInstance();
//					java.util.Date parse = new SimpleDateFormat("dd-MM-YYYY").parse(toDo.getTargetDate());
//					calendarInstance.setTime(parse);
					//Date valueOf = Date.valueOf(new SimpleDateFormat("dd-MM-YYYY").parse(toDo.getTargetDate()));
					
					String[] split = toDo.getTargetDate().split("-");
					Date date = new Date(Integer.parseInt(split[2]),
							Integer.parseInt(split[1]),
							Integer.parseInt(split[0]));
					System.out.println("valueof=" + date);
					System.out.println("0=" + split[0] );
					System.out.println("1=" + split[1]);
					System.out.println("2=" + split[2]);
					
					calendarInstance.set(Integer.parseInt(split[2]), Integer.parseInt(split[1]), Integer.parseInt(split[0]));
					
					//System.out.println("parse=" + parse);
					ps.setDate(2, new java.sql.Date(calendarInstance.getTimeInMillis()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				ps.setBoolean(3, toDo.getIsItDone());
				ps.setInt(4, toDo.getPosition());
				return ps.execute();
			}});

		synchronized(this.toDoList) 
		{
			this.toDoList.clear();
			this.toDoList = jdbcTemplate.query("select * from todo", new RowMapper<ToDoDatumModel>() 
			{

				@Override
				public ToDoDatumModel mapRow(ResultSet rs, int rowNum) throws SQLException {

					ToDoDatumModel todo= new ToDoDatumModel(rs.getString("description"),
							simpleDateFormat.format(rs.getDate("target_date")),
							rs.getBoolean("isItDone"), 
							rs.getInt("id"));
					System.out.println("todo=" + todo);
					return todo;
				}});

		}
	}

	//}


	@Override
	public ToDoDatumModel getSpecificToDo(int i) {
		ToDoDatumModel data = null;
		data=jdbcTemplate.queryForObject("select * from todo where id= '" + i + "'", new RowMapper<ToDoDatumModel>() {

			@Override
			public ToDoDatumModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				ToDoDatumModel todo= new ToDoDatumModel(rs.getString("description"),
						simpleDateFormat.format(rs.getDate("target_date")),
						rs.getBoolean("isItDone"), 
						i);
				System.out.println("getSpecificToDo toDo=" + todo);
				return todo;
			}});
		return data;
	}

}
