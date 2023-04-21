import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author wolfu
 *
 */
class AVLTest {

	@Test
	void AddandGettest() {
		AVLTree<Integer,String> Arbol = new AVLTree();
		Arbol.add(1, "A");
		Arbol.add(2, "B");
		Arbol.add(3, "C");
		Arbol.add(4, "D");
		Arbol.add(5, "E");
		
		assertEquals("C", Arbol.get(3));
		assertEquals("D", Arbol.get(4));
	}

}
