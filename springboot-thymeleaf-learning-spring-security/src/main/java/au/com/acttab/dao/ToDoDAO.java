package au.com.acttab.dao;

import java.util.List;

import au.com.acttab.model.ToDoDatumModel;

public interface ToDoDAO {
	
	public List<ToDoDatumModel>getAllToDos();
	public void delete(int id);
	public void save(final ToDoDatumModel toDo);
	public ToDoDatumModel getSpecificToDo(int i);
	
}
