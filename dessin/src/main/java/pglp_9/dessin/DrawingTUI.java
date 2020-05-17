package pglp_9.dessin;

import java.sql.SQLException;

/**
 * @author utilisateur
 *
 */
public class DrawingTUI {
  
	/**
	 * interpreter commands
	 * @param command commande à interpreter
	 * @return la commande executer ou null dans le cas contraire
	 * @throws SQLException 
	 */
	public Command nextCommand(String command) throws SQLException {
		if(command.contains("créer")) {
		
		}
		else if(command.contains("deplace")) {
			
		}
        else if(command.contains("supprime")) {
			
		}
		return null;
	}
	
	/**
	 * afficher les formes dessinés
	 */
	public void afficheDraw() {
		System.out.println("show");
	}
}
