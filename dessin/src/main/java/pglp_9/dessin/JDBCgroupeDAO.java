package pglp_9.dessin;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * classe DAo JDBC groupe
 *
 */
public class JDBCgroupeDAO extends DAO<GroupeForme>{
     
	/**
	 * attribut de connexion
	 */
	Connection con;
	
	/**
	 * constructeur 
	 */
	public JDBCgroupeDAO() {
		 this.con = FormeandRelation.getConnect();
    }
	
	/**
	  * creation de la table groupe
	 * @throws SQLException exception sql error
	 */
	public void createtableGroupe() throws SQLException {
    	 DatabaseMetaData dbmd = con.getMetaData();
         ResultSet rs = dbmd.getTables(null, null,"Groupeforme".toUpperCase(), null);
         String createforme="CREATE TABLE Groupeforme ("
					+ "nomgr VARCHAR(30) PRIMARY KEY,"
					+ "foreign key (nomgr) references Forme (Nomf)"
					+ ")";
          Statement stmt = con.createStatement();
             if (!rs.next()) {
             	stmt.execute(createforme);
             	System.out.println("Table Groupeforme crée");
 	            rs.close();
 	            stmt.close();
             }	
    }
	 
	/**
	 * supprimer table groupe
	 */
	public void droptableGroupe() {
		 Statement statement = null;
    	 try {
    		 statement = con.createStatement();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         try {
        	 statement.execute("drop table Groupeforme");
         } catch (SQLException e) {
         }
	 }
	 
	/**
	 *insérer dans la table groupe
	 */
	@Override
	public GroupeForme create(GroupeForme object) {
		DAOFactoryJDBC factory = new DAOFactoryJDBC();
	        try {
	        	PreparedStatement prepare = con.prepareStatement(
	                    "INSERT INTO Forme (Nomf)"
	                    + " VALUES(?)");
	                    prepare.setString(1, object.getNom());
	                    prepare.executeUpdate();
	            prepare = con.prepareStatement(
	                    "insert into Groupeforme (nomgr) VALUES(?)");
	            prepare.setString(1, object.getNom());
	            prepare.executeUpdate();
	            Iterator<Forme> iterator = object.iterator();
	            while (iterator.hasNext()) {
	          	  Forme forme  = iterator.next();
	                if (forme instanceof Cercle) {
	                    DAO<Cercle> cercle = factory.getCercleDAO();
	                    cercle.create((Cercle) forme);
	                } else if (forme instanceof Rectangle) {
	                    DAO<Rectangle> rectangle = factory.getRectangleDAO();
	                    rectangle.create((Rectangle) forme);
	                } else if (forme instanceof Triangle) {
	                    DAO<Triangle> triangle = factory.getTriangleDAO();
	                    triangle.create((Triangle) forme);
	                }else if (forme instanceof Carre) {
	                    DAO<Carre> carre = factory.getCarreDAO();
	                    carre.create((Carre) forme);
	                }else {
	                    this.create((GroupeForme) forme);
	                }
	                this.createRelation(object.getNom(), forme.getNom());
	            }
	           
	        } catch (SQLException e) {
	            
	            return null;
	        }
	        return object;
	}

	/**
	 *trouvé un élément 
	 */
	@Override
	public GroupeForme find(String id) {
		GroupeForme formegr = null;
        try {
            PreparedStatement prepare = con.prepareStatement(
                    "SELECT * FROM Groupeforme WHERE nomgr = ?");
            prepare.setString(1, id);
            ResultSet rs = prepare.executeQuery();
            if (rs.next()) {
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

	/**
	 *modifier un tuple dans la table groupe
	 */
	@Override
	public GroupeForme update(GroupeForme object) throws SQLException {
		  ArrayList<Forme> grp = this.findRelation(object.getNom());
	        if (!grp.isEmpty()) {
	            this.deleteRelation(object.getNom());
	            DAOFactoryJDBC factory = new DAOFactoryJDBC();
	            Iterator<Forme>  iterator = object.iterator();
	            while (iterator.hasNext()) {
	              	Forme forme = iterator.next();
	                if (forme instanceof Cercle) {
	                    DAO<Cercle> cercle = factory.getCercleDAO();
	                    cercle.update((Cercle) forme);
	                }else if (forme instanceof Rectangle) {
	                    DAO<Rectangle> rectangle = factory.getRectangleDAO();
	                    rectangle.update((Rectangle) forme);
	                } else if (forme instanceof Triangle) {
	                    DAO<Triangle> triangle = factory.getTriangleDAO();
	                    triangle.update((Triangle) forme);
	                }  else if (forme instanceof Carre) {
	                    DAO<Carre> carre = factory.getCarreDAO();
	                    carre.update((Carre) forme);
	                } else {
	                    this.update((GroupeForme) forme);
	                }
	                this.createRelation( object.getNom(),forme.getNom());
	            }
	            
	        } else {
	            return null;
	        }
	        return object;
	}

	/**
	 *supprimer un tuple du groupe
	 */
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

	/**
	 *recupere toute la table groupe
	 */
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
	/**
	 * insérer dans table relation
	 * @param nomGroupe nom du groupe contenant les formes
	 * @param nomForme nom du forme insérer au groupe
	 */
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
	
      /**
       * trouvé les relations d'un groupe avec formes 
     * @param id id du groupe
     * @return liste des composants d'un groupe
     */
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
	                	forme = rectangle.find(result.getString("nomForme"));
	                }
	                if (forme == null) {
	                	forme = triangle.find(result.getString("nomForme"));
	                } 
	                if (forme == null) {
	                	forme = carre.find(result.getString("nomForme"));
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
    
      /**
       * supprimer les relations d'un groupe avec formes 
     * @param id id du groupe
     */
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
