package blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class holding all game state and actions
 * 
 * @author Lou Lebohec
 *
 */
public class GameState {
	private int dealerScore;
	private int playerScore;
	private double bankroll;
	private int currentBet;
	private String gameOutcome;
	final int MINIMUM_BET = 10;
	private File file = new File("./src/save/save.txt");

	/**
	 * @param dealerScore
	 * @param playerScore
	 * @param bankroll
	 * @param currentBet
	 */
	public GameState() {
		this.dealerScore = 0;
		this.playerScore = 0;
		this.currentBet = 0;
		
		try(Scanner reader = new Scanner(file);){
			this.bankroll = reader.nextDouble();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		
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
		
		if(bankroll<MINIMUM_BET) {
			throw new IllegalStateException("Player does not have the funds to play another hand");
		}
		else {
			currentBet = MINIMUM_BET;
			saveBankroll(MINIMUM_BET*-1);
		}
	}
	
	/**
	 * Deals first card for player and dealer, and modifies scores accordingly
	 * @return an ArrayList containing the player's first card at 0 and the dealer's first card at 1
	 */
	public ArrayList<Card> deal() {
        ArrayList<Card> initialCards = new ArrayList<>();
        
        Card dealerFirstCard = new Card();
        initialCards.add(dealerFirstCard);
        int dealerValue = parseCardValue(dealerFirstCard.getCardValue());
        if(dealerScore>10&&dealerValue==11) {
        	dealerScore++;
        }
        else dealerScore+=(parseCardValue(dealerFirstCard.getCardValue()));
        
        dealerFirstCard = new Card();
        initialCards.add(dealerFirstCard);
        dealerValue = parseCardValue(dealerFirstCard.getCardValue());
        if(dealerScore>10&&dealerValue==11) {
        	dealerScore++;
        }
        else dealerScore+=(parseCardValue(dealerFirstCard.getCardValue()));
        
        dealerScore+=(parseCardValue(dealerFirstCard.getCardValue()));
        
        initialCards.add(playerHit());
        initialCards.add(playerHit());
  
        return initialCards;
    }
	
	/**
	 * Performs the hit functionality: 
	 * * give new card 
	 * * calculate score for Blackjack/bust checking
	 * 
	 * @param team 0 for player, 1 for dealer
	 */
	public Card playerHit() {
		Card card = new Card();
		System.out.println(card);
		int cardValue = parseCardValue(card.getCardValue());
		if(playerScore>10&&cardValue==11) {
			playerScore++;
		}
		else playerScore += parseCardValue(card.getCardValue());
		return card;
	}

	/**
	 * Performs the game actions that happen after a player has staid. 
	 * * Dealer hits to 17 
	 * * Dealer flips face down card
	 */
	public Card stay() {
		Card newCard = new Card();
		dealerScore+=(parseCardValue(newCard.getCardValue()));
		return newCard;
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
		else if(currentBet+change < MINIMUM_BET) {
			throw new IllegalStateException("Cannot bet less than minimum");
		}
		else {
			saveBankroll(change*-1);
			currentBet+=change;
		}	
	}
	
	public void naturalBlackjack() {
		if(playerScore == dealerScore) {	
			gameOutcome ="Its A Tie";
		}else {
			saveBankroll(currentBet*-1.5);			
			gameOutcome ="You Win!";
		}
	}
	
	public void updateBalance() {
		
		//removed the current bet from the bankroll earlier so we need to resore its value
		bankroll += currentBet;
		
		if(playerScore > dealerScore && playerScore <= 21) {
			bankroll += currentBet;
			gameOutcome = "Player Wins";
		}else if(playerScore < dealerScore && dealerScore <= 21) {
			bankroll -= currentBet;
			gameOutcome = "Dealer Wins";
		}else if(playerScore > 21) {
			gameOutcome = "Bust! Dealer Wins";
			bankroll -= currentBet;
		}else if(dealerScore > 21) {
			gameOutcome = "Bust! Player Wins";
			bankroll += currentBet;
		}else
			gameOutcome = "It's a Tie";
	}

	/**
	 * @return the gameOutcome
	 */
	public String getGameOutcome() {
		return gameOutcome;
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
	
	public void saveBankroll(double change) {
		try(PrintWriter writer = new PrintWriter(file);){
			bankroll+=change;
			writer.println(bankroll);
			writer.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
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
	public double getBankroll() {
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
