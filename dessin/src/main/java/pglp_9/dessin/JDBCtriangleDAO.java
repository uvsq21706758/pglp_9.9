package pglp_9.dessin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * classe DAo JDBC triangle
 *
 */
public class JDBCtriangleDAO extends DAO<Triangle> {

    
	/**
	 * attribut de connexion
	 */
	private final Connection con;

	/**
	 * constructeur 
	 */
	public JDBCtriangleDAO(final Connection con) {
		this.con= con;
	}

	/**
	 *insérer dans la table triangle
	 */
	@Override
	public Triangle create(Triangle object) {
        try {
            PreparedStatement prepare = con.prepareStatement(
            		"INSERT INTO Forme (Nomf)"
                            + " VALUES(?)");
                    prepare.setString(1, object.getNom());
                    prepare.executeUpdate();
            prepare = con.prepareStatement(
            		"INSERT  INTO Triangle (NomTr, point1_x, point1_y, point2_x, point2_y,point3_x, point3_y)" +
    				"VALUES (?, ?, ?, ?, ?, ?, ?)");
            prepare.setString(1, object.getNom());
            prepare.setInt(2, object.getPoint_1().getX());
            prepare.setInt(3, object.getPoint_1().getY());
            prepare.setInt(4, object.getPoint_2().getX());
            prepare.setInt(5, object.getPoint_2().getY());
            prepare.setInt(6, object.getPoint_3().getX());
            prepare.setInt(7, object.getPoint_3().getY());
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
	public Triangle find(String id) {
	        Triangle triangle = null;
	        try {
	            PreparedStatement prepare = con.prepareStatement(
	            		"SELECT * FROM Triangle WHERE NomTr = ? ");
	            prepare.setString(1, id);
	            ResultSet result = prepare.executeQuery();
	            if (result.next()) {
	            	 Point p1 = new Point(
	                         result.getInt("point1_x"),
	                         result.getInt("point1_y"));
	                 Point p2 = new Point(
	                         result.getInt("point2_x"),
	                         result.getInt("point2_y"));
	                 Point p3 = new Point(
	                         result.getInt("point3_x"),
	                         result.getInt("point3_y"));
	                 triangle = new Triangle(id, p1,p2,p3);
	            }
	        } catch (SQLException e) {
	            return null;
	        }
	        return triangle;
	}

	/**
	 *modifier un tuple dans la table triangle
	 */
	@Override
	public Triangle update(final Triangle object) {
        final Triangle triangle = this.find(object.getNom());
        if (triangle != null) {
            try {
                PreparedStatement prepare = con.prepareStatement(
                		"UPDATE Triangle SET point1_x =?, point1_y =?, "
            	                + "point2_x = ?, point2_y =?, point3_x =?,"
            	                +"point3_y = ? WHERE NomTr = ?");
                prepare.setInt(1, object.getPoint_1().getX());
                prepare.setInt(2, object.getPoint_1().getY());
                prepare.setInt(3, object.getPoint_2().getX());
                prepare.setInt(4, object.getPoint_2().getY());
                prepare.setInt(5, object.getPoint_3().getX());
                prepare.setInt(6, object.getPoint_3().getY());
                prepare.setString(7, object.getNom());
                prepare.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return triangle;
            }
        } else {
            return null;
        }
        return object;
	}

    
	/**
	 *supprimer un tuple du triangle
	 */
	@Override
	public void delete(Triangle object) {
	        try {
	            this.deleteAssociation(object.getNom());
	            PreparedStatement prepare = con.prepareStatement(
	            		"delete from Triangle where NomTr = ?");
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
	    *recupere toute la table triangle
	    */
		@Override
		public ArrayList<Triangle> show() {
			
			ArrayList<Triangle> triangle = new ArrayList<Triangle>();
	        try {
	            PreparedStatement prepare = con.prepareStatement(
	            		"SELECT NomTr FROM Triangle");
	            ResultSet result = prepare.executeQuery();
	            while (result.next()) {
	            	triangle.add(this.find(result.getString("NomTr")));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ArrayList<Triangle>();
	        }
	        return triangle;
		}
		
	/**
	 * supprimer les assocs de la forme avec un groupe
	 * @param id identifiant de la forme
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


