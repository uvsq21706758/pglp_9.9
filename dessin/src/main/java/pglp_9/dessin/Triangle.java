package pglp_9.dessin;

public class Triangle extends Forme{

	String nom;
	Point point_1,point_2,point_3;
    public Triangle(String nom,int x_1,int y_1,int x_2,int y_2,int x_3,int y_3) {
    	super(nom);
    	point_1=new Point(x_1, y_1);
    	point_2=new Point(x_2, y_2);
    	point_3=new Point(x_3, y_3);
    }
	public void deplace(int x, int y) {
	    point_1.deplace(x, y);
	    point_2.deplace(x, y);
	    point_3.deplace(x, y);
	}

	public String affiche() {
		  return "Triangle : " + nom + ", Point 1 " + point_1.toString()+ ", Point 2 "+ point_2.toString()+ ", Point 3 "+ point_3.toString();
			
		
	}

}