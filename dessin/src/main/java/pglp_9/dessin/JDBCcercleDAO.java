package pglp_9.dessin;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCcercleDAO extends DAO<Cercle>{
	Connection con;
	
	public JDBCcercleDAO() {
       this.con = BDcreation.getConnect();
    }

	@Override
	public Cercle create(Cercle object) throws SQLException {
		
		PreparedStatement prepare = con.prepareStatement(
                "INSERT INTO Forme (Nomf)"
                + " VALUES(?)");
                prepare.setString(1, object.getNom());
                prepare.executeUpdate();
		prepare =con.prepareStatement(
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
	                    "SELECT * FROM Cercle WHERE Nomcrl = ? ");
	            prepare.setString(1, id);
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
                "UPDATE Cercle SET centre_x = ?, centre_y =? , "
                + "rayon = ? WHERE Nomcrl = ?");
                prepare.setInt(1, object.getCentre().getX());
        		prepare.setInt(2, object.getCentre().getY());
        		prepare.setInt(3, object.getRayon());
        		prepare.setString(4, object.getNom());
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
            try {
            	PreparedStatement prepare = con.prepareStatement(
                        "delete from Relation where nomForme = ?");
                prepare.setString(1, object.getNom());
                prepare = con.prepareStatement(
                        "delete from Cercle where Nomcrl = ?");
                prepare.setString(1, object.getNom());
                prepare.executeUpdate();
                prepare = con.prepareStatement(
                        "delete from Forme where Nomf = ?");
                prepare.setString(1, object.getNom());
                prepare.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
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
