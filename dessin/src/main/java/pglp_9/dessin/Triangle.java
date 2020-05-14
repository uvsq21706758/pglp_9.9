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
		this.point_1.setX(this.point_1.getX() + x);
		this.point_1.setY(this.point_1.getY() + y);
		this.point_2.setX(this.point_2.getX() + x);
		this.point_2.setY(this.point_2.getY() + y);
		this.point_3.setX(this.point_3.getX() + x);
		this.point_3.setY(this.point_3.getY() + y);
	}

	public void affiche() {
		System.out.println("Triangle : " + nom + ", Point 1 " + point_1.toString()+ ", Point 2 "+ point_2.toString()+ ", Point 3 "+ point_3.toString());
			
		
	}

}
