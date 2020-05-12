package pglp_9.dessin;

public class Triangle extends Forme{

	Point point_1,point_2,point_3;
    public Point getPoint_1() {
		return point_1;
	}
	public void setPoint_1(Point point_1) {
		this.point_1 = point_1;
	}
	public Point getPoint_2() {
		return point_2;
	}
	public void setPoint_2(Point point_2) {
		this.point_2 = point_2;
	}
	public Point getPoint_3() {
		return point_3;
	}
	public void setPoint_3(Point point_3) {
		this.point_3 = point_3;
	}
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

	public void affiche() {
		System.out.println("Triangle : " + nom + ", Point 1 " + point_1.toString()+ ", Point 2 "+ point_2.toString()+ ", Point 3 "+ point_3.toString());
			
		
	}

}
