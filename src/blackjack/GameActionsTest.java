package blackjack;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Testing class for game functionality
 * 
 * @author Lou LeBohec & Hayden Blackmer
 *
 */
class GameActionsTest {
	private GameState testState;
	
	@BeforeEach
	public void initEach() {
		testState = new GameState();
	}
}
