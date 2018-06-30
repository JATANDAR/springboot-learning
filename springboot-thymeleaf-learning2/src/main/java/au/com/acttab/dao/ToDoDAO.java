package au.com.acttab.dao;

import java.util.List;

import au.com.acttab.model.ToDoDatumModel;

public interface ToDoDAO {
	
	public List<ToDoDatumModel>getAllToDos();
	public boolean delete();
	public void save(final ToDoDatumModel toDo);
	public ToDoDatumModel getSpecificToDo(int i);
	
}
