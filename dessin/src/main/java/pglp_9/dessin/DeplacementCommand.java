package pglp_9.dessin;

import java.sql.SQLException;


/**
 *classe deplacement command
 *
 */
public class DeplacementCommand implements Command{
	
	/**
	 * attribut forme
	 */
	Forme forme;
	
	/**
	 * attribut point 
	 */
	Point point;
	
	/**
	 * constructeur
	 * @param forme
	 * @param point
	 */
	public DeplacementCommand(Forme forme,Point point) {
		this.forme=forme;
		this.point=point;
	}
	
	/**
	 *execution command
	 * @throws SQLException 
	 */
	public void execute() throws SQLException {
		 forme.deplace(point.getX(), point.getY());
	DAOFactoryJDBC factory = new DAOFactoryJDBC();
	 
        if (forme instanceof Cercle) {
            DAO<Cercle> cercle = factory.getCercleDAO();
             cercle.update((Cercle) forme);
        } else if (forme instanceof Rectangle) {
        	DAO<Rectangle> rectangle = factory.getRectangleDAO();
             rectangle.update((Rectangle) forme);
        } else if (forme instanceof Triangle) {
        	DAO<Triangle> triangle = factory.getTriangleDAO();
             triangle.update((Triangle) forme);
        }  else if (forme instanceof Carre) {
        	DAO<Carre> carre = factory.getCarreDAO();
             carre.update((Carre) forme);
        }
        else {
            DAO<GroupeForme> groupe = factory.getGroupeDAO();
             groupe.update((GroupeForme) forme);
        }
       
		
	}

}
