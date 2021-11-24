package blackjack;


/**
 * Class holding all game state and actions
 * 
 * @author Lou Lebohec
 *
 */
public class GameState {
	private int dealerScore;
	private int playerScore;
	private int bankroll;
	private int currentBet;

	/**
	 * @param dealerScore
	 * @param playerScore
	 * @param bankroll
	 * @param currentBet
	 */
	public GameState() {
		this.dealerScore = 0;
		this.playerScore = 0;
		this.bankroll = 1000;
		this.currentBet = 0;
	}

	/**
	 * Performs the hit functionality: * give new card * calculate score for
	 * Blackjack/bust checking
	 * 
	 * @param team 0 for player, 1 for dealer
	 */
	public Card hit() {
		Card card = new Card();
		System.out.println(card);
		
		switch (card.getCardValue()) {
		case TWO:
			playerScore += 2;
			break;
		case THREE:
			playerScore += 3;
			break;
		case FOUR:
			playerScore += 4;
			break;
		case FIVE:
			playerScore += 5;
			break;
		case SIX:
			playerScore += 6;
			break;
		case SEVEN:
			playerScore += 7;
			break;
		case EIGHT:
			playerScore += 8;
			break;
		case NINE:
			playerScore += 9;
			break;
		case ACE:
			playerScore++;
			break;
		default:
			playerScore += 10;
			break;

		}
		
		if(playerScore==21) {
			winHand();
		}
		if(playerScore>21) {
			loseHand();
		}
		
		return card;
	}

	/**
	 * Performs the game actions that happen after a player has staid. * Dealer hits
	 * to 17 * Dealer flips face down card * Scores are calculated * win or lose are
	 * called appropriately
	 * 
	 * @param bet
	 */
	public static Card stay() {
		Card hiddenCard = new Card();
		
		
		return hiddenCard;
	}

	public static void modifyBet(int change) {

	}

	public static void submit() {

	}
	
	public static void winHand() {
		System.out.println("Player wins");
	}
	
	public static void loseHand() {
		System.out.println("Player loses");
	}

	/**
	 * @return the dealerScore
	 */
	public int getDealerScore() {
		return dealerScore;
	}

	/**
	 * @return the playerScore
	 */
	public int getPlayerScore() {
		return playerScore;
	}

	/**
	 * @return the bankroll
	 */
	public int getBankroll() {
		return bankroll;
	}

	/**
	 * @return the currentBet
	 */
	public int getCurrentBet() {
		return currentBet;
	}

	@Override
	public String toString() {
		return "GameState [dealerScore=" + dealerScore + ", playerScore=" + playerScore + ", bankroll=" + bankroll
				+ ", currentBet=" + currentBet + "]";
	}
	
	
}
