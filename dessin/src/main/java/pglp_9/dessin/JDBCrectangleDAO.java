package pglp_9.dessin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * classe DAo JDBC rectangle
 *
 */
public class JDBCrectangleDAO extends DAO<Rectangle> {

	/**
	 * attribut de connexion
	 */
	private final Connection con;

	/**
	 * constructeur 
	 */
	public JDBCrectangleDAO(Connection con) {
		this.con =con;
	}
	
	/**
	 *insérer dans la table rectangle
	 */
	@Override
	public Rectangle create(final Rectangle object) {
        try {
            PreparedStatement prepare = con.prepareStatement(
            		"INSERT INTO Forme (Nomf)"
                            + " VALUES(?)");
                    prepare.setString(1, object.getNom());
                    prepare.executeUpdate();
            prepare = con.prepareStatement(
            		"INSERT  INTO Rectangle (NomRc, point_x, point_y, largeur, longeur)" +
    				"VALUES (?, ?, ?, ?, ?)");
            prepare.setString(1, object.getNom());
            prepare.setInt(2, object.getPoint().getX());
            prepare.setInt(3, object.getPoint().getY());
            prepare.setInt(4, object.getLongueur());
            prepare.setInt(5, object.getLargeur());
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
	public Rectangle find(String id) {
        Rectangle rectangle = null;
        try {
            PreparedStatement prepare = con.prepareStatement(
            		"SELECT * FROM Rectangle WHERE NomRc = ?");
            prepare.setString(1, id);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                Point p = new Point(
                		 result.getInt("point_x"),
                         result.getInt("point_y")
                );
                try {
                	rectangle = new Rectangle(id, p,
                            result.getInt("longeur"),
                            result.getInt("largeur")
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return rectangle;
	}

	/**
	 *modifier un tuple dans la table rectangle
	 */
	@Override
	public Rectangle update(Rectangle object) {
	        final Rectangle rectangle = this.find(object.getNom());
	        if (rectangle != null) {
	            try {
	                PreparedStatement prepare = con.prepareStatement(
	                		"UPDATE Rectangle SET point_x = ?, point_y = ?, "
	            	                + "largeur = ?, longeur = ? WHERE NomRc = ?");
	                prepare.setInt(1, object.getPoint().getX());
	                prepare.setInt(2, object.getPoint().getY());
	                prepare.setInt(3, object.getLongueur());
	                prepare.setInt(4, object.getLargeur());
	                prepare.setString(5, object.getNom());
	                prepare.executeUpdate();
	            } catch (SQLException e) {
	                e.printStackTrace();
	                return rectangle;
	            }
	        } else {
	            return null;
	        }
	        return object;
	}

	/**
	 *supprimer un tuple du rectangle
	 */
	@Override
	public void delete(Rectangle object) {
        try {
            this.deleteAssociation(object.getNom());
            PreparedStatement prepare = con.prepareStatement(
            		"delete from Rectangle where NomRc= ?");
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
	 *recupere toute la table rectangle
	 */
		@Override
		public ArrayList<Rectangle> show() {
			ArrayList<Rectangle> rectangle = new ArrayList<Rectangle>();
	        try {
	            PreparedStatement prepare = con.prepareStatement(
	            		"SELECT NomRc FROM Rectangle");
	            ResultSet result = prepare.executeQuery();
	            while (result.next()) {
	            	rectangle.add(this.find(result.getString("NomRc")));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ArrayList<Rectangle>();
	        }
	        return rectangle;
		}
	/**
	 * supprimer les assocs de la forme avec un groupe
	 * @param ididentifiant de la forme
	 */
	private void deleteAssociation(final String id) {
	        try {
	            PreparedStatement prepare = con.prepareStatement(
	            		"delete from Relation where nomForme = ?");
	            prepare.setString(1, id);
	            prepare.executeUpdate();
	        } catch (SQLException e) {
	        }
		
	}

	}


