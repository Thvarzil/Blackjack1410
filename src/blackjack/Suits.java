package blackjack;

public enum Suits {
	CLUBS,
	DIAMONDS,
	HEARTS,
	SPADES;
	
	@Override
    public String toString() {
        return name().toLowerCase();
    }
}
