package au.com.acttab.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import au.com.acttab.dao.ToDoDAO;
import au.com.acttab.model.ToDoDatumModel;

public class ToDoDAOImpl implements ToDoDAO {

	private JdbcTemplate jdbcTemplate;
	private DataSource ds;
	List<ToDoDatumModel> toDoList = new ArrayList<ToDoDatumModel>();
	
	public ToDoDAOImpl(DataSource ds) {
		this.ds = ds;
		this.jdbcTemplate = new JdbcTemplate(this.ds);
	}
	
	
	@Override
	public List<ToDoDatumModel> getAllToDos() {
		if(this.toDoList.isEmpty()) {
			synchronized(this.toDoList) 
			{
				this.toDoList = jdbcTemplate.query("select * from todo", new RowMapper<ToDoDatumModel>() 
				{
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
					@Override
					public ToDoDatumModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						ToDoDatumModel todo= new ToDoDatumModel(rs.getString("description"),
								simpleDateFormat.format(rs.getDate("target_date")),
								rs.getBoolean("isItDone"), 
								rowNum + 1);
						System.out.println("todo=" + todo);
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
	public void editToDo(int i) {

	}

	@Override
	public ToDoDatumModel getSpecificToDo(int i) {
		return null;
	}

}
