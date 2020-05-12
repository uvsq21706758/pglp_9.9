package pglp_9.dessin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCcercleDAO extends DAO<Cercle>{
	Connection con;
	
	public JDBCcercleDAO(Connection con) {
       this.con = con;
    }

	@Override
	public Cercle create(Cercle object) throws SQLException {
		PreparedStatement prepare = con.prepareStatement(
				"INSERT  INTO Cercle (Nomcrl, centre_x, centre_y, rayon)" +
				"VALUES (?, ?, ?, ?)");
		prepare.setString(1, object.getNom());
		prepare.setInt(2, object.getCentre().getX());
		prepare.setInt(3, object.getCentre().getY());
		prepare.setInt(4, object.getRayon());
		prepare.executeUpdate();
		
		return object;	
	}

	@Override
	public Cercle find(String id) throws SQLException {
		return null;
	
	}

	@Override
	public Cercle update(Cercle object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Cercle object) throws SQLException {
		Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Cercle where Nomcrl=" + object.getNom());
            if(rs.next()) {
              stmt.executeUpdate("delete from Cercle where Nomcrl="+ object.getNom());
              
            	rs.close();
            stmt.close();
           	  System.out.printf("Ligne supprimée \n");
           
            }else {
            	System.out.println("suppression impossible,identifiant introuvable!");
            }
		
	}

}
