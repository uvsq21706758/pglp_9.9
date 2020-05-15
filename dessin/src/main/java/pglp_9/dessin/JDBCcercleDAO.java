package pglp_9.dessin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCcercleDAO extends DAO<Cercle>{
	Connection con;
	
	public JDBCcercleDAO(Connection con) {
       this.con = con;
    }

	@Override
	public Cercle create(Cercle object) throws SQLException {
		PreparedStatement prepare = con.prepareStatement(
                "INSERT INTO Forme (Nomf)"
                + " VALUES(?)");
                prepare.setString(1, object.getNom());
                prepare.executeUpdate();
		prepare = con.prepareStatement(
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
	public Cercle find(String id) {
		Cercle cercle= null;
	        try {
	            PreparedStatement prepare = con.prepareStatement(
	                    "SELECT * FROM Cercle WHERE Nomcrl =" + id);
	            ResultSet result = prepare.executeQuery();
	            if (result.next()) {
	                Point centre = new Point(
	                        result.getInt("centre_x"),
	                        result.getInt("centre_y"));
	                try {
	                    cercle = new Cercle(id,centre,result.getInt("rayon"));
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    return null;
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	        return cercle;
	}

	@Override
	public Cercle update(Cercle object) throws SQLException {
         Cercle cercle = this.find(object.getNom());
        if (cercle != null) {
            try {
                PreparedStatement prepare = con.prepareStatement(
                "UPDATE Cercle SET centre_x = "+object.getCentre().getX()+", centre_y ="+object.getCentre().getY()+", "
                + "rayon = "+object.getRayon()+" WHERE Nomcrl = "+object.getNom());
                prepare.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return cercle;
            }
        } else {
            return null;
        }
        return object;
	}

	@Override
	public void delete(Cercle object) throws SQLException {
		Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Cercle where Nomcrl=" + object.getNom());
            if(rs.next()) {
              stmt.executeUpdate("delete from Relation where nomForme ="+ object.getNom());
              stmt.executeUpdate("delete from Cercle where Nomcrl="+ object.getNom());
              stmt.executeUpdate("delete from Forme where Nomf="+ object.getNom()); 
            	rs.close();
            stmt.close();
           	  System.out.printf("Ligne supprimée \n");
           
            }else {
            	System.out.println("suppression impossible,identifiant introuvable!");
            }
		
	}

	@Override
	public ArrayList<Cercle> show() {
		 ArrayList<Cercle> cercle = new ArrayList<Cercle>();
	        try {
	            PreparedStatement prepare = con.prepareStatement(
	                    "SELECT Nomcrl FROM Cercle");
	            ResultSet result = prepare.executeQuery();
	            while (result.next()) {
	            	cercle.add(this.find(result.getString("Nomcrl")));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ArrayList<Cercle>();
	        }
	        return cercle;
	}

}
