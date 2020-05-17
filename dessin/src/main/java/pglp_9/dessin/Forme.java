package pglp_9.dessin;

/**
 * classe abstraite forme
 *
 */
public abstract class Forme {
	
	/**
	 * attribut nom
	 */
	String nom;
	
	/**
	 * @return le nom du forme
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Constructeur
	 * @param nom  nom du forme
	 */
	public Forme(String nom) {
		this.nom=nom;
	}
	
	/**
	 * deplacer forme
	 * @param x abssisse 
	 * @param y ordonne
	 */
	public abstract void deplace(int x,int y);
	
	/**
	 * affiche forme
	 */
	public abstract void affiche();

}
