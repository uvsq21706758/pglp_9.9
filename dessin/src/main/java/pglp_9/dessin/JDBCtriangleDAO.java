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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Triangle update(Triangle object) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

}
