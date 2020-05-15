package pglp_9.dessin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCtriangleDAO extends DAO<Triangle>{
    
    Connection con;
	
	public JDBCtriangleDAO(Connection con) {
       this.con = con;
    }

	@Override
	public Triangle create(Triangle object) throws SQLException {
		PreparedStatement prepare = con.prepareStatement(
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
		
		return object;
	}

	@Override
	public Triangle find(String id) {
		Triangle triangle= null;
        try {
            PreparedStatement prepare = con.prepareStatement(
                    "SELECT * FROM Triangle WHERE NomTr =" + id);
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
                try {
                	triangle = new Triangle(id,p1,p2,p3);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return triangle;
	}

	@Override
	public Triangle update(Triangle object) {
		Triangle triangle = this.find(object.getNom());
	        if (triangle != null) {
	            try {
	                PreparedStatement prepare = con.prepareStatement(
	                "UPDATE Triangle SET point1_x ="+object.getPoint_1().getX()+", point1_y = "+object.getPoint_1().getY()+", "
	                + "point2_x = "+object.getPoint_2().getX()+", point2_y ="+object.getPoint_2().getY()+", point3_x ="+object.getPoint_3().getX()+","
	                +"point3_y = "+object.getPoint_3().getY()+ " WHERE NomTr = "+object.getNom());
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

	@Override
	public void delete(Triangle object) throws SQLException {
		Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Triangle where NomTr=" + object.getNom());
            if(rs.next()) {
              stmt.executeUpdate("delete from Triangle where NomTr="+ object.getNom());
            	rs.close();
            stmt.close();
           	  System.out.printf("Ligne supprimée \n");
           
            }else {
            	System.out.println("suppression impossible,identifiant introuvable!");
            }
	}

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

}
