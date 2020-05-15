package pglp_9.dessin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCgroupeDAO extends DAO<GroupeForme>{
     
	Connection con;
	
	public JDBCgroupeDAO() {
		 this.con = BDcreation.getConnect();
    }
	@Override
	public GroupeForme create(GroupeForme object) throws SQLException {
		DAOFactoryJDBC factory = new DAOFactoryJDBC();
	        try {
	            PreparedStatement prepare = con.prepareStatement(
	                    "INSERT INTO Forme (Nomf) VALUES(?)");
	            prepare.setString(1, object.getNom());
	            prepare.executeUpdate();
	            prepare = con.prepareStatement(
	                    "INSERT INTO Groupeforme (nomgr) VALUES(?)");
	            prepare.setString(1, object.getNom());
	            prepare.executeUpdate();
	            for (Forme forme : object.getList()) {
	                if (forme.getClass() == Cercle.class) {
	                    DAO<Cercle> dao = factory.getCercleDAO();
	                    dao.create((Cercle) forme);
	                } else if (forme.getClass() == Carre.class) {
	                    DAO<Carre> dao = factory.getCarreDAO();
	                    dao.create((Carre) forme);
	                } else if (forme.getClass() == Rectangle.class) {
	                    DAO<Rectangle> dao = factory.getRectangleDAO();
	                    dao.create((Rectangle) forme);
	                } else if (forme.getClass() == Triangle.class) {
	                    DAO<Triangle> dao = factory.getTriangleDAO();
	                    dao.create((Triangle) forme);
	                } else {
	                    this.create((GroupeForme) forme);
	                }
	                this.createRelation(object.getNom(), forme.getNom());
	            }
	           
	        } catch (SQLException e) {
	            
	            return null;
	        }
	        return object;
	}

	@Override
	public GroupeForme find(String id) throws SQLException {
		GroupeForme formegr = null;
        try {
            PreparedStatement prepare = con.prepareStatement(
                    "SELECT * FROM Groupeforme WHERE nomgr = ?");
            prepare.setString(1, id);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
            	formegr = new GroupeForme(id);
                ArrayList<Forme> list = findRelation(id);
                for (Forme forme : list) {
                	formegr.ajoutForme(forme);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return formegr;
	}

	@Override
	public GroupeForme update(GroupeForme object) throws SQLException {
		  ArrayList<Forme> grp = this.findRelation(object.getNom());
	        if (!grp.isEmpty()) {
	            this.deleteRelation(object.getNom());
	            DAOFactoryJDBC factory = new DAOFactoryJDBC();
	            for (Forme forme : object.getList()) {
	                if (forme.getClass() == Cercle.class) {
	                    DAO<Cercle> dao = factory.getCercleDAO();
	                    dao.create((Cercle) forme);
	                } else if (forme.getClass() == Carre.class) {
	                    DAO<Carre> dao = factory.getCarreDAO();
	                    dao.create((Carre) forme);
	                } else if (forme.getClass() == Rectangle.class) {
	                    DAO<Rectangle> dao = factory.getRectangleDAO();
	                    dao.create((Rectangle) forme);
	                } else if (forme.getClass() == Triangle.class) {
	                    DAO<Triangle> dao = factory.getTriangleDAO();
	                    dao.create((Triangle) forme);
	                } else {
	                    this.create((GroupeForme) forme);
	                }
	                this.createRelation( object.getNom(),forme.getNom());
	            }
	            
	        } else {
	            return null;
	        }
	        return object;
	}

	@Override
	public void delete(GroupeForme object) throws SQLException {
            try {
            	deleteRelation(object.getNom());
            	PreparedStatement prepare = con.prepareStatement(
                        "delete from Relation where nomForme = ?");
                prepare.setString(1, object.getNom());
                prepare = con.prepareStatement(
                        "delete from Groupeforme where nomgr= ?");
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
	public ArrayList<GroupeForme> show() {
		ArrayList<GroupeForme> groupe = new ArrayList<GroupeForme>();
        try {
            PreparedStatement prepare = con.prepareStatement(
                    "SELECT nomgr FROM Groupeforme");
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
            	groupe.add(this.find(result.getString("nomgr")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<GroupeForme>();
        }
        return groupe;
	}
	public void createRelation(String nomGroupe,String nomForme) {
        try {
            PreparedStatement prepare = con.prepareStatement(
            "INSERT INTO Relation"
            + " (nomGroupe, nomForme)"
            + " VALUES(?, ?)");
            prepare.setString(1, nomGroupe);
            prepare.setString(2, nomForme);
            prepare.executeUpdate();
        } catch (SQLException e) {
        }
    }
	
      public ArrayList<Forme> findRelation(final String id) {
	        ArrayList<Forme> rela = new ArrayList<Forme>();
	        DAOFactoryJDBC factory = new DAOFactoryJDBC();
	        try {
	            PreparedStatement prepare = con.prepareStatement(
	                    "SELECT nomForme FROM Relation WHERE nomGroupe = ?");
	            prepare.setString(1, id);
	            ResultSet result = prepare.executeQuery();
	            DAO<Rectangle> rectangle = factory.getRectangleDAO();
	            DAO<Triangle> triangle = factory.getTriangleDAO();
	            DAO<Cercle> cercle = factory.getCercleDAO();
	            DAO<Carre> carre = factory.getCarreDAO();
	            while (result.next()) {
	                Forme forme = cercle.find(result.getString("nomForme"));
	                if (forme == null) {
	                	forme = carre.find(result.getString("nomForme"));
	                }
	                if (forme == null) {
	                	forme = rectangle.find(result.getString("nomForme"));
	                }
	                if (forme == null) {
	                	forme = triangle.find(result.getString("nomForme"));
	                }
	                if (forme == null) {
	                	forme = this.find(result.getString("nomForme"));
	                }
	                rela.add(forme);

	            }
	           
	        } catch (SQLException e) {
	        
	            return new ArrayList<Forme>();
	        }
	        return rela;
	    }
      public void deleteRelation(final String id) {
          final int un = 1;
          try {
              PreparedStatement prepare = con.prepareStatement(
                      "delete from Relation where nomGroupe = ?");
              prepare.setString(un, id);
              prepare.executeUpdate();
          } catch (SQLException e) {
          }
      }

} 
