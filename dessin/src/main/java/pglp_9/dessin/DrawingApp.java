package pglp_9.dessin;

import java.io.File;
import java.sql.SQLException;
import java.util.Scanner;


/**
 * classe DrawingApp
 *
 */
public class DrawingApp {
	
	 /**
     * attribut scanner
     */
    Scanner scanner;
   
    /**
     * attribut dw 
     */
    DrawingTUI dw;
	/**
	 * constructeur 
	 */
	public DrawingApp(){
		dw = new DrawingTUI();
        scanner = new Scanner(System.in);
	}
	/**
	 * execution des commandes
	 * @throws SQLException 
	 */
	  public void run() throws SQLException {
	        System.out.print("Taper action pour afficher la liste des actions et quit pour quitter\n>");
	        String cmd = scanner.nextLine();
	        Command c;
	        while (!cmd.equals("quit")) {
	            c = dw.nextCommand(cmd);
	            if (c != null) {
	                c.execute();
	            }
	            dw.afficheDraw();
	            cmd = scanner.nextLine();
	           
	        }
	    }


	    /**
	     * méthode main 
	     * @param args arguments
	     * @throws Exception sql error exception
	     */
	    public static void main(final String[] args) throws Exception {
	        FormeandRelation.creationbd();
	        FormeandRelation.recuperetables();
	        DrawingApp app = new DrawingApp();
	        app.run();
	    }
	}