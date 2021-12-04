package blackjack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Represents a card
 * 
 * @author Lou LeBohec & Hayden Blackmer
 *
 */
public class Card {
	private Suits suit;
	private CardValue cardValue;
	private static final Random RANDOM = new Random();
	private static final List<CardValue> CardValues = Collections.unmodifiableList(Arrays.asList(CardValue.values()));
	private static final List<Suits> SuitList = Collections.unmodifiableList(Arrays.asList(Suits.values()));
	
	/**
	 * @param deck
	 */
	public Card(CardValue cardValue, Suits suit) {
		this.suit = suit;
		this.cardValue = cardValue;
	}
	
	
	/**
	 * Creates a random card 
	 */
	public Card() {
		this.suit = SuitList.get(RANDOM.nextInt(4));
		this.cardValue = CardValues.get(RANDOM.nextInt(13));
	}
	
	

	/**
	 * @return the suit
	 */
	public Suits getSuit() {
		return suit;
	}



	/**
	 * @return the cardValue
	 */
	public CardValue getCardValue() {
		return cardValue;
	}



	@Override
	public String toString() {
		return cardValue + "_of_" + suit.toString();
	}


}
