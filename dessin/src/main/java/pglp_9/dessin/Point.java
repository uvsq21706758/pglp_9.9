package pglp_9.dessin;

/**
 *classe point 
 *
 */
public class Point {
	 /**
	 * coordonnée du point
	 */
	public int x, y;

	    /**
	     * modifier l'abssisse x
	     * @param x
	     */
	    public void setX(int x) {
		this.x = x;
	}

	/**
	 * modfier l'ordonne y
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

		/**
	     * @return l'abssisse x 
	     */
	    public int getX() {
		return x;
	}

	/**
	 * @return l'ordonne y
	 */
	public int getY() {
		return y;
	}

		/**
		 * Constructeur du point
		 * @param x l'abssisse x du point
		 * @param y l'ordonne y du point
		 */
		public Point(int x, int y) {

	        this.x = x;

	        this.y = y;

	    }

	    /**
	     * deplacer le point
	     * @param dx valeur de décalage pour l'abssisse
	     * @param dy valeur de décalage pour l'ordonne
	     */
	    public void deplace(int dx, int dy) {

	        x += dx;

	        y += dy;

	    }

	    /**
	     *affiche cordonnées point
	     */
	    public String toString() {

	        return "Point de coordonnees : " + x + ", " + y;

	    }
	    /**
	     * @return une copie du point
	     */
	    public Point copie() {
	    	return new Point(x,y);
	    }
}
