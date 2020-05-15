package pglp_9.dessin;

import java.sql.Connection;


public class DAOFactoryJDBC {
	
    private static Connection con;
    
    public DAOFactoryJDBC() {
        con = BDcreation.getConnect();
    }
    
	public static DAO<Cercle> getCercleDAO () {
		return new JDBCcercleDAO(con) ;
		}
	
	public static DAO<Rectangle> getRectangleDAO () {
		return new JDBCrectangleDAO (con) ;
		}
	
	public static DAO<Triangle> getTriangleDAO () {
		return new JDBCtriangleDAO(con) ;
		}
	
	public static DAO<Carre> getCarreDAO () {
		return new JDBCcarreDAO(con);
		}
	
	public static DAO<GroupeForme> getGroupeDAO () {
		return new JDBCgroupeDAO();
		}   
	    
}
