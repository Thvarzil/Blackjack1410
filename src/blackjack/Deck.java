package blackjack;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {
	
	private ArrayList<Card> deck = new ArrayList<Card>();
	
	/*
	 * Creates and shuffles the deck
	 */
	public void createAndShuffel() {
		//create the deck
		for(Suits suit: Suits.values()) {
			for(CardValue cardValue : CardValue.values()) {
				Collections.addAll(deck, new Card(cardValue, suit));
			}
		}
		
		//Shuffle the deck
		Collections.shuffle(deck);
	}

	/**
	 * @return the deck
	 */
	public ArrayList<Card> getDeck() {
		return deck;
	}

}
