package blackjack;

public class TestApp {

	public static void main(String[] args) {
		GameState testGame = new GameState();
		
		while(testGame.getPlayerScore()<21) {
			testGame.hit();
		}
		

	}

}
