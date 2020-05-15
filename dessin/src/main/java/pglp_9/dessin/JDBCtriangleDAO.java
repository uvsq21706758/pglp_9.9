package pglp_9.dessin;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCtriangleDAO extends DAO<Triangle>{
    
    Connection con;
	
	public JDBCtriangleDAO() {
		 this.con = FormeandRelation.getConnect();
    }
	 public void createtableTriangle() throws SQLException {
    	 DatabaseMetaData dbmd = con.getMetaData();
         ResultSet rs = dbmd.getTables(null, null,"Triangle".toUpperCase(), null);
         String createforme="CREATE TABLE Triangle("
		           + "NomTr varchar(30) primary key,"
	                + "point1_x int,"
	                + "point1_y int,"
	                + "point2_x int,"
	                + "point2_y int,"
	                + "point3_x int,"
	                + "point3_y int,"
	                + "foreign key (NomTr) references Forme (Nomf)"
	           	+ ")";
          Statement stmt = con.createStatement();
             if (!rs.next()) {
             	stmt.execute(createforme);
             	System.out.println("Table Triangle crée");
 	            rs.close();
 	            stmt.close();
             }	
	}
	 
	 public void droptableTriangle() {
		 Statement statement = null;
    	 try {
    		 statement = con.createStatement();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         try {
        	 statement.execute("drop table Triangle");
         } catch (SQLException e) {
         }
	 }
	 
	@Override
	public Triangle create(Triangle object) throws SQLException {
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
		
		return object;
	}

	@Override
	public Triangle find(String id) {
		Triangle triangle= null;
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
	                "UPDATE Triangle SET point1_x =?, point1_y =?, "
	                + "point2_x = ?, point2_y =?, point3_x =?,"
	                +"point3_y = ?WHERE NomTr = ?");
	                
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

	@Override
	public void delete(Triangle object) throws SQLException {
		   try {
           	PreparedStatement prepare = con.prepareStatement(
                       "delete from Relation where nomForme = ?");
               prepare.setString(1, object.getNom());
               prepare = con.prepareStatement(
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
