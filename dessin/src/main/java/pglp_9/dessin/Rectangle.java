package pglp_9.dessin;

public class Rectangle extends Forme{

	Point point;
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public int getLargeur() {
		return largeur;
	}
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	public int getLongueur() {
		return longueur;
	}
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	int largeur,longueur;
    public Rectangle(String nom,Point p,int larg,int longr) {
    	super(nom);
    	point=p.copie();
    	this.largeur=larg;
    	this.longueur=longr;
    	
    }
	public void deplace(int x, int y) {
	    point.deplace(x, y);
	    
	}

	public void affiche() {
		System.out.println("Rectangle : " + nom + ", Point :" + point.toString()+", Longueur : "+longueur+", largeur :"+largeur);
			
		
	}

}
