package pglp_9.dessin;

/**
 *La forme cercle
 *
 */
public class Cercle extends Forme{
	
	/**
	 * attribut rayon
	 */
	int rayon;
	
	/**
	 * attribut centre
	 */
	Point centre;
	
    /**
     * @return le rayon du cercle
     */
    public int getRayon() {
		return rayon;
	}
	
	/**
	 * @return le centre du cercle
	 */
	public Point getCentre() {
		return centre;
	}

	/**
	 * Constructeur du cercle
	 * @param nom nom donné au cercle créer
	 * @param p position du centre du cerlce
	 * @param rayon rayon du cercle
	 */
	public Cercle(String nom,Point p,int rayon){
    	super(nom);
    	this.centre=p.copie();
    	this.rayon=rayon;
    }
	/**
	 *méthode pour deplacer le cercle
	 */
	public void deplace(int x, int y) {
		centre.deplace(x, y);
		
	}

	/**
	 *afficher le cercle
	 */
	public void affiche() {
		System.out.println("Cercle : " + nom + ", rayon " + rayon+ ", centre "+ centre.toString());
		
	}
	

}
