package blackjack;

import java.util.ArrayList;

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
	private boolean stayed;
	final int MINIMUM_BET = 15;

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
		this.stayed=false;
	}
	
	/**
	 * Sets up the game state for a new hand
	 * @throws IllegalStateException if bankroll is smaller than minimum bet
	 * 
	 */
	public void startHand() {
		playerScore = 0;
		dealerScore = 0;
		currentBet = 0;
		stayed = false;
		
		if(bankroll<MINIMUM_BET) {
			throw new IllegalStateException("Player does not have the funds to play another hand");
		}
		else {
			currentBet = MINIMUM_BET;
			bankroll-=MINIMUM_BET;
		}
	}
	
	/**
	 * Deals first card for player and dealer, and modifies scores accordingly
	 * @return an ArrayList containing the player's first card at 0 and the dealer's first card at 1
	 */
	public ArrayList<Card> deal() {
		ArrayList<Card> initialCards = new ArrayList<>();
		Card dealerFirstCard = new Card();
		
		dealerScore+=(parseCardValue(dealerFirstCard.getCardValue()));
		initialCards.add(hit());
		initialCards.add(dealerFirstCard);
		
		return initialCards;
	}
	
	/**
	 * Performs the hit functionality: 
	 * * give new card 
	 * * calculate score for Blackjack/bust checking
	 * 
	 * @param team 0 for player, 1 for dealer
	 */
	public Card hit() {
		Card card = new Card();
		System.out.println(card);
		
		playerScore += parseCardValue(card.getCardValue());
		return card;
	}

	/**
	 * Performs the game actions that happen after a player has staid. 
	 * * Dealer hits to 17 
	 * * Dealer flips face down card
	 */
	public ArrayList<Card> stay() {
		stayed = true;
		ArrayList<Card> dealerCards = new ArrayList<>();
		
		while(dealerScore<17) {
			Card newCard = new Card();
			dealerScore+=(parseCardValue(newCard.getCardValue()));
			dealerCards.add(newCard);
		}
		
		Card hiddenCard = new Card();
		dealerScore+=(parseCardValue(hiddenCard.getCardValue()));
		dealerCards.add(hiddenCard);
		return dealerCards;
	}

	/**
	 * Modifies the current bet
	 * @param change
	 * @throws IllegalArgumentException if change is larger than bankroll
	 */
	public void modifyBet(int change) {
		if(change>bankroll) {
			throw new IllegalArgumentException("bet cannot be larger than bankroll");
		}
		else if(currentBet+change<MINIMUM_BET) {
			throw new IllegalStateException("Cannot bet less than minimum");
		}
		else {
			bankroll-=change;
			currentBet+=change;
		}
		
	}
	
	/**
	 * Evaluates if player has won or lost, changes bankroll appropriately
	 * @return 0 if player has won, 1 if player has lost, 2 if drawn, 3 if hand is unfinished
	 */
	public int evaluateHand() {
		//Player bust
		if(playerScore>21) {
			return 1;
		}
		//Other states only checked after stay
		else if (stayed) {
			//Dealer bust
			if(dealerScore>21) {
				bankroll+=(currentBet*1.5);
				return 0;
			}
			//Dealer beats player
			else if(dealerScore>playerScore) {
				return 1;
			}
			//Player beats dealer
			else if(dealerScore<playerScore) {
				bankroll+=(currentBet*1.5);
				return 0;
			}
			//Draw
			else {
				bankroll+=currentBet;
				return 2;
			}
		}
		//Game is unfinished
		else return 3;
	}
	
	/**
	 * Translates a CardValue into the integer value of the card
	 * @param the CardValue to be parsed
	 * @return the integer value
	 */
	private int parseCardValue(CardValue value) {
		switch (value) {
		case TWO:
			return 2;
		case THREE:
			return 3;
		case FOUR:
			return 4;
		case FIVE:
			return 5;
		case SIX:
			return 6;
		case SEVEN:
			return 7;
		case EIGHT:
			return 8;
		case NINE:
			return 9;
		case ACE:
			return 11;
		default:
			return 10;
		}

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
