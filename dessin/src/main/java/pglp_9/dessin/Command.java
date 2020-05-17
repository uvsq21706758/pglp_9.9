package pglp_9.dessin;

import java.sql.SQLException;

/**
 * 
 *command pattern
 */
public interface Command {
	
	 /**
	  * execution command
	 * @throws SQLException sql error exception
	 */
	void execute() throws SQLException;
	 
}
