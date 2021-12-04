package blackjack;

/**
 * Test class for game functionality
 * 
 * @author Hayden Blackmer & Lou LeBohec
 *
 */
public class TestApp {

	public static void main(String[] args) {
		GameState testGame = new GameState();
		
		int test1 = 5;
		int test2 = test1;
		
		System.out.println(test1+" - "+test2);
		test1 = 15;
		System.out.println(test1+" - "+test2);
		

	}

}
