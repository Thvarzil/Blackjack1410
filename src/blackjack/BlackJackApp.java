package blackjack;

public class BlackJackApp {
	
	public static void main(String[] args) {
		//create deck an
		Deck deck = new Deck();
		deck.createAndShuffel();
		
		System.out.println(deck.getDeck());
	}
}	
