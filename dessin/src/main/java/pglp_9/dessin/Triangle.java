package pglp_9.dessin;

public class Triangle extends Forme{

	Point point_1,point_2,point_3;
    public Triangle(String nom,Point p1,Point p2,Point p3) {
    	super(nom);
    	point_1=p1.copie();
    	point_2=p2.copie();
    	point_3=p3.copie();
    }
	public void deplace(int x, int y) {
		this.point_1.deplace(x + point_1.getX(), y + point_1.getY());
		this.point_2.deplace(x + point_2.getX(), y + point_2.getY());
		this.point_3.deplace(x + point_3.getX(), y + point_3.getY());
	}

	public String affiche() {
		  return "Triangle : " + nom + ", Point 1 " + point_1.toString()+ ", Point 2 "+ point_2.toString()+ ", Point 3 "+ point_3.toString();
			
		
	}

}
