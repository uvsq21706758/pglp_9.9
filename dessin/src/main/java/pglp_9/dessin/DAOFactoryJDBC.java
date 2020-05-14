package pglp_9.dessin;

public class DAOFactoryJDBC {
	
	public static DAO<Cercle> getCercleDAO () {
		return new JDBCcercleDAO(null) ;
		}
	
	public static DAO<Rectangle> getRectangleDAO () {
		return new JDBCrectangleDAO (null) ;
		}
	
	public static DAO<Triangle> getTriangleDAO () {
		return new JDBCtriangleDAO(null) ;
		}
	
	public static DAO<Carre> getCarreDAO () {
		return new JDBCcarreDAO(null);
		}
	
	public static DAO<GroupeForme> getGroupeDAO () {
		return new JDBCgroupeDAO();
		}   
	    
}
