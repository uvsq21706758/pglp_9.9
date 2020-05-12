package pglp_9.dessin;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDcreation {
	
	String driver ="org.apache.derby.jdbc.EmbeddedDriver";
	String dburl = "jdbc:derby:data;create=true";
	
	Connection con;
	
	public BDcreation() throws SQLException {
		  con=DriverManager.getConnection(dburl);
	}
	
	public Connection getConnect() {
        return con;
    }
	
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
	
	
    public void createtableCercle() throws SQLException {
    	 DatabaseMetaData dbmd = getConnect().getMetaData();
         ResultSet rs = dbmd.getTables(null, null,"Cercle".toUpperCase(), null);
         String createforme="CREATE TABLE Cercle("
				 + "Nomcr varchar(30) primary key,"
	                + "centre_x int,"
	                + "centre_y int,"
	                + "rayon int"
	           + ")";
          Statement stmt = getConnect().createStatement();
             if (!rs.next()) {
             	stmt.execute(createforme);
             	System.out.println("Table Cercle crée");
 	            rs.close();
 	            stmt.close();
             }	
		
	}
   
    public void createtableRectangle() throws SQLException {
    	 DatabaseMetaData dbmd = getConnect().getMetaData();
         ResultSet rs = dbmd.getTables(null, null,"Rectangle".toUpperCase(), null);
         String createforme="CREATE TABLE Rectangle("
				 + "NomRc varchar(30) primary key,"
	                + "point_x int,"
	                + "point_y int,"
	                + "largeurint,"
	                + "longeur int"
	              + ")";
          Statement stmt = getConnect().createStatement();
             if (!rs.next()) {
             	stmt.execute(createforme);
             	System.out.println("Table Rectangle crée");
 	            rs.close();
 	            stmt.close();
             }	
	}
    
    public void createtableTriangle() throws SQLException {
    	 DatabaseMetaData dbmd = getConnect().getMetaData();
         ResultSet rs = dbmd.getTables(null, null,"Triangle".toUpperCase(), null);
         String createforme="CREATE TABLE Triangle("
		           + "NomTr varchar(30) primary key,"
	                + "point1_x int,"
	                + "point1_y int,"
	                + "point2_x int,"
	                + "point2_y int,"
	                + "point3_x int,"
	                + "point3_y int"
	           	+ ")";
          Statement stmt = getConnect().createStatement();
             if (!rs.next()) {
             	stmt.execute(createforme);
             	System.out.println("Table Triangle crée");
 	            rs.close();
 	            stmt.close();
             }	
	}
    
    public void createtableCarre() throws SQLException {
    	 DatabaseMetaData dbmd = getConnect().getMetaData();
         ResultSet rs = dbmd.getTables(null, null,"Carre".toUpperCase(), null);
         String createforme="CREATE TABLE Carre("
		           + "NomCr varchar(30) primary key,"
	                + "point_x int,"
	                + "point_y int,"
	                + "cote int"
		+ ")";
          Statement stmt = getConnect().createStatement();
             if (!rs.next()) {
             	stmt.execute(createforme);
             	System.out.println("Table Carre crée");
 	            rs.close();
 	            stmt.close();
             }	
	}
    
    public void createtableGroupe() throws SQLException {
    	 DatabaseMetaData dbmd = getConnect().getMetaData();
         ResultSet rs = dbmd.getTables(null, null,"Groupeforme".toUpperCase(), null);
         String createforme="CREATE TABLE Groupeforme ("
					+ "nomgr VARCHAR(30) PRIMARY KEY"
					+ ")";
          Statement stmt = getConnect().createStatement();
             if (!rs.next()) {
             	stmt.execute(createforme);
             	System.out.println("Table Groupeforme crée");
 	            rs.close();
 	            stmt.close();
             }	
    }
    
    public void relation() throws SQLException {
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
    
    public void dropTables() {
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
         try {
        	 statement.execute("drop table Groupeforme");
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
        	 statement.execute("drop table Carre");
         } catch (SQLException e) {
         }
         try {
        	 statement.execute("drop table Forme");
         } catch (SQLException e) {
         }
    }
	
}
