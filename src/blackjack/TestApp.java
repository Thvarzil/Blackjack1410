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
		
		while(testGame.getPlayerScore()<21) {
			testGame.hit();
		}
		

	}

}
