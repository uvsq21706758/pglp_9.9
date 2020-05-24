package pglp_9.dessin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *classe DAO JDBC carre
 *
 */
public class JDBCcarreDAO extends DAO<Carre> {
	
	/**
	 * attribut de connexion
	 *
	 */
     private final Connection con;
     
    /**
	 * constructeur 
	 *@param con attibut de connection
	 */
	public JDBCcarreDAO(final Connection con) {
		 this.con =con;
    }

	
	/**
	 *insérer dans la table carré
	 */
	@Override
	public Carre create(final Carre object) {
		        try {
		            PreparedStatement prepare = con.prepareStatement(
		            		"INSERT INTO Forme (Nomf)"
		                            + " VALUES(?)");
		                    prepare.setString(1, object.getNom());
		                    prepare.executeUpdate();
		            prepare = con.prepareStatement(
		            		"INSERT  INTO Carre (NomCr, point_x, point_y, cote)" +
		    				"VALUES (?, ?, ?, ?)");
		            prepare.setString(1, object.getNom());
		            prepare.setInt(2, object.getPoint().getX());
		            prepare.setInt(3, object.getPoint().getY());
		            prepare.setInt(4, object.getCote());
		            prepare.executeUpdate();
		        } catch (SQLException e) {
		            return null;
		        }
		        return object;
		
	}

	/**
	 *trouvé un élément 
	 */
	@Override
	public Carre find(final String id) {
	        Carre carre = null;
	        try {
	            PreparedStatement prepare = con.prepareStatement(
	            		"SELECT * FROM Carre WHERE NomCr =?" );
	            prepare.setString(1, id);
	            ResultSet result = prepare.executeQuery();
	            if (result.next()) {
	                Point p = new Point(result.getInt("point_x"),
	                        result.getInt("point_y"));
	                try {
	                	carre = new Carre(id, p, result.getInt("cote"));
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    return null;
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	        return carre;
	}
	 
	/**
	 *modifier un tuple dans la table carré
	 */
	@Override
	public Carre update(final Carre object) {
	        final Carre carre = this.find(object.getNom());
	        if (carre != null) {
	            try {
	                PreparedStatement prepare = con.prepareStatement(
	                		"UPDATE Carre SET point_x = ?, point_y =?, "
	            	                + "cote =? WHERE NomCr = ? ");
	                prepare.setInt(1, object.getPoint().getX());
	                prepare.setInt(2, object.getPoint().getY());
	                prepare.setInt(3, object.getCote());
	                prepare.setString(4, object.getNom());
	                prepare.executeUpdate();
	            } catch (SQLException e) {
	                e.printStackTrace();
	                return carre;
	            }
	        } else {
	            return null;
	        }
	        return object;
		}

	/**
	 *supprimer un tuple du carré
	 */
	 @Override
	 public void delete(final Carre object) {
		        try {
		            this.deleteComposant(object.getNom());
		            PreparedStatement prepare = con.prepareStatement(
		            		"delete from Carre where NomCr = ?");
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
    *recupere toute la table carré
    */
	@Override
	public ArrayList<Carre> show() {
		ArrayList<Carre> carre = new ArrayList<Carre>();
        try {
            PreparedStatement prepare = con.prepareStatement(
            		"SELECT NomCr FROM Carre");
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
            	carre.add(this.find(result.getString("NomCr")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Carre>();
        }
        return carre;
	}

   /**
    * supprimer les assocs de la forme avec un groupe
    * @param id identifiant de la forme
    */
	private void deleteComposant(final String id) {
	        try {
	            PreparedStatement prepare = con.prepareStatement(
	            		"delete from Relation where nomForme = ?");
	            prepare.setString(1, id);
	            prepare.executeUpdate();
	        } catch (SQLException e) {
	        }
		
	}
		
	}

	


