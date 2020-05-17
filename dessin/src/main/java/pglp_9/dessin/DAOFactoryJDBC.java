package pglp_9.dessin;


/**
 * classe factory
 *
 */
public class DAOFactoryJDBC {
	
    /**
     * constructeur
     */
    public DAOFactoryJDBC() {
        FormeandRelation.getConnect();
    }
    
	/**
	 * @return DAOcercle
	 */
	public  DAO<Cercle> getCercleDAO () {
		return new JDBCcercleDAO() ;
		}
	
	/**
	 * @return DAOrectangle
	 */
	public  DAO<Rectangle> getRectangleDAO () {
		return new JDBCrectangleDAO () ;
		}
	
	/**
	 * @return DAOtriangle
	 */
	public  DAO<Triangle> getTriangleDAO () {
		return new JDBCtriangleDAO() ;
		}
	
	/**
	 * @return DAOcarre
	 */
	public   DAO<Carre> getCarreDAO () {
		return new JDBCcarreDAO();
		}
	
	/**
	 * @return DAOgroupe
	 */
	public  DAO<GroupeForme> getGroupeDAO () {
		return new JDBCgroupeDAO();
		}   
	    
}
