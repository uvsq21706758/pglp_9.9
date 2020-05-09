package pglp_9.dessin;

public class Carre extends Forme{
	
	Point point;
	int cote;

	public Carre(String nom,Point p,int cote) {
		super(nom);
		point=p.copie();
		this.cote=cote;
	}

	public void deplace(int x, int y) {
		point.deplace(x, y);
		
	}

	public void affiche() {
		System.out.println( "Carre : " + nom + ", Point : " + point.toString()+ ", cote :"+ cote);
		
	}

}
