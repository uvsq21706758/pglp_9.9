package pglp_9.dessin;


public class DAOFactoryJDBC {
	
    public DAOFactoryJDBC() {
        BDcreation.getConnect();
    }
    
	public  DAO<Cercle> getCercleDAO () {
		return new JDBCcercleDAO() ;
		}
	
	public  DAO<Rectangle> getRectangleDAO () {
		return new JDBCrectangleDAO () ;
		}
	
	public  DAO<Triangle> getTriangleDAO () {
		return new JDBCtriangleDAO() ;
		}
	
	public   DAO<Carre> getCarreDAO () {
		return new JDBCcarreDAO();
		}
	
	public  DAO<GroupeForme> getGroupeDAO () {
		return new JDBCgroupeDAO();
		}   
	    
}
