package pglp_9.dessin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCcarreDAO extends DAO<Carre>{
    
	Connection con;
	
	public JDBCcarreDAO(Connection con) {
       this.con = con;
    }

	@Override
	public Carre create(Carre object) throws SQLException {
		PreparedStatement prepare = con.prepareStatement(
				"INSERT  INTO Carre (NomCr, point_x, point_y, cote)" +
				"VALUES (?, ?, ?, ?)");
		prepare.setString(1, object.getNom());
		prepare.setInt(2, object.getPoint().getX());
		prepare.setInt(3, object.getPoint().getY());
		prepare.setInt(4, object.getCote());
		prepare.executeUpdate();
		
		return object;	
	}

	@Override
	public Carre find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carre update(Carre object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Carre object) throws SQLException {
		Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Carre where NomCr=" + object.getNom());
            if(rs.next()) {
              stmt.executeUpdate("delete from Carre where NomCr="+ object.getNom());
              
            	rs.close();
            stmt.close();
           	  System.out.printf("Ligne supprimée \n");
           
            }else {
            	System.out.println("suppression impossible,identifiant introuvable!");
            }
	}

}
