package pglp_9.dessin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAO<T> {
	String driver ="org.apache.derby.jdbc.EmbeddedDriver";
	String dburl = "jdbc:derby:data;create=true";
	
	Connection con;
	public DAO() throws SQLException {
	   con=DriverManager.getConnection(dburl);
	}
	
	public abstract T create(T object);
   
    public abstract T find(String id);
       
    public abstract T update(T object);
   
    public abstract void delete(T object);
    
    public Connection getConnect() {
        return con;
    }
}
