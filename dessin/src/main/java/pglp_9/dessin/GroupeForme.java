package pglp_9.dessin;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * classe groupe 
 * composite pour formes
 *
 */
public class GroupeForme extends Forme implements Iterable<Forme>{
	
	 /**
	 * la liste des formes
	 */
	private ArrayList<Forme> formes;

	/**
	 * constructeur
	 * @param nom nom donné au groupe
	 */
	public GroupeForme(String nom) {
		super(nom);
		 formes = new ArrayList<Forme>();
	}

	/**
	 *deplacer le groupe des formes
	 */
	@Override
	public void deplace(int x, int y) {
		 for (Forme forme : formes) {
	            forme.deplace(x, y);
	        }
		
	}

	/**
	 *afficher le groupe
	 */
	@Override
	public void affiche() {
		  System.out.println("Groupe :");
	        for (Forme forme : formes) {
	            forme.affiche();
	        }
	}
	/**
	 * ajout d'une forme dans le groupe
	 * @param forme forme rajouté
	 */
	public void ajoutForme(Forme forme) {
		this.formes.add(forme);
	}
	
	/**supprimer une forme dans le groupe
	 * @param forme forme a supprimer
	 */
	public void suppForme(final Forme forme) {
	        formes.remove(forme);
	    }
	
	/**
	 * @return la liste des formes
	 */
	public ArrayList<Forme> getList() {
        return this.formes;
    }
	
    /**
     * interface iterable
     * @return iterator elements
     */
	public Iterator<Forme> iterator() {
		 return formes.iterator();
	}
}
