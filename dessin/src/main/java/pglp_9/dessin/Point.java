package pglp_9.dessin;

public class Point {
	 public int x, y;

	    public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

		public Point(int x, int y) {

	        this.x = x;

	        this.y = y;

	    }

	    public void deplace(int dx, int dy) {

	        x += dx;

	        y += dy;

	    }

	    public String toString() {

	        return "Point de coordonnees : " + x + ", " + y;

	    }
	    public Point copie() {
	    	return new Point(x,y);
	    }
}
