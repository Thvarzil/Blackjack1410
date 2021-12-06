package blackjack;

public enum CardValue {
	TWO,
	THREE,
	FOUR,
	FIVE,
	SIX,
	SEVEN,
	EIGHT,
	NINE,
	TEN,
	JACK,
	QUEEN,
	KING,
	ACE;
	
	@Override
    public String toString() {
        return name().toLowerCase();
    }
}

