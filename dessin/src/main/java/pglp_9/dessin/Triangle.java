package pglp_9.dessin;

/**
 * forme triangle
 *
 */
public class Triangle extends Forme{

	/**
	 * attributs points
	 */
	Point point_1,point_2,point_3;
	
    /**
     * @return point_1
     */
    public Point getPoint_1() {
		return point_1;
	}
	
	/**
	 * @return point_2
	 */
	public Point getPoint_2() {
		return point_2;
	}
	
	/**
	 * @return point_3
	 */
	public Point getPoint_3() {
		return point_3;
	}
	
	/**
	 * Constructeur du triangle
	 * @param nom nom donné au triangle
	 * @param p1 p1 du triangle
	 * @param p2 p2 du triangle
	 * @param p3 p3 du triangle
	 */
	public Triangle(String nom,Point p1,Point p2,Point p3) {
    	super(nom);
    	point_1=p1.copie();
    	point_2=p2.copie();
    	point_3=p3.copie();
    }
	/**
	 *methode deplacer un triangle
	 */
	public void deplace(int x, int y) {
		this.point_1.setX(this.point_1.getX() + x);
		this.point_1.setY(this.point_1.getY() + y);
		this.point_2.setX(this.point_2.getX() + x);
		this.point_2.setY(this.point_2.getY() + y);
		this.point_3.setX(this.point_3.getX() + x);
		this.point_3.setY(this.point_3.getY() + y);
	}

	/**
	 *afficher triangle
	 */
	public void affiche() {
		System.out.println("Triangle : " + nom + ", Point 1 " + point_1.toString()+ ", Point 2 "+ point_2.toString()+ ", Point 3 "+ point_3.toString());
			
		
	}

}
