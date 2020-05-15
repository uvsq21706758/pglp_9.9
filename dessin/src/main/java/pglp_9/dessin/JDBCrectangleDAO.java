package pglp_9.dessin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCrectangleDAO extends DAO<Rectangle>{

	Connection con;
	
	public JDBCrectangleDAO() {
		 this.con = BDcreation.getConnect();
    }

	@Override
	public Rectangle create(Rectangle object) throws SQLException {
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
		prepare.setInt(4, object.getLargeur());
		prepare.setInt(5, object.getLongueur());
		prepare.executeUpdate();
		
		return object;	
	}

	@Override
	public Rectangle find(String id) {
		Rectangle rectangle= null;
        try {
            PreparedStatement prepare = con.prepareStatement(
                    "SELECT * FROM Rectangle WHERE NomRc = ?");
            prepare.setString(1, id);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                Point point = new Point(
                        result.getInt("point_x"),
                        result.getInt("point_y"));
                try {
                	rectangle = new Rectangle(id,point,result.getInt("largeur"),result.getInt("longeur"));
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

	@Override
	public Rectangle update(Rectangle object) {
	         Rectangle rectangle = this.find(object.getNom());
	        if (rectangle != null) {
	            try {
	                PreparedStatement prepare = con.prepareStatement(
	                "UPDATE Rectangle SET point_x = ?, point_y = ?, "
	                + "largeur = ?, longeur = ? WHERE NomRc = ?");
	               
	        		prepare.setInt(1, object.getPoint().getX());
	        		prepare.setInt(2, object.getPoint().getY());
	        		prepare.setInt(3, object.getLargeur());
	        		prepare.setInt(4, object.getLongueur());
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

	@Override
	public void delete(Rectangle object) throws SQLException {
		  try {
          	PreparedStatement prepare = con.prepareStatement(
                      "delete from Relation where nomForme = ?");
              prepare.setString(1, object.getNom());
              prepare = con.prepareStatement(
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

}
