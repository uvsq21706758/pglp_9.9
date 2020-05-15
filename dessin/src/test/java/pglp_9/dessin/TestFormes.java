package pglp_9.dessin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class TestFormes 
   
{
	    @Test
	    public void testConstructeurPoint() {
	        Point point= new Point(3,5);
	        assertTrue(point.getX() == 3 && point.getY() ==5);
	    }
	    
	    @Test
	    public void testDeplacePoint() {
		    Point point= new Point(6,8);
		    point.deplace(2, 3);
	        assertTrue(point.getX() == 8 && point.getY() == 11);
	    }
	    
	    @Test
	    public void testToStringPoint() {
	    	Point point= new Point(7,1);
	    	point.deplace(1, 1);
	        assertTrue(point.toString().equals("Point de coordonnees : " + 8 + ", " + 2));
	    }
	    
	    @Test
	    public void testCopiePoint() {
	    	Point point = new Point(10,20);
	    	Point point1 = point.copie();
	        assertTrue(point1.getX() == point.getX() && point1.getY() == point.getY() && point != point1);
	    }
	    
	    @Test
	    public void testConstructeurCercle() {
	        Cercle cercle = new Cercle("Cercle", new Point(3,4), 2);
	        assertEquals(cercle.getNom(), "Cercle");
	        assertTrue(cercle.getCentre().toString().equals("Point de coordonnees : " + 3 + ", " + 4));
	        assertTrue(cercle.getRayon() == 2);
	    }
	    
	    @Test
	    public void testDeplaceCercle() {
	        Cercle cercle = new Cercle("Cercle", new Point(3,4), 2);
	        cercle.deplace(9, 11);
	        assertTrue(cercle.getCentre().toString().equals("Point de coordonnees : " + 12 + ", " + 15));
	    }
	    
	    @Test
	    public void testAfficheCercle() {
	        Cercle cercle = new Cercle("Cercle", new Point(3,4), 2);
	        cercle.affiche();
	    }
	    
	    @Test
	    public void testConstructeurRectangle() {
	        Rectangle rectangle = new Rectangle("Rectangle", new Point(1,1), 3, 2);
	        assertEquals(rectangle.getNom(), "Rectangle");
	        assertTrue(rectangle.getPoint().toString().equals("Point de coordonnees : " + 1+ ", " + 1));
	        assertTrue(rectangle.getLongueur() == 2);
	        assertTrue(rectangle.getLargeur() == 3);
	    }
	    
	    @Test
	    public void testAfficheRectangle() {
	        Rectangle rectangle = new Rectangle("Rectangle", new Point(1,1), 3, 2);
	        rectangle.affiche();
	    }
	    
	    @Test
	    public void testDeplaceRectangle(){
	        Rectangle rectangle = new Rectangle("Rectangle", new Point(1,1), 3, 2);
	        rectangle.deplace(11, 3);
	        assertTrue(rectangle.getPoint().toString().equals("Point de coordonnees : " + 12 + ", " + 4));
	    }
	    
	    @Test
	    public void testConstructeurTriangle() {
	        Triangle triangle = new Triangle("Triangle", new Point(1,1), new Point(1,2), new Point(1,3));
	        assertEquals(triangle.getNom(), "Triangle");
	        assertTrue(triangle.getPoint_1().toString().equals("Point de coordonnees : " + 1 + ", " + 1));
	        assertTrue(triangle.getPoint_2().toString().equals("Point de coordonnees : " + 1 + ", " + 2));
	        assertTrue(triangle.getPoint_3().toString().equals("Point de coordonnees : " + 1 + ", " + 3));
	    }
	    
	    @Test
	    public void testAfficheTriangle() {
	        Triangle triangle= new Triangle("Triangle", new Point(1,1), new Point(1,2), new Point(1,3));
	        triangle.affiche();
	    }
	    
	    @Test
	    public void testDeplaceTriangle() {
	        Triangle triangle=new Triangle("Triangle", new Point(1,1), new Point(1,2), new Point(1,3));
	        triangle.deplace(3, 4);
	        
	        assertEquals(triangle.getPoint_1().getX(),4);
			assertEquals(triangle.getPoint_1().getY(),5);
			
			assertEquals(triangle.getPoint_2().getX(),4);
			assertEquals(triangle.getPoint_2().getY(),6);
			
			assertEquals(triangle.getPoint_3().getX(),4);
			assertEquals(triangle.getPoint_3().getY(),7);
	    }
	    
	    @Test
	    public void testConstructeurCarre() {
	        Carre carre = new Carre("carre", new Point(3,2), 2);
	        assertEquals(carre.getNom(), "carre");
	        assertEquals(carre.getPoint().toString(),"Point de coordonnees : " + 3 + ", " + 2);
	          assertTrue(carre.getCote() == 2);
	    }
	 
		@Test
	    public void testDepaceCarre() {
		Carre carre1 = new Carre ("Carre",new Point(5,3),3);
		carre1.deplace( 2, 2);
		assertEquals(carre1.getPoint().getX(),7);
		assertEquals(carre1.getPoint().getY(),5);
		carre1.deplace( 4, 5);
		assertEquals(carre1.getPoint().getX(),11);
		assertEquals(carre1.getPoint().getY(),10);
	    }
		
		@Test
	    public void testAfficheCarre() {
			Carre carre2 = new Carre("Carre", new Point(3,4), 2);
			carre2.affiche();
	    }
		
		@Test
		public void testConstructeurGroupe() {
		   GroupeForme groupe = new GroupeForme("Groupe");
		   assertTrue(groupe.getList().isEmpty() && groupe.getNom().equals("Groupe"));
		}
		
		@Test
	    public void testAjoutGroupe() {
	        GroupeForme groupe = new GroupeForme("groupe");
	        Carre carre = new Carre("Carre",  new Point(5,3),2);
	        groupe.ajoutForme(carre);
	        assertTrue(groupe.getList().size() == 1 && groupe.getList().get(0) == carre);
	    }
		
	    @Test
	    public void testSuppGroupe(){
	        GroupeForme groupe = new GroupeForme("Groupe");
	        Carre carre = new Carre("Carre",  new Point(5,3),2);
	        groupe.ajoutForme(carre);
	        groupe.suppForme(carre);
	        assertTrue(groupe.getList().isEmpty());
	    }
	    
	    @Test
	    public void testIterateGroupe() {
	        GroupeForme groupe = new GroupeForme("Groupe");
	        Carre carre = new Carre("Carre",  new Point(5,3),2);
	        groupe.ajoutForme(carre);
	        Iterator<Forme> itf = groupe.iteratorGroupe();
	        assertTrue(itf.hasNext() && itf.next() == carre && itf.hasNext() == false);
	    }
	    
	    @Test
	    public void testAfficheGroupe(){
	        GroupeForme groupe = new GroupeForme("Groupe");
	        Carre carre = new Carre("Carre",  new Point(5,3),2);
	        groupe.ajoutForme(carre);
	        groupe.affiche();
	    }
	    
	    @Test
	    public void testDeplaceGroupe(){
	        GroupeForme groupe = new GroupeForme("Groupe");
	        Carre carre = new Carre("Carre",  new Point(5,3),2);
	        groupe.ajoutForme(carre);
	        groupe.deplace(1,1);
	        assertTrue(carre.getPoint().toString().equals("Point de coordonnees : " +6+ ", " + 4));
	    }

}
