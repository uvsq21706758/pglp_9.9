package pglp_9.dessin;

import java.sql.SQLException;

public class CreationCommand implements Command{
	
		Forme forme;
		
     public CreationCommand(Forme forme) {
    	 this.forme=forme;
     }
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
