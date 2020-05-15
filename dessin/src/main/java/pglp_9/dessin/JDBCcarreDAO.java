package pglp_9.dessin;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCcarreDAO extends DAO<Carre>{
    
	Connection con;
	
	public JDBCcarreDAO() {
		 this.con = FormeandRelation.getConnect();
    }
	
	 public void createtableCarre() throws SQLException {
    	 DatabaseMetaData dbmd = con.getMetaData();
         ResultSet rs = dbmd.getTables(null, null,"Carre".toUpperCase(), null);
         String createforme="CREATE TABLE Carre("
		           + "NomCr varchar(30) primary key,"
	                + "point_x int,"
	                + "point_y int,"
	                + "cote int,"
	                + "foreign key (NomCr) references Forme (Nomf)"
		+ ")";
          Statement stmt = con.createStatement();
             if (!rs.next()) {
             	stmt.execute(createforme);
             	System.out.println("Table Carre crée");
 	            rs.close();
 	            stmt.close();
             }	
	}
	 
	 public void droptableCarre() {
		 Statement statement = null;
    	 try {
    		 statement = con.createStatement();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         try {
        	 statement.execute("drop table Carre");
         } catch (SQLException e) {
         }
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
                    "SELECT * FROM Carre WHERE NomCr =?" );
            prepare.setString(1, id);
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
	                "UPDATE Carre SET point_x = ?, point_y =?, "
	                + "cote =? WHERE NomCr = ? ");
	                prepare.setInt(1, object.getPoint().getX());
	        		prepare.setInt(2, object.getPoint().getY());
	        		prepare.setInt(3, object.getCote());
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
	public void delete(Carre object) throws SQLException {
            try {
            	PreparedStatement prepare = con.prepareStatement(
                        "delete from Relation where nomForme = ?");
                prepare.setString(1, object.getNom());
                prepare = con.prepareStatement(
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
