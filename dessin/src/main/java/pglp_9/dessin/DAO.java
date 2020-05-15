package pglp_9.dessin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class DAO<T> {
	
	public abstract T create(T object) throws SQLException;
   
    public abstract T find(String id) throws SQLException;
       
    public abstract T update(T object) throws SQLException;
   
    public abstract void delete(T object) throws SQLException;
    
    public abstract ArrayList<T> show();
  
}
