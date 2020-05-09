package pglp_9.dessin;

public class Cercle extends Forme{
	
	int rayon;
	Point centre;
	
    public Cercle(String nom,int x,int y,int rayon){
    	super(nom);
    	this.centre=new Point(x,y);
    	this.rayon=rayon;
    }
	public void deplace(int x, int y) {
		centre.deplace(x, y);
		
	}

	public String affiche() {
		  return "Cercle : " + nom + ", rayon " + rayon+ ", centre "+ centre.toString();
		
	}
	

}
