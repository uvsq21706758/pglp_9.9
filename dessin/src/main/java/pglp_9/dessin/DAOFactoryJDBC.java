package pglp_9.dessin;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * classe factory
 *
 */
public class DAOFactoryJDBC {
	
	Connection con;
	
    /**
     * constructeur
     */
    public DAOFactoryJDBC() {
        con=FormeandRelation.getConnect();
    }
    
	/**
	 * @return DAOcercle
	 */
	public  DAO<Cercle> getCercleDAO () {
		return new JDBCcercleDAO(con) ;
		}
	
	/**
	 * @return DAOrectangle
	 */
	public  DAO<Rectangle> getRectangleDAO () {
		return new JDBCrectangleDAO (con) ;
		}
	
	/**
	 * @return DAOtriangle
	 */
	public  DAO<Triangle> getTriangleDAO () {
		return new JDBCtriangleDAO(con) ;
		}
	
	/**
	 * @return DAOcarre
	 */
	public   DAO<Carre> getCarreDAO () {
		return new JDBCcarreDAO(con);
		}
	
	/**
	 * @return DAOgroupe
	 */
	public  DAO<GroupeForme> getGroupeDAO () {
		return new JDBCgroupeDAO(con);
		}   
	    

    /**
    * methode pour fermer la connection
    */
    public void close() {
         try {
        con.close();
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }
}
