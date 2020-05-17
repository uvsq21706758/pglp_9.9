package pglp_9.dessin;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *classe DAO JDBC cercle
 *
 */
public class JDBCcercleDAO extends DAO<Cercle>{
	
	/**
	 * attribut de connexion
	 */
	Connection con;
	
	/**
	 * constructeur 
	 */
	public JDBCcercleDAO() {
       this.con = FormeandRelation.getConnect();
    }
	
	 /**
	 * creation de la table cercle
	 * @throws SQLException exception sql error
	 */
	public void createtableCercle() throws SQLException {
	    	 DatabaseMetaData dbmd = con.getMetaData();
	         ResultSet rs = dbmd.getTables(null, null,"Cercle".toUpperCase(), null);
	         String createforme="CREATE TABLE Cercle("
					 + "Nomcrl varchar(30) primary key,"
		                + "centre_x int,"
		                + "centre_y int,"
		                + "rayon int,"
		                + "foreign key (Nomcrl) references Forme (Nomf)"
		           + ")";
	          Statement stmt =con.createStatement();
	             if (!rs.next()) {
	             	stmt.execute(createforme);
	             	System.out.println("Table Cercle crée");
	 	            rs.close();
	 	            stmt.close();
	             }	
			
		}
	  
	 /**
	  * supprimer table cercle
	  */
	public void droptableCercle() {
		  Statement statement = null;
	    	 try {
	    		 statement = con.createStatement();
	         } catch (SQLException e) {
	             e.printStackTrace();
	         }
		  try {
	        	 statement.execute("drop table Cercle");
	         } catch (SQLException e) {
	         }
	  }

	/**
	 *insérer dans la table cercle
	 */
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

	/**
	 *trouvé un élément 
	 */
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

	/**
	 *modifier un tuple dans la table cercle
	 */
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

	/**
	 *supprimer un tuple du cercle
	 */
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

	/**
	 *recupere toute la table cercle
	 */
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
