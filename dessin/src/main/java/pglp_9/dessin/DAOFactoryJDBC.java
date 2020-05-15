package pglp_9.dessin;

import java.sql.Connection;


public class DAOFactoryJDBC {
	
    private  Connection con;
    
    public DAOFactoryJDBC() {
        con = BDcreation.getConnect();
    }
    
	public  DAO<Cercle> getCercleDAO () {
		return new JDBCcercleDAO(con) ;
		}
	
	public  DAO<Rectangle> getRectangleDAO () {
		return new JDBCrectangleDAO (con) ;
		}
	
	public  DAO<Triangle> getTriangleDAO () {
		return new JDBCtriangleDAO(con) ;
		}
	
	public  DAO<Carre> getCarreDAO () {
		return new JDBCcarreDAO(con);
		}
	
	public  DAO<GroupeForme> getGroupeDAO () {
		return new JDBCgroupeDAO(con);
		}   
	    
}
