package pglp_9.dessin;

/**
 * La Forme Carré 
 *
 */
public class Carre extends Forme{
	
	/**
	 * attribut point du carré
	 */
	
	Point point;
	
	/**
	 * Méthode getPoint
	 * @return la position du point
	 */
	public Point getPoint() {
		return point.copie();
	}
	/**
	 * @return taille du cote
	 */
	public int getCote() {
		return cote;
	}
	/**
	 * taille du cote
	 */
	int cote;

	/**
	 * Constructeur du carré
	 * @param nom nom donné au carré créer
	 * @param p position du point du carré
	 * @param cote 
	 */
	public Carre(String nom,Point p,int cote) {
		super(nom);
		point=p.copie();
		this.cote=cote;
	}

	/**
	 *deplacer un carré
	 */
	public void deplace(int x, int y) {
		point.deplace(x, y);
		
	}

	/**
	 *méthode pour afficher un carré
	 */
	public void affiche() {
		System.out.println( "Carre : " + nom + ", Point : " + point.toString()+ ", cote :"+ cote);
		
	}

}
