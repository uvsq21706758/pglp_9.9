package pglp_9.dessin;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 *classe suppression command
 *
 */
public class SuppressionCommand implements Command{

	 /**
	 * liste des formes
	 */
	ArrayList<Forme> formeList;
	  
    /**
     * constructeur
     */
    public SuppressionCommand() {
	 this.formeList=new ArrayList<Forme>();
    }
   
	/**
	 *execution command
	 */
	public void execute() throws SQLException {
		DAOFactoryJDBC factory = new DAOFactoryJDBC();
		 for (Forme forme : formeList) {
        if (forme instanceof Cercle) {
            DAO<Cercle> cercle = factory.getCercleDAO();
             cercle.delete((Cercle) forme);
        } else if (forme instanceof Rectangle) {
        	DAO<Rectangle> rectangle = factory.getRectangleDAO();
             rectangle.delete((Rectangle) forme);
        } else if (forme instanceof Triangle) {
        	DAO<Triangle> triangle = factory.getTriangleDAO();
             triangle.delete((Triangle) forme);
        }  else if (forme instanceof Carre) {
        	DAO<Carre> carre = factory.getCarreDAO();
             carre.delete((Carre) forme);
        }
        else {
        	DAO<GroupeForme> groupe = factory.getGroupeDAO();
             groupe.delete((GroupeForme) forme);
        }
		 }
            System.out.println("La forme est supprimée");
       
		 
	}

}
