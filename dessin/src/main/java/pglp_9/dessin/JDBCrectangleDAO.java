package pglp_9.dessin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCrectangleDAO extends DAO<Rectangle>{

	Connection con;
	
	public JDBCrectangleDAO(Connection con) {
       this.con = con;
    }

	@Override
	public Rectangle create(Rectangle object) throws SQLException {
		PreparedStatement prepare = con.prepareStatement(
				"INSERT  INTO Rectangle (NomRc, point_x, point_y, largeur, longeur)" +
				"VALUES (?, ?, ?, ?, ?)");
		prepare.setString(1, object.getNom());
		prepare.setInt(2, object.getPoint().getX());
		prepare.setInt(3, object.getPoint().getY());
		prepare.setInt(4, object.getLargeur());
		prepare.setInt(5, object.getLongueur());
		prepare.executeUpdate();
		
		return object;	
	}

	@Override
	public Rectangle find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle update(Rectangle object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Rectangle object) throws SQLException {
		Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Rectangle where NomRc=" + object.getNom());
            if(rs.next()) {
              stmt.executeUpdate("delete from Rectangle where NomRc="+ object.getNom());
            	rs.close();
            stmt.close();
           	  System.out.printf("Ligne supprimée \n");
           
            } else {
            	System.out.println("suppression impossible,identifiant introuvable!");
            }
	}

}
