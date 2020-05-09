package pglp_9.dessin;

public class Rectangle extends Forme{

	Point point;
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
