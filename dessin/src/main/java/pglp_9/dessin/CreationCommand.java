package pglp_9.dessin;

import java.sql.SQLException;

/**
 *classe creation command
 *
 */
public class CreationCommand implements Command{
	
		/**
		 * attribut forme
		 */
		Forme forme;
		
     /**
      * constructeur
     * @param forme la forme a créer
     */
    public CreationCommand(Forme forme) {
    	 this.forme=forme;
     }
	/**
	 *execution command
	 */
	public void execute() throws SQLException {
	
		DAOFactoryJDBC factory = new DAOFactoryJDBC();
		
        if (forme instanceof Cercle) {
            DAO<Cercle> cercle = factory.getCercleDAO();
             cercle.create((Cercle) forme);
        } else if (forme instanceof Rectangle) {
        	DAO<Rectangle> rectangle = factory.getRectangleDAO();
             rectangle.create((Rectangle) forme);
        } else if (forme instanceof Triangle) {
        	DAO<Triangle> triangle = factory.getTriangleDAO();
             triangle.create((Triangle) forme);
        }  else if (forme instanceof Carre) {
        	DAO<Carre> carre = factory.getCarreDAO();
             carre.create((Carre) forme);
        }
        else {
            DAO<GroupeForme> groupe = factory.getGroupeDAO();
             groupe.create((GroupeForme) forme);
        }
       
            System.out.println("La forme est créée ");
       
	}

}
