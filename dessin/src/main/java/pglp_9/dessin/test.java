package pglp_9.dessin;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class test {
	@Override
	public GroupeForme create(GroupeForme object) throws SQLException {
		DAOFactoryJDBC factory = new DAOFactoryJDBC();
	        try {
	            PreparedStatement prepare = con.prepareStatement(
	                    "INSERT INTO Forme (Nomf) VALUES(?)");
	            prepare.setString(1, object.getNom());
	            prepare.executeUpdate();
	            prepare = con.prepareStatement(
	                    "INSERT INTO Groupeforme (nomgr) VALUES(?)");
	            prepare.setString(1, object.getNom());
	            prepare.executeUpdate();
	      
	            Iterator<Forme> iterator = object.iteratorGroupe();
	            while (iterator.hasNext()) {
	          	  Forme forme  = iterator.next();
	                if (forme instanceof Cercle) {
	                    DAO<Cercle> cercle = factory.getCercleDAO();
	                    cercle.create((Cercle) forme);
	                } else if (forme instanceof Carre) {
	                    DAO<Carre> carre = factory.getCarreDAO();
	                    carre.create((Carre) forme);
	                } else if (forme instanceof Rectangle) {
	                    DAO<Rectangle> rectangle = factory.getRectangleDAO();
	                    rectangle.create((Rectangle) forme);
	                } else if (forme instanceof Triangle) {
	                    DAO<Triangle> triangle = factory.getTriangleDAO();
	                    triangle.create((Triangle) forme);
	                } else {
	                    this.create((GroupeForme) forme);
	                }
	                this.createRelation(object.getNom(), forme.getNom());
	            }
	           
	        } catch (SQLException e) {
	            
	            return null;
	        }
	        return object;
	}
}
