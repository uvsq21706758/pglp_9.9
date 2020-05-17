package pglp_9.dessin;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * classe DrawingApp
 *
 */
public class DrawingApp 
{
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
	    public DrawingApp() {
	        dw = new DrawingTUI();
	        scanner = new Scanner(System.in);
	    }
	    
	    /**
	     * interaction avec DrawingTUI ,pour executer la prochaine commande
	     * @throws SQLException sql exception error
	     */
	    public void run() throws SQLException {
	        String saisie = " ";
	        Command command;
	        while (!saisie.equals("quit")) {
	        	command = dw.nextCommand(saisie);
	        	if(!(command==null)) {
	        	command.execute();
	        	}
	            dw.afficheDraw();
	            saisie = scanner.nextLine();
	        }
	    }
	    /**
	     * methode main
	     * @param args arguments
	     * @throws Exception sql error exception
	     */
	    public static void main(final String[] args) throws Exception {
	        
	            DrawingApp d = new DrawingApp();
	            d.run();
	           
	    }
}
