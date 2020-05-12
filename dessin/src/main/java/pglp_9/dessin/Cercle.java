package pglp_9.dessin;

public class Cercle extends Forme{
	
	int rayon;
	Point centre;
	
    public int getRayon() {
		return rayon;
	}
	public void setRayon(int rayon) {
		this.rayon = rayon;
	}
	public Point getCentre() {
		return centre;
	}
	public void setCentre(Point centre) {
		this.centre = centre;
	}
	public Cercle(String nom,Point p,int rayon){
    	super(nom);
    	this.centre=p.copie();
    	this.rayon=rayon;
    }
	public void deplace(int x, int y) {
		centre.deplace(x, y);
		
	}

	public void affiche() {
		System.out.println("Cercle : " + nom + ", rayon " + rayon+ ", centre "+ centre.toString());
		
	}
	

}
