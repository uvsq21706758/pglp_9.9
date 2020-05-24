package pglp_9.dessin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 *classe pour la creation de la base de donnée ainsi que ses tables
 *
 */
public class FormeandRelation {
	
	
	/**
	 * @return la connection de bd
	 */
	   public static Connection getConnect() {
	        try {
	            return DriverManager.getConnection(
	                    "jdbc:derby:data;create=false");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	   
	    /**
	     * appel au methodes creation et supp des tables de la base
	     * @throws Exception sql error exception
	     */
	  public static void recuperetables() throws Exception{
			 Connection con = FormeandRelation.getConnect();
			 FormeandRelation.drop(con);
			 FormeandRelation.createtableForme(con);
			 FormeandRelation.CreatetableCercle(con);
			 FormeandRelation.CreatetableRectangle(con);
	         FormeandRelation.CreatetableTriangle(con);
	         FormeandRelation.CreatetableCarre(con);
	         FormeandRelation.createtableGroupe(con);
		     FormeandRelation.createrelation(con);
		    }
	  
	    /**
	     * creation de la base de donne
	     * @throws SQLException sql error exception
	     */
	    public static void creationbd()  {
	        try {
	            DriverManager.getConnection(
	                "jdbc:derby:data;create=true");
	        } catch (SQLException e) {
	           System.out.println("cette base de donnee, existe deja");
	        }
	    }
	    
	 /**
	  * creation de la table forme
	  * @param con attribut de connection
	  * @throws SQLException sql error exception
	  */
	 private static void createtableForme(final Connection con)
	      throws SQLException {
	          String table = "create table Forme ("
	                  + "Nomf varchar(30) primary key"
	                  + ")";
	          Statement stat = con.createStatement();
	          stat.execute(table);
	      
	 }
	 
	 /**
	  * creation de la table cercle
	 * @param con attribut de connection de bd
	  * @throws SQLException sql error exception
	  */
	 private static void CreatetableCercle(final Connection con)
			    throws SQLException {
	        String table = "CREATE TABLE Cercle("
					 + "Nomcrl varchar(30) primary key,"
		                + "centre_x int,"
		                + "centre_y int,"
		                + "rayon int,"
		                + "foreign key (Nomcrl) references Forme (Nomf)"
		           + ")";
	        Statement stat = con.createStatement();
	        stat.execute(table);
	    }
	 
	 /**
	  * creation de la table rectangle
	 * @param con attribut de connection de bd
	 * @throws SQLException sql error exception
	  */
	 private static void CreatetableRectangle(final Connection con)
			 throws SQLException {
	        String table ="CREATE TABLE Rectangle("
					 + "NomRc varchar(30) primary key,"
		                + "point_x int,"
		                + "point_y int,"
		                + "largeur int,"
		                + "longeur int,"
		                + "foreign key (NomRc) references Forme (Nomf)"
		              + ")";
	        Statement stat = con.createStatement();
	        stat.execute(table);
	    }
	 
	 /**
	  * creeation de la table triangle
	  * @param con attribut de connection
	  * @throws SQLException sql error exception
	  */
	 private static void CreatetableTriangle(final Connection con)
			  throws SQLException {
	        String table = "CREATE TABLE Triangle("
			           + "NomTr varchar(30) primary key,"
		                + "point1_x int,"
		                + "point1_y int,"
		                + "point2_x int,"
		                + "point2_y int,"
		                + "point3_x int,"
		                + "point3_y int,"
		                + "foreign key (NomTr) references Forme (Nomf)"
		           	+ ")";
	        Statement stat = con.createStatement();
	        stat.execute(table);
	    }
	 /**
	  * creation de la table carré
	  * @param con attribut de connection de bd
	  * @throws SQLException sql error exception
	  */
	 private static void CreatetableCarre(final Connection con)
			    throws SQLException {
	        String table ="CREATE TABLE Carre("
			           + "NomCr varchar(30) primary key,"
		                + "point_x int,"
		                + "point_y int,"
		                + "cote int,"
		                + "foreign key (NomCr) references Forme (Nomf)"
			+ ")";
	        Statement stat = con.createStatement();
	        stat.execute(table);
	    }
	
	 /**
	  * creation de la table groupe
	  * @param con attribut de connection de bd
	  * @throws SQLException sql error exception
	  */
	 private static void createtableGroupe(final Connection con)
			   throws SQLException {
	        String table ="CREATE TABLE Groupeforme ("
					+ "nomgr VARCHAR(30) PRIMARY KEY,"
					+ "foreign key (nomgr) references Forme (Nomf)"
					+ ")";
	        Statement stat = con.createStatement();
	        stat.execute(table);
	    }
	 private static void createrelation(final Connection connect)
		      throws SQLException {
	        String table = "create table Relation ("
	                + "nomGroupe varchar(30),"
	                + "nomForme varchar(30),"
	                + "primary key (nomGroupe, nomForme),"
	                + "foreign key (nomGroupe) references "
	                + "Groupeforme (nomgr),"
	                + "foreign key (nomForme) "
	                + "references Forme (Nomf)"
	                + ")";
	        Statement stat = connect.createStatement();
	        stat.execute(table);
	    }
	 
	 /**
	  * supprimer les tables 
	  * @param con attribut de connetion
	  */
	 private static void drop(final Connection con){
		 Statement statement =null;
		 try {
	            statement = con.createStatement();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		 try {
        	 statement.execute("drop table Relation");
         } catch (SQLException e) {
         }
         try {
        	 statement.execute("drop table Groupeforme");
         } catch (SQLException e) {
         }
         try {
        	 statement.execute("drop table Carre");
         } catch (SQLException e) {
         }
         try {
        	 statement.execute("drop table Cercle");
         } catch (SQLException e) {
         }
         try {
        	 statement.execute("drop table Rectangle");
         } catch (SQLException e) {
         }
         try {
        	 statement.execute("drop table Triangle");
         } catch (SQLException e) {
         }
         try {
        	 statement.execute("drop table Forme");
         } catch (SQLException e) {
         }
	 }
	}