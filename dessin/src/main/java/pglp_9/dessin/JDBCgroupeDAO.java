package pglp_9.dessin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * classe DAo JDBC groupe
 *
 */
public class JDBCgroupeDAO extends DAO<GroupeForme> {
    
	/**
	 * attribut de connexion
	 */
	private final Connection con;


	/**
	 * constructeur 
	 */
	 public JDBCgroupeDAO(final Connection con) {
	        this.con = con;
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
            factory.close();
        } catch (SQLException e) {
            factory.close();
            return null;
        }
        return object;
	}

	/**
	 *trouvé un élément 
	 */
	@Override
	public GroupeForme find(String id) {
	        GroupeForme formegr= null;
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
	
	/**
	 *modifier un tuple dans la table groupe
	 */
	@Override
	public GroupeForme update(GroupeForme object) {
		ArrayList<Forme> grp = this.findRelation(
                object.getNom());
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
            factory.close();
        } else {
            return null;
        }
        return object;
	}

	/**
	 *supprimer un tuple du groupe
	 */
	@Override
	public void delete(GroupeForme object) {
	        try {
	            this.deleteRelation(object.getNom());
	            this.deleteAsso(object.getNom());
	            PreparedStatement prepare = con.prepareStatement(
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
		   ArrayList<GroupeForme> grp = new ArrayList<GroupeForme>();
	        try {
	            PreparedStatement prepare = con.prepareStatement(
	            		"SELECT nomgr FROM Groupeforme");
	            ResultSet result = prepare.executeQuery();
	            while (result.next()) {
	                grp.add(this.find(result.getString("nomgr")));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ArrayList<GroupeForme>();
	        }
	        return grp;
	    }
	
	/**
	 * insérer dans table relation
	 * @param nomGroupe nom du groupe contenant les formes
	 * @param nomForme nom du forme insérer au groupe
	 */
	private void createRelation(final String nomGroupe,final String nomForme) {
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
    private ArrayList<Forme> findRelation(final String id) {
	        ArrayList<Forme> rela = new ArrayList<Forme>();
	        DAOFactoryJDBC factory = new DAOFactoryJDBC();
	        try {
	            PreparedStatement prepare = con.prepareStatement(
	            		"SELECT nomForme FROM Relation WHERE nomGroupe = ?");
	            prepare.setString(1, id);
	            ResultSet result = prepare.executeQuery();
	            DAO<Cercle> daoCe = factory.getCercleDAO();
	            DAO<Carre> daoCa = factory.getCarreDAO();
	            DAO<Rectangle> daoR = factory.getRectangleDAO();
	            DAO<Triangle> daoT = factory.getTriangleDAO();
	            while (result.next()) {
	                Forme f = daoCe.find(result.getString("nomForme"));
	                if (f == null) {
	                    f = daoCa.find(result.getString("nomForme"));
	                }
	                if (f == null) {
	                    f = daoR.find(result.getString("nomForme"));
	                }
	                if (f == null) {
	                    f = daoT.find(result.getString("nomForme"));
	                }
	                if (f == null) {
	                    f = this.find(result.getString("nomForme"));
	                }
	                rela.add(f);
	            }
	            factory.close();
	        } catch (SQLException e) {
	            factory.close();
	            return new ArrayList<Forme>();
	        }
	        return rela;
		}
			
   /**
   * supprimer les relations d'un groupe avec formes 
   * @param id id du groupe
   */
	private void deleteRelation(final String id) {
	        try {
	            PreparedStatement prepare = con.prepareStatement(
	            		"delete from Relation where nomGroupe = ?");
	            prepare.setString(1, id);
	            prepare.executeUpdate();
	        } catch (SQLException e) {
	        }
	    }
		
	/**
	 * supprimer les assocs de la forme avec un groupe
	 * @param id identifiant de la forme
	 */
	private void deleteAsso(final String id) {
        try {
            PreparedStatement prepare = con.prepareStatement(
            		"delete from Relation where nomForme = ?");
            prepare.setString(1, id);
            prepare.executeUpdate();
        } catch (SQLException e) {
        }
    }
		
	}


