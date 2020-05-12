package pglp_9.dessin;

public class Carre extends Forme{
	
	Point point;
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public int getCote() {
		return cote;
	}

	public void setCote(int cote) {
		this.cote = cote;
	}

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
