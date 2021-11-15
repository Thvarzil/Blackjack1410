package blackjack;
public class Card {
	private Suits suit;
	private CardValue cardValue;
	
	/**
	 * @param deck
	 */
	public Card(CardValue cardValue, Suits suit) {
		this.suit = suit;
		this.cardValue = cardValue;
	}

	@Override
	public String toString() {
		return cardValue + "_of_" + suit.toString();
	}
}
