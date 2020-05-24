package pglp_9.dessin;

import java.io.CharConversionException;

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

	    public Point(String p)throws CharConversionException {
			 p.replace(" ", "");
	        if (p.charAt(0) != '('
	                || p.charAt(p.length() - 1) != ')') {
	                    System.err.println(p);
	                    throw new CharConversionException();
	                }
	                String position2 = p.substring(1, p.length() - 1);
	                String[] positionSplit = position2.split(",");
	                if (positionSplit.length != 2) {
	                    System.err.println(p);
	                    throw new CharConversionException();
	                }
	                try {
	                    x = Integer.parseInt(positionSplit[0]);
	                    y = Integer.parseInt(positionSplit[1]);
	                } catch (NumberFormatException e) {
	                    System.err.println("Caractère inconnu "
	                            + "lors de la conversion des nombres.");
	                    throw e;
	                }
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
	    	return "(" + x + "," + y + ")";

	    }
	    /**
	     * @return une copie du point
	     */
	    public Point copie() {
	    	return new Point(x,y);
	    }
}
