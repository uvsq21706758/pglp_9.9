package pglp_9.dessin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCcarreDAO extends DAO<Carre>{
    
	Connection con;
	
	public JDBCcarreDAO(Connection con) {
       this.con = con;
    }

	@Override
	public Carre create(Carre object) throws SQLException {
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
		
		return object;	
	}

	@Override
	public Carre find(String id) {
		Carre carre= null;
        try {
            PreparedStatement prepare = con.prepareStatement(
                    "SELECT * FROM Carre WHERE NomCr =" + id);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                Point point = new Point(
                        result.getInt("point_x"),
                        result.getInt("point_y"));
                try {
                	carre = new Carre(id,point,result.getInt("cote"));
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

	@Override
	public Carre update(Carre object) {
		Carre cercle = this.find(object.getNom());
	        if (cercle != null) {
	            try {
	                PreparedStatement prepare = con.prepareStatement(
	                "UPDATE Carre SET point_x = "+object.getPoint().getX()+", point_y ="+object.getPoint().getY()+", "
	                + "cote = "+object.getCote()+" WHERE NomCr = "+object.getNom());
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
	public void delete(Carre object) throws SQLException {
		Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Carre where NomCr=" + object.getNom());
            if(rs.next()) {
              stmt.executeUpdate("delete from Relation where nomForme ="+ object.getNom());
              stmt.executeUpdate("delete from Carre where NomCr="+ object.getNom());
              stmt.executeUpdate("delete from Forme where Nomf="+ object.getNom());
            	rs.close();
            stmt.close();
           	  System.out.printf("Ligne supprimée \n");
           
            }else {
            	System.out.println("suppression impossible,identifiant introuvable!");
            }
	}

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

}
