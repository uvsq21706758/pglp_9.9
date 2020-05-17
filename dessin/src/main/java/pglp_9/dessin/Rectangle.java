package pglp_9.dessin;

/**
 * Forme rectangle
 *
 */
public class Rectangle extends Forme{

	/**
	 * point du rectangle
	 */
	Point point;
	/**
	 * @return la position du point
	 */
	public Point getPoint() {
		return point.copie();
	}
	/**
	 * @return la largeur du rectangle
	 */
	public int getLargeur() {
		return largeur;
	}
	/**
	 * @return la longueur de rectangle
	 */
	public int getLongueur() {
		return longueur;
	}
	
	/**
	 * attribut largeur et longueur d'un rectangle
	 */
	int largeur,longueur;
	
    /**
     * constructeur du rectangle
     * @param nom nom attribué au rectangle créer
     * @param p position du rectangle
     * @param larg largeur
     * @param longr longueur
     */
    public Rectangle(String nom,Point p,int larg,int longr) {
    	super(nom);
    	point=p.copie();
    	this.largeur=larg;
    	this.longueur=longr;
    	
    }
	/**
	 *méthode pour deplacer le rectangle
	 */
	public void deplace(int x, int y) {
	    point.deplace(x, y);
	    
	}

	/**
	 *afficher un rectangle
	 */
	public void affiche() {
		System.out.println("Rectangle : " + nom + ", Point :" + point.toString()+", Longueur : "+longueur+", largeur :"+largeur);
			
		
	}

}
