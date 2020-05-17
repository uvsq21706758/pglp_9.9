package pglp_9.dessin;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * classe contient creation et suppression des tables forme et relation
 *
 */
public class FormeandRelation {
	
	/**
	 * attribut driver
	 */
	String driver ="org.apache.derby.jdbc.EmbeddedDriver";
	
	/**
	 * attribut dburl
	 */
	String dburl = "jdbc:derby:data;create=true";
	
	/**
	 * attribut de connexion
	 */
	static Connection con;
	
	/**
	 * constructeur
	 * @throws SQLException sql error exception
	 */
	public FormeandRelation() throws SQLException {
		  con=DriverManager.getConnection(dburl);
	}
	
	/**
	 * @return la connexion de la base de donnée
	 */
	public static  Connection getConnect() {
        return con;
    }
	
	/**
	  * creation de la table forme
	 * @throws SQLException exception sql error
	 */
	public void createtableForme() throws SQLException {
 
        DatabaseMetaData dbmd = getConnect().getMetaData();
        ResultSet rs = dbmd.getTables(null, null,"Forme".toUpperCase(), null);
        String createforme="create table Forme ("
                + "Nomf varchar(30) primary key"
                + ")";
         Statement stmt = getConnect().createStatement();
            if (!rs.next()) {
            	stmt.execute(createforme);
            	System.out.println("Table Forme crée");
	            rs.close();
	            stmt.close();
            }	
	}
    
	/**
	  * creation de la table relation
	 * @throws SQLException exception sql error
	 */
    public void createrelation() throws SQLException {
    	DatabaseMetaData dbmd = getConnect().getMetaData();
        ResultSet rs = dbmd.getTables(null, null,"Relation".toUpperCase(), null);
        String createforme="create table Relation ("
                + "nomGroupe varchar(30),"
                + "nomForme varchar(30),"
                + "primary key (nomGroupe, nomForme),"
                + "foreign key (nomGroupe) references "
                + "Groupeforme (nomgr),"
                + "foreign key (nomForme) "
                + "references Forme (Nomf)"
                + ")";
         Statement stmt = getConnect().createStatement();
            if (!rs.next()) {
            	stmt.execute(createforme);
            	System.out.println("Table Relation crée");
	            rs.close();
	            stmt.close();
            }	
    }
    
    /**
	 * supprimer table relation
	 */
    public void dropRelation() {
    	Statement statement = null;
    	 try {
    		 statement = getConnect().createStatement();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         try {
        	 statement.execute("drop table Relation");
         } catch (SQLException e) {
         }
    }
	
    /**
	 * supprimer table forme
	 */
    public void dropForme() {
    	Statement statement = null;
    	 try {
    		 statement = getConnect().createStatement();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         try {
        	 statement.execute("drop table Forme");
         } catch (SQLException e) {
         }
    }
}
