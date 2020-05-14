package pglp_9.dessin;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;
/**
 * Unit test for simple App.
 */
public class AppTest 
   
{
		@Test
	public void test() {
		Carre c1 = new Carre ("Carre",new Point(5,3),3);
		c1.deplace( 2, 2);
		assertEquals(c1.getPoint().getX(),7);
		assertEquals(c1.getPoint().getY(),5);
		c1.deplace( 4, 5);
		assertEquals(c1.getPoint().getX(),11);
		assertEquals(c1.getPoint().getY(),10);
	}
}
