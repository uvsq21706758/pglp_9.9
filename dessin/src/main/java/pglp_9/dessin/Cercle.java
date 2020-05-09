package pglp_9.dessin;

public class Cercle extends Forme{
	
	int rayon;
	Point centre;
	
    public Cercle(String nom,Point p,int rayon){
    	super(nom);
    	this.centre=p.copie();
    	this.rayon=rayon;
    }
	public void deplace(int x, int y) {
		centre.deplace(x, y);
		
	}

	public String affiche() {
		  return "Cercle : " + nom + ", rayon " + rayon+ ", centre "+ centre.toString();
		
	}
	

}
