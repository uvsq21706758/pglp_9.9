package pglp_9.dessin;

public class Point {
	 public int x, y;

	    public Point(int x, int y) {

	        this.x = x;

	        this.y = y;

	    }

	    public void deplace(int dx, int dy) {

	        x += dx;

	        y += dy;

	    }

	    public String affiche() {

	        return "Point de coordonnees : " + x + ", " + y;

	    }
}
